package com.sda.proiect.services;

import com.sda.proiect.database.dao.InvoiceDao;
import com.sda.proiect.models.Invoice;
import com.sda.proiect.models.InvoiceInput;
import com.sda.proiect.utils.mappers.InputToDatabaseObject;
import org.hibernate.SessionFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class OrchestratorService {

    private final FileParserService fileParserService;
    private final InvoiceDao dao;

    public OrchestratorService(SessionFactory session) {
        this.fileParserService = new FileParserService();
        this.dao = new InvoiceDao(session);
    }

    public void runApplication() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        printMenu();

        while (isRunning) {
            int option = scanner.nextInt();
            scanner.nextLine(); // Known issue on scanner - https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo

            switch (option) {
                case 0:
                    System.out.println("Thank you for using the application!");
                    isRunning = false;
                    break;
                case 1:
                    System.out.print("Please input the path to file: ");
                    String path = scanner.nextLine();
                    writeContentToDatabase(path);
                    printMenu();
                    break;
                case 2:
                    printAllInvoices();
                    printMenu();
                    break;
                case 3:
                    printTodaysEntries();
                    printMenu();
                    break;
                case 4:
                    System.out.println("To Be Designed");
                    break;
                default:
                    printMenu();
            }
        }
    }

    private void printTodaysEntries() {
        List<Invoice> invoices = dao.getInvoices();

        // extragem data de azi, din care pastram doar data calendaristic in format YYYY-MM-DD
        String today = extractToday();

        // filtram lista pe baza datei
//        List<Invoice> collect = invoices.stream()
//                .filter(invoice -> invoice.getCreated() != null)
//                .filter(invoice -> today.equals(invoice.getCreated().toString()))
//                .collect(Collectors.toList());

        Predicate<Invoice> isNotNullDate = invoice -> invoice.getCreated() != null;
        Predicate<Invoice> isCreatedToday = invoice -> today.equals(invoice.getCreated().toString());
        List<Invoice> collect = invoices.stream()
                .filter(isNotNullDate)
                .filter(isCreatedToday)
                .collect(Collectors.toList());

        printInvoices(collect);
    }

    private String extractToday() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        return formatter.format(new Date());
    }

    private void printAllInvoices() {
        List<Invoice> invoices = dao.getInvoices();

        printInvoices(invoices);
    }

    private void printInvoices(List<Invoice> invoices) {
        System.out.println("You have retrieved " + invoices.size() + " entries:");
        for (int i = 0; i < invoices.size(); i++) {
            // TODO - aliniati coloanele prin adaugare la primul parametru a unui padding
            // se poate cauta "String.format pad element"
            String line = String.format("%s. %s", (i + 1), invoices.get(i));
            System.out.println(line);
        }

//        for (Invoice temp : invoices) {
//            System.out.println(temp);
//        }

        System.out.println("\n");
    }

    private void writeContentToDatabase(String path) {
        List<InvoiceInput> invoiceInputs = fileParserService.readUserFile(path);
        System.out.println("Successfully read " + invoiceInputs.size() + " items from path: " + path);

        for (InvoiceInput inputElement : invoiceInputs) {
            // map element from InvoiceInput type to Invoice type
            // so we can save to database
            Invoice mappedInput = InputToDatabaseObject.map(inputElement);
            boolean isSaved = dao.createInvoice(mappedInput);
            if (!isSaved) {
                System.out.println("Could not save to database the following item: " + mappedInput.toString());
            } else {
                System.out.println("Successfully saved to database the following item: " + mappedInput.toString());
            }
        }
    }

    private void printMenu() {
        System.out.println("Please choose an option:");
        System.out.println("0 - Exit");
        System.out.println("1 - Input file");
        System.out.println("2 - Get all entries");
        System.out.println("3 - Get today's entries");
        // TODO - tema
        System.out.println("4 - Display total income for today");
        System.out.println("5 - Show menu");
        System.out.println("\n");
    }
}

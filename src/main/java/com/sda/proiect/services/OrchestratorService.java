package com.sda.proiect.services;

import com.sda.proiect.models.InvoiceInput;

import java.util.List;
import java.util.Scanner;

public class OrchestratorService {

    private final FileParserService fileParserService;

    public OrchestratorService() {
        this.fileParserService = new FileParserService();
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
                    break;
                case 2:
                    System.out.println("To Be Designed");
                    break;
                case 3:
                    System.out.println("To Be Designed");
                    break;
                case 4:
                    System.out.println("To Be Designed");
                    break;
                default:
                    printMenu();
            }
        }
    }

    private void writeContentToDatabase(String path) {
        List<InvoiceInput> invoiceInputs = fileParserService.readUserFile(path);

        System.out.println("Successfully read " + invoiceInputs.size() + " items from path: " + path);
        System.out.println("PLACEHOLDER: Writing to db...");
    }

    private void printMenu() {
        System.out.println("Please choose an option:");
        System.out.println("0 - Exit");
        System.out.println("1 - Input file");
        System.out.println("2 - Get all entries");
        System.out.println("3 - Get today's entries");
        System.out.println("4 - Generate Z report");
        System.out.println("5 - Show menu");
        System.out.println("\n");
    }
}

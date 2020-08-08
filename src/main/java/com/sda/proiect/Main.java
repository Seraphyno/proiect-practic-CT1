package com.sda.proiect;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        printMenu();
        runApplication();
    }

    private static void runApplication() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

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
                    validateJsonFileType(path);
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

    private static void validateJsonFileType(String path) {
        if (path == null) {
            System.out.println("Path is missing, please choose option");
        } else if (!path.endsWith(".json")) {
            System.out.println("The file is not JSON type!");
        } else {
            System.out.println("You wrote: " + path);
        }
    }

    private static void printMenu() {
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

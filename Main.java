package DSAFinalProject;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Employee employee = new Employee();

        System.out.println("Welcome to the Video Rental System!");

        while (true) {
            System.out.println("\nEnter (1) if you are a Customer, (2) if you are an Employee, or ANYTHING else to exit:");
            String choice = scanner.nextLine();

            if (choice.equals("1")) customerMenu(scanner, employee);
            else if (choice.equals("2")) employeeMenu(scanner, employee);
            else {
                System.out.println("Exiting the system. Goodbye!");
                break;
            }
        }
        scanner.close();
    }

    private static void customerMenu(Scanner scanner, Employee employee) {
        while (true) {
            System.out.println("\nCustomer Menu:");
            System.out.println("1. Rent a Video");
            System.out.println("2. Return a Video");
            System.out.println("3. Exit");

            String choice = scanner.nextLine();
            if (choice.equals("3")) break;

            System.out.print("Enter your phone number: ");
            String phoneNumber = scanner.nextLine();

            if (employee.phoneNumberValidator(phoneNumber)) {
                Customer customer = employee.getCustomer(phoneNumber);
                if (choice.equals("1")) {
                    System.out.print("Enter video barcode: ");
                    String barcode = scanner.nextLine();
                    String title = employee.getVideoTitle(barcode);
                    if (title != null) customer.rent(new Video(title, barcode));
                    else System.out.println("Video does not exist.");
                } else if (choice.equals("2")) {
                    System.out.print("Enter video barcode: ");
                    String barcode = scanner.nextLine();
                    customer.returnMovie(new Video("Temp", barcode));
                }
            } else {
                System.out.println("Invalid phone number.");
            }
        }
    }

    private static void employeeMenu(Scanner scanner, Employee employee) {
        while (true) {
            System.out.println("\nEmployee Menu:");
            System.out.println("1. Add a Customer");
            System.out.println("2. Add a Video");
            System.out.println("3. Search for a Movie");
            System.out.println("4. Exit");

            String choice = scanner.nextLine();
            if (choice.equals("4")) break;

            if (choice.equals("1")) {
                System.out.print("Enter phone number: ");
                String phoneNumber = scanner.nextLine();
                System.out.print("Enter first name: ");
                String firstName = scanner.nextLine();
                System.out.print("Enter last name: ");
                String lastName = scanner.nextLine();
                employee.addCustomer(phoneNumber, firstName, lastName);
            } else if (choice.equals("2")) {
                System.out.print("Enter video title: ");
                String title = scanner.nextLine();
                System.out.print("Enter barcode: ");
                String barcode = scanner.nextLine();
                employee.addVideo(new Video(title, barcode));
            } else if (choice.equals("3")) {
                System.out.print("Enter movie title to search: ");
                String title = scanner.nextLine();
                employee.searchForRenter(title);
            }
        }
    }
}

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

            if (choice.equals("1")) {
                customerMenu(scanner, employee);
            } else if (choice.equals("2")) {
                employeeMenu(scanner, employee);
            } else {
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
            System.out.println("3. Check movies owned");
            System.out.println("4. View Available Videos");
            System.out.println("5. Exit");


            String choice = scanner.nextLine();
            if (choice.equals("5")) break;

            System.out.print("Enter your phone number: ");
            String phoneNumber = scanner.nextLine();

            if (employee.phoneNumberValidator(phoneNumber)) {
                Customer customer = employee.getCustomer(phoneNumber);
                if (choice.equals("1")) {
                    System.out.print("Enter video barcode: ");
                    String barcode = scanner.nextLine();
                    String title = employee.getVideoTitle(barcode);
                    if (title != null) {
                        customer.rent(new Video(title, barcode));
                    } else {
                        System.out.println("Video does not exist.");
                    }
                } else if (choice.equals("2")) {
                    System.out.print("Enter video barcode: ");
                    String barcode = scanner.nextLine();
                    if (customer.ownedMovies.containsKey(barcode)) {
                        customer.returnMovie(new Video("Temp", barcode));
                    } else {
                        System.out.println("You have not rented this video.");
                    }
                } else if (choice.equals("3")) {
                    System.out.println("Rented Videos:");
                    customer.printOwnedMovies();
                }else if (choice.equals("4")) {
                    System.out.println("Available Videos:");
                    employee.videos();
                } else {
                    System.out.println("Invalid phone number.");
                }
            }
        }
    }

    private static void employeeMenu(Scanner scanner, Employee employee) {
        while (true) {
            System.out.println("\nEmployee Menu:");
            System.out.println("1. Add a Customer");
            System.out.println("2. Add a Video");
            System.out.println("3. Search for a Movie Renter");
            System.out.println("4. View Available Videos");
            System.out.println("5. View Customers");
            System.out.println("6. Exit");

            String choice = scanner.nextLine();
            if (choice.equals("6")) break;

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
                if (employee.isValidTitle(title) && employee.isValidBarcode(barcode)) {
                    employee.addVideo(new Video(title, barcode));
                } else {
                    System.out.println("Invalid title or barcode. Please try again.");
                }
            } else if (choice.equals("3")) {
                System.out.print("Enter movie title to search: ");
                String title = scanner.nextLine();
                System.out.println(employee.searchForRenter(title));
            }else if (choice.equals("4")) {
                System.out.println("Available Videos:");
                employee.videos();
            } else if (choice.equals("5")) {
                System.out.println("Customer List:");
                employee.customers();
            }
        }
    }
}

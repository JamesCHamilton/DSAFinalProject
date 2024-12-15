package DSAFinalProject;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Employee employee = new Employee();
        HashTable customerTable = new HashTable();

        System.out.println("Welcome to the Video Rental System!");
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Add a Customer");
            System.out.println("2. Add a Video");
            System.out.println("3. Rent a Video");
            System.out.println("4. Return a Video");
            System.out.println("5. Search who rented a video");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add a customer
                    System.out.println("Enter Phone Number:");
                    int phoneNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter First Name:");
                    String firstName = scanner.nextLine();
                    System.out.println("Enter Last Name:");
                    String lastName = scanner.nextLine();

                    customerTable.put(String.valueOf(phoneNumber), new Customer(phoneNumber, firstName, lastName));
                    System.out.println("Customer added successfully!");
                    break;

                case 2:
                    // Add a video
                    System.out.println("Enter Video Title:");
                    String title = scanner.nextLine();
                    System.out.println("Enter Video Barcode (12 digits):");
                    int barcode = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Video video = new Video(title, barcode);
                    if (employee.isValidTitle(video) && employee.isValidBarcode(video)) {
                        employee.addVideo(video);
                        System.out.println("Video added successfully!");
                    } else {
                        System.out.println("Invalid video details!");
                    }
                    break;

                case 3:
                    // Rent a video
                    System.out.println("Enter Customer Phone Number:");
                    String customerPhone = scanner.nextLine();
                    Customer customer = (Customer) customerTable.get(customerPhone)[0];

                    if (customer != null) {
                        System.out.println("Enter Video Barcode to Rent:");
                        int rentBarcode = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        Video rentVideo = new Video("", rentBarcode);
                        customer.rent(rentVideo);
                    } else {
                        System.out.println("Customer not found!");
                    }
                    break;

                case 4:
                    // Return a video (Feature stub - would need implementation)
                    System.out.println("Feature not implemented.");
                    break;

                case 5:
                    // Search who rented a specific video
                    System.out.println("Enter Video Title to Search:");
                    String searchTitle = scanner.nextLine();
                    System.out.println("Search Feature: Not implemented yet."); // Would require lookup logic
                    break;

                case 6:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}

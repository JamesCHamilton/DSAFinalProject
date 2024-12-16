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
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 3) break;

            System.out.print("Enter your phone number: ");
            String phoneNumber = scanner.nextLine();
            Customer customer = employee.getCustomer(phoneNumber);

            if (customer == null) {
                System.out.println("Invalid phone number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter the video barcode: ");
                    String barcode = scanner.nextLine();
                    String title = employee.getVideoTitle(barcode);
                    if (title != null){
                        customer.rent(new Video(title, barcode));
                        System.out.println("Successfully rented a movie");
                    }
                    else{
                        System.out.println("Video does not exist.");
                    }   
                    
                    break;

                case 2:
                    System.out.print("Enter the video barcode: ");
                    barcode = scanner.nextLine();
                    String movieTitle = (String) customer.ownedMovies.get(barcode)[0];
                    if (movieTitle != null) {
                        customer.returnMovie(new Video(movieTitle, barcode));
                        System.out.println("Successfully returned a movie");
                    } else {
                        System.out.println("Invalid video details.");
                    }
                    break;
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
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 4) break;

            switch (choice) {
                case 1:
                    System.out.print("Enter phone number: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter first name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter last name: ");
                    String lastName = scanner.nextLine();
                    employee.addCustomer(phone, firstName, lastName);
                    System.out.println("Successfully added a customer");
                    break;

                case 2:
                    System.out.print("Enter video title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter barcode: ");
                    String barcode = scanner.nextLine();
                    employee.addVideo(new Video(title, barcode));
                    System.out.println("Successfully added a video");
                    break;

                case 3:
                    System.out.print("Enter movie title to search for renter: ");
                    title = scanner.nextLine();
                    System.out.println(employee.searchForRenter(title));
                    
                    break;
            }
        }
    }
}

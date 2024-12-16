package DSAFinalProject;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Employee employee = new Employee();

        System.out.println("Welcome to the Video Rental System!");
        System.out.println("Enter (1) if you are a Customer, (2) if you are an Employee, or ANYTHING else to exit:");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) { // Customer Menu
            while (true) {
                System.out.println("\nCustomer Menu:");
                System.out.println("1. Rent a Video");
                System.out.println("2. Return a Video");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                int customerChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (customerChoice) {
                    case 1: // Rent a Video
                        System.out.print("Enter your phone number: ");
                        Long phoneNumber = scanner.nextLong();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter the video barcode: ");
                        String movieBarcode = scanner.nextLine();

                        if (employee.phoneNumberValidator(phoneNumber)) {
                            if (employee.videolist.containsKey(movieBarcode)) {
                                Video videoToRent = new Video(
                                    (String) employee.videolist.get(movieBarcode)[0], 
                                    movieBarcode
                                );
                                Customer customer = (Customer) employee.customerList.get(String.valueOf(phoneNumber))[0];
                                customer.rent(videoToRent, phoneNumber, movieBarcode);
                            } else {
                                System.out.println("The video does not exist in the system.");
                            }
                        } else {
                            System.out.println("Invalid phone number. Please ensure you are registered.");
                        }
                        break;

                    case 2: // Return a Video
                        System.out.print("Enter your phone number: ");
                        phoneNumber = scanner.nextLong();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter the video barcode: ");
                        movieBarcode = scanner.nextLine();

                        if (employee.phoneNumberValidator(phoneNumber)) {
                            Customer customer = (Customer) employee.customerList.get(String.valueOf(phoneNumber))[0];
                            Video videoToReturn = new Video((String) customer.ownedMovies.get(movieBarcode)[0],movieBarcode);
                            customer.returnMovie(videoToReturn, phoneNumber, movieBarcode);
                        } else {
                            System.out.println("Invalid phone number or no rented video matches the barcode.");
                        }
                        break;

                    case 3: // Exit
                        System.out.println("Thank you for visiting our shop!");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        } else if (choice == 2) { // Employee Menu
            while (true) {
                System.out.println("\nEmployee Menu:");
                System.out.println("1. Add a Customer");
                System.out.println("2. Add a Video");
                System.out.println("3. Search who rented a video");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");

                int employeeChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (employeeChoice) {
                    case 1: // Add a Customer
                        System.out.print("Enter phone number: ");
                        long phoneNumber = scanner.nextLong();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter first name: ");
                        String firstName = scanner.nextLine();
                        System.out.print("Enter last name: ");
                        String lastName = scanner.nextLine();

                        employee.addCustomer(phoneNumber, firstName, lastName);
                        System.out.println("Customer added successfully!");
                        break;

                    case 2: // Add a Video
                        System.out.print("Enter video title: ");
                        String videoTitle = scanner.nextLine();
                        System.out.print("Enter video barcode (12 digits): ");
                        String barcode = scanner.nextLine();

                        Video newVideo = new Video(videoTitle, barcode);
                        if (employee.validateVideo(newVideo)) {
                            employee.addVideo(newVideo);
                            System.out.println("Video added successfully!");
                        } else {
                            System.out.println("Invalid video details. Please check the title and barcode.");
                        }
                        break;

                    case 3: // Search who rented a video
                        System.out.print("Enter the video title: ");
                        String searchTitle = scanner.nextLine();

                        String renter = employee.searchForRenter(searchTitle);
                        System.out.println(renter);
                        break;

                    case 4: // Exit
                        System.out.println("Goodbye!");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        } else {
            System.out.println("Exiting the system. Goodbye!");
            scanner.close();
        }
    }

}

package DSAFinalProject;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Customer {
    private int phoneNumber;
    private String firstName;
    private String lastName;
    private final int MAXRENTABLEMOVIES = 3;
    int moviesOwned = 0;
    Map<String,Integer> ownedMovies = new HashMap<String, Integer>();

    public Customer(int phoneNumber, String firstName, String lastName){
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void rent(Video video){rentHelper(video);}

    private void rentHelper(Video video){
        if(moviesOwned < MAXRENTABLEMOVIES){
            Scanner scanner = new Scanner(System.in);
            



            scanner.close();
        }else{
            System.out.printf("You can not rent anymore movies. You are currently renting %d", moviesOwned);
        }

    }






    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public int getPhoneNumber() {return phoneNumber;}
}

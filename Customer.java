package DSAFinalProject;


public class Customer {
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private final int MAXRENTABLEMOVIES = 3;
    int moviesOwned = 0;
    HashTable ownedMovies = new HashTable();
    private Employee employee;

    
    public Customer(String phoneNumber, String firstName, String lastName, Employee employee){
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employee = employee;
    }

    public void rent(Video video){rentHelper(video);}
    public void returnMovie(Video video){returnHelepr(video);}
    public void printOwnedMovies(){ownedMoviesPrinter();}

    //average -> O(1) worst -> O(n)
    private void rentHelper(Video video){

        System.out.printf("Movies Owned: %d, Max Rentable: %d\n", moviesOwned, MAXRENTABLEMOVIES);

        if (moviesOwned >= MAXRENTABLEMOVIES) {
            System.out.println("You cannot rent anymore movies.");
            return;
        }

        if (!employee.doesVideoExists(video.getBarcode())) {
            System.out.println("This video does not exist in the system.");
            return;
        }

        if (!employee.videolist.containsKey(video.getBarcode())) {
            System.out.println("This movie does not exist.");
            return;
        }

        // Add movie to ownedMovies and remove it from videolist
        ownedMovies.put(video.getBarcode(), video.getMovieTitle());
        employee.videolist.remove(video.getBarcode());
        System.out.printf("You have rented %s\n", video.getMovieTitle());
        moviesOwned++;
        System.out.printf("Movies Owned after increment: %d\n", moviesOwned);
    }

    //average -> O(m) worst -> O(m+n)
    private void returnHelepr(Video video){
        if (ownedMovies.containsKey(video.getBarcode())) {
            String movieTitle = (String) ownedMovies.get(video.getBarcode())[0]; 
            ownedMovies.remove(video.getBarcode());
            employee.addVideo(new Video(movieTitle, video.getBarcode())); 
            System.out.printf("You have returned %s. Thank you!%n", movieTitle);
            moviesOwned--;
        } else {
            System.out.println("You do not have that movie.");
        }
    }

    //refer to time complexity of printTable in Hashtable class
    private void ownedMoviesPrinter(){
        ownedMovies.printTable();
    }

    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getPhoneNumber() {return phoneNumber;}
}

package DSAFinalProject;


public class Customer {
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private final int MAXRENTABLEMOVIES = 3;
    int moviesOwned = 0;
    HashTable ownedMovies = new HashTable();
    Employee employee = new Employee();
    
    public Customer(String phoneNumber, String firstName, String lastName){
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void rent(Video video){rentHelper(video);}
    public void returnMovie(Video video){returnHelepr(video);}

    //O(n)
    private void rentHelper(Video video){

        if (ownedMovies.containsKey(video.getBarcode())) {
            System.out.println("You have already rented this movie.");
            return;
        }

            if(moviesOwned < MAXRENTABLEMOVIES){
                if(employee.videolist.containsKey(video.getBarcode())){
                    ownedMovies.put(video.getBarcode(), video.getMovieTitle());
                    employee.videolist.remove(video.getBarcode());
                    System.out.printf("You have rented %s", video.getMovieTitle());
                    moviesOwned++;
                }
                else{
                    System.out.println("That movie does not exists");
                }                
            }else{
                System.out.printf("You can not rent anymore movies. You are currently renting %d", moviesOwned);
            }
        }

    //O(n)
    private void returnHelepr(Video video){
            if(ownedMovies.containsKey(video.getBarcode())){
                ownedMovies.remove(video.getBarcode());
                employee.videolist.put(video.getBarcode(), video.getMovieTitle());
                System.out.printf("You have returned %s, Thank you", video.getMovieTitle());
                moviesOwned--;
            }else{
                System.out.println("You do not have that movie");
            }
        }

    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getPhoneNumber() {return phoneNumber;}
}

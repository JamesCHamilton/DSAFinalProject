package DSAFinalProject;


public class Customer {
    private int phoneNumber;
    private String firstName;
    private String lastName;
    private final int MAXRENTABLEMOVIES = 3;
    int moviesOwned = 0;
    HashTable ownedMovies = new HashTable();
    Employee employee = new Employee();
    
    public Customer(int phoneNumber, String firstName, String lastName){
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void rent(Video video, int phoneNumber, String movieBarcode){rentHelper(video, phoneNumber, movieBarcode);}
    public void returnMovie(Video video, int phoneNumber, String movieBarcode){returnHelepr(video, phoneNumber, movieBarcode);}

    //O(n)
    private void rentHelper(Video video, int phoneNumber, String movieBarcode){

        if (ownedMovies.containsKey(movieBarcode)) {
            System.out.println("You have already rented this movie.");
            return;
        }
        
        if(employee.phoneNumberValidator(phoneNumber)){
            if(moviesOwned < MAXRENTABLEMOVIES){
                if(employee.videolist.containsKey(movieBarcode)){
                    ownedMovies.put(video.getBarcode(), video.getMovieTitle());
                    employee.videolist.remove(movieBarcode);
                    System.out.printf("You have rented %s", video.getMovieTitle());
                    moviesOwned++;
                }
                else{
                    System.out.println("That movie does not exists");
                }                
            }else{
                System.out.printf("You can not rent anymore movies. You are currently renting %d", moviesOwned);
            }
        }else{
            System.out.println("Invalid phone number");
        }
    }

    //O(n)
    private void returnHelepr(Video video, int phoneNumber, String movieBarcode){
        if(employee.phoneNumberValidator(phoneNumber)){
            if(ownedMovies.containsKey(movieBarcode)){
                ownedMovies.remove(movieBarcode);
                employee.videolist.put(video.getBarcode(), video.getMovieTitle());
                System.out.printf("You have returned %s, Thank you", video.getMovieTitle());
            }else{
                System.out.println("You do not have that movie");
            }
        }else{
            System.out.println("Invalid phone number");
        }
    }

    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public int getPhoneNumber() {return phoneNumber;}
}

package DSAFinalProject;

public class Employee {

    public void addCustomer(int phoneNumber, String firstName, String lastName){addCustomerHelper(phoneNumber, firstName, lastName);}
    public void addVideo(Video video){addVideoHelper(video);}    
    public void videos(){printVideos();}
    public boolean isValidTitle(Video video){return titleValidatorHelper(video);}
    public boolean isValidBarcode(Video video){return barcodeValidator(video);}
    public boolean validateVideo(Video video){return validateVideoHelper(video);}
    public boolean phoneNumberValidator(int phoneNumber){return phoneNumberValidatorHelper(phoneNumber);}
    public String searchForRenter(String movieTitle){return searchRenterHelper(movieTitle);}


    HashTable videolist = new HashTable();
    HashTable customerList = new HashTable();

    //O(1) for average case, O(n) for worst case
    private void addVideoHelper(Video video){
        if(validateVideo(video)){
            videolist.put(video.getBarcode(), video.getMovieTitle());
        }
    }
    //O(m) m = movieTitle length 
    private boolean titleValidatorHelper(Video video){
        if(video.getMovieTitle().strip().equalsIgnoreCase(null) || video.getMovieTitle().strip().equalsIgnoreCase("")){
            return false;
        }
        return true;
    }
    //O(1)
    private boolean barcodeValidator(Video video){
        if((video.getBarcode()).length() != 12){
            return false;
        }
        return true;
    }

    //O(1) + O(m) = O(m) cause of titleValidatorHelper
    private boolean validateVideoHelper(Video video){
        if(titleValidatorHelper(video) && barcodeValidator(video)){
            return true;
        }
        return false;
    }

    //O(n+m)
    private void printVideos(){
        videolist.printTable();
    }

    //O(1) for average case, O(n) for worst case
    private void addCustomerHelper(int phoneNumber, String firstName, String lastName){
        if (customerList.containsKey(String.valueOf(phoneNumber))) {
            System.out.println("Customer with this phone number already exists.");
            return;
        }
        customerList.put(String.valueOf(phoneNumber), new Customer(phoneNumber, firstName, lastName));
        
    }
    //O(1) for average case, O(n) for worst case
    private boolean phoneNumberValidatorHelper(int phoneNumber){
        return customerList.containsKey(String.valueOf(phoneNumber));
    }

    //O(n+m)
    private String searchRenterHelper(String movieTitle){
        String barcode = null;
        for (String videoBarcode : videolist.keys()) { 
            String currentTitle = (String) videolist.get(videoBarcode)[0];
            if (currentTitle.equalsIgnoreCase(movieTitle)) {
                barcode = videoBarcode;
                break;
            }
        }

        if (barcode == null) {
            return "This movie does not exist in the system.";
        }

        for (String customerKey : customerList.keys()) { 
            Customer customer = (Customer) customerList.get(customerKey)[0];
            if (customer.ownedMovies.containsKey(barcode)) {
                return "This video is rented by: " + customer.getFirstName() + " " + customer.getLastName();
            }
        }

        return "No one has rented this movie.";
    }
}

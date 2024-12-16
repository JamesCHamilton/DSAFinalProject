package DSAFinalProject;

public class Employee {

    public void addCustomer(String phoneNumber, String firstName, String lastName){addCustomerHelper(phoneNumber, firstName, lastName);}
    public void addVideo(Video video){addVideoHelper(video);}    
    public void videos(){printVideos();}
    public boolean isValidTitle(String movieTitle){return titleValidatorHelper(movieTitle);}
    public boolean isValidBarcode(String barcode){return barcodeValidator(barcode);}
    public boolean validateVideo(Video video){return validateVideoHelper(video);}
    public boolean phoneNumberValidator(String phoneNumber){return phoneNumberValidatorHelper(phoneNumber);}
    public boolean isNumberValid(String phoneNumber){return isValidPhoneNumberHelper(phoneNumber);}
    public String searchForRenter(String movieTitle){return searchRenterHelper(movieTitle);}
    public String getVideoTitle(String barcode){return videoTitle(barcode);}
    public Customer getCustomer(String phoneNumber){return customerFetcher(phoneNumber);}
    
    
    
        HashTable videolist = new HashTable();
    HashTable customerList = new HashTable();

    //O(1) for average case, O(n) for worst case
    private void addVideoHelper(Video video){
        if(validateVideo(video)){
            videolist.put(video.getBarcode(), video.getMovieTitle());
        }
    }
    //O(m) m = movieTitle length 
    private boolean titleValidatorHelper(String movieTitle){
        if(movieTitle.strip().equalsIgnoreCase(null) || movieTitle.strip().isBlank()){
            return false;
        }
        return true;
    }
    //O(1)
    private boolean barcodeValidator(String barcode){
        return barcode.length() == 12 && barcode != null;
    }

    //O(1) + O(m) = O(m) cause of titleValidatorHelper
    private boolean validateVideoHelper(Video video){
        if(titleValidatorHelper(video.getMovieTitle()) && barcodeValidator(video.getBarcode())){
            return true;
        }
        return false;
    }

    //O(n+m)
    private void printVideos(){
        videolist.printTable();
    }

    //O(1) for average case, O(n) for worst case
    private void addCustomerHelper(String phoneNumber, String firstName, String lastName){
        if (customerList.containsKey(phoneNumber)) {
            System.out.println("Customer with this phone number already exists.");
            return;
        }
        if(isNumberValid(phoneNumber) == false){
            System.out.println("Not a valid 10 digit phone number");
            return;
        }
        customerList.put(phoneNumber.trim(), new Customer(phoneNumber, firstName, lastName));
        
    }
    //O(1) for average case, O(n) for worst case
    private boolean phoneNumberValidatorHelper(String phoneNumber){
        return customerList.containsKey(phoneNumber);
    }

    private boolean isValidPhoneNumberHelper(String phoneNumber){
        return phoneNumber.length() == 10; 
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
    private String videoTitle(String barcode){
        Object[] videoData = videolist.get(barcode);
        if (videoData != null && videoData[0] instanceof String) {
            return (String) videoData[0];
        }
        return null;
    }
    private Customer customerFetcher(String phoneNumber) {
        Object[] customerData = customerList.get(phoneNumber);
        if (customerData != null && customerData[0] instanceof Customer) {
            return (Customer) customerData[0];
        }
        return null;
    }
}

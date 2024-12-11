package DSAFinalProject;

public class Employee {

    public void addVideo(Video video){addVideoHelper(video);}
    public boolean isValidTitle(Video video){return titleValidatorHelper(video);}
    public boolean isValidBarcode(Video video){return barcodeValidator(video);}
    public boolean validateVideo(Video video){return validateVideoHelper(video);}


    HashTable videolist = new HashTable();

    private void addVideoHelper(Video video){
        if(validateVideo(video)){
            videolist.put(video.getMovieTitle(), video.getBarcode() );
        }
    }

    private boolean titleValidatorHelper(Video video){
        if(video.getMovieTitle().equalsIgnoreCase(null) || video.getMovieTitle().strip().equalsIgnoreCase("")){
            return false;
        }
        return true;
    }

    private boolean barcodeValidator(Video video){
        if(Integer.toString(video.getBarcode()).length() != 12){
            return false;
        }
        return true;
    }

    private boolean validateVideoHelper(Video video){
        if(titleValidatorHelper(video) && barcodeValidator(video)){
            return true;
        }
        return false;
    }
    
}

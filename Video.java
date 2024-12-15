package DSAFinalProject;

public class Video {
    private String barcode;
    private String movieTitle;

    public Video(String movieTitle,String barcode){
        this.barcode = barcode;
        this.movieTitle = movieTitle;
    }

    private void print(){
        System.out.printf("%s %d", movieTitle, barcode);
    }
    
    public void videoPrinter(){print();}
    public String getBarcode() {return barcode;}
    public String getMovieTitle() {return movieTitle;}

    
}

package DSAFinalProject;

public class Video {
    private int barcode;
    private String movieTitle;

    public Video(String movieTitle,int barcode){
        this.barcode = barcode;
        this.movieTitle = movieTitle;
    }

    

    private void print(){
        System.out.printf("%s %d", movieTitle, barcode);
    }
    
    public void videoPrinter(){print();}
    public int getBarcode() {return barcode;}
    public String getMovieTitle() {return movieTitle;}

    
}

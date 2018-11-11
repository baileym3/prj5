package musicpreferencevisualization;

public class Song {

    private String title;
    private String artist;
    private String year;
    private String genre;
    private int likes;
    private int listens;


    public Song(
        String titleIn,
        String artistIn,
        String yearIn,
        String genreIn) {
        title = titleIn;
        artist = artistIn;
        year = yearIn;
        genre = genreIn;
    }


    public String title() {
        return title;
    }


    public String artist() {
        return artist;
    }


    public String year() {
        return year;
    }


    public String genre() {
        return genre;
    }


    public int getLikes() {
        return likes;
    }


    public int getListens() {
        return listens;
    }


    public String toString() {
        StringBuilder sB = new StringBuilder();
        sB.append(title);
        sB.append(artist);
        sB.append(year);
        sB.append(genre);
        return sB.toString();
    }


}

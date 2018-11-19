package prj5;

/**
 * Creates song class with fields used for sorting
 * 
 * @author Ishaan Singh (ishaan15)
 * @version 2018.11.15
 */
public class Song {

    private String title;
    private String artist;
    private String year;
    private String genre;
    private String likes;
    private String heard;


    /**
     * Default constructor that populates the title, artist, year, and genre
     * fields
     * 
     * @param titleIn
     *            the title of the song
     * @param artistIn
     *            the artist who performs the song
     * @param yearIn
     *            the year the song was released
     * @param genreIn
     *            the genre of the song
     */
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


    /**
     * Getter method for the number of likes
     * 
     * @return the number of likes for a song
     */
    public String getLikes() {
        return likes;
    }


    public void setLikes(String likesIn) {
        likes = likesIn;
    }


    /**
     * Getter method for the number of listens for a song
     * 
     * @return the number of listens for a song
     */
    public String getHeard() {
        return heard;
    }


    public void setHeard(String heardIn) {
        heard = heardIn;
    }


    /**
     * Getter method for the year of a song
     * 
     * @return the year a song was released
     */
    public String year() {
        return year;
    }


    /**
     * Getter method for the genre of a song
     * 
     * @return the genre of the song
     */
    public String genre() {
        return genre;
    }


    /**
     * Getter method for the title of a song
     * 
     * @return the title of the song
     */
    public String title() {
        return title;
    }


    /**
     * Getter method for the artist of a song
     * 
     * @return the artist that performs the song
     */
    public String artist() {
        return artist;
    }


    /**
     * Returns the "{title, artist, year, and genre} "
     */
    public String toString() {
        StringBuilder sB = new StringBuilder();
        sB.append("Song Title: " + title());
        sB.append("\n");
        sB.append("Song Artist: " + artist());
        sB.append("\n");
        sB.append("Song Genre: " + genre());
        sB.append("\n");
        sB.append("Song Year: " + year());
        sB.append("\n");
        sB.append("Heard");
        sB.append("\n");
        sB.append(getHeard());
        sB.append("\n");
        sB.append("Likes");
        sB.append("\n");
        sB.append(getLikes());
        sB.append("\n");
        sB.append("\n");
        return sB.toString();
    }

}

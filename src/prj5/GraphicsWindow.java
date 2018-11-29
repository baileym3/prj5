package prj5;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;
import CS2114.Button;
import CS2114.Shape;
import CS2114.TextShape;
import CS2114.Window;
import CS2114.WindowSide;

/**
 * Graphics window class
 * 
 * @author Bailey(baileym3)
 * @version 2018.11.18
 */
public class GraphicsWindow {

    private Window window;
    private FileReader fR;
    private DoublyLinkedList1 songsDLL;
    private String[] songs;
    private static final int BAR_FACTOR = 75;


    /**
     * Created the window and buttons, sets the file reader to the parameter and
     * uses it to read the songs and survey data
     * 
     * @throws FileNotFoundException
     *             if the song and survey files cannot be found
     */
    public GraphicsWindow(FileReader fRIn) throws FileNotFoundException {
        window = new Window("Project 5 Implementation");
        fR = fRIn;
        fR.readSongs();
        fR.readSurvey();
        songsDLL = fR.getSongsDLL();
        songs = songsDLL.songNames();
        createButtons();
    }


    /**
     * Creates the buttons
     */
    private void createButtons() {
        Button prev = new Button("< Previous");
        window.addButton(prev, WindowSide.NORTH);
        prev.onClick(this, "clickedPrev");
        prev.disable();
        Button sortArtist = new Button("Sort By Artist Name");
        window.addButton(sortArtist, WindowSide.NORTH);
        sortArtist.onClick(this, "clickedArtist");
        Button sortTitle = new Button("Sort By Song Title");
        window.addButton(sortTitle, WindowSide.NORTH);
        sortTitle.onClick(this, "clickedTitle");
        Button sortYear = new Button("Sort By Release Year");
        window.addButton(sortYear, WindowSide.NORTH);
        sortYear.onClick(this, "clickedYear");
        Button sortGenre = new Button("Sort By Genre");
        window.addButton(sortGenre, WindowSide.NORTH);
        sortGenre.onClick(this, "clickedGenre");
        Button next = new Button("Next >");
        window.addButton(next, WindowSide.NORTH);
        next.onClick(this, "clickedNext");
        Button hobby = new Button("Represent Hobby");
        window.addButton(hobby, WindowSide.SOUTH);
        hobby.onClick(this, "clickedHobby");
        Button region = new Button("Represent Region");
        window.addButton(region, WindowSide.SOUTH);
        region.onClick(this, "clickedRegion");
        Button major = new Button("Represent Major");
        window.addButton(major, WindowSide.SOUTH);
        major.onClick(this, "clickedMajor");
        Button quit = new Button("Quit");
        window.addButton(quit, WindowSide.SOUTH);
        quit.onClick(this, "clickedQuit");
    }
    private void drawVerticalBars() {
        for (int x = 1; x <= 3; x++) {
            for (int y = 1; y <= 3; y++) {
                Shape i = new Shape(x * 200 - 50, y * 90 - 50, 3, 40,
                    Color.BLACK);
                window.addShape(i);
            }
        }
    }
    private void updateSongs() {
        songs = songsDLL.songNames();
    }
    /**
     * Executes when the Quit button is clicked
     * @param button
     */
    public void clickedQuit(Button button) {
        System.exit(0);
    }

    public void clickedHobby(Button button) {
        window.removeAllShapes();
        drawHobbyGraphs();
        drawVerticalBars();
        addSongInfo();
        drawHobbyLegend();
    }
    
    public void clickedMajor(Button button) {
        window.removeAllShapes();
        drawVerticalBars();
        drawMajorLegend();
        addSongInfo();
    }
    public void clickedRegion(Button button) {
        window.removeAllShapes();
        drawVerticalBars();
        drawRegionLegend();
        addSongInfo();
    }
    public void clickedArtist(Button button) {
        window.removeAllShapes();
        drawVerticalBars();
        songsDLL.artistSort();
        updateSongs();
        this.addSongInfo();
    }
    public void clickedTitle(Button button) {
        window.removeAllShapes();
        drawVerticalBars();
        songsDLL.titleSort();
        updateSongs();
        this.addSongInfo();
    }
    public void clickedGenre(Button button) {
        window.removeAllShapes();
        drawVerticalBars();
        songsDLL.genreSort();
        updateSongs();
        this.addSongInfo();
    }
    public void clickedYear(Button button) {
        window.removeAllShapes();
        drawVerticalBars();
        songsDLL.yearSort();
        updateSongs();
        this.addSongInfo();
    }
    private void drawRegionLegend() {
        TextShape legend = new TextShape(640, 110, "Region Legend", Color.BLACK);
        legend.setBackgroundColor(Color.WHITE);
        window.addShape(legend);
        TextShape ne = new TextShape(640, 130, "Northeast", Color.MAGENTA);
        ne.setBackgroundColor(Color.WHITE);
        window.addShape(ne);
        TextShape southeast = new TextShape(640, 145, "Southeast", Color.BLUE);
        southeast.setBackgroundColor(Color.WHITE);
        window.addShape(southeast);
        TextShape other = new TextShape(640, 160, "Other U.S.", Color.ORANGE);
        other.setBackgroundColor(Color.WHITE);
        window.addShape(other);
        TextShape outside = new TextShape(640, 175, "Outside U.S.", Color.GREEN);
        outside.setBackgroundColor(Color.WHITE);
        window.addShape(outside);
        TextShape songTitle = new TextShape(655, 195, "Song Title",
            Color.BLACK);
        songTitle.setBackgroundColor(Color.WHITE);
        window.addShape(songTitle);
        window.addShape(new Shape(685, 200, 3, 50, Color.BLACK));
        TextShape heardlike = new TextShape(640, 225, "Heard  Likes");
        heardlike.setBackgroundColor(Color.WHITE);
        window.addShape(heardlike);
    }
    /**
     * Draws the legend for the majors
     */
    private void drawMajorLegend() {
        TextShape legend = new TextShape(640, 110, "Major Legend", Color.BLACK);
        legend.setBackgroundColor(Color.WHITE);
        window.addShape(legend);
        TextShape cs = new TextShape(640, 130, "Comp Sci", Color.MAGENTA);
        cs.setBackgroundColor(Color.WHITE);
        window.addShape(cs);
        TextShape otherEng = new TextShape(640, 145, "Other Eng", Color.BLUE);
        otherEng.setBackgroundColor(Color.WHITE);
        window.addShape(otherEng);
        TextShape math = new TextShape(640, 160, "Math/CMDA", Color.ORANGE);
        math.setBackgroundColor(Color.WHITE);
        window.addShape(math);
        TextShape other = new TextShape(640, 175, "Other", Color.GREEN);
        other.setBackgroundColor(Color.WHITE);
        window.addShape(other);
        TextShape songTitle = new TextShape(655, 195, "Song Title",
            Color.BLACK);
        songTitle.setBackgroundColor(Color.WHITE);
        window.addShape(songTitle);
        window.addShape(new Shape(685, 200, 3, 50, Color.BLACK));
        TextShape heardlike = new TextShape(640, 225, "Heard  Likes");
        heardlike.setBackgroundColor(Color.WHITE);
        window.addShape(heardlike);
    }
    /**
     * Draws the legend containing the hobbies
     */
    private void drawHobbyLegend() {
        TextShape legend = new TextShape(640, 110, "Hobby Legend", Color.BLACK);
        legend.setBackgroundColor(Color.WHITE);
        window.addShape(legend);
        TextShape read = new TextShape(640, 130, "Read", Color.MAGENTA);
        read.setBackgroundColor(Color.WHITE);
        window.addShape(read);
        TextShape art = new TextShape(640, 145, "Art", Color.BLUE);
        art.setBackgroundColor(Color.WHITE);
        window.addShape(art);
        TextShape sports = new TextShape(640, 160, "Sports", Color.ORANGE);
        sports.setBackgroundColor(Color.WHITE);
        window.addShape(sports);
        TextShape music = new TextShape(640, 175, "Music", Color.GREEN);
        music.setBackgroundColor(Color.WHITE);
        window.addShape(music);
        TextShape songTitle = new TextShape(655, 195, "Song Title",
            Color.BLACK);
        songTitle.setBackgroundColor(Color.WHITE);
        window.addShape(songTitle);
        window.addShape(new Shape(685, 200, 3, 50, Color.BLACK));
        TextShape heardlike = new TextShape(640, 225, "Heard  Likes");
        heardlike.setBackgroundColor(Color.WHITE);
        window.addShape(heardlike);
    }


    public void addSongInfo() {
        int x = 0;
        int x2 = 0;
        int x3 = 0;
        TextShape newSong;
        TextShape artistOutput;
        String artist;
        for (int i = 0; i < songs.length; i++) {
            artist = songsDLL.getSong(songs[i]).artist();
            if (x / 3 == 0) {
                int yLoc = 5;
                newSong = new TextShape(0, yLoc, songs[i]);
                newSong.setX(x * 200 + 150 - newSong.getWidth() / 2);
                artistOutput = new TextShape(0, yLoc + 15, artist);
                artistOutput.setX(x * 200 + 150 - artistOutput.getWidth() / 2);
            }
            else if (x / 3 == 1) {
                int yLoc = 90;
                newSong = new TextShape(0, yLoc, songs[i]);
                newSong.setX(x2 * 200 + 150 - newSong.getWidth() / 2);
                artistOutput = new TextShape(0, yLoc + 15, artist);
                artistOutput.setX(x2 * 200 + 150 - artistOutput.getWidth() / 2);
                x2++;
            }
            else {
                int yLoc = 175;
                newSong = new TextShape(0, yLoc, songs[i]);
                newSong.setX(x3 * 200 + 150 - newSong.getWidth() / 2);
                artistOutput = new TextShape(0, yLoc + 15, artist);
                artistOutput.setX(x3 * 200 + 150 - artistOutput.getWidth() / 2);
                x3++;
            }
            newSong.setBackgroundColor(Color.WHITE);
            window.addShape(newSong);
            artistOutput.setBackgroundColor(Color.WHITE);
            window.addShape(artistOutput);
            x++;
        }
    }

    
    public void drawHobbyGraphs() {
        for (int i = 0; i < songs.length; i++) {
            // HEARD
            int xLoc;
            int yLoc;
            if (i / 3 == 0) {
                yLoc = 40;
            }
            else if (i / 3 == 1) {
                yLoc = 130;
            }
            else {
                yLoc = 220;
            }
            if (i % 3 == 0) {
                xLoc = 150;
            }
            else if (i % 3 == 1) {
                xLoc = 350;
            }
            else {
                xLoc = 550;
            }
            String songName = songs[i];
            if (fR.getTotalHobby("reading") > 0) {
                int readingHeardSize = fR.getHeardHobbyNumber(songName,
                    "reading") * BAR_FACTOR / fR.getTotalHobby("reading");
                Shape readingHeardGraph = new Shape(xLoc - readingHeardSize,
                    yLoc, readingHeardSize, 10, Color.MAGENTA);
                window.addShape(readingHeardGraph);
            }
            if (fR.getTotalHobby("sports") > 0) {
                int sportsHeardSize = fR.getHeardHobbyNumber(songName, "sports")
                    * BAR_FACTOR / fR.getTotalHobby("sports");
                Shape sportsHeardGraph = new Shape(xLoc - sportsHeardSize, yLoc
                    + 20, sportsHeardSize, 10, Color.orange);
                window.addShape(sportsHeardGraph);
            }
            if (fR.getTotalHobby("art") > 0) {
                int artHeardSize = fR.getHeardHobbyNumber(songName, "art")
                    * BAR_FACTOR / fR.getTotalHobby("art");
                Shape artHeardGraph = new Shape(xLoc - artHeardSize, yLoc + 10,
                    artHeardSize, 10, Color.blue);
                window.addShape(artHeardGraph);
            }
            if (fR.getTotalHobby("music") > 0) {
                int musicHeardSize = fR.getHeardHobbyNumber(songName, "music")
                    * BAR_FACTOR / fR.getTotalHobby("music");
                Shape musicHeardGraph = new Shape(xLoc - musicHeardSize, yLoc
                    + 30, musicHeardSize, 10, Color.green);
                window.addShape(musicHeardGraph);
            }
            // LIKES
            if (fR.getTotalHobby("reading") > 0) {
                int size = fR.getLikesHobbyNumber(songName, "reading")
                    * BAR_FACTOR / fR.getTotalHobby("reading");
                Shape graph = new Shape(xLoc, yLoc, size, 10, Color.magenta);
                window.addShape(graph);
            }
            if (fR.getTotalHobby("art") > 0) {
                int size = fR.getLikesHobbyNumber(songName, "art") * BAR_FACTOR
                    / fR.getTotalHobby("art");
                Shape graph = new Shape(xLoc, yLoc + 10, size, 10, Color.BLUE);
                window.addShape(graph);
            }
            if (fR.getTotalHobby("music") > 0) {
                int size = fR.getLikesHobbyNumber(songName, "music")
                    * BAR_FACTOR / fR.getTotalHobby("music");
                Shape graph = new Shape(xLoc, yLoc + 30, size, 10, Color.GREEN);
                window.addShape(graph);
            }
            if (fR.getTotalHobby("sports") > 0) {
                int size = fR.getLikesHobbyNumber(songName, "sports")
                    * BAR_FACTOR / fR.getTotalHobby("sports");
                Shape graph = new Shape(xLoc, yLoc + 20, size, 10, Color.orange);
                window.addShape(graph);
            }
        }
    }
    
}

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
public class GraphicsWindow implements Observer {

    private Window window;
    private FileReader fR;
    private DoublyLinkedList1 songsDLL;
    private String[] songs;


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
        /*
         * list = listInput;
         * 
         * list.addObserver(this);
         */
        songsDLL = fR.getSongsDLL();
        songs = songsDLL.songNames();
        Button prev = new Button("< Previous");
        window.addButton(prev, WindowSide.NORTH);
        prev.onClick(this, "clickedPrev");
        Button sortArtist = new Button("Sort By Artist Name");
        window.addButton(sortArtist, WindowSide.NORTH);
        Button sortTitle = new Button("Sort By Song Title");
        window.addButton(sortTitle, WindowSide.NORTH);
        Button sortYear = new Button("Sort By Release Year");
        window.addButton(sortYear, WindowSide.NORTH);
        Button sortGenre = new Button("Sort By Genre");
        window.addButton(sortGenre, WindowSide.NORTH);
        Button next = new Button("Next >");
        window.addButton(next, WindowSide.NORTH);
        Button hobby = new Button("Represent Hobby");
        window.addButton(hobby, WindowSide.SOUTH);
        Button region = new Button("Represent Region");
        window.addButton(region, WindowSide.SOUTH);
        Button major = new Button("Represent Major");
        window.addButton(major, WindowSide.SOUTH);
        Button quit = new Button("Quit");
        window.addButton(quit, WindowSide.SOUTH);
        quit.onClick(this, "clickedQuit");
        for (int x = 1; x <= 3; x++) {
            for (int y = 1; y <= 3; y++) {
                Shape i = new Shape(x * 200 - 50, y * 90 - 50, 3, 40,
                    Color.BLACK);
                window.addShape(i);
            }
        }
        // barGraphs();
        addGraphs();
        addSongInfo();
        drawLegend();

    }


    /**
     * Draws the legend
     */
    private void drawLegend() {
        TextShape legend = new TextShape(640, 110, "Hobby Legend", Color.BLACK);
        legend.setBackgroundColor(Color.WHITE);
        window.addShape(legend);
        TextShape read = new TextShape(640, 130, "Read", Color.PINK);
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
        // Shape legendBox = new Shape(500, xLoc , 100, 100, Color.BLACK);
        // window.addShape(legendBox);
    }


    private void drawHobbyBarGraphs(String songName, int x) {
        // HEARD
        int i = x;
        int xLoc = (150 + (i * 100));
        if (fR.getTotalHobby("reading") > 0) {
            int readingHeardSize = fR.getHeardHobbyNumber(songName, "reading")
                * 50 / fR.getTotalHobby("reading");
            Shape readingHeardGraph = new Shape(xLoc - readingHeardSize, 40,
                readingHeardSize, 10, Color.PINK);
            window.addShape(readingHeardGraph);
        }
        if (fR.getTotalHobby("sports") > 0) {
            int sportsHeardSize = fR.getHeardHobbyNumber(songName, "sports")
                * 50 / fR.getTotalHobby("sports");
            Shape sportsHeardGraph = new Shape(xLoc - sportsHeardSize, 50,
                sportsHeardSize, 10, Color.YELLOW);
            window.addShape(sportsHeardGraph);
        }
        if (fR.getTotalHobby("art") > 0) {
            int artHeardSize = fR.getHeardHobbyNumber(songName, "art") * 50 / fR
                .getTotalHobby("art");
            Shape artHeardGraph = new Shape(xLoc - artHeardSize, 60,
                artHeardSize, 10, Color.blue);
            window.addShape(artHeardGraph);
        }
        if (fR.getTotalHobby("music") > 0) {
            int musicHeardSize = fR.getHeardHobbyNumber(songName, "music") * 50
                / fR.getTotalHobby("music");
            Shape musicHeardGraph = new Shape(xLoc - musicHeardSize, 70,
                musicHeardSize, 10, Color.green);
            window.addShape(musicHeardGraph);
        }
        // LIKES
        if (fR.getTotalHobby("reading") > 0) {
            int size = fR.getLikesHobbyNumber(songName, "reading") * 50 / fR
                .getTotalHobby("reading");
            Shape graph = new Shape(xLoc, 40, size, 10, Color.PINK);
            window.addShape(graph);
        }
        if (fR.getTotalHobby("art") > 0) {
            int size = fR.getLikesHobbyNumber(songName, "art") * 50 / fR
                .getTotalHobby("art");
            Shape graph = new Shape(xLoc, 60, size, 10, Color.BLUE);
            window.addShape(graph);
        }
        if (fR.getTotalHobby("music") > 0) {
            int size = fR.getLikesHobbyNumber(songName, "music") * 50 / fR
                .getTotalHobby("music");
            Shape graph = new Shape(xLoc, 70, size, 10, Color.GREEN);
            window.addShape(graph);
        }
        if (fR.getTotalHobby("sports") > 0) {
            int size = fR.getLikesHobbyNumber(songName, "sports") * 50 / fR
                .getTotalHobby("sports");
            Shape graph = new Shape(xLoc, 50, size, 10, Color.yellow);
            window.addShape(graph);
        }
        i++;
    }


    public void addSongInfo() {
        int x = 0;
        int x2 = 0;
        int x3 = 0;
        for (int i = 0; i < songs.length; i++) {
            if (x / 3 == 0) {
                int yLoc = 90;
                TextShape newSong = new TextShape(50 + (i * 250), yLoc,
                    songs[i]);
                window.addShape(newSong);
            }
            else if (x / 3 == 1) {
                int yLoc = 180;
                TextShape newSong = new TextShape(50 + (x2 * 250), yLoc,
                    songs[i]);
                window.addShape(newSong);
                x2++;
            }
            else {
                int yLoc = 360;
                TextShape newSong = new TextShape(50 + (x3 * 250), yLoc,
                    songs[i]);
                window.addShape(newSong);
                x3++;
            }
            x++;
        }
    }


    public void addGraphs() {
        for (int i = 0; i < songs.length; i++) {
            drawHobbyBarGraphs(songs[i], i);
        }
    }


    /**
     * Draws bargraphs
     */
    public void barGraphs() {
        TextShape song = new TextShape(100, 5, "Call Me Maybe");
        song.setBackgroundColor(Color.WHITE);
        window.addShape(song);
        TextShape artist = new TextShape(80, 20, "By Carly Rae Jepson");
        artist.setBackgroundColor(Color.WHITE);
        window.addShape(artist);
        window.addShape(new Shape(105, 40, 90, 10, Color.PINK));
        window.addShape(new Shape(80, 50, 110, 10, Color.BLUE));
        window.addShape(new Shape(130, 60, 70, 10, Color.ORANGE));
        window.addShape(new Shape(120, 70, 60, 10, Color.GREEN));
    }


    /**
     * Adds update method
     * 
     * @param arg0
     *            waiting
     * @param arg1
     *            to wait for
     */
    @Override
    public void update(Observable arg0, Object arg1) {
        // Empty on purpose
    }

}

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
    private boolean hobbyDisplayed;
    private boolean regionDisplayed;
    private boolean majorDisplayed;
    private Button prev;
    private Button next;
    private int firstIndex;
    private int lastIndex;


    /**
     * Created the window and buttons, sets the file reader to the parameter and
     * uses it to read the songs and survey data
     * 
     * @throws FileNotFoundException
     *             if the song and survey files cannot be found
     */
    public GraphicsWindow(FileReader fRIn) throws FileNotFoundException {
        window = new Window("Project 5: baileym3 ishaan5 name");
        fR = fRIn;
        fR.readSongs();
        fR.readSurvey();
        songsDLL = fR.getSongsDLL();
        songs = songsDLL.songNames();
        createButtons();
        hobbyDisplayed = false;
        regionDisplayed = false;
        majorDisplayed = false;
        firstIndex = 0;
        lastIndex = 8;
    }


    /**
     * Creates the buttons
     */
    private void createButtons() {
        prev = new Button("< Previous");
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
        next = new Button("Next >");
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


    /**
     * Draws the vertical bars as seen in the window
     */
    private void drawVerticalBars() {
        for (int x = 1; x <= 3; x++) {
            for (int y = 1; y <= 3; y++) {
                Shape i = new Shape(x * 200 - 50, y * 90 - 50, 3, 40,
                    Color.BLACK);
                window.addShape(i);
            }
        }
    }


    /**
     * Updates the songs variables with the songsDLL song names
     */
    private void updateSongs() {
        songs = songsDLL.songNames();
    }


    /**
     * Executes when the Quit button is clicked
     * 
     * @param button
     */
    public void clickedQuit(Button button) {
        System.exit(0);
    }


    public void clickedPrev(Button button) {
        window.removeAllShapes();
        next.enable();
        drawVerticalBars();
        firstIndex -= 9;
        if (lastIndex % 9 != 0) {
            lastIndex -= (lastIndex % 9);
        }
        else {
            lastIndex -= 9;
            
        }
        lastIndex--;
        if (firstIndex <= 0) {
            prev.disable();
        }
        update();
        this.addSongInfo();
    }


    public void clickedNext(Button button) {
        window.removeAllShapes();
        prev.enable();
        drawVerticalBars();
        firstIndex += 9;
        lastIndex += 9;
        if (lastIndex > songs.length) {
            lastIndex = songs.length - 1;
            next.disable();
        }
        update();
        this.addSongInfo();
    }


    public void clickedHobby(Button button) {
        window.removeAllShapes();
        drawVerticalBars();
        drawHobbyGraphs();
        addSongInfo();
        drawHobbyLegend();
        hobbyDisplayed = true;
        majorDisplayed = false;
        regionDisplayed = false;
    }


    public void clickedMajor(Button button) {
        window.removeAllShapes();
        drawVerticalBars();
        drawMajorGraphs();
        drawMajorLegend();
        addSongInfo();
        majorDisplayed = true;
        hobbyDisplayed = false;
        regionDisplayed = false;
    }


    public void clickedRegion(Button button) {
        window.removeAllShapes();
        drawVerticalBars();
        drawRegionGraphs();
        drawRegionLegend();
        addSongInfo();
        regionDisplayed = true;
        hobbyDisplayed = false;
        majorDisplayed = false;
    }


    public void clickedArtist(Button button) {
        window.removeAllShapes();
        drawVerticalBars();
        songsDLL.artistSort();
        updateSongs();
        update();
        this.addSongInfo();

    }


    public void clickedTitle(Button button) {
        window.removeAllShapes();
        drawVerticalBars();
        songsDLL.titleSort();
        updateSongs();
        update();
        this.addSongInfo();
        drawVerticalBars();
    }


    public void clickedGenre(Button button) {
        window.removeAllShapes();
        drawVerticalBars();
        songsDLL.genreSort();
        updateSongs();
        update();
        this.addSongInfo();
    }


    /**
     * Sorts the data by year when clicked
     * 
     * @param button
     */
    public void clickedYear(Button button) {
        window.removeAllShapes();
        drawVerticalBars();
        songsDLL.yearSort();
        updateSongs();
        update();
        this.addSongInfo();
    }


    /**
     * Draws the legend for the different regions
     */
    private void drawRegionLegend() {
        TextShape legend = new TextShape(640, 110, "Region Legend",
            Color.BLACK);
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
        TextShape outside = new TextShape(640, 175, "Outside U.S.",
            Color.GREEN);
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


    /**
     * Displays the song title and artist on the window
     */
    public void addSongInfo() {
        int x = 0;
        int x2 = 0;
        int x3 = 0;
        TextShape newSong;
        TextShape artistOutput;
        String artist;
        for (int i = firstIndex; i <= lastIndex; i++) {
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


    private void drawHobbyGraphs() {
        for (int i = firstIndex; i <= lastIndex; i++) {
            // HEARD
            int xLoc;
            int yLoc;
            int rem = i % 9;
            if (rem >= 0 && rem <= 2) {
                yLoc = 40;
            }
            else if (rem >= 3 && rem <= 5) {
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
                Shape graph = new Shape(xLoc + 3, yLoc, size, 10,
                    Color.magenta);
                window.addShape(graph);
            }
            if (fR.getTotalHobby("art") > 0) {
                int size = fR.getLikesHobbyNumber(songName, "art") * BAR_FACTOR
                    / fR.getTotalHobby("art");
                Shape graph = new Shape(xLoc + 3, yLoc + 10, size, 10,
                    Color.BLUE);
                window.addShape(graph);
            }
            if (fR.getTotalHobby("music") > 0) {
                int size = fR.getLikesHobbyNumber(songName, "music")
                    * BAR_FACTOR / fR.getTotalHobby("music");
                Shape graph = new Shape(xLoc + 3, yLoc + 30, size, 10,
                    Color.GREEN);
                window.addShape(graph);
            }
            if (fR.getTotalHobby("sports") > 0) {
                int size = fR.getLikesHobbyNumber(songName, "sports")
                    * BAR_FACTOR / fR.getTotalHobby("sports");
                Shape graph = new Shape(xLoc + 3, yLoc + 20, size, 10,
                    Color.orange);
                window.addShape(graph);
            }
        }
    }


    private void drawRegionGraphs() {
        for (int i = firstIndex; i <= lastIndex; i++) {
            // HEARD
            int xLoc;
            int yLoc;
            int rem = i % 9;
            if (rem >= 0 && rem <= 2) {
                yLoc = 40;
            }
            else if (rem >= 3 && rem <= 5) {
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
            if (fR.getTotalRegion("Northeast") > 0) {
                int northeastHeardSize = fR.getHeardRegionNumber(songName,
                    "Northeast") * BAR_FACTOR / fR.getTotalRegion("Northeast");
                Shape northeastHeardGraph = new Shape(xLoc - northeastHeardSize,
                    yLoc, northeastHeardSize, 10, Color.MAGENTA);
                window.addShape(northeastHeardGraph);
            }
            if (fR.getTotalRegion(
                "United States (other than Southeast or Northwest)") > 0) {
                int otherUSHeardSize = fR.getHeardRegionNumber(songName,
                    "United States (other than Southeast or Northwest)")
                    * BAR_FACTOR / fR.getTotalRegion(
                        "United States (other than Southeast or Northwest)");
                Shape otherUSHeardGraph = new Shape(xLoc - otherUSHeardSize,
                    yLoc + 20, otherUSHeardSize, 10, Color.orange);
                window.addShape(otherUSHeardGraph);
            }
            if (fR.getTotalRegion("Southeast") > 0) {
                int southeastHeardSize = fR.getHeardRegionNumber(songName,
                    "Southeast") * BAR_FACTOR / fR.getTotalRegion("Southeast");
                Shape southeastHeardGraph = new Shape(xLoc - southeastHeardSize,
                    yLoc + 10, southeastHeardSize, 10, Color.blue);
                window.addShape(southeastHeardGraph);
            }
            if (fR.getTotalRegion("Outside of United States") > 0) {
                int outsideHeardSize = fR.getHeardRegionNumber(songName,
                    "Outside of United States") * BAR_FACTOR / fR
                        .getTotalRegion("Outside of United States");
                Shape outsideHeardGraph = new Shape(xLoc - outsideHeardSize,
                    yLoc + 30, outsideHeardSize, 10, Color.green);
                window.addShape(outsideHeardGraph);
            }
            // LIKES
            if (fR.getTotalRegion("Northeast") > 0) {
                int size = fR.getLikesRegionNumber(songName, "Northeast")
                    * BAR_FACTOR / fR.getTotalRegion("Northeast");
                Shape graph = new Shape(xLoc + 3, yLoc, size, 10, Color.magenta);
                window.addShape(graph);
            }
            if (fR.getTotalRegion("Southeast") > 0) {
                int size = fR.getLikesRegionNumber(songName, "Southeast")
                    * BAR_FACTOR / fR.getTotalRegion("Southeast");
                Shape graph = new Shape(xLoc + 3, yLoc + 10, size, 10, Color.BLUE);
                window.addShape(graph);
            }
            if (fR.getTotalRegion(
                "United States (other than Southeast or Northwest)") > 0) {
                int size = fR.getLikesRegionNumber(songName,
                    "United States (other than Southeast or Northwest)")
                    * BAR_FACTOR / fR.getTotalRegion(
                        "United States (other than Southeast or Northwest)");
                Shape graph = new Shape(xLoc + 3, yLoc + 20, size, 10, Color.ORANGE);
                window.addShape(graph);
            }
            if (fR.getTotalRegion("Outside of United States") > 0) {
                int size = fR.getLikesRegionNumber(songName,
                    "Outside of United States") * BAR_FACTOR / fR
                        .getTotalRegion("Outside of United States");
                Shape graph = new Shape(xLoc + 3, yLoc + 30, size, 10,
                    Color.GREEN);
                window.addShape(graph);
            }
        }
    }


    private void drawMajorGraphs() {
        for (int i = firstIndex; i <= lastIndex; i++) {
            // HEARD
            int xLoc;
            int yLoc;
            int rem = i % 9;
            if (rem >= 0 && rem <= 2) {
                yLoc = 40;
            }
            else if (rem >= 3 && rem <= 5) {
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
            if (fR.getTotalMajor("Computer Science") > 0) {
                int majorHeardSize = fR.getHeardMajorNumber(songName,
                    "Computer Science") * BAR_FACTOR / fR.getTotalMajor(
                        "Computer Science");
                Shape majorHeardGraph = new Shape(xLoc - majorHeardSize, yLoc,
                    majorHeardSize, 10, Color.MAGENTA);
                window.addShape(majorHeardGraph);
            }
            if (fR.getTotalMajor("Other Engineering") > 0) {
                int otherEngHeardSize = fR.getHeardMajorNumber(songName,
                    "Other Engineering") * BAR_FACTOR / fR.getTotalMajor(
                        "Other Engineering");
                Shape otherEngHeardGraph = new Shape(xLoc - otherEngHeardSize,
                    yLoc + 10, otherEngHeardSize, 10, Color.blue);
                window.addShape(otherEngHeardGraph);
            }
            if (fR.getTotalMajor("Math or CMDA") > 0) {
                int mathHeardSize = fR.getHeardMajorNumber(songName,
                    "Math or CMDA") * BAR_FACTOR / fR.getTotalMajor(
                        "Math or CMDA");
                Shape mathHeardGraph = new Shape(xLoc - mathHeardSize, yLoc
                    + 20, mathHeardSize, 10, Color.orange);
                window.addShape(mathHeardGraph);
            }
            if (fR.getTotalMajor("Other") > 0) {
                int otherHeardSize = fR.getHeardMajorNumber(songName, "Other")
                    * BAR_FACTOR / fR.getTotalMajor("Other");
                Shape otherHeardGraph = new Shape(xLoc - otherHeardSize, yLoc
                    + 30, otherHeardSize, 10, Color.green);
                window.addShape(otherHeardGraph);
            }
            // LIKES
            if (fR.getTotalMajor("Computer Science") > 0) {
                int size = fR.getLikesMajorNumber(songName, "Computer Science")
                    * BAR_FACTOR / fR.getTotalMajor("Computer Science");
                Shape graph = new Shape(xLoc + 3, yLoc, size, 10,
                    Color.magenta);
                window.addShape(graph);
            }
            if (fR.getTotalMajor("Other Engineering") > 0) {
                int size = fR.getLikesMajorNumber(songName, "Other Engineering")
                    * BAR_FACTOR / fR.getTotalMajor("Other Engineering");
                Shape graph = new Shape(xLoc + 3, yLoc + 10, size, 10,
                    Color.BLUE);
                window.addShape(graph);
            }
            if (fR.getTotalMajor("Math or CMDA") > 0) {
                int size = fR.getLikesMajorNumber(songName, "Math or CMDA")
                    * BAR_FACTOR / fR.getTotalMajor("Math or CMDA");
                Shape graph = new Shape(xLoc + 3, yLoc + 20, size, 10,
                    Color.ORANGE);
                window.addShape(graph);
            }
            if (fR.getTotalMajor("Other") > 0) {
                int size = fR.getLikesMajorNumber(songName, "Other")
                    * BAR_FACTOR / fR.getTotalMajor("Other");
                Shape graph = new Shape(xLoc + 3, yLoc + 30, size, 10,
                    Color.GREEN);
                window.addShape(graph);
            }
        }
    }


    /**
     * Updates the window when a "Sort by" button is clicked
     */
    public void update() {
        if (hobbyDisplayed == true) {
            drawHobbyGraphs();
            this.drawHobbyLegend();
        }
        else if (regionDisplayed == true) {
            drawRegionGraphs();
            drawRegionLegend();
        }
        else {
            drawMajorGraphs();
            drawMajorLegend();
        }
    }

}

package prj5;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import CS2114.Button;
import CS2114.Shape;
import CS2114.TextShape;
import CS2114.Window;
import CS2114.WindowSide;

public class GraphicsWindow implements Observer {

    private Window window;
    private Shape bottomRight;
    private Shape bottomCenter;
    private Shape bottomLeft;
    private Shape topRight;
    private Shape topCenter;
    private Shape topLeft;
    private Shape middleRight;
    private Shape middleCenter;
    private Shape middleLeft;

    // private DoubleLinkedList list;


    public GraphicsWindow(/* DoubleLinkedList listInput */) {
        window = new Window("Project 5 Implementation");
        /*
         * list = listInput;
         * 
         * list.addObserver(this);
         */
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
        barGraphs();
        drawLegend();

    }


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
        // Shape legendBox = new Shape(500, 150, 100, 100, Color.BLACK);
        // window.addShape(legendBox);
    }


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


    private void sleep() {

    }


    public void clickedPrev(Button button) {

    }


    public void clickedNext(Button button) {

    }


    public void clickedSortByTitle(Button button) {

    }


    public void clickedSortByArtist(Button button) {

    }


    public void clickedSortByYear(Button button) {

    }


    public void clickedSortByGenre(Button button) {

    }


    public void clickedRepresentMajor(Button button) {

    }


    public void clickedRepresentRegion(Button button) {

    }


    public void clickedRepresentHobby(Button button) {

    }


    public void clickedQuit(Button button) {

        System.exit(0);

    }


    @Override

    public void update(Observable arg0, Object arg1) {

        // TODO Auto-generated method stub

    }
}

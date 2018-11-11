package musicpreferencevisualization;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import CS2114.Button;
import CS2114.Shape;
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

        window.addButton(sortArtist, WindowSide.NORTH);

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

    }


    public void barGraphs() {

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

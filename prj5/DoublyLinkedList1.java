package prj5;

/**
 * DoublyLinkedList implementation with sorting methods for title, genre, year,
 * and artist name
 * 
 * @author Ishaan Singh (ishaan15)
 * @version 2018.11.15
 *
 */
public class DoublyLinkedList1 {

    private Node<Song> head;
    private Node<Song> tail;
    private int size;
    private String[] songNames;


    /**
     * Inner doubly linked node class
     * 
     * @author Ishaan Singh (ishaan15)
     * @version 2018.11.15
     *
     * @param <Song>
     *            the data type to be used
     */
    private class Node<Song> {

        private Song song;
        private Node<Song> next;
        private Node<Song> prev;


        public Node(Song songIn) {
            song = songIn;
            next = null;
            prev = null;
        }


        public void setNext(Node<Song> nextNode) {
            next = nextNode;
        }


        public void setPrev(Node<Song> prevNode) {
            prev = prevNode;
        }


        public Node<Song> getNext() {
            return next;
        }


        public Node<Song> getPrev() {
            return prev;
        }


        public Song getData() {
            return song;
        }
    }


    /**
     * Default constructor
     */
    public DoublyLinkedList1() {
        head = null;
        tail = null;
        size = 0;
    }


    /**
     * Checks to see if the list is empty
     * 
     * @return true if the list is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * Returns the number of items of the list
     * 
     * @return the number of items in the list
     */
    public int size() {
        return size;
    }


    /**
     * Adds a new song into the list at the back
     * 
     * @param newSong
     *            the song to be added
     */
    public void add(Song newSong) {
        Node<Song> newNode = new Node<Song>(newSong);
        if (isEmpty()) {
            head = newNode;
        }
        else {
            tail.setNext(newNode);
        }
        tail = newNode;
        size++;
    }


    /**
     * Removes a song from the front of the list
     */
    public void remove() {
        Node<Song> firstNode = head;
        head = head.getNext();
        firstNode.setNext(null);
        size--;
    }


    /**
     * Outputs all the song names in the DLL
     */
    public String[] songNames() {
        Node<Song> curr = head;
        int x = 0;
        songNames = new String[size];
        while (curr != null) {
            songNames[x] = curr.getData().title();
            curr = curr.getNext();
            x++;
        }
        return songNames;
    }


    public Song getSong(String newSong) {
        Node<Song> currentNode = head;
        Song song = null;
        while (currentNode != null) {
            if (currentNode.getData().title().equals(newSong)) {
                song = currentNode.getData();
            }
            currentNode = currentNode.getNext();
        }
        return song;
    }


    /**
     * Returns a string of all the songs and their data in the list
     */
    public String toString() {
        StringBuilder sB = new StringBuilder("{");
        Node<Song> currentNode = head;
        while (currentNode != null) {
            sB.append(currentNode.getData().toString());
            currentNode = currentNode.getNext();
        }
        sB.append("}");
        return sB.toString();
    }


    /**
     * Sort by genre
     */
    public void genreSort() {
        if (size() > 1) {
            assert head != null;
            Node<Song> unsortedPart = head.getNext();
            assert unsortedPart != null;
            head.setNext(null);
            while (unsortedPart != null) {
                Node<Song> nodeToInsert = unsortedPart;
                unsortedPart = unsortedPart.getNext();
                insertInOrderGenre(nodeToInsert);
            }
        }
    }


    /*
     * Sort by title
     */
    public void titleSort() {
        if (size() > 1) {
            assert head != null;
            Node<Song> unsortedPart = head.getNext();
            assert unsortedPart != null;
            head.setNext(null);
            while (unsortedPart != null) {
                Node<Song> nodeToInsert = unsortedPart;
                unsortedPart = unsortedPart.getNext();
                insertInOrderTitle(nodeToInsert);
            }
        }
    }


    /**
     * Sort by artist name
     */
    public void artistSort() {
        if (size() > 1) {
            assert head != null;
            Node<Song> unsortedPart = head.getNext();
            assert unsortedPart != null;
            head.setNext(null);
            while (unsortedPart != null) {
                Node<Song> nodeToInsert = unsortedPart;
                unsortedPart = unsortedPart.getNext();
                insertInOrderArtist(nodeToInsert);
            }
        }
    }


    /**
     * Sort by year
     */
    public void yearSort() {
        if (size() > 1) {
            assert head != null;
            Node<Song> unsortedPart = head.getNext();
            assert unsortedPart != null;
            head.setNext(null);
            while (unsortedPart != null) {
                Node<Song> nodeToInsert = unsortedPart;
                unsortedPart = unsortedPart.getNext();
                insertInOrderYear(nodeToInsert);
            }
        }
    }


    /**
     * Private helper method used to sort by genre
     * 
     * @param nodeToInsert
     *            the node to insert into the list, given by genreSort() method
     */
    private void insertInOrderGenre(Node<Song> nodeToInsert) {
        Song item = nodeToInsert.getData();
        Node<Song> currentNode = head;
        Node<Song> previousNode = null;
        while ((currentNode != null) && (item.genre().compareTo(currentNode
            .getData().genre()) > 0)) {
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }
        if (previousNode != null) {
            previousNode.setNext(nodeToInsert);
            nodeToInsert.setNext(currentNode);
        }
        else {
            nodeToInsert.setNext(head);
            head = nodeToInsert;
        }
    }


    /**
     * Private helper method used to sort by title
     * 
     * @param nodeToInsert
     *            the node to insert into the list, given by titleSort() method
     */
    private void insertInOrderTitle(Node<Song> nodeToInsert) {
        Song item = nodeToInsert.getData();
        Node<Song> currentNode = head;
        Node<Song> previousNode = null;
        while ((currentNode != null) && (item.title().compareTo(currentNode
            .getData().title()) > 0)) {
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }
        if (previousNode != null) {
            previousNode.setNext(nodeToInsert);
            nodeToInsert.setNext(currentNode);
        }
        else {
            nodeToInsert.setNext(head);
            head = nodeToInsert;
        }
    }


    /**
     * Private helper method used to sort by artist name
     * 
     * @param nodeToInsert
     *            the node to insert into the list, given by artistSort() method
     */
    private void insertInOrderArtist(Node<Song> nodeToInsert) {
        Song item = nodeToInsert.getData();
        Node<Song> currentNode = head;
        Node<Song> previousNode = null;
        while ((currentNode != null) && (item.artist().compareTo(currentNode
            .getData().artist()) > 0)) {
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }
        if (previousNode != null) {
            previousNode.setNext(nodeToInsert);
            nodeToInsert.setNext(currentNode);
        }
        else {
            nodeToInsert.setNext(head);
            head = nodeToInsert;
        }
    }


    /**
     * Private helper method used to sort by year
     * 
     * @param nodeToInsert
     *            the node to insert into the list, given by yearSort() method
     */
    private void insertInOrderYear(Node<Song> nodeToInsert) {
        Song item = nodeToInsert.getData();
        Node<Song> currentNode = head;
        Node<Song> previousNode = null;
        while ((currentNode != null) && (item.year().compareTo(currentNode
            .getData().year()) > 0)) {
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }
        if (previousNode != null) {
            previousNode.setNext(nodeToInsert);
            nodeToInsert.setNext(currentNode);
        }
        else {
            nodeToInsert.setNext(head);
            head = nodeToInsert;
        }
    }
}

package prj5;

import java.util.Scanner;

public class DoublyLinkedList<Song> {

    private class Node<Song> {

        private Song data;
        private Node<Song> prev;
        private Node<Song> next;


        public Node() {
            data = null;
            prev = null;
            next = null;
        }


        public Node(Song newEntry) {
            data = newEntry;
        }


        public Song getData() {
            return data;
        }


        public Node<Song> getNextNode() {
            return next;
        }


        public Node<Song> getPreviousNode() {
            return prev;
        }


        public void setNextNode(Node<Song> nextNode) {
            next = nextNode;
        }


        public void setPrevNode(Node<Song> prevNode) {
            prev = prevNode;
        }
    }

    private Node<Song> head;
    private Node<Song> tail;

    private Scanner scannerSong;
    private Scanner scannerSurvey;

    private int size;


    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }


    public boolean isEmpty() {
        return size == 0;
    }


    public int getSize() {
        return size;
    }


    public void add(Song newEntry) {
        Node<Song> newNode = new Node<Song>(newEntry);
        if (isEmpty()) {
            head = newNode;
        }
        else {
            tail.setNextNode(newNode);
        }
        tail = newNode;
        size++;
    }


    public void remove() {
        head = head.getNextNode();
        size--;
    }


    public Song getNextData() {
        Node<Song> currentNode = head;
        Song result = currentNode.getData();
        currentNode = currentNode.getNextNode();
        return result;
    }


    public String toString() {
        StringBuilder sB = new StringBuilder("{");
        Node<Song> currentNode = head;
        while (currentNode != null) {
            sB.append(currentNode.getData().toString());
            currentNode = currentNode.getNextNode();
        }
        sB.append("}");
        return sB.toString();
    }

}

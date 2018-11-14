package prj5;

import java.util.Scanner;

public class DoublyLinkedList<T> {

    private class Node<T> {

        private T data;
        private Node<T> prev;
        private Node<T> next;


        public Node() {
            data = null;
            prev = null;
            next = null;
        }


        public Node(T newEntry) {
            data = newEntry;
        }


        public T getData() {
            return data;
        }


        public Node<T> getNextNode() {
            return next;
        }


        public Node<T> getPreviousNode() {
            return prev;
        }


        public void setNextNode(Node<T> nextNode) {
            next = nextNode;
        }


        public void setPrevNode(Node<T> prevNode) {
            prev = prevNode;
        }
    }

    private Node<T> head;
    private Node<T> tail;

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


    public void add(T newEntry) {
        Node<T> newNode = new Node<T>(newEntry);
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


    public T getNextData() {
        Node<T> currentNode = head;
        T result = currentNode.getData();
        currentNode = currentNode.getNextNode();
        return result;
    }


    public String toString() {
        StringBuilder sB = new StringBuilder("{");
        Node<T> currentNode = head;
        while (currentNode != null) {
            sB.append(currentNode.getData().toString());
            currentNode = currentNode.getNextNode();
        }
        sB.append("}");
        return sB.toString();
    }

}

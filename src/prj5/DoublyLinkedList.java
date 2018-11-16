package prj5;

/**
 * DoublyLinkedList implementation for students
 * 
 * @author Ishaan Singh (ishaan15)
 * @version 2018.11.15
 *
 */
public class DoublyLinkedList {

    private Node<Student> head;
    private Node<Student> tail;
    private int size;
    private String[] songNames;


    /**
     * Inner doubly linked node class
     * 
     * @author Ishaan Singh (ishaan15)
     * @version 2018.11.15
     *
     * @param <Student>
     *            the data type to be used
     */
    private class Node<Student> {

        private Student student;
        private Node<Student> next;
        private Node<Student> prev;


        public Node(Student studentIn) {
            student = studentIn;
            next = null;
            prev = null;
        }


        public void setNext(Node<Student> nextNode) {
            next = nextNode;
        }


        public void setPrev(Node<Student> prevNode) {
            prev = prevNode;
        }


        public Node<Student> getNext() {
            return next;
        }


        public Node<Student> getPrev() {
            return prev;
        }


        public Student getData() {
            return student;
        }
    }


    /**
     * Default constructor
     */
    public DoublyLinkedList() {
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
     * @param newStudent
     *            the song to be added
     */
    public void add(Student newStudent) {
        Node<Student> newNode = new Node<Student>(newStudent);
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
        Node<Student> firstNode = head;
        head = head.getNext();
        firstNode.setNext(null);
        size--;
    }


    /**
     * Returns a string of all the songs and their data in the list
     */
    public String toString() {
        StringBuilder sB = new StringBuilder("{");
        Node<Student> currentNode = head;
        while (currentNode != null) {
            sB.append(currentNode.getData().toString());
            currentNode = currentNode.getNext();
        }
        sB.append("}");
        return sB.toString();
    }


    /**
     * Outputs all the song names in the DLL
     */
    public Student[] studentArray() {
        Node<Student> curr = head;
        int x = 0;
        Student[] students = new Student[size];
        while (curr != null) {
            students[x] = curr.getData();
            curr = curr.getNext();
            x++;
        }
        return students;
    }

}

package prj5;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This provides implementation for some of the LList methods.
 *
 * @author Mark Wiggans (mmw125)
 * @version 3/29/15
 * @author Eric Williamson
 * @version 10/30/15
 * @author maellis1
 * @version 11/1/15
 * @param <Song>
 *            The type of object the class will store
 */
public class SortMethods<Song> {

    /**
     * This represents a node in a doubly linked list. This node stores data, a
     * pointer to the node before it in the list, and a pointer to the node
     * after it in the list
     *
     * @param <Song>
     *            This is the type of object that this class will store
     * @author Mark Wiggans (mmw125)
     * @version 4/14/2015
     */
    private static class Node<Song> {
        private Node<Song> next;
        private Node<Song> previous;
        private Song data;


        /**
         * Creates a new node with the given data
         *
         * @param d
         *            the data to put inside the node
         */
        public Node(Song d) {
            data = d;
        }


        /**
         * Sets the node after this node
         *
         * @param n
         *            the node after this one
         */
        public void setNext(Node<Song> n) {
            next = n;
        }


        /**
         * Sets the node before this one
         *
         * @param n
         *            the node before this one
         */
        public void setPrevious(Node<Song> n) {
            previous = n;
        }


        /**
         * Gets the next node
         *
         * @return the next node
         */
        public Node<Song> next() {
            return next;
        }


        /**
         * Gets the node before this one
         *
         * @return the node before this one
         */
        public Node<Song> previous() {
            return previous;
        }


        /**
         * Gets the data in the node
         *
         * @return the data in the node
         */
        public Song getData() {
            return data;
        }


        public String title() {
            // TODO Auto-generated method stub
            return "title";
        }


        public String name() {
            // TODO Auto-generated method stub
            return "name";
        }


        public String genre() {
            // TODO Auto-generated method stub
            return "genre";
        }


        public String year() {
            // TODO Auto-generated method stub
            return "year";
        }
    }

    /**
     * How many nodes are in the list
     */
    private int size;

    /**
     * The first node in the list. THIS IS A SENTINEL NODE AND AS SUCH DOES NOT
     * HOLD ANY DATA. REFER TO init()
     */
    private Node<Song> head;

    /**
     * The last node in the list. THIS IS A SENTINEL NODE AND AS SUCH DOES NOT
     * HOLD ANY DATA. REFER TO init()
     */
    private Node<Song> tail;


    /**
     * Create a new DLList object.
     */
    public SortMethods() {
        init();
    }


    /**
     * Initializes the object to have the head and tail nodes
     */
    private void init() {
        head = new SortMethods.Node<Song>(null);
        tail = new SortMethods.Node<Song>(null);
        head.setNext(tail);
        tail.setPrevious(head);
        size = 0;
    }


    /**
     * Checks if the array is empty
     *
     * @return true if the array is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * Gets the number of elements in the list
     *
     * @return the number of elements
     */
    public int size() {
        return size;
    }


    /**
     * Removes all of the elements from the list
     */
    public void clear() {
        init();
    }


    /**
     * Checks if the list contains the given object
     *
     * @param obj
     *            the object to check for
     * @return true if it contains the object
     */
    public boolean contains(Song obj) {
        return lastIndexOf(obj) != -1;
    }


    /**
     * Gets the object at the given position
     *
     * @param index
     *            where the object is located
     * @return The object at the given position
     * @throws IndexOutOfBoundsException
     *             if there no node at the given index
     */
    public Song get(int index) {
        return getNodeAtIndex(index).getData();
    }


    /**
     * Adds a element to the end of the list
     *
     * @param newEntry
     *            the element to add to the end
     */
    public void add(Song newEntry) {
        add(size(), newEntry);
    }


    /**
     * Adds the object to the position in the list
     *
     * @param index
     *            where to add the object
     * @param obj
     *            the object to add
     * @throws IndexOutOfBoundsException
     *             if index is less than zero or greater than size
     * @throws IllegalArgumentException
     *             if obj is null
     */
    public void add(int index, Song obj) {
        if (index < 0 || size < index) {
            throw new IndexOutOfBoundsException();
        }
        if (obj == null) {
            throw new IllegalArgumentException("Cannot add null "
                + "objects to a list");
        }

        Node<Song> nodeAfter;
        if (index == size) {
            nodeAfter = tail;
        }
        else {
            nodeAfter = getNodeAtIndex(index);
        }

        Node<Song> addition = new Node<Song>(obj);
        addition.setPrevious(nodeAfter.previous());
        addition.setNext(nodeAfter);
        nodeAfter.previous().setNext(addition);
        nodeAfter.setPrevious(addition);
        size++;

    }


    /**
     * gets the node at that index
     * 
     * @param index
     * @return node at index
     */
    private Node<Song> getNodeAtIndex(int index) {
        if (index < 0 || size() <= index) {
            throw new IndexOutOfBoundsException("No element exists at "
                + index);
        }
        Node<Song> current = head.next(); // as we have a sentinel node
        for (int i = 0; i < index; i++) {
            current = current.next();
        }
        return current;
    }


    /**
     * Gets the last time the given object is in the list
     *
     * @param obj
     *            the object to look for
     * @return the last position of it. -1 If it is not in the list
     */
    public int lastIndexOf(Song obj) {
        /*
         * We should go from the end of the list as then we an stop once we find
         * the first one
         */
        Node<Song> current = tail.previous();
        for (int i = size() - 1; i >= 0; i--) {
            if (current.getData().equals(obj)) {
                return i;
            }
            current = current.previous();
        }
        return -1; // if we do not find it
    }


    /**
     * Removes the element at the specified index from the list
     *
     * @param index
     *            where the object is located
     * @throws IndexOutOfBoundsException
     *             if there is not an element at the index
     * @return true if successful
     */
    public boolean remove(int index) {
        Node<Song> nodeToBeRemoved = getNodeAtIndex(index);
        nodeToBeRemoved.previous().setNext(nodeToBeRemoved.next());
        nodeToBeRemoved.next().setPrevious(nodeToBeRemoved.previous());
        size--;
        return true;
    }


    /**
     * Removes the first object in the list that .equals(obj)
     *
     * @param obj
     *            the object to remove
     * @return true if the object was found and removed
     */

    public boolean remove(Song obj) {
        Node<Song> current = head.next();
        while (!current.equals(tail)) {
            if (current.getData().equals(obj)) {
                current.previous().setNext(current.next());
                current.next().setPrevious(current.previous());
                size--;
                return true;
            }
            current = current.next();
        }
        return false;
    }


    /**
     * Returns a string representation of the list If a list contains A, B, and
     * C, the following should be returned "{A, B, C}" (Without the quotations)
     *
     * @return a string representing the list
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        if (!isEmpty()) {
            Node<Song> currNode = head.next();
            while (currNode != tail) {
                Song element = currNode.getData();
                builder.append(element.toString());
                if (currNode.next != tail) {
                    builder.append(", ");
                }
                currNode = currNode.next();
            }
        }

        builder.append("}");
        return builder.toString();
    }


    public void sortByTitle() {
        Node<Song> firstNode = head.next();
        if (size() > 1) {
            assert firstNode != null;
            Node<Song> unsorted = firstNode.next;
            assert unsorted != null;
            firstNode.setNext(null);
            while (unsorted != null) {
                Node<Song> nodeToInsert = unsorted;
                unsorted = unsorted.next;
                String title = nodeToInsert.title();
                Node<Song> currentNode = firstNode;
                Node<Song> previousNode = null;
                while (currentNode != null && title.compareToIgnoreCase(currentNode.title())>0) {
                    previousNode = currentNode;
                    currentNode = currentNode.next;
                }
                if (previousNode != null) {
                    previousNode.setNext(nodeToInsert);
                    nodeToInsert.setPrevious(previousNode);
                    nodeToInsert.setNext(currentNode);
                    currentNode.setPrevious(nodeToInsert);
                }
                else {
                    nodeToInsert.setNext(firstNode);
                    nodeToInsert.setPrevious(head);
                    firstNode.setPrevious(nodeToInsert);
                }
            }
        }
    }
    
    public void sortByName() {
        Node firstNode = head.next;
        if (size() > 1) {
            assert firstNode != null;
            Node unsorted = firstNode.next;
            assert unsorted != null;
            firstNode.setNext(null);
            while (unsorted != null) {
                Node nodeToInsert = unsorted;
                unsorted = unsorted.next;
                String name = nodeToInsert.name();
                Node currentNode = firstNode;
                Node previousNode = null;
                while (currentNode != null && name.compareToIgnoreCase(currentNode.name())>0) {
                    previousNode = currentNode;
                    currentNode = currentNode.next;
                }
                if (previousNode != null) {
                    previousNode.setNext(nodeToInsert);
                    nodeToInsert.setPrevious(previousNode);
                    nodeToInsert.setNext(currentNode);
                    currentNode.setPrevious(nodeToInsert);
                }
                else {
                    nodeToInsert.setNext(firstNode);
                    nodeToInsert.setPrevious(head);
                    firstNode.setPrevious(nodeToInsert);
                }
            }
        }
    }
    
    public void sortByGenre() {
        Node firstNode = head.next;
        if (size() > 1) {
            assert firstNode != null;
            Node unsorted = firstNode.next;
            assert unsorted != null;
            firstNode.setNext(null);
            while (unsorted != null) {
                Node nodeToInsert = unsorted;
                unsorted = unsorted.next;
                String genre = nodeToInsert.genre();
                Node currentNode = firstNode;
                Node previousNode = null;
                while (currentNode != null && genre.compareToIgnoreCase(currentNode.genre())>0) {
                    previousNode = currentNode;
                    currentNode = currentNode.next;
                }
                if (previousNode != null) {
                    previousNode.setNext(nodeToInsert);
                    nodeToInsert.setPrevious(previousNode);
                    nodeToInsert.setNext(currentNode);
                    currentNode.setPrevious(nodeToInsert);
                }
                else {
                    nodeToInsert.setNext(firstNode);
                    nodeToInsert.setPrevious(head);
                    firstNode.setPrevious(nodeToInsert);
                }
            }
        }
    }
    
    public void sortByYear() {
        Node firstNode = head.next;
        if (size() > 1) {
            assert firstNode != null;
            Node unsorted = firstNode.next;
            assert unsorted != null;
            firstNode.setNext(null);
            while (unsorted != null) {
                Node nodeToInsert = unsorted;
                unsorted = unsorted.next;
                int year = Integer.parseInt(nodeToInsert.year());
                Node currentNode = firstNode;
                Node previousNode = null;
                int yearCurrent = Integer.parseInt(currentNode.year());
                while (currentNode != null && year>yearCurrent) {
                    previousNode = currentNode;
                    currentNode = currentNode.next;
                }
                if (previousNode != null) {
                    previousNode.setNext(nodeToInsert);
                    nodeToInsert.setPrevious(previousNode);
                    nodeToInsert.setNext(currentNode);
                    currentNode.setPrevious(nodeToInsert);
                }
                else {
                    nodeToInsert.setNext(firstNode);
                    nodeToInsert.setPrevious(head);
                    firstNode.setPrevious(nodeToInsert);
                }
            }
        }
    }


    /**
     * Iterator method creates Iterator object
     *
     * @return new Iterator object
     */
    public Iterator<Song> iterator() {
        return new DLListIterator<Song>();
    }


    /**
     * Iterator method creates ReverseIterator object
     *
     * @return new Iterator object
     */
    public Iterator<Song> reverseIterator() {
        return new RDLListIterator<Song>();
    }


    private class RDLListIterator<A> implements Iterator<Song> {
        private Node nextNode;
        private boolean nextHasBeenCalled;


        /**
         * Creates a new DLListIterator
         */
        public RDLListIterator() {
            nextNode = tail;
            nextHasBeenCalled = false;
        }


        /**
         * Checks if there are more elements in the list
         *
         * @return true if there are more elements in the list
         */
        @Override
        public boolean hasNext() {
            return nextNode.previous.getData() != null;
        }


        /**
         * Gets the next value in the list
         *
         * @return the next value
         * @throws NoSuchElementException
         *             if there are no nodes left in the list
         */
        @Override
        public Song next() {
            if (hasNext()) {
                nextNode = nextNode.previous;
                nextHasBeenCalled = true;
                return (Song)nextNode.getData();
            }
            else {
                throw new NoSuchElementException(
                    "There are no more nodes left in the list");
            }
        }


        /**
         * Removes the last object returned with next() from the list
         *
         * @throws IllegalStateException
         *             if next has not been called yet
         *             and if the element has already been removed
         */
        @Override
        public void remove() {
            if (!nextHasBeenCalled || nextNode.previous() == null) {
                throw new IllegalStateException(
                    "The next() method has not been called yet");
            }
            else {
                nextNode.next.setPrevious(nextNode.previous());
                nextNode.previous.setNext(nextNode.next);
                nextNode.setNext(null);
                nextNode.setPrevious(null);
                size--;
            }
        }

    }


    private class DLListIterator<A> implements Iterator<Song> {

        private Node nextNode;
        private boolean nextHasBeenCalled;


        /**
         * Creates a new DLListIterator
         */
        public DLListIterator() {
            nextNode = head;
            nextHasBeenCalled = false;
        }


        /**
         * Checks if there are more elements in the list
         *
         * @return true if there are more elements in the list
         */
        @Override
        public boolean hasNext() {
            return nextNode.next.getData() != null;
        }


        /**
         * Gets the next value in the list
         *
         * @return the next value
         * @throws NoSuchElementException
         *             if there are no nodes left in the list
         */
        @Override
        public Song next() {
            if (hasNext()) {
                // Node returnNode = nextNode;
                nextNode = nextNode.next;
                nextHasBeenCalled = true;
                return (Song)nextNode.getData();
            }
            else {
                throw new NoSuchElementException(
                    "There are no more nodes left in the list");
            }
        }


        /**
         * Removes the last object returned with next() from the list
         *
         * @throws IllegalStateException
         *             if next has not been called yet
         *             and if the element has already been removed
         */
        @Override
        public void remove() {
            if (!nextHasBeenCalled || nextNode.next() == null) {
                throw new IllegalStateException(
                    "The next() method has not been called yet");
            }
            else {
                nextNode.next.setPrevious(nextNode.previous());
                nextNode.previous.setNext(nextNode.next);
                nextNode.setNext(null);
                nextNode.setPrevious(null);
                size--;
            }
        }
    }
}

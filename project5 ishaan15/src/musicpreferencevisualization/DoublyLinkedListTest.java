package musicpreferencevisualization;

public class DoublyLinkedListTest extends student.TestCase {

    private DoublyLinkedList<String> dLL;


    public void setUp() {
        dLL = new DoublyLinkedList<String>();
    }


    public void testSizeandIsEmpty() {
        assertTrue(dLL.isEmpty());
        assertEquals(0, dLL.getSize());
    }


    public void testAddAndRemove() {
        dLL.add("tupac");
        assertFalse(dLL.isEmpty());
        assertEquals(1, dLL.getSize());
        assertEquals("{tupac}", dLL.toString());
        dLL.remove();
        assertTrue(dLL.isEmpty());
        assertEquals(0, dLL.getSize());
    }


    public void testInsertionSort() {
        dLL.add("A");
        dLL.add("C");
        dLL.add("B");
        // dLL.insertionSort();
        assertEquals("{ABC}", dLL.toString());
    }

}

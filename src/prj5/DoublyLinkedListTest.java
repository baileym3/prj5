package prj5;

public class DoublyLinkedListTest extends student.TestCase {

    private DoublyLinkedList dLL;
    private Student first;
    private Student second;
    private Student third;


    public void setUp() {
        String[] songData = { "yes", "yes" };
        dLL = new DoublyLinkedList();
        first = new Student("1", "1/1/2018", "CS", "SouthEast", "Sports",
            songData);
        second = new Student("2", "1/1/2018", "Math", "SouthEast", "Music",
            songData);
        third = new Student("3", "1/1/2018", "CS", "NorthEast", "Sports",
            songData);
    }


    public void testHobbySort() {

    }

}

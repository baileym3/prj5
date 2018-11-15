package prj5;

/**
 * Tests methods in DoublyLinkedList1 class
 * 
 * @author Ishaan Singh (ishaan15)
 * @version 2018.11.15
 */
public class DoublyLinkedList1Test extends student.TestCase {

    private DoublyLinkedList1 dLL;
    private Song first;
    private Song second;
    private Song third;


    /**
     * Setup method to be run before every test
     */
    public void setUp() {
        dLL = new DoublyLinkedList1();
        first = new Song("When I was your man", "Bruno Mars", "2014", "Pop");
        second = new Song("Be with me", "Old Dominion", "2016", "Country");
        third = new Song("Arms around you", "XXXTentaction", "2018", "Rap");

    }


    /**
     * Tests add, remove, getSize, and isEmpty
     */
    public void testAddandRemove() {
        dLL.add(first);
        assertEquals(1, dLL.size());
        assertFalse(dLL.isEmpty());
        dLL.remove();
        assertEquals(0, dLL.size());
        assertTrue(dLL.isEmpty());
    }


    /**
     * Tests genreSort()
     * Should be: Be with me, when i was your man, arms around you
     */
    public void testGenreSort() {
        dLL.add(first);
        dLL.add(third);
        dLL.add(second);
        dLL.genreSort();
        assertEquals(
            "{{Be with me, Old Dominion, 2016, Country, } {When I was your man, Bruno Mars, 2014, Pop, } {Arms around you, XXXTentaction, 2018, Rap, } }",
            dLL.toString());
    }


    /**
     * Tests titleSort()
     * Should be: arms around you, be with me, when I was your man
     */
    public void testTitleSort() {
        dLL.add(first);
        dLL.add(second);
        dLL.add(third);
        dLL.titleSort();
        assertEquals(
            "{{Arms around you, XXXTentaction, 2018, Rap, } {Be with me, Old Dominion, 2016, Country, } {When I was your man, Bruno Mars, 2014, Pop, } }",
            dLL.toString());
    }


    /**
     * Tests artistSort()
     * Should be: When I was your man, Be with me
     */
    public void testArtistSort() {
        dLL.add(first);
        dLL.add(second);
        dLL.artistSort();
        assertEquals(
            "{{When I was your man, Bruno Mars, 2014, Pop, } {Be with me, Old Dominion, 2016, Country, } }",
            dLL.toString());
    }


    /**
     * Tests yearSort()
     * Should be: When I was your man, be with me, arms around you
     */
    public void testYearSort() {
        dLL.add(third);
        dLL.add(second);
        dLL.add(first);
        dLL.yearSort();
        assertEquals(
            "{{When I was your man, Bruno Mars, 2014, Pop, } {Be with me, Old Dominion, 2016, Country, } {Arms around you, XXXTentaction, 2018, Rap, } }",
            dLL.toString());
    }
}

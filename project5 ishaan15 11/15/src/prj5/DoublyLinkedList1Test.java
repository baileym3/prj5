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


    public void testSongNames() {
        dLL.add(first);
        dLL.add(second);
        dLL.add(third);
        String[] songNamesIn = dLL.songNames();
        String songNames = "";
        for (int i = 0; i < songNamesIn.length; i++) {
            songNames += songNamesIn[i];
            if (i < songNamesIn.length - 1) {
                songNames += ", ";
            }
        }
        assertEquals("When I was your man, Be with me, Arms around you",
            songNames);
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
        assertEquals("{Song Title: Be with me\r\n"
            + "Song Artist:Old Dominion\r\n" + "Song Genre: Country\r\n"
            + "Song Year: 2016\r\n" + "Heard\r\n" + "0\r\n"
            + "Likes0Song Title: When I was your man\r\n"
            + "Song Artist:Bruno Mars\r\n" + "Song Genre: Pop\r\n"
            + "Song Year: 2014\r\n" + "Heard\r\n" + "0\r\n"
            + "Likes0Song Title: Arms around you\r\n"
            + "Song Artist:XXXTentaction\r\n" + "Song Genre: Rap\r\n"
            + "Song Year: 2018\r\n" + "Heard\r\n" + "0\r\n" + "Likes0}", dLL
                .toString());

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
        assertEquals("{Song Title: Arms around you\r\n"
            + "Song Artist:XXXTentaction\r\n" + "Song Genre: Rap\r\n"
            + "Song Year: 2018\r\n" + "Heard\r\n" + "0\r\n"
            + "Likes0Song Title: Be with me\r\n"
            + "Song Artist:Old Dominion\r\n" + "Song Genre: Country\r\n"
            + "Song Year: 2016\r\n" + "Heard\r\n" + "0\r\n"
            + "Likes0Song Title: When I was your man\r\n"
            + "Song Artist:Bruno Mars\r\n" + "Song Genre: Pop\r\n"
            + "Song Year: 2014\r\n" + "Heard\r\n" + "0\r\n" + "Likes0}", dLL
                .toString());
    }


    /**
     * Tests artistSort()
     * Should be: When I was your man, Be with me
     */
    public void testArtistSort() {
        dLL.add(first);
        dLL.add(second);
        dLL.artistSort();
        assertEquals("{Song Title: When I was your man\r\n"
            + "Song Artist:Bruno Mars\r\n" + "Song Genre: Pop\r\n"
            + "Song Year: 2014\r\n" + "Heard\r\n" + "0\r\n"
            + "Likes0Song Title: Be with me\r\n"
            + "Song Artist:Old Dominion\r\n" + "Song Genre: Country\r\n"
            + "Song Year: 2016\r\n" + "Heard\r\n" + "0\r\n" + "Likes0}", dLL
                .toString());
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
        assertEquals("{Song Title: When I was your man\r\n"
            + "Song Artist:Bruno Mars\r\n" + "Song Genre: Pop\r\n"
            + "Song Year: 2014\r\n" + "Heard\r\n" + "0\r\n"
            + "Likes0Song Title: Be with me\r\n"
            + "Song Artist:Old Dominion\r\n" + "Song Genre: Country\r\n"
            + "Song Year: 2016\r\n" + "Heard\r\n" + "0\r\n"
            + "Likes0Song Title: Arms around you\r\n"
            + "Song Artist:XXXTentaction\r\n" + "Song Genre: Rap\r\n"
            + "Song Year: 2018\r\n" + "Heard\r\n" + "0\r\n" + "Likes0}", dLL
                .toString());
    }
}

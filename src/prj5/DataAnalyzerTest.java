package prj5;

public class DataAnalyzerTest extends student.TestCase {

    private Student stud;
    private String[] songNames;
    private DataAnalyzer dA;


    public void setUp() {
        String[] songData = { "yes", "yes", "yes", "no" };
        stud = new Student("1", "1/1/2018", "Math or CMDA", "SouthEast",
            "Sports", songData);
        String[] songNames = { "first song", "second song" };
        dA = new DataAnalyzer(stud, songNames);
    }


    public void testGetLikes() {
        assertTrue(dA.likes("first song"));
        assertFalse(dA.likes("second song"));
    }


    public void testHeard() {
        assertTrue(dA.heard("first song"));
        assertTrue(dA.heard("second song"));
    }
}

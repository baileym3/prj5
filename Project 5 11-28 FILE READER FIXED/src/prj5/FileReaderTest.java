package prj5;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Tests methods in File Reader using test1 csv files
 * 
 * @author Ishaan Singh (ishaan15)
 * @version 2018.11.28
 */
public class FileReaderTest extends student.TestCase {

    private FileReader fR;


    /**
     * Setup method to be ran before every test method
     */
    public void setUp() throws FileNotFoundException {
        fR = new FileReader(new File("MusicSurveyDataTest1.csv"), new File(
            "SongListTest1.csv"));
        fR.readSongs();
        fR.readSurvey();
    }


    /**
     * Tests getLikesHobby() which returns a string:
     * Read: Art: Music: Sports:
     */
    public void testGetLikesHobby() {
        assertEquals("Read: 0 Art: 0 Music: 1 Sports: 2", fR.getLikesHobby(
            "All These Things I've Done"));
    }


    /**
     * Tests getHeardHobby() which returns a string:
     * Read: Art: Music: Sports:
     */
    public void testGetHeardHobby() {
        assertEquals("Read: 0 Art: 0 Music: 0 Sports: 2", fR.getHeardHobby(
            "All These Things I've Done"));
    }


    /**
     * Tests getHeardHobbyNumber() which returns an int
     */
    public void testGetHeardHobbyNumber() {
        assertEquals(2, fR.getHeardHobbyNumber("All These Things I've Done",
            "sports"));
        assertEquals(0, fR.getHeardHobbyNumber("All These Things I've Done",
            "art"));
        assertEquals(0, fR.getHeardHobbyNumber("All These Things I've Done",
            "coding"));
    }


    /**
     * Tests getHeardMajorNumber() which returns an int
     */
    public void testGetHeardMajorNumber() {
        assertEquals(1, fR.getHeardMajorNumber("All These Things I've Done",
            "Computer Science"));
        assertEquals(1, fR.getHeardMajorNumber("All These Things I've Done",
            "Math or CMDA"));
    }


    /**
     * Tests getHeardRegionNumber() which returns an int
     */
    public void testGetHeardRegionNumber() {
        assertEquals(2, fR.getHeardRegionNumber("All These Things I've Done",
            "southeast"));
        assertEquals(0, fR.getHeardRegionNumber("All These Things I've Done",
            "northeast"));
    }


    /**
     * Tests getLikesMajorNumber() which returns an int
     */
    public void testGetLikesMajorNumber() {
        assertEquals(2, fR.getLikesMajorNumber("All These Things I've Done",
            "computer science"));
    }


    /**
     * Tests getTotalHobby() which returns an int
     */
    public void testGetTotalHobby() {
        assertEquals(4, fR.getTotalHobby("sports"));
        assertEquals(1, fR.getTotalHobby("reading"));
        assertEquals(1, fR.getTotalHobby("music"));
        assertEquals(0, fR.getTotalHobby("art"));
    }


    /**
     * Tests getTotalMajor() which returns an int
     */
    public void testGetTotalMajor() {
        assertEquals(3, fR.getTotalMajor("math or cMDa"));
        assertEquals(3, fR.getTotalMajor("comPUter sCience"));
        assertEquals(0, fR.getTotalMajor("other engineering"));
        assertEquals(0, fR.getTotalMajor("other"));
    }


    /**
     * Tests getTotalRegion() which returns an int
     */
    public void testGetTotalRegion() {
        assertEquals(6, fR.getTotalRegion("southEasT"));
        assertEquals(0, fR.getTotalRegion("northeAst"));
        assertEquals(0, fR.getTotalRegion("outside of united states"));
        assertEquals(0, fR.getTotalRegion(
            "united states (other than southeast or northeast)"));
    }
}

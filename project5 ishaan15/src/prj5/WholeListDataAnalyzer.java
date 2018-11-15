package prj5;

/**
 * Uses survey scanner to analyze data and output the likes and listens for
 * every song
 * 
 * @author Ishaan Singh
 * @version 2018.11.15
 */
public class WholeListDataAnalyzer extends DoublyLinkedList<Student> {

    private DataAnalyzer dA;
    private DoublyLinkedList<Student> dLL;
    private SurveyScanner input;
    private String[] songNames;


    /**
     * Defaul constructor that takes in songFileName and surveyFileName
     * 
     * @param songFileName
     *            the file name of the song data file
     * @param surveyFileName
     *            the file name of the survey data file
     */
    public WholeListDataAnalyzer(String songFileName, String surveyFileName) {
        input = new SurveyScanner(surveyFileName, songFileName);
        songNames = input.getSongNames();
        dLL = input.getDLL();
    }


    /**
     * Returns the number of likes for a song
     * 
     * @param songName
     *            the song for which to return the number of likes
     * @return the number of likes for song songName
     */
    public int getLikes(String songName) {
        int likes = 0;
        for (int i = 0; i < dLL.getSize(); i++) {
            Student newStud = dLL.getNextData();
            dA = new DataAnalyzer(newStud, songNames);
            if (dA.likes(songName)) {
                likes++;
            }
        }
        return likes;
    }


    /**
     * Returns the number of students who have heard this song
     * 
     * @param songName
     *            the song for which to return the number of students who have
     *            heard this song
     * @return the number of listens for a song songName
     */
    public int getHeard(String songName) {
        int heard = 0;
        for (int i = 0; i < dLL.getSize(); i++) {
            Student newStud = dLL.getNextData();
            dA = new DataAnalyzer(newStud, songNames);
            if (dA.heard(songName)) {
                heard++;
            }
        }

        return heard;
    }
}

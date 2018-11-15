package prj5;

import java.util.Observable;

public class WholeListDataAnalyzer extends Observable {

    private DataAnalyzer dA;
    private DoublyLinkedList<Student> dLL;
    private SurveyScanner input;
    private String[] songNames;


    public WholeListDataAnalyzer(String songFileName, String surveyFileName) {
        input = new SurveyScanner(surveyFileName, songFileName);
        songNames = input.getSongNames();
        dLL = input.getDLL();
    }
    
    public String[] getSongs() {
        return songNames;
    }

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

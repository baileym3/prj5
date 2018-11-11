package musicpreferencevisualization;

public class WholeListDataAnalyzer extends DoublyLinkedList<Student> {

    private DataAnalyzer dA;
    private DoublyLinkedList<Student> dLL;
    private Input input;
    private String[] songNames;


    public WholeListDataAnalyzer(String songFileName, String surveyFileName) {
        input = new Input(surveyFileName, songFileName);
        songNames = input.getSongNames();
        dLL = input.getDLL();
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

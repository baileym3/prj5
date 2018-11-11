package musicpreferencevisualization;

import java.util.Scanner;

public class Input {

    private String songFileName;
    private String surveyFileName;
    private String[] songData;
    private String[] songNames;
    private DoublyLinkedList<Student> dLL;


    public Input(String surveyFile, String songFile) {
        dLL = new DoublyLinkedList<Student>();
        songFileName = songFile;
        surveyFileName = surveyFile;
    }


    public void scanSong() {
        Scanner songScanner = new Scanner(songFileName);
        int x = 0;
        while (songScanner.hasNextLine()) {
            while (songScanner.hasNext()) {
                String songName = songScanner.next();
                songNames[x] = songName;
                x++;
                String artist = songScanner.next();
                String year = songScanner.next();
                String genre = songScanner.next();
                Song newSong = new Song(songName, artist, year, genre);
            }
        }
        songScanner.close();
    }


    public void scanSurvey() {
        Scanner surveyScanner = new Scanner(surveyFileName);
        int i = 0;
        while (surveyScanner.hasNextLine()) {
            while (surveyScanner.hasNext()) {
                String studNum = surveyScanner.next();
                String date = surveyScanner.next();
                String major = surveyScanner.next();
                String region = surveyScanner.next();
                String hobby = surveyScanner.next();
                while (surveyScanner.hasNext()) {
                    songData[i] = surveyScanner.next();
                    i++;
                }
                Student newStudent = new Student(studNum, date, major, region,
                    hobby, songData);
                dLL.add(newStudent);
            }
        }
        surveyScanner.close();
    }


    public String[] getSongNames() {
        return songNames;
    }


    public DoublyLinkedList<Student> getDLL() {
        return dLL;
    }
}

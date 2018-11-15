package prj5;

import java.util.Scanner;

/**
 * Scans survey and song data using a dLL to store the student data
 * 
 * @author Ishaan Singh (ishaan15)
 * @version 2018.11.15
 *
 */
public class SurveyScanner {

    private String songFileName;
    private String surveyFileName;
    private String[] songData;
    private String[] songNames;
    private DoublyLinkedList<Student> dLL;


    /**
     * Default constructor, takes a survey file and song file
     * 
     * @param surveyFile
     *            the name of the survey file
     * @param songFile
     *            the name of the song data file
     */
    public SurveyScanner(String surveyFile, String songFile) {
        dLL = new DoublyLinkedList<Student>();
        songFileName = songFile;
        surveyFileName = surveyFile;
    }


    /**
     * Scans all of the songs from the song data file into new songs and into a
     * songNames array
     */
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


    /**
     * Scans all of the survey data and uses it to make new students as well as
     * creating a song data array for every student
     */
    public void scanSurvey() {
        Scanner surveyScanner = new Scanner(surveyFileName);
        int i = 0;
        while (surveyScanner.hasNextLine()) {
            while (surveyScanner.hasNext()) {
                String pid = surveyScanner.next();
                String date = surveyScanner.next();
                String major = surveyScanner.next();
                String region = surveyScanner.next();
                String hobby = surveyScanner.next();
                while (surveyScanner.hasNext()) {
                    songData[i] = surveyScanner.next();
                    i++;
                }
                Student newStudent = new Student(pid, date, major, region,
                    hobby, songData);
                dLL.add(newStudent);
            }
        }
        surveyScanner.close();
    }


    /**
     * Getter method for the songNames array
     * 
     * @return songNames as a string array
     */
    public String[] getSongNames() {
        return songNames;
    }


    /**
     * Returns doubly linked list of students
     * 
     * @return dLL<Student>
     */
    public DoublyLinkedList<Student> getDLL() {
        return dLL;
    }
}

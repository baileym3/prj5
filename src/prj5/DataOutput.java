package prj5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Class that calculates
 * 
 * @author Ishaan Singh
 * @version 11/15/2018
 */
public class DataOutput {

    private DoublyLinkedList1 songs;
    private DoublyLinkedList students;
    private String[] songNames;
    private Student[] studentsArray;
    private int totalResponses;
    private String surveyFile;
    private String songFile;


    public DataOutput(String surveyFileName, String songFileName) {
        songs = new DoublyLinkedList1();
        students = new DoublyLinkedList();
        songNames = new String[10000];//songs.songNames();
        studentsArray = students.studentArray();
        surveyFile =  surveyFileName;
        songFile = songFileName;
    }
    public DoublyLinkedList1 getSongs() {
        return songs;
    }

    /**
     * Scans all of the songs from the song data file into new songs and into
     * songs DLL
     * @throws IOException 
     */
    public void scanSong() {
        Scanner songScanner = null;
        Path source = Paths.get(songFile);
        try {
            songScanner = new Scanner(source);
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int x = 0;
        while (songScanner.hasNextLine()) {
            while (songScanner.hasNext()) {
                String songName = songScanner.next();
                System.out.println(songName);
                songNames[x] = songName;
                x++;
                String artist = songScanner.next();
                String year = songScanner.next();
                String genre = songScanner.next();
                Song newSong = new Song(songName, artist, year, genre);
                songs.add(newSong);
            }
        }
        songScanner.close();
    }


    /**
     * Scans all of the survey data and uses it to make new students and adds
     * them into students DLL
     */
    public void scanSurvey() {
        String[] songData = {};
        Scanner surveyScanner = null;
        try {
            surveyScanner = new Scanner(new FileInputStream(surveyFile));
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
                students.add(newStudent);
            }
        }
        surveyScanner.close();
    }


    public void addSong(Song newSong) {
        songs.add(newSong);
    }


    public void addStudent(Student newStud) {
        students.add(newStud);
    }


    public Student[] getLikes(Song newSong) {
        int k = 0;
        Student[] array = new Student[studentsArray.length];
        String title = newSong.title();
        int x = -1;
        for (int i = 0; i < songNames.length; i++) {
            if (songNames[i] == title) {
                x = i;
            }
        }
        if (x == -1) {
            return array;
        }
        else {
            for (int w = 0; w < studentsArray.length; w++) {
                Student newStud = studentsArray[w];
                String[] studLikes = newStud.getLikes();
                if (studLikes[x].equals("Yes")) {
                    array[k] = studentsArray[w];
                    k++;
                }
            }
        }
        return array;
    }


    public Student[] getHeard(Song newSong) {
        int k = 0;
        Student[] array = new Student[studentsArray.length];
        String title = newSong.title();
        int x = -1;
        for (int i = 0; i < songNames.length; i++) {
            if (songNames[i] == title) {
                x = i;
            }
        }
        if (x == -1) {
            return array;
        }
        else {
            for (int w = 0; w < studentsArray.length; w++) {
                String[] studLikes = studentsArray[w].getHeard();
                if (studLikes[x].equals("Yes")) {
                    array[k] = studentsArray[w];
                    k++;
                }
            }
        }
        return array;
    }


    public int totalResponses(Song newSong) {
        int k = 0;
        String title = newSong.title();
        int x = -1;
        for (int i = 0; i < songNames.length; i++) {
            if (songNames[i] == title) {
                x = i;
            }
        }
        if (x == -1) {
            return 0;
        }
        else {
            for (int w = 0; w < studentsArray.length; w++) {
                String[] studLikes = studentsArray[w].getHeard();
                String[] studHeard = studentsArray[w].getHeard();
                if (studLikes[x].equals("Yes") || studLikes[x].equals("No")
                    || studHeard[x].equals("Yes") || studHeard[x].equals(
                        "No")) {
                    totalResponses++;
                }
            }
        }
        return totalResponses;
    }


    public Student[] hobbyLikes(String hobby, Song song) {
        int k = 0;
        Student[] likes = getLikes(song);
        Student[] result = new Student[likes.length];
        for (int i = 0; i < likes.length; i++) {
            if (likes[i].getHobby().equals(hobby)) {
                result[k] = likes[i];
                k++;
            }
        }
        return result;
    }


    public Student[] hobbyHeard(String hobby, Song song) {
        int k = 0;
        Student[] heard = getHeard(song);
        Student[] result = new Student[heard.length];
        for (int i = 0; i < heard.length; i++) {
            if (heard[i].getHobby().equals(hobby)) {
                result[k] = heard[i];
                k++;
            }
        }
        return result;
    }


    public Student[] MajorLikes(String major, Song song) {
        int k = 0;
        Student[] likes = getLikes(song);
        Student[] result = new Student[likes.length];
        for (int i = 0; i < likes.length; i++) {
            if (likes[i].getMajor().equals(major)) {
                result[k] = likes[i];
                k++;
            }
        }
        return result;
    }


    public Student[] majorHeard(String major, Song song) {
        int k = 0;
        Student[] heard = getHeard(song);
        Student[] result = new Student[heard.length];
        for (int i = 0; i < heard.length; i++) {
            if (heard[i].getMajor().equals(major)) {
                result[k] = heard[i];
                k++;
            }
        }
        return result;
    }


    public Student[] regionLikes(String region, Song song) {
        int k = 0;
        Student[] likes = getLikes(song);
        Student[] result = new Student[likes.length];
        for (int i = 0; i < likes.length; i++) {
            if (likes[i].getRegion().equals(region)) {
                result[k] = likes[i];
                k++;
            }
        }
        return result;
    }


    public Student[] regionHeard(String region, Song song) {
        int k = 0;
        Student[] heard = getHeard(song);
        Student[] result = new Student[heard.length];
        for (int i = 0; i < heard.length; i++) {
            if (heard[i].getRegion().equals(region)) {
                result[k] = heard[i];
                k++;
            }
        }
        return result;
    }
}

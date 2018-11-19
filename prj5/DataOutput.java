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
    private File surveyFile;
    private File songFile;


    public DataOutput(File surveyFileName, File songFileName) {
        songs = new DoublyLinkedList1();
        students = new DoublyLinkedList();
        songNames = new String[1000];// songs.songNames();
        surveyFile = surveyFileName;
        songFile = songFileName;
    }


    public DoublyLinkedList1 getSongs() {
        return songs;
    }


    /**
     * Scans all of the songs from the song data file into new songs and into
     * songs DLL
     * 
     * @throws FileNotFoundException
     * 
     * @throws IOException
     */
    public void scanSong() throws FileNotFoundException {
        Scanner songScanner = new Scanner(songFile);
        songScanner.nextLine();
        int x = 0;
        while (songScanner.hasNextLine()) {
            while (songScanner.hasNext()) {
                String[] songLine = new String[4];
                String song1 = songScanner.nextLine();
                songLine = song1.split(",");
                String songName = songLine[0];
                String artist = songLine[1];
                String year = songLine[2];
                String genre = songLine[3];
                songNames[x] = songName;
                x++;
                Song newSong = new Song(songName, artist, year, genre);
                songs.add(newSong);

                Student[] likes = getLikes(songName);
                int z = 0;
                int reading = 0;
                int music = 0;
                int sports = 0;
                int art = 0;
                while (likes[z] != null) {
                    if (likes[z].getHobby().equals("music")) {
                        music++;
                    }
                    else if (likes[z].getHobby().equals("sports")) {
                        sports++;
                    }
                    else if (likes[z].getHobby().equals("art")) {
                        art++;
                    }
                    else {
                        reading++;
                    }
                    z++;
                }

                StringBuilder sB = new StringBuilder();
                sB.append("reading" + reading);
                sB.append(" art" + art);
                sB.append(" sports" + sports);
                sB.append(" music" + music);
                newSong.setLikes(sB.toString());
            }
        }
        songScanner.close();

    }


    /**
     * Scans all of the survey data and uses it to make new students and adds
     * them into students DLL
     * 
     * @throws FileNotFoundException
     */
    public void scanSurvey() throws FileNotFoundException {
        String[] songData = new String[songNames.length];
        Scanner surveyScanner = new Scanner(surveyFile);
        surveyScanner.nextLine();
        while (surveyScanner.hasNextLine()) {
            while (surveyScanner.hasNext()) {
                String[] studentData = new String[songNames.length + 5];
                String studentDataLine = surveyScanner.nextLine();
                studentData = studentDataLine.split(",");
                if (studentData.length >= 5) {
                    String pid = studentData[0];
                    String date = studentData[1];
                    String major = studentData[2];
                    String region = studentData[3];
                    String hobby = studentData[4];
                    // System.out.println(pid);
                    int x = 5;
                    int i = 0;
                    while (x < studentData.length) {
                        songData[i] = studentData[x];
                        x++;
                        i++;
                    }
                    Student newStudent = new Student(pid, date, major, region,
                        hobby, songData);
                    students.add(newStudent);
                }
            }
        }
        surveyScanner.close();
        studentsArray = students.studentArray();
    }


    public void addSong(Song newSong) {
        songs.add(newSong);
    }


    public void addStudent(Student newStud) {
        students.add(newStud);
    }


    public Student[] getLikes(String newSong) {
        int k = 0;
        Student[] array = new Student[studentsArray.length];
        int x = -1;
        int i = 0;
        while (songNames[i] != null) {
            if (songNames[i].equals(newSong)) {
                x = i;
            }
            i++;
        }
        if (x == -1) {
            return array;
        }
        else {
            for (int w = 0; w < studentsArray.length; w++) {
                if (studentsArray[w] != null) {
                    String[] studLikes = studentsArray[w].getLikes();
                    if (studLikes[x].equals("Yes")) {
                        array[k] = studentsArray[w];
                        k++;
                    }
                    w++;
                }
            }

        }
        return array;
    }


    public Student[] getHeard(String newSong) {
        int k = 0;
        Student[] array = new Student[studentsArray.length];
        int x = -1;
        int i = 0;
        while (songNames[i] != null) {
            if (songNames[i].equals(newSong)) {
                x = i;
            }
            i++;
        }
        if (x == -1) {
            return array;
        }
        else {
            for (int w = 0; w < studentsArray.length; w++) {
                if (studentsArray[w] != null) {
                    String[] studLikes = studentsArray[w].getHeard();
                    if (studLikes[x].equals("Yes")) {
                        array[k] = studentsArray[w];
                        k++;
                    }
                    w++;
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


    public void hobbiesLikes() {
        int i = 0;
        while (songNames[i] != null) {
            int reading = 0;
            int music = 0;
            int sports = 0;
            int art = 0;
            Student[] likes = getLikes(songNames[i]);
            int x = 0;
            while (likes[x] != null) {
                if (likes[x].getHobby().equals("music")) {
                    music++;
                }
                else if (likes[x].getHobby().equals("sports")) {
                    sports++;
                }
                else if (likes[x].getHobby().equals("art")) {
                    art++;
                }
                else {
                    reading++;
                }
                x++;
            }

            StringBuilder sB = new StringBuilder();
            sB.append("reading" + reading);
            sB.append(" art" + art);
            sB.append(" sports" + sports);
            sB.append(" music" + music);
            songs.getSong(songNames[i]).setLikes(sB.toString());
            i++;
        }
    }


    public void hobbiesHeard() {
        int i = 0;
        while (songNames[i] != null) {
            int reading = 0;
            int music = 0;
            int sports = 0;
            int art = 0;
            Student[] heard = getHeard(songNames[i]);
            int x = 0;
            while (heard[x] != null) {
                if (heard[x].getHobby().equals("music")) {
                    music++;
                }
                else if (heard[x].getHobby().equals("sports")) {
                    sports++;
                }
                else if (heard[x].getHobby().equals("art")) {
                    art++;
                }
                else {
                    reading++;
                }
                x++;
            }

            StringBuilder sB = new StringBuilder();
            sB.append("reading" + reading);
            sB.append(" art" + art);
            sB.append(" sports" + sports);
            sB.append(" music" + music);
            songs.getSong(songNames[i]).setHeard(sB.toString());
            i++;
        }

    }


    public Student[] hobbyLikes(String hobby, String song) {
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


    public Student[] hobbyHeard(String hobby, String song) {
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


    public Student[] MajorLikes(String major, String song) {
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


    public Student[] majorHeard(String major, String song) {
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


    public Student[] regionLikes(String region, String song) {
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


    public Student[] regionHeard(String region, String song) {
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

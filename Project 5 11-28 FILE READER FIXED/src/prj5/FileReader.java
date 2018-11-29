package prj5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * File reader class which scans the songs and students into doubly linked lists
 * and handles the computations for the number of students who like/heard a song
 * in a region/major/hobby
 * 
 * @author Ishaan Singh (ishaan15)
 * @version 2018.11.28
 *
 */
public class FileReader {

    private int readingH;
    private int sportsH;
    private int artH;
    private int musicH;
    private int compSciM;
    private int mathCMDAM;
    private int otherEngeM;
    private int otherM;
    private int northEastR;
    private int southEastR;
    private int restUSR;
    private int outUSR;
    private DoublyLinkedList students;
    private DoublyLinkedList1 songs;
    private String[] songArray;
    private Student[] studentArray;
    private File surveyFileIn;
    private File songFileIn;


    /**
     * Sets hobby/major/region counts to 0, creates new doubly linked lists for
     * songs and students, and reads the songs and surveys from the parameter
     * files
     * 
     * @param surveyFile
     *            the file containing music survey data
     * @param songFile
     *            the file containing song data
     * @throws FileNotFoundException
     *             if the surveyFile or songFile cannot be found
     */
    public FileReader(File surveyFile, File songFile)
        throws FileNotFoundException {
        readingH = 0;
        sportsH = 0;
        artH = 0;
        musicH = 0;
        compSciM = 0;
        mathCMDAM = 0;
        otherEngeM = 0;
        otherM = 0;
        northEastR = 0;
        southEastR = 0;
        restUSR = 0;
        outUSR = 0;
        students = new DoublyLinkedList();
        songs = new DoublyLinkedList1();
        surveyFileIn = surveyFile;
        songFileIn = songFile;
    }


    /**
     * Reads the songs using a scanner and adds them into a dLL, sets the
     * songArray to an array of all the song names
     * 
     * @throws FileNotFoundException
     *             if songFileIn cannot be found
     */
    public void readSongs() throws FileNotFoundException {
        Scanner songScanner = new Scanner(songFileIn);
        songScanner.nextLine();
        while (songScanner.hasNextLine()) {
            String songLine = songScanner.nextLine();
            String[] songData = songLine.split(",");
            if (songData.length == 4) {
                String title = songData[0];
                String artist = songData[1];
                String year = songData[2];
                String genre = songData[3];
                Song newSong = new Song(title, artist, year, genre);
                songs.add(newSong);
            }
        }
        songScanner.close();
        songArray = songs.songNames();
    }


    /**
     * Scans the surveyFileIn and adds those students into a dLL, sets
     * studentArray to the dLL's toArray() method. Counts the total students in
     * a region/hobby/major in their respective data fields
     * 
     * @throws FileNotFoundException
     *             if the surveyFileIn cannot be found
     */
    public void readSurvey() throws FileNotFoundException {
        Scanner surveyScanner = new Scanner(surveyFileIn);
        surveyScanner.nextLine();
        while (surveyScanner.hasNextLine()) {
            String surveyLine = surveyScanner.nextLine();
            String[] surveyData = surveyLine.split(",");
            if (surveyData.length >= 5) {
                String pid = surveyData[0];
                String date = surveyData[1];
                String major = surveyData[2];
                if (major.toLowerCase().equals(("computer science"))) {
                    compSciM++;
                }
                else if (major.toLowerCase().equals(("math or cmda"))) {
                    mathCMDAM++;
                }
                else if (major.toLowerCase().equals(("other engineering"))) {
                    otherEngeM++;
                }
                else if (major.toLowerCase().equals(("other"))) {
                    otherM++;
                }
                String region = surveyData[3];
                if (region.toLowerCase().equals(("northeast"))) {
                    northEastR++;
                }
                else if (region.toLowerCase().equals(("southeast"))) {
                    southEastR++;
                }
                else if (region.toLowerCase().equals(
                    ("united states (other than southest or northwest)"))) {
                    restUSR++;
                }
                else if (region.toLowerCase().equals(
                    ("outside of united states"))) {
                    outUSR++;
                }
                String hobby = surveyData[4];
                if (hobby.toLowerCase().equals("reading")) {
                    readingH++;
                }
                else if (hobby.toLowerCase().equals("sports")) {
                    sportsH++;
                }
                else if (hobby.toLowerCase().equals("art")) {
                    artH++;
                }
                else if (hobby.toLowerCase().equals("music")) {
                    musicH++;
                }
                int x = 5;
                int i = 0;
                String[] songData = new String[surveyData.length - 5];
                while (x < surveyData.length) {
                    songData[i] = surveyData[x];
                    x++;
                    i++;
                }
                Student newStudent = new Student(pid, date, major, region,
                    hobby, songData);
                students.add(newStudent);
            }
        }
        surveyScanner.close();
        studentArray = students.studentArray();
    }


    /**
     * Returns a student[] of all of the students who like the song in the
     * parameter
     * 
     * @param songName
     *            a string containing the name of the song
     * @return a Student[] of students who like the parameter song
     */
    public Student[] whoLikes(String songName) {
        int a = 0;
        int x = 0;
        int index = -1;
        while (x < songArray.length) {
            if (songArray[x].equals(songName)) {
                index = x;
                break;
            }
            x++;
        }
        if (index == -1) {
            return null;
        }
        else {
            int n = 0;
            int size = 0;
            while (n < students.size()) {
                Student student = studentArray[n];
                String[] likes = student.getLikes();
                if (likes.length >= index + 1) {
                    if (likes[index].toLowerCase().equals("yes")) {
                        size++;
                    }

                }
                n++;
            }
            Student[] result = new Student[size];
            int z = 0;
            while (z < students.size()) {
                Student student = studentArray[z];
                String[] likes = student.getLikes();
                if (likes.length >= index + 1) {
                    if (likes[index].toLowerCase().equals("yes")) {
                        result[a] = student;
                        a++;
                    }

                }
                z++;
            }
            return result;
        }
    }


    /**
     * Returns a student[] of students who have heard the song in the parameter
     * 
     * @param songName
     *            a string containing the song name
     * @return a student[] of students who have heard the song in the parameter
     */
    public Student[] whoHeard(String songName) {
        int a = 0;
        int x = 0;
        int index = -1;
        while (x < songArray.length) {
            if (songArray[x].equals(songName)) {
                index = x;
                break;
            }
            x++;
        }
        if (index == -1) {
            return null;
        }
        else {
            int n = 0;
            int size = 0;
            while (n < students.size()) {
                Student student = studentArray[n];
                String[] heard = student.getHeard();
                if (heard.length > index && heard[index] != null) {
                    if (heard[index].toLowerCase().equals("yes")) {
                        size++;
                    }

                }
                n++;
            }
            Student[] result = new Student[size];
            int z = 0;
            while (z < students.size()) {
                Student student = studentArray[z];
                String[] heard = student.getHeard();
                if (heard.length > index && heard[index] != null) {
                    if (heard[index].toLowerCase().equals("yes")) {
                        result[a] = student;
                        a++;
                    }

                }
                z++;
            }
            return result;
        }
    }


    /**
     * Returns a string of the hobbies that like this song in the format:
     * Read: Art: Music: Sports:
     * 
     * @param songName
     *            string containing the song's name
     * @return a string of the hobbies that like the song in the parameter
     */
    public String getLikesHobby(String songName) {
        int read = 0;
        int sports = 0;
        int art = 0;
        int music = 0;
        Student[] likes = whoLikes(songName);
        for (int i = 0; i < likes.length; i++) {
            String hobby = likes[i].getHobby();
            if (hobby.equals("reading")) {
                read++;
            }
            else if (hobby.equals("art")) {
                art++;
            }
            else if (hobby.equals("music")) {
                music++;
            }
            else if (hobby.equals("sports")) {
                sports++;
            }
        }
        StringBuilder sB = new StringBuilder();
        sB.append("Read: " + read + " Art: " + art + " Music: " + music
            + " Sports: " + sports);
        return sB.toString();
    }


    /**
     * Returns a string of the hobbies that heard this song in the format:
     * Read: Art: Music: Sports:
     * 
     * @param songName
     *            string containing the song's name
     * @return a string of the hobbies that heard the song in the parameter
     */
    public String getHeardHobby(String songName) {
        int read = 0;
        int sports = 0;
        int art = 0;
        int music = 0;
        Student[] heard = whoHeard(songName);
        for (int i = 0; i < heard.length; i++) {
            String hobby = heard[i].getHobby();
            if (hobby.equals("reading")) {
                read++;
            }
            else if (hobby.equals("art")) {
                art++;
            }
            else if (hobby.equals("music")) {
                music++;
            }
            else if (hobby.equals("sports")) {
                sports++;
            }
        }
        StringBuilder sB = new StringBuilder();
        sB.append("Read: " + read + " Art: " + art + " Music: " + music
            + " Sports: " + sports);
        return sB.toString();
    }


    /**
     * Returns an integer representing how many people heard the song in the
     * parameter and the hobby in the parameter
     * 
     * @param songName
     *            the song's name
     * @param hobbyIn
     *            the hobby to get a number for
     * @return an integer representing how many people in a hobby heard the song
     *         in the parameter
     */
    public int getHeardHobbyNumber(String songName, String hobbyIn) {
        int hobbyNum = 0;
        Student[] heard = whoHeard(songName);
        for (int i = 0; i < heard.length; i++) {
            String hobby = heard[i].getHobby().toLowerCase();
            if (hobby.equals(hobbyIn.toLowerCase())) {
                hobbyNum++;
            }
        }
        return hobbyNum;

    }


    /**
     * Returns an integer representing how many people heard the song in the
     * parameter and the major in the parameter
     * 
     * @param songName
     *            the song's name
     * @param majorIn
     *            the major to get a number for
     * @return an integer representing how many people in a major heard the song
     *         in the parameter
     */
    public int getHeardMajorNumber(String songName, String majorIn) {
        int majorNum = 0;
        Student[] heard = whoHeard(songName);
        for (int i = 0; i < heard.length; i++) {
            String major = heard[i].getMajor().toLowerCase();
            if (major.equals(majorIn.toLowerCase())) {
                majorNum++;
            }
        }
        return majorNum;
    }


    /**
     * Returns an integer representing how many people heard the song in the
     * parameter and are from region in the parameter
     * 
     * @param songName
     *            the song's name
     * @param regionIn
     *            the region to get a number for
     * @return an integer representing how many people in a region heard the
     *         song
     *         in the parameter
     */
    public int getHeardRegionNumber(String songName, String regionIn) {
        int regionNum = 0;
        Student[] heard = whoHeard(songName);
        for (int i = 0; i < heard.length; i++) {
            String region = heard[i].getRegion().toLowerCase();
            if (region.equals(regionIn.toLowerCase())) {
                regionNum++;
            }
        }
        return regionNum;
    }


    /**
     * Returns an integer representing how many people like the song in the
     * parameter and the hobby in the parameter
     * 
     * @param songName
     *            the song's name
     * @param hobbyIn
     *            the hobby to get a number for
     * @return an integer representing how many people in a hobby like the song
     *         in the parameter
     */
    public int getLikesHobbyNumber(String songName, String hobbyIn) {
        int hobbyNum = 0;
        Student[] likes = whoLikes(songName);
        for (int i = 0; i < likes.length; i++) {
            String hobby = likes[i].getHobby();
            if (hobby.equals(hobbyIn)) {
                hobbyNum++;
            }
        }
        return hobbyNum;

    }


    /**
     * Returns an integer representing how many people like the song in the
     * parameter and are in the major in the parameter
     * 
     * @param songName
     *            the song's name
     * @param majorIn
     *            the major to get a number for
     * @return an integer representing how many people in a major like the song
     *         in the parameter
     */
    public int getLikesMajorNumber(String songName, String majorIn) {
        int majorNum = 0;
        Student[] likes = whoLikes(songName);
        for (int i = 0; i < likes.length; i++) {
            String major = likes[i].getMajor().toLowerCase();
            if (major.equals(majorIn.toLowerCase())) {
                majorNum++;
            }
        }
        return majorNum;
    }


    /**
     * Returns an integer representing how many people like the song in the
     * parameter and are from the region in the parameter
     * 
     * @param songName
     *            the song's name
     * @param regionIn
     *            the region to get a number for
     * @return an integer representing how many people in a region like the song
     *         in the parameter
     */
    public int getLikesRegionNumber(String songName, String regionIn) {
        int regionNum = 0;
        Student[] likes = whoLikes(songName);
        for (int i = 0; i < likes.length; i++) {
            String region = likes[i].getRegion().toLowerCase();
            if (region.equals(regionIn.toLowerCase())) {
                regionNum++;
            }
        }
        return regionNum;
    }


    /**
     * Returns the total number of students with this hobby
     * 
     * @param hobby
     *            the hobby to return a number for
     * @return the total number of students with this hobby
     */
    public int getTotalHobby(String hobby) {
        if (hobby.toLowerCase().equals("reading")) {
            return readingH;
        }
        else if (hobby.toLowerCase().equals("sports")) {
            return sportsH;
        }
        else if (hobby.toLowerCase().equals("art")) {
            return artH;
        }
        else if (hobby.toLowerCase().equals("music")) {
            return musicH;
        }
        else {
            return -1;
        }
    }


    /**
     * Returns the total number of students with this major
     * 
     * @param major
     *            the hobby to return a number for
     * @return the total number of students with this major
     */
    public int getTotalMajor(String major) {
        if (major.toLowerCase().equals(("computer science"))) {
            return compSciM;
        }
        else if (major.toLowerCase().equals(("math or cmda"))) {
            return mathCMDAM;
        }
        else if (major.toLowerCase().equals(("other engineering"))) {
            return otherEngeM;
        }
        else if (major.toLowerCase().equals(("other"))) {
            return otherM;
        }
        else {
            return -1;
        }
    }


    /**
     * Returns the total number of students in this region
     * 
     * @param region
     *            the region to return a number for
     * @return the total number of students in this region
     */
    public int getTotalRegion(String region) {
        if (region.toLowerCase().equals(("northeast"))) {
            return northEastR;
        }
        else if (region.toLowerCase().equals(("southeast"))) {
            return southEastR;
        }
        else if (region.toLowerCase().equals(
            ("united states (other than southeast or northeast)"))) {
            return restUSR;
        }
        else if (region.toLowerCase().equals(("outside of united states"))) {
            return outUSR;
        }
        else {
            return -1;
        }
    }


    /**
     * Returns the songs dLL
     * 
     * @return dLL containing the songs
     */
    public DoublyLinkedList1 getSongsDLL() {
        return songs;
    }
}

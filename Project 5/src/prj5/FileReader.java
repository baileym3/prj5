package prj5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
        readSongs(songFile);
        readSurvey(surveyFile);
        System.out.println(this.getHeardHobbyNumber(
            "All These Things I've Done", "sports"));
        System.out.println(this.getHeardMajorNumber(
            "All These Things I've Done", "Computer Science"));
        System.out.println(this.getHeardRegionNumber(
            "All These Things I've Done", "southeast"));
        System.out.println(this.getLikesHobbyNumber(
            "All These Things I've Done", "sports"));
        System.out.println(this.getLikesMajorNumber(
            "All These Things I've Done", "Computer Science"));
        System.out.println(this.getLikesRegionNumber(
            "All These Things I've Done", "southeast"));

    }


    public void readSongs(File songFile) throws FileNotFoundException {
        Scanner songScanner = new Scanner(songFile);
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
        songArray = songs.songNames();
    }


    public void readSurvey(File surveyFile) throws FileNotFoundException {
        Scanner surveyScanner = new Scanner(surveyFile);
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
        studentArray = students.studentArray();
    }


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
                if (heard.length >= index) {
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
                if (heard.length >= index) {
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


    public int getHeardHobbyNumber(String songName, String hobbyIn) {
        int hobbyNum = 0;
        Student[] heard = whoHeard(songName);
        for (int i = 0; i < heard.length; i++) {
            String hobby = heard[i].getHobby();
            if (hobby.equals(hobbyIn)) {
                hobbyNum++;
            }
        }
        return hobbyNum;

    }


    public int getHeardMajorNumber(String songName, String majorIn) {
        int mathOrCMDA = 0;
        int cS = 0;
        int otherEnge = 0;
        int other = 0;
        Student[] heard = whoHeard(songName);
        for (int i = 0; i < heard.length; i++) {
            String major = heard[i].getMajor();
            if (major.equals("Math or CMDA")) {
                mathOrCMDA++;
            }
            else if (major.equals("Computer Science")) {
                cS++;
            }
            else if (major.equals("Other Engineering")) {
                otherEnge++;
            }
            else if (major.equals("Other")) {
                other++;
            }
        }
        if (majorIn.equals("Math or CMDA")) {
            return mathOrCMDA;
        }
        else if (majorIn.equals("Computer Science")) {
            return cS;
        }
        else if (majorIn.equals("Other Engineering")) {
            return otherEnge;
        }
        else if (majorIn.equals("Other")) {
            return other;
        }
        else {
            return 0;
        }
    }


    public int getLikesMajorNumber(String songName, String majorIn) {
        int mathOrCMDA = 0;
        int cS = 0;
        int otherEnge = 0;
        int other = 0;
        Student[] likes = whoLikes(songName);
        for (int i = 0; i < likes.length; i++) {
            String major = likes[i].getMajor();
            if (major.equals("Math or CMDA")) {
                mathOrCMDA++;
            }
            else if (major.equals("Computer Science")) {
                cS++;
            }
            else if (major.equals("Other Engineering")) {
                otherEnge++;
            }
            else if (major.equals("Other")) {
                other++;
            }
        }
        if (majorIn.equals("Math or CMDA")) {
            return mathOrCMDA;
        }
        else if (majorIn.equals("Computer Science")) {
            return cS;
        }
        else if (majorIn.equals("Other Engineering")) {
            return otherEnge;
        }
        else if (majorIn.equals("Other")) {
            return other;
        }
        else {
            return 0;
        }
    }


    public int getHeardRegionNumber(String songName, String regionIn) {
        int nE = 0;
        int sE = 0;
        int otherUS = 0;
        int outUS = 0;
        Student[] heard = whoHeard(songName);
        for (int i = 0; i < heard.length; i++) {
            String region = heard[i].getRegion();
            if (region.toLowerCase().equals(("northeast"))) {
                nE++;
            }
            else if (region.toLowerCase().equals(("southeast"))) {
                sE++;
            }
            else if (region.toLowerCase().equals(
                ("united states (other than southest or northwest)"))) {
                otherUS++;
            }
            else if (region.toLowerCase().equals(
                ("outside of united states"))) {
                outUS++;
            }
        }
        if (regionIn.toLowerCase().equals(("northeast"))) {
            return nE;
        }
        else if (regionIn.toLowerCase().equals(("southeast"))) {
            return sE;
        }
        else if (regionIn.toLowerCase().equals(
            ("united states (other than southest or northwest)"))) {
            return otherUS;
        }
        else if (regionIn.toLowerCase().equals(("outside of united states"))) {
            return outUS;
        }
        else {
            return 0;
        }
    }


    public int getLikesRegionNumber(String songName, String regionIn) {
        int nE = 0;
        int sE = 0;
        int otherUS = 0;
        int outUS = 0;
        Student[] likes = whoLikes(songName);
        for (int i = 0; i < likes.length; i++) {
            String region = likes[i].getRegion();
            if (region.toLowerCase().equals(("northeast"))) {
                nE++;
            }
            else if (region.toLowerCase().equals(("southeast"))) {
                sE++;
            }
            else if (region.toLowerCase().equals(
                ("united states (other than southest or northwest)"))) {
                otherUS++;
            }
            else if (region.toLowerCase().equals(
                ("outside of united states"))) {
                outUS++;
            }
        }
        if (regionIn.toLowerCase().equals(("northeast"))) {
            return nE;
        }
        else if (regionIn.toLowerCase().equals(("southeast"))) {
            return sE;
        }
        else if (regionIn.toLowerCase().equals(
            ("united states (other than southest or northwest)"))) {
            return otherUS;
        }
        else if (regionIn.toLowerCase().equals(("outside of united states"))) {
            return outUS;
        }
        else {
            return 0;
        }
    }


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
            return 0;
        }
    }


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
            return 0;
        }
    }


    public int getTotalRegion(String region) {
        if (region.toLowerCase().equals(("northeast"))) {
            return northEastR;
        }
        else if (region.toLowerCase().equals(("southeast"))) {
            return southEastR;
        }
        else if (region.toLowerCase().equals(
            ("united states (other than southest or northwest)"))) {
            return restUSR;
        }
        else if (region.toLowerCase().equals(("outside of united states"))) {
            return outUSR;
        }
        else {
            return 0;
        }
    }
}

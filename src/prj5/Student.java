package prj5;

import java.util.Scanner;

public class Student {

    private String num;
    private String date;
    private String major;
    private String region;
    private String hobby;
    private String[] songData;


    public Student(
        String stuNum,
        String dateIn,
        String majorIn,
        String regionIn,
        String hobbyIn,
        String[] songDataIn) {
        num = stuNum;
        date = dateIn;
        major = majorIn;
        region = regionIn;
        hobby = hobbyIn;
        songData = songDataIn;
    }


    public String getNum() {
        return num;
    }


    public String getDate() {
        return date;
    }


    public String getMajor() {
        return major;
    }


    public String getRegion() {
        return region;
    }


    public String getHobby() {
        return hobby;
    }


    public String[] getSongData() {
        return songData;
    }

}

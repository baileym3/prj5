package prj5;

import java.io.File;

public class Input {
    public static void main(String[] args) {
        System.out.println(new File("MusicSurveyData.csv").getAbsolutePath());
        DataOutput output = new DataOutput(
            "C:\\Users\\Baile\\eclipse-workspacejava\\Project5Files\\MusicSurveyData.csv",
            "C:\\Users\\Baile\\eclipse-workspacejava\\Project5Files\\SongList.csv");
        output.scanSong();
        output.scanSurvey();
        System.out.println(output.getSongs().size());
        // System.out.print(output.getSongs().songNames()[0].toString());
    }
}

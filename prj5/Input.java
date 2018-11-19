package prj5;

import java.io.File;
import java.io.FileNotFoundException;

public class Input {
    public static void main(String[] args) {
        File surveyFile = new File(args[0]);
        File songFile = new File(args[1]);
        DataOutput output = new DataOutput(surveyFile, songFile);
        try {
            output.scanSurvey();
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            output.scanSong();
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        output.getSongs().genreSort();
        output.hobbiesHeard();
        // output.hobbiesLikes();
        System.out.println(output.getSongs().toString());
        output.getSongs().titleSort();
        System.out.println(output.getSongs().toString());
    }
}

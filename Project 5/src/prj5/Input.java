package prj5;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Main class that runs everything
 * 
 * @author Ishaan Singh
 * @version 2018.11.18
 */
public class Input {
    /**
     * Program runner
     * 
     * @param args
     *            the file names
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length == 2) {
            File surveyFile = new File(args[0]);
            File songFile = new File(args[1]);
            FileReader fR = new FileReader(surveyFile, songFile);
        }
        else {
            File surveyFile = new File("MusicSurveyDataTest1.csv");
            File songFile = new File("SongListTest1.csv");
            FileReader fR = new FileReader(surveyFile, songFile);
        }

    }
}

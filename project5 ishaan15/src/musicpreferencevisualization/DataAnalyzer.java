package musicpreferencevisualization;

import java.util.NoSuchElementException;

public class DataAnalyzer {

    private String[] likes;
    private String[] listens;
    private String[] songData;
    private String[] songNames;


    public DataAnalyzer(Student studentIn, String[] songNamesIn) {
        songNames = songNamesIn;
        songData = studentIn.getSongData();
        listens = new String[(songNames.length / 2) + 1];
        likes = new String[(songNames.length / 2) + 1];
        int x = 0;
        int y = 0;
        for (int i = 0; i < songData.length; i += 2) {
            listens[x] = songData[i];
            x++;
        }
        for (int z = 1; z < songData.length; z += 2) {
            likes[y] = songData[z];
            y++;
        }
    }


    public int getSongIndex(String songName) {
        int index = -1;
        for (int x = 0; x < songNames.length; x++) {
            if (songNames[x].equals(songName)) {
                index = x;
            }
        }
        return index;
    }


    public boolean likes(String songName) {
        int songNum = getSongIndex(songName);
        if (songNum == -1) {
            throw new NoSuchElementException();
        }
        else if (likes[songNum].equals("yes")) {
            return true;
        }
        else {
            return false;
        }
    }


    public boolean heard(String songName) {
        int songNum = getSongIndex(songName);
        if (songNum == -1) {
            throw new NoSuchElementException();
        }
        else if (listens[songNum].equals("yes")) {
            return true;
        }
        else {
            return false;
        }
    }
}

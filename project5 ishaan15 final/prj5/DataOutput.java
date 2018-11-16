package prj5;

public class DataOutput {

    private DoublyLinkedList1 songs;
    private DoublyLinkedList students;
    private String[] songNames;
    private Student[] studentsArray;
    private int totalResponses;


    public DataOutput() {
        songs = new DoublyLinkedList1();
        students = new DoublyLinkedList();
        songNames = songs.songNames();
        studentsArray = students.studentArray();
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

package prj5;

/**
 * Student class containing fields and methods used for sorting
 * 
 * @author Ishaan Singh (ishaan15)
 * @version 2018.11.15
 *
 */
public class Student {

    private String num;
    private String date;
    private String major;
    private String region;
    private String hobby;
    private String[] songData;


    /**
     * Default constructor with student number, date recorded, their major,
     * region they are from, their hobby, and the song data
     * 
     * @param stuNum
     *            student's ID number
     * @param dateIn
     *            date of survey
     * @param majorIn
     *            major of student
     * @param regionIn
     *            region student is from
     * @param hobbyIn
     *            hobby of the student
     * @param songDataIn
     *            the song data from the survey
     */
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


    /**
     * Getter method for stuNum()
     * 
     * @return stuNum
     */
    public String getNum() {
        return num;
    }


    /**
     * Getter method for date of survey
     * 
     * @return date
     */
    public String getDate() {
        return date;
    }


    /**
     * Getter method for major of student
     * 
     * @return major
     */
    public String getMajor() {
        return major;
    }


    /**
     * Getter method for region of student
     * 
     * @return region
     */
    public String getRegion() {
        return region;
    }


    /**
     * Getter method for hobby of student
     * 
     * @return hobby
     */
    public String getHobby() {
        return hobby;
    }


    /**
     * Getter method for songData from the survey
     * 
     * @return songData
     */
    public String[] getSongData() {
        return songData;
    }


    /**
     * Returns the string array of likes for a student
     * 
     * @return String array of likes students
     */
    public String[] getLikes() {
        String[] likes = new String[(songData.length / 2)];
        int x = 0;
        for (int i = 1; i < songData.length; i += 2) {
            likes[x] = songData[i];
            x++;
        }
        return likes;
    }


    /**
     * Returns the string array of likes for a student
     * 
     * @return string array of heard students
     */
    public String[] getHeard() {
        String[] heard = new String[songData.length];
        int x = 0;
        for (int i = 0; i < songData.length; i += 2) {
            heard[x] = songData[i];
            x++;
        }
        return heard;
    }


    public String toString() {
        return num;
    }
}

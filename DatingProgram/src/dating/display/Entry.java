package dating.display;

/**
 * Created by Jurko on 20/02/14.
 */
public class Entry {
    public String NAME;
    public int AGE;
    public char SEX;
    public char[] ANSWERS = new char[15];

    Entry(String name, int age, char sex, String answers) {
        NAME = name;
        AGE = age;
        SEX = sex;
        ANSWERS = answers.trim().toCharArray();
    }

    @Override
    public String toString() {
        return NAME + " " + AGE + ",  " + SEX;
    }


}

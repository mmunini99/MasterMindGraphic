package Settings;

public class Trials {

    private String lvlofdifficulty;

    private int numberofcolor;
    private int numberoftrials;

    static private String type_hard = "HARD";
    static private String type_medium = "MEDIUM";
    static private String type_easy = "EASY";

    public Trials(String lvl) {
        this.lvlofdifficulty = lvl;
        this.numberofcolor = setNumberofColor(lvlofdifficulty);
        this.numberoftrials = setNumberofTrials(lvlofdifficulty);
    }

    private int setNumberofTrials(String lvl) {
        if (lvl.equals(type_hard)) {
            return 7;
        } else if (lvl.equals(type_medium)) {
            return 12;
        } else if (lvl.equals(type_easy)) {
            return 15;
        } else {
            return 0;
        }
    }

    private int setNumberofColor(String lvl) {
        if (lvl.equals(type_hard)) {
            return 4;
        } else if (lvl.equals(type_medium)) {
            return 4;
        } else if (lvl.equals(type_easy)) {
            return 3;
        } else {
            return 0;
        }
    }

    public int getNumberofcolor() {
        return numberofcolor;
    }

    public int getNumberoftrials() {
        return numberoftrials;
    }

}

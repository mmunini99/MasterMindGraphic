package Settings;

public class Trials {
    private final int NumberOfTrials; //The number of trials available to the player.

    //The three difficulties.
    static protected String type_hard = "HARD";
    static protected String type_medium = "MEDIUM";
    static protected String type_easy = "EASY";

    //Class Constructor
    public Trials(String lvl) {
        this.NumberOfTrials = setNumberOfTrials(lvl);
    }

    //The number of Trials is set according to the difficulty level
    private int setNumberOfTrials(String lvl) {
        if (lvl.equals(type_hard)) {
            return 7;
        } else if (lvl.equals(type_medium)) {
            return 12;
        } else if (lvl.equals(type_easy)) {
            return 15;
        } else {
            throw new RuntimeException("Trials: setNumberOfTrials: non existing difficulty level");
        }
    }

    //A get function to obtain the number of trials.
    public int getNumberOfTrials() {
        return NumberOfTrials;
    }

}

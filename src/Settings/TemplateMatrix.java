package Settings;

public class TemplateMatrix {

    private int lengthoftrials;

    private final int lengthofguess;

    private final int lengthoffeedback;

    private int[] guessofplayer;

    private int[] feedback;

    private int[][] template;

    private int round;

    private int getLengthofarray(int[] array) {
        return array.length;
    }

    private int getrowsoftemplate() {
        return template.length;
    }

    private int[][] gettemplate() {
        return template;
    }


    public void setguessandfeed(int[] guess, int[] feed, int count) {

        int[][] matrixold = template;

        int nrow = lengthofguess + lengthoffeedback;

        for (int i = 0; i < nrow; i++) {

            if (i < 4) {
                matrixold[count][i] = guess[i];
            } else {
                matrixold[count][i] = feed[i - 4];
            }
        }

        template = matrixold;


    }


    public TemplateMatrix(int lengthoftrials,
                          int LOG ,
                          int LOF) {

        lengthofguess = LOG;
        lengthoffeedback = LOF;
        int nrow = lengthofguess + lengthoffeedback;
        int ncolumn = lengthoftrials;
        template = new int[ncolumn][nrow];

    }

}

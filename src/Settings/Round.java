package Settings;

public class Round {
    public int[] guess; //The var used to store the guess of the player.
    private final int[] feedback; // feedback[0] = amount of correct colors in the correct position; feedback[1] =
                                    // amount of guessed colors, but they are in the wrong position; feedback[2] =
                                    // completely wrong.

    // Constructor
    public Round( int[] secretCode, int[] guess) {

        int[] feedback = new int[3];
        int[] secretCode_flag = new int[secretCode.length]; // 1 if the element has already been used, 0 if it has not
        int[] guessCode_flag = new int[secretCode.length]; // 1 if the element has already been used, 0 if it has not

        // Check if there are right numbers in the right position
        for (int i = 0; i < guess.length; i++) {
            if (secretCode[i] == guess[i]) {
                feedback[0]++;
                secretCode_flag[i] = 1;
                guessCode_flag[i] = 1;
            }
        }

        // Check if there are right numbers in the wrong position
        for (int j = 0; j < secretCode.length; j++) {
            for (int i = 0; i < guess.length; i++) {

                if (i != j) {

                    if (secretCode[j] == guess[i] && secretCode_flag[j] == 0 && guessCode_flag[i] == 0) {
                        feedback[1]++;
                        secretCode_flag[j] = 1;
                        guessCode_flag[i] = 1;
                        break;
                    }
                }
            }
        }

        feedback[2] = guess.length - feedback[0] - feedback[1];

        this.guess = guess;
        this.feedback = feedback;

    }

    // Get methods
    public int[] getFeedback() {
        return feedback;
    }


}

package Settings;

import java.util.Arrays;

public class Round {
    public int roundNumber; // roundNumber identifies the current round in the series of rounds that are
                     // performed
    private int[] secretCode;
    public int[] guess;
    public int[] feedback; // feedback[0] = amount of correct colors in the correct position; feedback[1] =
                    // amount of guessed colors, but they are in the wrong position; feedback[2] =
                    // completely wrong

    // Constructor
    public Round(int roundNumber, int[] secretCode, int[] guess) {

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

        /*
         * // Now we need to reshuffle the results
         * 
         * // Convert feedback to a list
         * List<Integer> feedback_list = new ArrayList<>();
         * for (int value : feedback) {
         * feedback_list.add(value);
         * }
         * 
         * // Shuffle the list
         * Collections.shuffle(feedback_list);
         * 
         * // Convert the shuffled list back to feedback
         * for (int i = 0; i < feedback.length; i++) {
         * feedback[i] = feedback_list.get(i);
         * }
         */

        this.roundNumber = roundNumber;
        this.secretCode = secretCode;
        this.guess = guess;
        this.feedback = feedback;

    }

    // Getter methods
    public int getRoundNumber() {
        return roundNumber;
    }

    public int[] getSecretCode() {
        return secretCode;
    }

    public int[] getGuess() {
        return guess;
    }

    public int[] getFeedback() {
        return feedback;
    }

    // Override toString method for better representation
    @Override
    public String toString() {
        String string_secretCode = arrayToString(secretCode);
        String string_guess = arrayToString(guess);
        return "Round " + roundNumber +
                "\nSecret Code: " + string_secretCode +
                "\nGuess: " + string_guess +
                "\nFeedback: " + Arrays.toString(feedback) +
                "\n----------------------";
    }

    private static String arrayToString(int[] array) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < array.length; i++) {
            stringBuilder.append(array[i]);
        }

        return stringBuilder.toString();
    }

    /*
     * // Test
     * public static void main(String[] args) {
     * int roundNumber = 1;
     * int[] secretCode = { 1, 1, 1, 4 };
     * int[] guess = { 1, 4, 4, 0 };
     * 
     * Round round = new Round(roundNumber, secretCode, guess);
     * System.out.println(round);
     * }
     */
}

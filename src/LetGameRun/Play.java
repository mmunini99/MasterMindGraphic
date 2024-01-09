package LetGameRun;
import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;
import Settings.SetUp;
import Settings.Round;

public class Play {

    //CODICE AGGIUSTATO PER LA GRAFICA

    //################# GAME VARIABLES ############################################
    private boolean initializedGame = false;
    private String input = "EASY"; //input nel codice vecchio
    private SetUp gamesetup;
    private int lengthofseq;
    private int trials;
    private int lengthoftemplate;
    private int[] secretCode;
    private int[] feedback;

    //################# GET FUNCTIONS #############################################

    public int getLengthofseq(){
        return lengthofseq;
    }

    public int getTrials(){
        return trials;
    }

    public int getLengthoftemplate(){
        return lengthoftemplate;
    }

    public boolean getInitialized(){
        return initializedGame;
    }

    public String getInput(){
        return input;
    }

    //################# SETUP FUNCTIONS ##############################################

    public void setDifficulty(int d){
        switch (d){
            case 1:
                input = "EASY";
                break;
            case 2:
                input = "MEDIUM";
                break;
            case 3:
                input = "HARD";
                break;
            default:
                throw new RuntimeException("Difficulty inserted does not exist");
        }
    }

    public void initializeGame(){
        if(input!=null) {
            gamesetup = new SetUp(input);
        }
        else{
            throw new RuntimeException("Not decided difficulty");
        }
        lengthofseq = gamesetup.getNumberofcolor();
        trials = gamesetup.getNumberoftrials();
        lengthoftemplate = gamesetup.getLengthTemplate();
        secretCode = generateSecretCode(lengthofseq, lengthoftemplate);
        initializedGame = true;
    }

    //####################### GAME FUNCTIONS ###########################################

    // Generates a random 4-digit secret code
    private static int[] generateSecretCode(int LOS, int LOT) {
        int[] code = new int[LOS];
        Random random = new Random();

        for (int i = 0; i < LOS; i++) {
            code[i] = random.nextInt((LOT - 1));
        }

        return code;

    }

/*
    //CODICE ORIGINALE INTONSO

    public static void main(String[] args) {

        System.out.println("Enter the level. You can choose between EASY, MEDIUM or HARD");

        String input;

        do {

            input = System.console().readLine();
            input = input.toUpperCase();

        } while (!checkuserinputlevel(input));

        System.out.println("You will play with the " + input + " level. Have a nice game!");

        SetUp gamesetup = new SetUp(input);

        int lengthofseq = gamesetup.getNumberofcolor();
        int trials = gamesetup.getNumberoftrials();
        int lengthoftemplate = gamesetup.getLengthTemplate();


        int[] secretCode = generateSecretCode(lengthofseq, lengthoftemplate);

        int[] feedback;

        // ###################### FINO A QUI FATTO!!!!!! #########################################

        System.out.println("Welcome to Mastermind! Try to guess the " + Integer.toString(lengthofseq) + "-digit secret code.");

        int count = 0;

        while (count < trials) {


            int[] playerGuess = getPlayerGuess(lengthofseq, lengthoftemplate);

            feedback = provideFeedback(count, secretCode, playerGuess);
            displayFeedback(feedback);

            if (isGameOver(feedback, lengthofseq)) {

                count = count + trials + 1;

                System.out.println("Congratulations! You guessed the secret code: " + Arrays.toString(secretCode));


            } else if (count < trials && count != trials - 1) {
                count = count + 1;

                int count_trials = trials - count;

                System.out.println("Look the feedback! You have " + Integer.toString(count_trials) + " guess. Good luck!");

            } else if (count == trials - 1) {
                count = count + 1;

                System.out.println("Sorry you lose!");
                System.out.println("The correct combination was: ");
                System.out.println(Arrays.toString(secretCode));
            }


        }






    }

    // Gets a guess from the player
    private static int[] getPlayerGuess(int lengthofseq, int lengthoftemplate) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your" + Integer.toString(lengthofseq)  + "-digit guess: ");
        String guessString = scanner.nextLine();

        int max_number_to_choose = lengthoftemplate - 1;

        // Validate the input
        while (guessString.length() != lengthofseq || !guessString.matches("[0-"+ Integer.toString(max_number_to_choose)+ "]+")) {
            System.out.println("Invalid input. Please enter a "+ Integer.toString(lengthofseq) + "-digit number using digits 0 to "+ Integer.toString(max_number_to_choose) +".");
            System.out.print("Enter your " + Integer.toString(lengthofseq) + "-digit guess: ");
            guessString = scanner.nextLine();
        }

        String guessStringChar = guessString.toString();

        int[] guessStringNumeric = new int[guessStringChar.length()];

        for (int i = 0; i < guessStringChar.length(); i++){
            guessStringNumeric[i] = guessStringChar.charAt(i) - '0';
        }

        scanner.close(); // close the Scanner scanner previously open; if we don't do that we have a warning

        return guessStringNumeric;
    }

    // Provides feedback on the player's guess
    private static int[] provideFeedback(int round, int[] secretCode, int[] playerGuess) {


        Round provideroffeedback = new Round(round, secretCode, playerGuess);


        return provideroffeedback.getFeedback();
    }

    private static void displayFeedback(int[] feedback) {
        System.out.println("Feedback: " + feedback[0] + " correct position, " + feedback[1] + " correct digit, "+
                feedback[2] + " wrong guesses");
    }

    private static boolean isGameOver(int[] feedback, int lengthofseq) {
        return feedback[0] == lengthofseq;
    }


    private static boolean checkuserinputlevel(String testo) {
        if (testo.equals("HARD") || testo.equals("MEDIUM") || testo.equals("EASY")) {
            return true;
        } else {
            System.out.println("Sorry, this choice is not allowed. Choose between EASY, MEDIUM or HARD");
            return false;}
    }

 */
}

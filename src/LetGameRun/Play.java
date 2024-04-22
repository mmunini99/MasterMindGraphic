package LetGameRun;

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

import static LetGameRun.GameMessages.*;
import Settings.CalculatePoints;
import Settings.CheckGuessValidity;
import Settings.ProvideFeedback;
import Settings.ScannerDifficulty;
import Settings.SetDifficulty;
import Settings.TemplateMatrix;
import static Settings.SetDifficulty.Level;

public class Play {
    private Scanner scanner;
    private ScannerDifficulty scannerDifficulty;
    private Level difficultyLvl;
    private SetDifficulty setDifficulty;
    private int lengthOfSequence;
    private int trials;
    private int maxValue;
    public int[] secretCode;
    public int[] feedback = new int[3]; //---> create a constant
    private int count; //used in graphic implementation
    private boolean initialized = false; //used in graphic implementation

    private CheckGuessValidity scannerGuess;
    private TemplateMatrix resultSummary;

    private boolean printToFile;
    public boolean playerWon;

    public Play(Scanner scanner, boolean printToFile) {
        this.scanner = scanner;
        this.printToFile = printToFile;
        scannerDifficulty = new ScannerDifficulty(scanner);
    }

    //Used for graphic implementation
    public Play(){}
    
    public void askForDifficulty(){
        // Initialize the feedback array
        this.feedback = new int[3]; //---> create a constant

        scannerDifficulty.getDifficulty(scanner);
        this.difficultyLvl = scannerDifficulty.getDifficultyLevel();
        this.setDifficulty = new SetDifficulty(difficultyLvl);
        this.trials = setDifficulty.getNumberOfTrials();
        this.lengthOfSequence = setDifficulty.getLengthOfSequence();
        this.maxValue = setDifficulty.getMaxValueForColor();

        this.scannerGuess = new CheckGuessValidity(scanner, difficultyLvl);
        this.resultSummary = new TemplateMatrix(trials, lengthOfSequence, printToFile);

        initialized = true;

    }

    public boolean LetGameRun(int[] secretCode) {
        // Display the second welcome message
        System.out.println(firstWelcomeMessage(difficultyLvl.toString()));

        // Display the number of trials that the player has to guess the secret code
        System.out.println(declareNumberOfTrials(trials));
        // Initialize the counter for the trials
        int count = 0;
        // Initialize the final score
        int final_score = 0;
        ProvideFeedback provideFeedback = new ProvideFeedback(secretCode);
        // Loop over the trials
        while (count < trials) {
            if (count > 0) {
                resultSummary.printTemplate(count);
            }
            System.out.println(enterYourGuess(lengthOfSequence));
            // Get the player's guess
            int[] guess = scannerGuess.validateGuess();
            // Check if the player's guess is correct
            this.feedback = provideFeedback.getFeedback(guess);
            resultSummary.setGuess(guess, feedback, count);
            // Display the feedback
            provideFeedback.displayFeedback();
            // Check if the player has guessed the secret code
            if (wasSecretCodeGuessed(feedback)) {
                this.playerWon = true;
                break;
            }
            // Check if the player has trials left
            if (areTrialsLeft(count)) {
                count++;
                System.out.println(displayRemainingTrials(trials - count));
            } else {
                this.playerWon = false;
                break;
            }

        }
        // Calculate the final score
        CalculatePoints score = new CalculatePoints();
        final_score = score.calculateFinalScore(difficultyLvl, trials - count);
        // Display the final score
        System.out.println(provideFinalScore(final_score));

        return playerWon;
    }

    // Generates a random 4-digit secret code
    public int[] generateSecretCode() {

        // Initialise a new array of integers
        int[] code = new int[lengthOfSequence];
        Random random = new Random();
        // Fill the array with random numbers from 0 to 5
        for (int i = 0; i < lengthOfSequence; i++) {
            code[i] = random.nextInt((maxValue + 1));
        }
        return code;
    }

    //################################# FUNCTION USED FOR GRAPHIC IMPLEMENTATION ######################################

    public void inizializePlay(String diff){
        // Initialize the feedback array
        this.feedback = new int[3]; //---> create a constant

        this.difficultyLvl = Level.valueOf(diff.toUpperCase());
        this.setDifficulty = new SetDifficulty(difficultyLvl);
        this.trials = setDifficulty.getNumberOfTrials();
        this.lengthOfSequence = setDifficulty.getLengthOfSequence();
        this.maxValue = setDifficulty.getMaxValueForColor();

        secretCode = generateSecretCode();

        initialized = true;

    }

    public void computeFeedback(int[] guess){
        ProvideFeedback provider = new ProvideFeedback(secretCode);
        this.feedback = provider.getFeedback(guess);
    }
    public boolean getInitialized(){
        return initialized;
    }
    public int getCount() {
        return count;
    }
    public void AugCount(){
        count++;
    }
    public void setSecretCode(int[] secretCode) {
        this.secretCode = secretCode;
    }
    // Check if the player has trials left
    public boolean areTrialsLeft(int count) {
        return count < trials && count != trials - 1;
    }
    public int[] getSecretCode() {
        return secretCode;
    }
    public boolean wasSecretCodeGuessed(int[] feedback) {
        return feedback[0] == lengthOfSequence;
    }

    //#########################################################################################################

    public static void main(String[] args) {
        final boolean printToFile = false;

        Scanner scanner = new Scanner(System.in);
        Play play = new Play(scanner, printToFile);

        play.askForDifficulty();

        int[] secretCode = play.generateSecretCode();
        
        boolean playerWon = play.LetGameRun(secretCode);

        if (playerWon) {
            System.out.println(secretCodeWasGuessed());
        } else {
            System.out.println(lostMessage(Arrays.toString(play.secretCode)));
        }
        scanner.close();
    }
}

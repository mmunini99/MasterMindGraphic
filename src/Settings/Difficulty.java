package Settings;

// Import the Scanner class, used to take user input
import java.util.Scanner;

// Given the user input, given as a string, return the corresponding parameters of the game
public class Difficulty {

    // Define the names of the levels of difficulty, as constants
    private static final String EASY = "EASY";
    private static final String MEDIUM = "MEDIUM";
    private static final String HARD = "HARD";

    // Define the name of the exit command, as a constant
    private static final String EXIT = "EXIT";


    // Make the user choose the difficulty, scanning the input
    private String getDifficulty(){

        // Ask the user to choose the difficulty
        System.out.println("Choose difficulty between " + EASY + ", " + MEDIUM + " and " + HARD + " or type " + EXIT + " to quit the game:");

        // Open scanner, using try-with-resources to close it automatically
        try(Scanner scanner = new Scanner(System.in)){

            // Scan the input and convert it to uppercase
            String difficulty = scanner.nextLine().toUpperCase();
            

            while (true) {
                if (difficulty.equals(EASY) || difficulty.equals(MEDIUM) || difficulty.equals(HARD)){
                    // If the input is valid, return it
                    System.out.println("You will play with the " + difficulty + " level. Have a nice game!");
                    return difficulty;
                } else if (difficulty.equals(EXIT)){
                    // If the input is the exit command, exit the program
                    System.out.println("Exiting the game...");
                    System.exit(0);
                    return null;
                } else {
                    // If the input is not valid, ask the user to try again
                    System.out.println("Invalid difficulty. Please try again or type " + EXIT + " to quit the game:");
                    difficulty = scanner.nextLine().toUpperCase();
                    // return null;
                }
            }

        }
    }

    public int[] getGameParameters(){
        String difficulty = getDifficulty();

        // Return the parameters of the game corresponding to the difficulty as
        // an array of integers made as:
        // [number of colors, number of trials, length of sequence]
        switch (difficulty) {
            case EASY:
                return new int[]{3, 15, 3};
            case MEDIUM:
                return new int[]{6, 12, 4};
            case HARD:
                return new int[]{6, 7, 4};
            default:
                System.out.println("Invalid difficulty. Quitting the game...");
                System.exit(0);
                return null;
        }
    }
    
}

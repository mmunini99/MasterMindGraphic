package LetGameRun;
import java.util.Random;
import Settings.SetUp;
import Settings.Round;

//############################### CLASS DESCRIPTION #############################################################
/*
This class is the core of the backend process of the game. When a new game in Single or Multiplayer starts a Play class
associated to the board is initialized. Play contains all the backend relevant info of the game and all the function
to manage the game mechanics.
 */

public class Play {

    //################# GAME VARIABLES ############################################
    //These variables are fundamental to manage the game mechanics

    private boolean initializedGame = false; //is the game initialized? (correct characteristics loaded).
    private String input = "EASY"; //the difficulty of the game.
    private int LengthOfSeq; //The number of slots that composed the secret code and the guess.
    private int trials; //The number of trails allowed to the player to guess the secret code.
    private int LengthOfTemplate; //The number of colors available in the secret code and also to the player.

    private int[] secretCode; //The secret code of the game.
    private int[] feedback; //The feedback after the last guess.

    private int count; //The number of trails already performed (the turn).

    private boolean WinningStatus; //With the last guess has the player won the game?

    //################# GET FUNCTIONS #############################################
    //The functions are used to retrieve some information about the game.

    public int getTrials(){
        return trials;
    } //get the total number of trails for the game.

    public boolean getInitialized(){
        return initializedGame;
    } //get if the game is initialized or not.

    public String getInput(){
        return input;
    } //get the difficulty of the game.

    public int getCount(){
        return count;
    } //get the turn.

    public int[] getFeedback(){
        return feedback;
    } //get the feedback of the last guess.
    public boolean getWinningStatus() { return WinningStatus;} //get if the player has won or not after the last guess.

    public int[] getSecretCode(){
        return secretCode;
    } //get the secret code of the game.

    //####################### COUNT FUNCTIONS #######################################
    //This function is used to augment the count var a.k.a. to symbolically move to the next turn
    public void AugCount(){
        count++;
    }

    //################# SETUP FUNCTIONS ##############################################
    //These functions are used during the initialization and setup of the game.

    //Set the difficulty of the game.
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
                throw new RuntimeException("Play: setDifficulty: difficulty inserted does not exist");
        }
    }

    //Perform the initialization of the game.
    public void initializeGame(){
        SetUp gamesetup;

        //Check if a valid difficulty has been passed.
        if(input!=null) {
            gamesetup = new SetUp(input);
        }
        else{
            throw new RuntimeException("Play: initializeGame: Not decided difficulty");
        }

        LengthOfSeq = 4; //set the length of the guess/secret code.
        trials = gamesetup.getNumberOfTrials(); //the number of trials.
        LengthOfTemplate = gamesetup.getLengthTemplate(); //and the number of available colors.
        secretCode = generateSecretCode(LengthOfSeq, LengthOfTemplate); //Then generate the secret code.
        initializedGame = true; //Now the initialization is done so mark the status var.
    }

    //Set the secretCode externally (used in multiplayer mode where there are two games with same code)
    public void setSecretCode(int[] NewCode){
        //Check if the given code has at least LOS slots
        if(NewCode.length== LengthOfSeq){
            //Now start to copy each value checking that is compatible with the LOT
            for(int i = 0; i< LengthOfSeq; i++){
                if((NewCode[i]>=0)&&(NewCode[i]< LengthOfTemplate)){
                    secretCode[i]=NewCode[i];
                }
                else{
                    //if it is out of the template length, raise an error.
                    throw new RuntimeException("Play: invalid number in given new secret code");
                }
            }
        }
        else{
            //or raise an error.
            throw new RuntimeException("Play: setSecretCode: given new secret code is too long");
        }
    }

    //####################### GAME FUNCTIONS ###########################################
    //These functions are used during the game to do all the necessary mechanics.

    // Generates a random LOS-digit secret code with a number of possible values given by LOT.
    private static int[] generateSecretCode(int LOS, int LOT) {
        int[] code = new int[LOS];
        Random random = new Random();

        for (int i = 0; i < LOS; i++) {
            code[i] = random.nextInt(LOT);
        }

        return code;
    }

    //Class constructor. No info is needed because the game and its initialization are performed in separate
    //step of the game
    public Play(){}

    //This function is an external bridge to allow the Feedback phase without interacting too much with internal
    //characteristics.
    public void FeedbackManager( int[] GuessPlayer) {
        //Provide the feedback.
        feedback = provideFeedback(secretCode, GuessPlayer);

        //And recompute the winning status.
        WinningStatus = isGameOver(feedback, LengthOfSeq);

    }

    //This function is used to provide the feedback given the secret code and the guess.
    private static int[] provideFeedback( int[] secretCode, int[] playerGuess) {

        int[] FeedbackArray = new int[4]; //Set up the output feedback array

        int position = 0; //Position is used to move in the feedback array

        //The round class perform the feedback computation so one is created.
        Round ProviderOfFeedBack = new Round(secretCode, playerGuess);

        //The feedback elaboration given by the Provider. (the number of each type of feedback slots)
        int[] FeedbackCompressed = ProviderOfFeedBack.getFeedback();

        //Start to produce the true feedback
        for (int i = 0; i < 3; i++) {
            int CategoryFeedback = FeedbackCompressed[i]; //The number of the type "i" slots

            //Set a number of slots in the feedback according to that type.
            for (int j = 0; j < CategoryFeedback; j++ ) {

                FeedbackArray[position] = i;  //if 0 right color/position, if 1 right color only, if 2 wrong

                position += 1;
            }
        }

        return FeedbackArray;
    }


    //Check if the game is over a.k.a. if the player has won the game or the game is still going.
    private static boolean isGameOver(int[] feedback, int LOS) {
        int counter = 0;

        //Check if the player has won the game
        for (int i = 0; i < LOS; i++){

            if (feedback[i] == 0) {
                counter += 1;
            }

        }

        //And if it happens return true otherwise false.
        return counter == LOS;

    }

}

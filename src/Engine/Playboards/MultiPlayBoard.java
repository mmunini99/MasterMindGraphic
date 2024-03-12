package Engine.Playboards;

import Engine.Boards.Board;
import Engine.Boards.EasyBoard;
import Engine.Boards.HardBoard;
import Engine.Boards.MediumBoard;
import Engine.GraphicElements.Button;
import Engine.Events_Manager;
import Engine.GraphicElements.GameText;
import Engine.WorkFlow;
import LetGameRun.Play;
import processing.core.PApplet;

//############################### CLASS DESCRIPTION #############################################################
/*
This PlayBoard is created to manage the multiplayer games. It's an extension of the abstract class GenericPlayBoard
introduced to link the Boards to the Multiplayer and Single-player PlayBoards which have different implementation of
some methods. The PlayBoard manage the link between the graphic and the back-end game allowing this two element to
interact together. The Multiplayer board is made to manage simultaneously a 2 player game whose turn are alternating.
For this reason, contrary of SinglePlayBoard, it's necessary to have two separate Plays and Boards because the sequence
of guesses of each player has to be saved in an independent instance of Play and a different Boards to render has to
be prepared.
 */

public class MultiPlayBoard extends GenericPlayBoard{

    //######################### FUNDAMENTALS VARIABLES ######################################
    //This section contains the fundamental vars necessary for the class to work properly.

    //Used to refer to the Processing Application
    private final PApplet SW;

    //Used to refer to the workflow
    private final WorkFlow myWorkflow;

    //Event Manger, needed to manage the Button events
    private final Events_Manager EM = new Events_Manager();

    //Playing in multiplayer necessitate to have two separate Plays
    private Play actual_game1, actual_game2;

    //And two separate boards because there are two game progresses
    private Board game_board1, game_board2;

    //############################## TEXT VARS #################################################
    //These two texts are used to indicate which player is the actual turn
    private final GameText P1text; //Player 1
    private final GameText P2text; //Player 2

    //####################### VARIABLE TO PLAY ########################################
    //The color selected from the board palette by the player. It's used to give to the board slot that color
    //Default = -1 produce no effects.
    private int selected_color=-1;

    //What Player is the one who is doing the turn. It's a boolean because we have simply two players
    private boolean actualPlayer = true; //TRUE: Player1, FALSE: Player2

    //########################## BUTTONS ##############################################

    //When a guess is complete, this button will start then a sequence to check this guess
    protected final Button CheckButton;

    //############################ CLASS CONSTRUCTOR ######################################
    public MultiPlayBoard(PApplet arg1, WorkFlow WF){

        //Set the reference to the workflow and Processing application
        SW = arg1;
        myWorkflow = WF;

        //Instantiate and set characteristics of the Check Button
        CheckButton = new Button("CHECK",SW);
        CheckButton.setPosition(Math.round(SW.width*0.16F),SW.height/16);
        CheckButton.setButtonColor(0,255,217);
        CheckButton.setTextColor(0,0,0);
        CheckButton.setSize(Math.round(SW.width*0.68F),SW.height/8);
        CheckButton.setTextSizePct(0.6F);
        CheckButton.setPaddingsPct(0.375F,0.15F);

        //Instantiate and set characteristics of the Game Text of Player 1 (Player 1 turn)
        P1text = new GameText(SW);
        P1text.setText("P1");
        P1text.setTextColor(255,0,0);
        P1text.setPositions(Math.round(SW.width*0.80F),SW.height/4);
        P1text.setSize(Math.round(SW.width*0.08F));
        P1text.setTextSize(12F);

        //Instantiate and set characteristics of the Game Text of Player 1 (Player 2 turn)
        P2text = new GameText(SW);
        P2text.setText("P2");
        P2text.setTextColor(0,0,255);
        P2text.setPositions(Math.round(SW.width*0.80F),SW.height/4);
        P2text.setSize(Math.round(SW.width*0.08F));
        P2text.setTextSize(12F);
    }

    //########################## PLAYBOARD FUNCTIONS ###############################
    //These functions are used to control different aspects of the PlayBoard. Some of them are accessible externally
    //while other are simply support functions used by other internal methods.

    //This function will initialize the PlayBoard. It's fundamental not only for its instancing role but also to reset
    //the PlayBoard for the next new games.
    @Override
    public void initializePlayBoard(){

        //Instantiate, initialize the Play Class associated to Player 1 and set the difficulty
        actual_game1 = new Play();
        actual_game1.setDifficulty(myWorkflow.getDifficulty());
        actual_game1.initializeGame();

        //As well for the Player 2 one's
        actual_game2 = new Play();
        actual_game2.setDifficulty(myWorkflow.getDifficulty());
        actual_game2.initializeGame();

        //However the two secret code must coincide so the secret code of the Play of the Player 2 is
        //set equal to the secret code of the Player 1's game.
        actual_game2.setSecretCode(actual_game1.getSecretCode());

        //According to the difficulty (it's the same for both) load the correct board version.
        //An error raises for the default cases.
        switch (actual_game1.getInput()){
            case "EASY":
                game_board1 = new EasyBoard(SW,myWorkflow,this);
                game_board2 = new EasyBoard(SW,myWorkflow,this);
                break;
            case "MEDIUM":
                game_board1 = new MediumBoard(SW,myWorkflow,this);
                game_board2 = new MediumBoard(SW,myWorkflow,this);
                break;
            case "HARD":
                game_board1 = new HardBoard(SW,myWorkflow,this);
                game_board2 = new HardBoard(SW,myWorkflow,this);
                break;
            default:
                throw new RuntimeException("MultiPlayBoard: initializePlayBoard: not defined input");
        }

        //Activate the interactivity of the first slot of the board for both players
        game_board1.slotGroupActivation(0);
        game_board2.slotGroupActivation(0);

        //Clean all the old unreferenced vars.
        System.gc();
    }

    //This function is used to retrieve the player guess from the board and compute the feedback
    //To assign the progresses correctly the actual player is checked
    @Override
    protected void FeedbackCheck(){
        if(actualPlayer) {
            //Get the player guess from the player's board actual slots
            int[] player_guess = game_board1.getSlotsContent(actual_game1.getCount());
            //Compute the feedback using the feedback manager of the game
            actual_game1.FeedbackManager(player_guess);
            //Set the feedback slots on the player's board
            game_board1.setFeedbackSlots(actual_game1.getFeedback(), actual_game1.getCount());
        }
        else{
            int[] player_guess = game_board2.getSlotsContent(actual_game2.getCount());
            actual_game2.FeedbackManager(player_guess);
            game_board2.setFeedbackSlots(actual_game2.getFeedback(), actual_game2.getCount());
        }
    }

    //Valid Guess will player guess is valid a.k.a. all of the 4 slots are filled with a color.
    @Override
    protected boolean ValidGuess(){

        int[] player_guess;

        //Check which player's turn is and act conform
        if(actualPlayer){
            player_guess = game_board1.getSlotsContent(actual_game1.getCount());
        }
        else{
            player_guess = game_board2.getSlotsContent(actual_game2.getCount());
        }

        for (int i : player_guess) {
            if (i == -1) {
                return false;
            }
        }

        return true;
    }

    //########################### SHOW FUNCTIONS #############################################

    //Show the Playboard of Player 1
    private void showPlayBoardP1(){
        //It's fundamental that the game of Player 1 is initialized (it's done inside the Playboard initialization)
        //If it's false an error raise.
        if(actual_game1.getInitialized()){
            SW.background(0); //set the background color (0=BLACK)

            game_board1.showBoard(); //Render the board,
            CheckButton.showButton(); //the check button
            P1text.showText(); //and the identifier text

            //The Check Button procedure if the guess is valid
            if(EM.Button_Pressed(CheckButton,SW) && ValidGuess()){
                FeedbackCheck(); //1° Obtain the feedback
                game_board1.slotGroupDeactivation(actual_game1.getCount()); //° deactivate the actual slots
                actual_game1.AugCount(); //and move to the next turn
                //Now we have three possibilities:

                //A: Player 1 winning (guess of P1 is the secret code)
                if(actual_game1.getWinningStatus()){
                    //Go to the win menu but before communicate the winner to the EndGameMenus
                    myWorkflow.getEndgamemenus().setWinner(actualPlayer);
                    myWorkflow.nextStep();
                    SW.clear(); //Clear all previous elements from the screen
                }

                //B: Drawing (no more trials allowed and no one has guess correctly)
                if(actual_game1.getCount()> actual_game1.getTrials()-1){
                    //Give the secret code to the end menu e move to the drawing menu
                    myWorkflow.getEndgamemenus().setSecretCode(actual_game1.getSecretCode(), game_board1.getPalette());
                    myWorkflow.GoToStep(11);
                    SW.clear();
                }

                //C: Prepare for the next turn
                else {
                    //Activate the new sets of slots and continue
                    game_board1.slotGroupActivation(actual_game1.getCount());
                }

                //And switch actual player to start Player 2 turn
                actualPlayer = false;
            }
        }
        else{
            throw new RuntimeException("MultiPlayBoard: showPlayBoardP1: Game not initialized");
        }
    }

    //Show the Playboard of Player 2
    private void showPlayBoardP2() {
        //It's fundamental that the game of Player 2 is initialized (it's done inside the Playboard initialization)
        //If it's false an error raise.
        if(actual_game2.getInitialized()){
            SW.background(0); //set the background color (0=BLACK)

            game_board2.showBoard(); //Render the board,
            CheckButton.showButton(); //the check button
            P2text.showText(); //and the identifier text

            //The Check Button procedure if the guess is valid
            if(EM.Button_Pressed(CheckButton,SW) && ValidGuess()){
                FeedbackCheck(); //1° Obtain the feedback
                game_board2.slotGroupDeactivation(actual_game2.getCount()); //° deactivate the actual slots
                actual_game2.AugCount(); //and move to the next turn
                //Now we have three possibilities:

                //A: Player 2 winning (guess of P2 is the secret code)
                if(actual_game2.getWinningStatus()){
                    //Go to the win menu but before communicate the winner to the EndGameMenus
                    myWorkflow.getEndgamemenus().setWinner(actualPlayer);
                    myWorkflow.nextStep();
                    SW.clear(); //Clear all previous elements from the screen
                }

                //B: Drawing (no more trials allowed and no one has guess correctly)
                if(actual_game2.getCount()> actual_game2.getTrials()-1){
                    myWorkflow.getEndgamemenus().setSecretCode(actual_game2.getSecretCode(), game_board2.getPalette());
                    myWorkflow.GoToStep(11);
                    SW.clear();
                }

                //C: Prepare for the next turn
                else {
                    //Activate the new sets of slots and continue
                    game_board2.slotGroupActivation(actual_game2.getCount());
                }

                //And switch actual player to start Player 1 turn
                actualPlayer = true;
            }
        }
        else{
            throw new RuntimeException("MultiPlayBoard: showPlayBoardP2: Game not initialized");
        }
    }

    //This generic function will show the correct board according to the actualPlayer
    @Override
    public void showPlayBoard(){
        if(actualPlayer){
            showPlayBoardP1();
        }
        else{
            showPlayBoardP2();
        }
    }

    //############################ SET METHODS  ###########################################

    //This method is used to set the selected color. It's called when there is a mouse press on the palette (see Board)
    @Override
    public void setSelectedColor(int i) {
        if(i>=0) {
            selected_color = i;
        }
        else{
            throw new RuntimeException("MultiPlayBoard: setSelectedColor: Selected Color cannot be a negative number");
        }
    }

    //########################### GET METHODS ########################################

    //As the one above, this one will return the actual selected color. Used to give color to the slots (Board)
    @Override
    public int getSelectedColor(){
        return selected_color;
    }

}

package Engine.Playboards;

import Engine.Boards.Board;
import Engine.Boards.EasyBoard;
import Engine.Boards.HardBoard;
import Engine.Boards.MediumBoard;
import Engine.GraphicElements.Button;
import Engine.Events_Manager;
import Engine.WorkFlow;
import LetGameRun.Play;
import processing.core.PApplet;

//############################### CLASS DESCRIPTION #############################################################
/*
This PlayBoard is created to manage the single-player games. It's an extension of the abstract class GenericPlayBoard
introduced to link the Boards to the Multiplayer and Single-player PlayBoards which have different implementation of
some methods. The PlayBoard manage the link between the graphic and the back-end game allowing this two element to
interact together.
 */

public class SinglePlayBoard extends GenericPlayBoard {

    //######################### FUNDAMENTALS VARIABLES ######################################
    //This section contains the fundamental vars necessary for the class to work properly.

    //Used to refer to the Processing Application
    private final PApplet SW;

    //The game is being play on the playboard
    private Play actual_game;

    //Used to refer to the workflow
    private final WorkFlow myWorkflow;

    //The Board needed to play the game, depends on the difficulty
    private Board gameboard;

    //Event Manger, needed to manage the Button events
    private final Events_Manager EM = new Events_Manager();

    //####################### VARIABLE TO PLAY ########################################

    //The color selected from the board palette by the player. It's used to give to the board slot that color
    //Default = -1 produce no effects.
    private int selected_color=-1;

    //########################## BUTTONS ##############################################

    //When a guess is complete, this button will start then a sequence to check this guess
    private final Button CheckButton;

    //############################ CLASS CONSTRUCTOR ######################################
    public SinglePlayBoard(PApplet arg1, WorkFlow WF){

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
    }

    //########################## PLAYBOARD FUNCTIONS ###############################
    //These functions are used to control different aspects of the PlayBoard. Some of them are accessible externally
    //while other are simply support functions used by other internal methods.

    //This function will initialize the PlayBoard. It's fundamental not only for its instancing role but also to reset
    //the PlayBoard for the next new games.
    @Override
    public void initializePlayBoard(){

        //Instantiate, initialize the game class and set the difficulty
        actual_game = new Play();
        actual_game.setDifficulty(myWorkflow.getDifficulty());
        actual_game.initializeGame();

        //According to the difficulty load the correct board version. An error raises for the default cases.
        switch (actual_game.getInput()){
            case "EASY":
                gameboard = new EasyBoard(SW,myWorkflow,this); //Easy
                break;
            case "MEDIUM":
                gameboard = new MediumBoard(SW,myWorkflow,this); //Medium
                break;
            case "HARD":
                gameboard = new HardBoard(SW,myWorkflow,this); //Hard
                break;
            default:
                throw new RuntimeException("SinglePlayBoard: initializePlayboard: undefined input");
        }

        //Activate the interactivity of the first slot of the board
        gameboard.slotGroupActivation(0);

        System.gc(); //Clean all the old unreferenced vars.
    }

    //This function is used to retrieve the player guess from the board and compute the feedback
    @Override
    protected void FeedbackCheck(){
        //Get the player guess from the board actual slots
        int[] player_guess = gameboard.getSlotsContent(actual_game.getCount());

        //Compute the feedback using the feedback manager of the game
        actual_game.FeedbackManager(player_guess);

        //Set the feedback slots on the board
        gameboard.setFeedbackSlots(actual_game.getFeedback(), actual_game.getCount());
    }

    //Valid Guess will player guess is valid a.k.a. all of the 4 slots are filled with a color.
    @Override
    protected boolean ValidGuess(){

        int[] player_guess = gameboard.getSlotsContent(actual_game.getCount());

        for (int i : player_guess) {
            if (i == -1) {
                return false;
            }
        }

        return true;
    }

    //########################### SHOW FUNCTIONS #############################################

    //Show the Playboard
    @Override
    public void showPlayBoard(){
        //It's fundamental that the game is initialized (it's done inside the Playboard initialization)
        //If it's false an error raise.
        if(actual_game.getInitialized()){
            SW.background(0); //set the background color (0=BLACK)

            gameboard.showBoard(); //Render the board
            CheckButton.showButton(); //and the check button

            //The Check Button procedure if the guess is valid
            if(EM.Button_Pressed(CheckButton,SW) && ValidGuess()){
                FeedbackCheck(); //1° Obtain the feedback
                gameboard.slotGroupDeactivation(actual_game.getCount()); //° deactivate the actual slots
                actual_game.AugCount(); //and move to the next turn
                //Now we have three possibilities:

                //A: Winning (guess is the secret code)
                if(actual_game.getWinningStatus()){
                    //Go to the win menu
                    myWorkflow.nextStep();
                    SW.clear();  //Clear all previous elements from the screen
                }

                //B: Losing (no more trials allowed)
                if(actual_game.getCount()> actual_game.getTrials()-1){
                    //Give the secret code to the end menu e move to the defeat menu
                    myWorkflow.getEndgamemenus().setSecretCode(actual_game.getSecretCode(),gameboard.getPalette());
                    myWorkflow.GoToStep(5);
                    SW.clear();
                }

                //C: Try Again
                else {
                    //Activate the new sets of slots and continue
                    gameboard.slotGroupActivation(actual_game.getCount());
                }
            }
        }
        else{
            throw new RuntimeException("SinglePlayboard: showPlayBoard: Game not initialized");
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
            throw new RuntimeException("SinglePlayBoard: setSelectedColor: selected color cannot be a negative number");
        }
    }

    //########################### GET METHODS ########################################

    //As the one above, this one will return the actual selected color. Used to give color to the slots (Board)
    @Override
    public int getSelectedColor(){
        return selected_color;
    }

}

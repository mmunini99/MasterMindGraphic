package Engine.Playboards;

import Engine.Boards.Board;
import Engine.Boards.EasyBoard;
import Engine.Boards.HardBoard;
import Engine.Boards.MediumBoard;
import Engine.GraphicElements.Button;
import Engine.EventsManager;
import Engine.Workflow;
import LetGameRun.Play;
import processing.core.PApplet;

//############################### CLASS DESCRIPTION #############################################################
/*
This PlayBoard is created to manage the single-player games. It's an extension of the abstract class GenericPlayBoard
introduced to link the Boards to the Multiplayer and Single-player PlayBoards which have different implementation of
some methods. The PlayBoard manage the link between the graphic and the back-end game allowing this two element to
interact together.
 */

public class SinglePlayboard extends GenericPlayboard {

    //######################### FUNDAMENTALS VARIABLES ######################################
    //This section contains the fundamental vars necessary for the class to work properly.

    private final PApplet SW;

    private Play actualGame;

    private final Workflow myWorkflow;

    private Board gameboard;

    private final EventsManager EM = new EventsManager();

    //####################### VARIABLE TO PLAY ########################################

    private int selectedColor =-1;

    //########################## BUTTONS ##############################################

    private final Button checkButton;

    //############################ CLASS CONSTRUCTOR ######################################
    public SinglePlayboard(PApplet arg1, Workflow WF){

        SW = arg1;
        myWorkflow = WF;

        checkButton = new Button("CHECK",SW);
        checkButton.setPosition(Math.round(SW.width*0.16F),SW.height/16);
        checkButton.setButtonColor(0,255,217);
        checkButton.setTextColor(0,0,0);
        checkButton.setSize(Math.round(SW.width*0.68F),SW.height/8);
        checkButton.setTextSizePct(0.6F);
        checkButton.setPaddingsPct(0.375F,0.15F);
    }

    //########################## PLAYBOARD FUNCTIONS ###############################
    //These functions are used to control different aspects of the PlayBoard. Some of them are accessible externally
    //while other are simply support functions used by other internal methods.

    @Override
    public void initializePlayBoard(){

        actualGame = new Play();
        actualGame.initializePlay(myWorkflow.getDifficultyAsString());

        switch (myWorkflow.getDifficultyAsString()){
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

        gameboard.slotGroupActivation(0);

        System.gc();
    }

    @Override
    protected void feedbackComputation(){

        int[] playerGuess = gameboard.getSlotsContent(actualGame.getCount());

        actualGame.computeFeedback(playerGuess);

        gameboard.setFeedbackSlots(adaptFeedback(actualGame.feedback), actualGame.getCount());
    }

    @Override
    protected boolean validGuess(){

        int[] playerGuess = gameboard.getSlotsContent(actualGame.getCount());

        for (int i : playerGuess) {
            if (i == -1) {
                return false;
            }
        }

        return true;
    }



    //########################### SHOW FUNCTIONS #############################################

    @Override
    public void showPlayBoard(){
        //initialized game is need -> playboard initialization
        if(actualGame.getInitialized()){
            SW.background(0); //set the background color

            gameboard.showBoard();
            checkButton.showButton();

            //Check button sequence:
            if(EM.isButtonPressed(checkButton,SW) && validGuess()){
                feedbackComputation();

                gameboard.slotGroupDeactivation(actualGame.getCount());
                actualGame.AugCount(); //move to the next turn
                //Now we have three possibilities:

                //3 Possibilities:
                //A: Victory
                if(actualGame.wasSecretCodeGuessed(actualGame.feedback)){
                    myWorkflow.nextStep();
                    SW.clear();  //Clear all previous elements from the screen
                }

                //B: Defeat
                if(!actualGame.areTrialsLeft(actualGame.getCount())){
                    myWorkflow.getEndGameMenus().setSecretCode(actualGame.getSecretCode(),gameboard.getPalette());
                    myWorkflow.goToStep(5);
                    SW.clear();
                }

                //C: New turn
                else {
                    gameboard.slotGroupActivation(actualGame.getCount());
                }
            }
        }
        else{
            throw new RuntimeException("SinglePlayboard: showPlayBoard: Game not initialized");
        }
    }

    //############################ SET METHODS  ###########################################

    @Override
    public void setSelectedColor(int i) {
        if(i>=0) {
            selectedColor = i;
        }
        else{
            throw new RuntimeException("SinglePlayBoard: setSelectedColor: selected color cannot be a negative number");
        }
    }

    //########################### GET METHODS ########################################

    @Override
    public int getSelectedColor(){
        return selectedColor;
    }

}

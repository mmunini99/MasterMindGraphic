package Engine.Playboards;

import Engine.Boards.Board;
import Engine.Boards.EasyBoard;
import Engine.Boards.HardBoard;
import Engine.Boards.MediumBoard;
import Engine.GraphicElements.Button;
import Engine.EventsManager;
import Engine.GraphicElements.GameText;
import Engine.Workflow;
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

public class MultiPlayboard extends GenericPlayboard {

    //######################### FUNDAMENTALS VARIABLES ######################################
    //This section contains the fundamental vars necessary for the class to work properly.

    private final PApplet SW;

    private final Workflow myWorkflow;

    private final EventsManager EM = new EventsManager();

    private Play actualGamePlayer1, actualGamePlayer2;

    private Board gameboardPlayer1, gameboardPlayer2;

    //############################## TEXT VARS #################################################
    //These two texts are used to indicate which player is the actual turn

    private final GameText textP1;
    private final GameText textP2;

    //####################### VARIABLE TO PLAY ########################################
    private int selectedColor =-1;
    private boolean actualPlayer = true; //TRUE: Player1, FALSE: Player2

    //########################## BUTTONS ##############################################
    protected final Button checkButton;

    //############################ CLASS CONSTRUCTOR ######################################
    public MultiPlayboard(PApplet arg1, Workflow WF){
        SW = arg1;
        myWorkflow = WF;

        checkButton = new Button("CHECK",SW);
        checkButton.setPosition(Math.round(SW.width*0.16F),SW.height/16);
        checkButton.setButtonColor(0,255,217);
        checkButton.setTextColor(0,0,0);
        checkButton.setSize(Math.round(SW.width*0.68F),SW.height/8);
        checkButton.setTextSizePct(0.6F);
        checkButton.setPaddingsPct(0.375F,0.15F);

        textP1 = new GameText(SW);
        textP1.setText("P1");
        textP1.setTextColor(255,0,0);
        textP1.setPositions(Math.round(SW.width*0.80F),SW.height/4);
        textP1.setSize(Math.round(SW.width*0.08F));
        textP1.setTextSize(12F);

        textP2 = new GameText(SW);
        textP2.setText("P2");
        textP2.setTextColor(0,0,255);
        textP2.setPositions(Math.round(SW.width*0.80F),SW.height/4);
        textP2.setSize(Math.round(SW.width*0.08F));
        textP2.setTextSize(12F);
    }

    //########################## PLAYBOARD FUNCTIONS ###############################
    //These functions are used to control different aspects of the PlayBoard. Some of them are accessible externally
    //while other are simply support functions used by other internal methods.

    @Override
    public void initializePlayBoard(){

        actualGamePlayer1 = new Play();
        actualGamePlayer1.inizializePlay(myWorkflow.getDifficultyAsString());

        actualGamePlayer2 = new Play();
        actualGamePlayer2.inizializePlay(myWorkflow.getDifficultyAsString());

        actualGamePlayer2.setSecretCode(actualGamePlayer1.getSecretCode());

        switch (myWorkflow.getDifficultyAsString()){
            case "EASY":
                gameboardPlayer1 = new EasyBoard(SW,myWorkflow,this);
                gameboardPlayer2 = new EasyBoard(SW,myWorkflow,this);
                break;
            case "MEDIUM":
                gameboardPlayer1 = new MediumBoard(SW,myWorkflow,this);
                gameboardPlayer2 = new MediumBoard(SW,myWorkflow,this);
                break;
            case "HARD":
                gameboardPlayer1 = new HardBoard(SW,myWorkflow,this);
                gameboardPlayer2 = new HardBoard(SW,myWorkflow,this);
                break;
            default:
                throw new RuntimeException("MultiPlayBoard: initializePlayBoard: not defined input");
        }

        gameboardPlayer1.slotGroupActivation(0);
        gameboardPlayer2.slotGroupActivation(0);

        System.gc();
    }

    @Override
    protected void feedbackComputation(){
        if(actualPlayer) {

            int[] player_guess = gameboardPlayer1.getSlotsContent(actualGamePlayer1.getCount());

            actualGamePlayer1.computeFeedback(player_guess);

            gameboardPlayer1.setFeedbackSlots(adaptFeedback(actualGamePlayer1.feedback), actualGamePlayer1.getCount());
        }
        else{
            int[] player_guess = gameboardPlayer2.getSlotsContent(actualGamePlayer2.getCount());

            actualGamePlayer2.computeFeedback(player_guess);

            gameboardPlayer2.setFeedbackSlots(adaptFeedback(actualGamePlayer2.feedback), actualGamePlayer2.getCount());
        }
    }

    @Override
    protected boolean validGuess(){

        int[] playerGuess;

        if(actualPlayer){
            playerGuess = gameboardPlayer1.getSlotsContent(actualGamePlayer1.getCount());
        }
        else{
            playerGuess = gameboardPlayer2.getSlotsContent(actualGamePlayer2.getCount());
        }

        for (int i : playerGuess) {
            if (i == -1) {
                return false;
            }
        }

        return true;
    }

    //########################### SHOW FUNCTIONS #############################################

    private void showPlayBoardP1(){
        //initialized game is need -> playboard initialization
        if(actualGamePlayer1.getInitialized()){
            SW.background(0); //set the background color

            gameboardPlayer1.showBoard();
            checkButton.showButton();
            textP1.showText();

            //Check button sequence:
            if(EM.isButtonPressed(checkButton,SW) && validGuess()){
                feedbackComputation();

                gameboardPlayer1.slotGroupDeactivation(actualGamePlayer1.getCount());
                actualGamePlayer1.AugCount(); //move to the next turn

                //3 Possibilities:
                //A: P1 Wins
                if(actualGamePlayer1.wasSecretCodeGuessed(actualGamePlayer1.feedback)){
                    myWorkflow.getEndGameMenus().setWinner(actualPlayer);
                    myWorkflow.nextStep();
                    SW.clear(); //Clear the screen
                }

                //B: Drawing
                if(!actualGamePlayer1.areTrialsLeft(actualGamePlayer1.getCount()-1)){
                    myWorkflow.getEndGameMenus().setSecretCode(actualGamePlayer1.getSecretCode(), gameboardPlayer1.getPalette());
                    myWorkflow.goToStep(11);
                    SW.clear();
                }

                //C: New turn
                else {
                    gameboardPlayer1.slotGroupActivation(actualGamePlayer1.getCount());
                }

                //Change player
                actualPlayer = false;
            }
        }
        else{
            throw new RuntimeException("MultiPlayBoard: showPlayBoardP1: Game not initialized");
        }
    }

    private void showPlayBoardP2() {
        //initialized game is need -> playboard initialization
        if(actualGamePlayer2.getInitialized()){
            SW.background(0); //set the background color

            gameboardPlayer2.showBoard();
            checkButton.showButton();
            textP2.showText();

            //Check button sequence:
            if(EM.isButtonPressed(checkButton,SW) && validGuess()){
                feedbackComputation();

                gameboardPlayer2.slotGroupDeactivation(actualGamePlayer2.getCount());
                actualGamePlayer2.AugCount(); //move to the next turn

                //3 Possibilities:
                //A: P1 Wins
                if(actualGamePlayer2.wasSecretCodeGuessed(actualGamePlayer2.feedback)){
                    myWorkflow.getEndGameMenus().setWinner(actualPlayer);
                    myWorkflow.nextStep();
                    SW.clear(); //Clear the screen
                }

                //B: Drawing
                if(!actualGamePlayer2.areTrialsLeft(actualGamePlayer2.getCount()-1)){
                    myWorkflow.getEndGameMenus().setSecretCode(actualGamePlayer2.getSecretCode(), gameboardPlayer2.getPalette());
                    myWorkflow.goToStep(11);
                    SW.clear();
                }

                //C: New turn
                else {
                    gameboardPlayer2.slotGroupActivation(actualGamePlayer2.getCount());
                }

                //Change player
                actualPlayer = true;
            }
        }
        else{
            throw new RuntimeException("MultiPlayBoard: showPlayBoardP2: Game not initialized");
        }
    }

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

    @Override
    public void setSelectedColor(int i) {
        if(i>=0) {
            selectedColor = i;
        }
        else{
            throw new RuntimeException("MultiPlayBoard: setSelectedColor: Selected Color cannot be a negative number");
        }
    }

    //########################### GET METHODS ########################################

    @Override
    public int getSelectedColor(){
        return selectedColor;
    }

}

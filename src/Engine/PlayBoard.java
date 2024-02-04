package Engine;

import Engine.Boards.Board;
import Engine.Boards.EasyBoard;
import Engine.Boards.HardBoard;
import Engine.Boards.MediumBoard;
import LetGameRun.Play;
import processing.core.PApplet;

public class PlayBoard {
    private final PApplet SW;
    private Play actualgame;
    private final WorkFlow myWorkflow;
    private Board gameboard;

    private final Events_Manager EM = new Events_Manager();

    //####################### VARIABLE TO PLAY ########################################
    private int selected_color=-1;

    //########################## BUTTONS ##############################################

    private final Button CheckButton;

    //############################ CONSTRUCTOR ######################################

    public PlayBoard(PApplet arg1, WorkFlow WF){
        SW = arg1;
        myWorkflow = WF;

        //CHECK BUTTON
        CheckButton = new Button("CHECK",SW);
        CheckButton.setPosition(SW.width/3,50);
        CheckButton.setButtonColor(0,255,217);
        CheckButton.setTextcolor(0,0,0);
        CheckButton.setSize(SW.width/3,100);
        CheckButton.setTextsizePerc(0.6F);
        CheckButton.setPaddingsPerc(0.35F,0.15F);
    }

    //########################## PLAYBOARD FUNCTIONS ###############################

    public void initializePlayBoard(){
        System.gc();
        actualgame = new Play();
        actualgame.setDifficulty(myWorkflow.difficulty);
        actualgame.initializeGame();
        switch (actualgame.getInput()){
            case "EASY":
                gameboard = new EasyBoard(SW,myWorkflow,this);
                break;
            case "MEDIUM":
                gameboard = new MediumBoard(SW,myWorkflow,this);
                break;
            case "HARD":
                gameboard = new HardBoard(SW,myWorkflow,this);
                break;
            default:
                throw new RuntimeException("Input non definito");
        }
        gameboard.slotGroupActivation(0);
    }

    private void FeedbackCheck(){
        int[] playerguess = gameboard.getSlotsContent(actualgame.getCount());
        actualgame.FeedbackManager(playerguess);

        //TODO: print da rimuovere serve solo per testing
        System.out.println("PLAYER GUESS:");
        for(int i=0;i<playerguess.length;i++){
            System.out.println(playerguess[i]);
        }
        System.out.println("FEEDBACK");
        for(int i=0;i<actualgame.getFeedback().length;i++){
            System.out.println(actualgame.getFeedback()[i]);
        }

        gameboard.setFeedbackSlots(actualgame.getFeedback(),actualgame.getCount());

    }

    private boolean ValidGuess(){

        int[] playerguess = gameboard.getSlotsContent(actualgame.getCount());

        for(int j=0; j<playerguess.length;j++){
            if(playerguess[j]==-1){
                return false;
            }
        }

        return true;
    }

    //########################### SHOW #############################################

    public void showPlayBoard(){
        if(actualgame.getInitialized()){
            SW.background(0);
            gameboard.showBoard();
            CheckButton.showButton();
            if(EM.Button_Pressed(CheckButton,SW) && ValidGuess()){
                FeedbackCheck();
                gameboard.slotGroupDeactivation(actualgame.getCount());
                actualgame.AugCount();
                //Winning
                if(actualgame.getwinningstatus()){
                    System.out.println("HAI VINTO!!!!");
                    //TODO: ci sarà da aggiungere poi la registrazione dei punteggi e altre cose
                    myWorkflow.nextStep();
                    SW.clear();
                }
                //Losing
                if(actualgame.getCount()>=actualgame.getTrials()-1){
                    System.out.println("HAI PERSO!!!!");
                    //TODO: ci sarà da aggiungere poi la registrazione dei punteggi e altre cose
                    myWorkflow.endgamemenus.setSecretCode(actualgame.getSecretCode(),gameboard.getPalette());
                    myWorkflow.GoToStep(5);
                    SW.clear();
                }
                gameboard.slotGroupActivation(actualgame.getCount());
            }
        }
        else{
            throw new RuntimeException("Game not inizialized");
        }
    }

    //############################ SET METHODS  ###########################################

    public void setSelectedColor(int i) {
        if(i>=0) {
            selected_color = i;
        }
        else{
            throw new RuntimeException("Selected Color cannot be a negative number");
        }
    }

    //########################### GET METHODS ########################################

    public int getSelectedColor(){
        return selected_color;
    }

}

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

    private Button CheckButton;

    //############################ CONSTRUCTOR ######################################

    public PlayBoard(PApplet arg1, WorkFlow WF){
        SW = arg1;
        myWorkflow = WF;

        //CHECK BUTTON
        CheckButton = new Button("CHECK",SW);
        CheckButton.setPosition(SW.width/3,50);
        CheckButton.setButtonColor(180,180,180);
        CheckButton.setTextcolor(0,0,0);
        CheckButton.setSize(SW.width/3,100);
        CheckButton.setTextsizePerc(0.6F);
        CheckButton.setPaddingsPerc(0.35F,0.15F);
    }

    //########################## PLAYBOARD FUNCTIONS ###############################

    public void initializePlayBoard(){
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

    //########################### SHOW #############################################

    public void showPlayBoard(){
        if(actualgame.getInitialized()){
            SW.background(0);
            gameboard.showBoard();
            CheckButton.showButton();
            if(EM.Button_Pressed(CheckButton,SW)){
                //TODO: sarà poi da aggiungere tutta la cosa di controllare l'input ecc...
                gameboard.slotGroupDeactivation(actualgame.getCount());
                actualgame.AugCount();
                if(actualgame.getCount()>=actualgame.getTrials()-1){
                    SW.exit(); //TODO: aggiustare poi con la sequenza di chiusura
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

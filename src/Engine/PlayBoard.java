package Engine;

import Engine.Boards.Board;
import Engine.Boards.EasyBoard;
import Engine.Boards.HardBoard;
import Engine.Boards.MediumBoard;
import LetGameRun.Play;
import processing.core.PApplet;

public class PlayBoard {
    private PApplet SW;
    private Play actualgame;
    private WorkFlow myWorkflow;

    private Board gameboard;

    //############################ COSTRUTTORE ######################################

    public PlayBoard(PApplet arg1, Play arg2, WorkFlow WF){
        SW = arg1;
        actualgame = arg2;
        myWorkflow = WF;
    }

    //########################## PLAYBOARD FUNCTIONS ###############################

    public void initializePlayBoard(){
        switch (actualgame.getInput()){
            case "EASY":
                gameboard = new EasyBoard(SW,myWorkflow);
                break;
            case "MEDIUM":
                gameboard = new MediumBoard(SW,myWorkflow);
                break;
            case "HARD":
                gameboard = new HardBoard(SW,myWorkflow);
                break;
            default:
                throw new RuntimeException("Input non definito");
        }
    }

    //########################### SHOW #############################################

    public void showPlayBoard(){
        if(actualgame.getInitialized()){
            //System.out.println(actualgame.getInput()); //TODO: to remove
            SW.background(0);
            gameboard.showBoard();
        }
        else{
            throw new RuntimeException("Game not inizialized");
        }
    }
}

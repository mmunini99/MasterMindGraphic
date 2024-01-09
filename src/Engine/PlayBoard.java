package Engine;

import Engine.Boards.Board;
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
        gameboard = new Board(SW,WF);
    }

    //########################### SHOW #############################################

    public void showPlayBoard(){
        if(actualgame.getInitialized()){
            SW.background(0);
            gameboard.showBoard();
        }
        else{
            throw new RuntimeException("Game not inizialized");
        }
    }
}

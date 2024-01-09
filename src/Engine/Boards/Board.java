package Engine.Boards;

import Engine.Button;
import Engine.Events_Manager;
import Engine.RGB;
import Engine.WorkFlow;
import processing.core.PApplet;
import processing.core.PImage;

public class Board {

    private PApplet SW;
    private WorkFlow myWorkflow;
    private Events_Manager EM = new Events_Manager();
    private RGB[] Palette;
    private int[][] PalettePositions;

    private PImage imageboard;

    private Button BackButton;

    //##################### CONSTRUCTOR ######################################################

    public Board(PApplet arg1,WorkFlow WF){
        SW = arg1;
        myWorkflow = WF;

        BackButton = new Button(SW.loadImage("Images/back.jpg"),SW);
        BackButton.setPosition(100,50);
        BackButton.setButtonColor(0,0,0);
        BackButton.setSize(100,100);
    }

    //################### SET FUNCTIONS FOR THE SUBCLASSES ###################################
    protected void setPalette(RGB[] specPalette){
        Palette = specPalette;
    }

    protected void setPalettePositions(int[][] PP){
        PalettePositions = PP;
    }

    protected void setImageboard(PImage P){
        imageboard = P;
    }


    //################# SHOW ###############################################################

    public void showBoard(){
        //General buttons
        BackButton.showButton();
        if(EM.Button_Pressed(BackButton,SW)){
            myWorkflow.previousStep();
            SW.clear();
        }

    }

}

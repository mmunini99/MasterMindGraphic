package Engine.Boards;

import Engine.Button;
import Engine.Events_Manager;
import Engine.RGB;
import Engine.WorkFlow;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class Board {

    //########################## FUNDAMENTAL VARS ###############################################
    protected PApplet SW;
    private WorkFlow myWorkflow;
    private Events_Manager EM = new Events_Manager();

    //######################## COLOR PALETTE ##################################################
    // ASSIGNED IN THE SUBCLASS CONSTRUCTOR
    private RGB[] Palette;
    private int[][] PalettePositions;

    private List<Button> PaletteButtons= new ArrayList<Button>();

    //####################### TRIALS ##########################################################
    //ASSIGNED IN THE SUBCLASS CONSTRUCTOR
    private int NumberOfTrials;

    //####################### BOARD IMAGE #####################################################
    private PImage imageboard;

    //##################### GENERAL BUTTONS #################################################
    private Button BackButton;
    private Button RulesButton;

    //##################### CONSTRUCTOR ######################################################

    public Board(PApplet arg1,WorkFlow WF){
        //Clean the memory of the previous boards and games
        System.gc();

        //######################## FUNDAMENTAL VARS #########################################
        SW = arg1;
        myWorkflow = WF;

        //######################## BACK BUTTON ########################################################
        BackButton = new Button(SW.loadImage("Images/back.jpg"),SW);
        BackButton.setPosition(100,50);
        BackButton.setButtonColor(0,0,0);
        BackButton.setSize(100,100);
        //####################### RULES BUTTON #######################################################
        RulesButton = new Button(SW.loadImage("Images/questionmark.png"),SW);
        RulesButton.setPosition(SW.width-200,50);
        RulesButton.setButtonColor(0,0,0);
        RulesButton.setSize(100,100);

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

    protected void setNumberOfTrials(int n){
        NumberOfTrials = n;
    }

    //################## PALETTE FUNCTIONS #################################################

    protected void setupPaletteButtons(){
        for(int i=0;i<Palette.length;i++){
            Button PB = new Button(SW);
            PB.setButtonColor(Palette[i].getR(),Palette[i].getG(),Palette[i].getB());
            PB.setPosition(PalettePositions[i][0],PalettePositions[i][1]);
            PB.setSize(200,200);
            PaletteButtons.add(PB);
        }
    }


    //################# SHOW ###############################################################

    public void showBoard(){
        //General buttons //TODO: BACK BUTTON NON FUNZIONA ANCORA. O MEGLIO MI FA TORNARE INDIETRO MA CAMBIANDO MODALITA'
        //TODO: IL GIOCO VIENE SEMPRE CARICATO NELLA PRIMA MODALITA' SCELTA
        BackButton.showButton();
        if(EM.Button_Pressed(BackButton,SW)){
            myWorkflow.previousStep();
            SW.clear();
        }
        RulesButton.showButton();
        //TODO: rulesbutton interaction

        //############################## IMAGES #########################################

        SW.image(imageboard,50,SW.height/2,SW.width-100,(SW.height/2)-50);

        //############################ PALETTE BUTTONS ##################################

        for(int i=0;i<PaletteButtons.size();i++){
            PaletteButtons.get(i).showButton();
        }

    }

}

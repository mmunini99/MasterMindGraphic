package Engine.Boards;

import Engine.*;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class Board {

    //########################## FUNDAMENTAL VARS ###############################################
    protected PApplet SW;
    private WorkFlow myWorkflow;
    private Events_Manager EM = new Events_Manager();

    private PlayBoard PB;

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

    protected int x1,y1,xsize,ysize;

    //##################### GENERAL BUTTONS #################################################
    private Button BackButton;
    private Button RulesButton;

    //###################### SLOTS #########################################################

    private Slot[][] slots;

    private Slot[][] feedback_slots;

    //##################### CONSTRUCTOR ######################################################

    public Board(PApplet arg1,WorkFlow WF,PlayBoard arg2){
        //Clean the memory of the previous boards and games
        System.gc();

        //######################## FUNDAMENTAL VARS #########################################
        SW = arg1;
        myWorkflow = WF;
        PB = arg2;

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

    //########################### SLOTS FUNCTIONS #########################################

    protected void setupSlots(int[][][] positions,int RX,int RY){
        slots = new Slot[positions.length][positions[0].length];
        for(int i=0;i<positions.length;i++){
            for(int j=0;j<positions[0].length;j++){
                slots[i][j] = new Slot(SW);
                slots[i][j].setPosition(positions[i][j][0],positions[i][j][1]);
                slots[i][j].setRadius(RX,RY);
            }
        }
    }

    protected void setupFeedbackSlots(int[][][] positions,int RX,int RY){
        feedback_slots = new Slot[positions.length][positions[0].length];
        RGB c = new RGB(255,0,0);
        for(int i=0;i<positions.length;i++){
            for(int j=0;j<positions[0].length;j++){
                feedback_slots[i][j] = new Slot(SW);
                feedback_slots[i][j].setPosition(positions[i][j][0],positions[i][j][1]);
                feedback_slots[i][j].setRadius(RX,RY);
                //TODO: QUESTO PEZZO DOPO E' POI DA RIMUOVERE
                feedback_slots[i][j].SlotActivation();
                feedback_slots[i][j].fillSlot(1);
                feedback_slots[i][j].setActiveColor(c);
                feedback_slots[i][j].SlotDeactivation();
            }
        }
    }

    public void slotGroupActivation(int i){
        if(i>=0 && i<slots.length){
            for(int j=0;j<slots[i].length;j++){
                slots[i][j].SlotActivation();
            }
        }
        else{
            throw new RuntimeException("Activated not existing group of slots");
        }
    }

    public void slotGroupDeactivation(int i){
        if(i>=0 && i<slots.length){
            for(int j=0;j<slots[i].length;j++){
                slots[i][j].SlotDeactivation();
            }
        }
        else{
            throw new RuntimeException("Activated not existing group of slots");
        }
    }

    //################# SHOW ###############################################################

    public void showBoard(){
        //General buttons
        BackButton.showButton();
        if(EM.Button_Pressed(BackButton,SW)){
            myWorkflow.previousStep();
            SW.clear();
        }
        RulesButton.showButton();
        //TODO: rulesbutton interaction

        //############################## IMAGES #########################################

        SW.image(imageboard,x1,y1,xsize,ysize);

        //############################ PALETTE BUTTONS ##################################

        for(int i=0;i<PaletteButtons.size();i++){
            PaletteButtons.get(i).showButton();
            if(EM.Button_Pressed(PaletteButtons.get(i),SW)){
                PB.setSelectedColor(i);
            }
        }

        for(int i=0;i<slots.length;i++){
            for(int j=0;j< slots[0].length;j++){
                slots[i][j].showSlot();
                if (EM.SlotPressed(slots[i][j], SW) && slots[i][j].isActiveSlot()) {
                    if (slots[i][j].isSlotEmpty() && PB.getSelectedColor() >= 0) {
                        slots[i][j].fillSlot(PB.getSelectedColor());
                        slots[i][j].setActiveColor(Palette[PB.getSelectedColor()]);
                    } else {
                            slots[i][j].emptySlot();
                    }
                }
            }
        }

        for(int i=0;i<feedback_slots.length;i++){
            for(int j=0;j<feedback_slots[0].length;j++){
                feedback_slots[i][j].showSlot();
            }
        }

    }

}

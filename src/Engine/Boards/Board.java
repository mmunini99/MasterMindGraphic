package Engine.Boards;

import Engine.*;
import Engine.GraphicElements.Button;
import Engine.GraphicElements.RGB;
import Engine.GraphicElements.Slot;
import Engine.Playboards.GenericPlayboard;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

//############################### CLASS DESCRIPTION #############################################################
/*
Board class is the true graphic component of the game while you are playing a game of Mastermind. It manages all the
interactions of the board such as the coloring of the slots and the choice of the colors. Not only its scope is the
interactive part but also the rendering of all the graphical elements such as the board itself, the colors on the
slots and feedback slots, the Palette Buttons and the general buttons. However, a lot of these characteristics depends
strongly on the difficulty of the game so a lot of variables are not specified and defined inside this class which
needs that its subclass define the graphical characteristics of the elements.
 */

public class Board {

    //########################## FUNDAMENTAL VARS ###############################################
    //This section contains the fundamental vars necessary for the class to work properly.

    protected final PApplet SW;

    private final Workflow myWorkflow;

    private final EventsManager EM = new EventsManager();

    private final GenericPlayboard PB;

    //######################## COLOR PALETTE VARS ##################################################
    //All of these variables are used to manage and characterize the palette of color used to play and also
    //the color selector square buttons shows with the board
    // ALL OF THESE ARE ASSIGNED IN THE SUBCLASS CONSTRUCTOR

    private RGB[] palette;
    private int[][] palettePositions;
    private final List<Button> paletteButtons = new ArrayList<>();

    //###################### FEEDBACK VARS #################################################
    //These are the variables associated to the representation of the feedback slots (used to show the computed
    //feedbacks)

    private Slot[][] feedbackSlots;

    private final RGB[] feedbackColor = new RGB[3];

    //####################### BOARD IMAGE VARS #####################################################
    //These are all the variables related to the image. The PImage one has to be defined by the subclass
    //and also the position of the left corner (x1,y1) and the image extension on the screen as to be
    //computed and given. The int vars are set protected because needed for computations in subclasses.

    private PImage imageBoard;

    protected int x1,y1, xSize, ySize;

    //##################### BUTTON VARS  #################################################
    //This sections contains the vars for the instances of all the buttons inside the board

    private final Button backButton;
    private final Button rulesButton;

    //###################### SLOTS #########################################################
    //Here we have the sets of slots needed to interact with the game and to give the guesses

    private Slot[][] slots;


    //##################### CLEAN CONSTRUCTOR ######################################################
    public Board(PApplet arg1, Workflow WF, GenericPlayboard arg2){
        System.gc();

        //######################## FUNDAMENTAL VARS #########################################

        SW = arg1;
        myWorkflow = WF;
        PB = arg2;

        //####################### FEEDBACK COLORS ###########################################

        feedbackColor[0] = new RGB(255,0,0); //RED
        feedbackColor[1] = new RGB(255,255,255); //WHITE
        feedbackColor[2] = new RGB(0,0,0); //BLACK

        backButton = new Button(SW.loadImage("Images/back.png"),SW);
        backButton.setPosition(Math.round(SW.width*0.04F),SW.height/16);
        backButton.setButtonColor(0,0,0);
        backButton.setSize(Math.round(SW.width*0.08F),SW.height/8);

        rulesButton = new Button(SW.loadImage("question_mark.png"),SW);
        rulesButton.setPosition(Math.round(SW.width*0.88F),SW.height/16);
        rulesButton.setButtonColor(0,0,0);
        rulesButton.setSize(Math.round(SW.width*0.08F),SW.height/8);

    }

    //################### SET FUNCTIONS FOR THE SUBCLASSES ###################################
    //These protected functions are used by the subclasses to set the Board characteristics. This procedure it's
    //necessary because the characteristics of the board depends on the difficulty.

    protected void setPalette(RGB[] specPalette){
        palette = specPalette;
    }

    protected void setPalettePositions(int[][] PP){
        palettePositions = PP;
    }

    protected void setImageBoard(PImage P){
        imageBoard = P;
    }

    //################## PALETTE FUNCTIONS #################################################
    //All of this functions are used to set up and interact with the Palette and the Palette Buttons

    protected void setupPaletteButtons(){
        for(int i = 0; i< palette.length; i++){
            Button paletteButton = new Button(SW);
            paletteButton.setButtonColor(palette[i].getR(), palette[i].getG(), palette[i].getB());
            paletteButton.setPosition(palettePositions[i][0], palettePositions[i][1]);
            paletteButton.setSize(Math.round(SW.width*0.12F),Math.round(SW.height*0.19F));
            paletteButtons.add(paletteButton);
        }
    }

    public RGB[] getPalette(){
        return palette;
    }

    //########################### SLOTS FUNCTIONS #########################################
    //All of this functions are used to set up the Slots and the Feedback slots characteristics by the subclasses.
    //Other methods are used to interact with those sets.

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
        feedbackSlots = new Slot[positions.length][positions[0].length];
        RGB c = new RGB(255,0,0);
        for(int i=0;i<positions.length;i++){
            for(int j=0;j<positions[0].length;j++){
                feedbackSlots[i][j] = new Slot(SW);
                feedbackSlots[i][j].setPosition(positions[i][j][0],positions[i][j][1]);
                feedbackSlots[i][j].setRadius(RX,RY);
                feedbackSlots[i][j].setFillingColor(c);
            }
        }
    }

    public void slotGroupActivation(int i){
        if(i>=0 && i<slots.length){
            for(int j=0;j<slots[i].length;j++){
                slots[i][j].slotActivation();
            }
        }
        else{
            throw new RuntimeException("Board: slotGroupActivation: Activated not existing group of slots");
        }
    }

    public void slotGroupDeactivation(int i){
        if(i>=0 && i<slots.length){
            for(int j=0;j<slots[i].length;j++){
                slots[i][j].slotDeactivation();
            }
        }
        else{
            throw new RuntimeException("Board: slotGroupActivation: Deactivated not existing group of slots");
        }
    }

    public int[] getSlotsContent(int i){
        int[] slotsContent = new int[4];
        if(i>=0 && i<slots.length) {
            for (int j = 0; j < slots[i].length; j++) {
                slotsContent[j] = slots[i][j].getContent();
            }
            return slotsContent;
        }
        else{
            throw new RuntimeException("Board: getSlotsContent: subset of slots not existing");
        }
    }

    public void setFeedbackSlots(int[] feedback,int count){
        for(int j = 0; j< feedbackSlots[count].length; j++) {
            feedbackSlots[count][j].slotActivation();
            feedbackSlots[count][j].fillSlot(feedback[j]);
            feedbackSlots[count][j].setFillingColor(feedbackColor[feedback[j]]);
            feedbackSlots[count][j].slotDeactivation();
        }
    }

    //################################## SHOW FUNCTIONS ########################################

    public void showBoard(){

        backButton.showButton();
        rulesButton.showButton();

        //Back Button
        if(EM.isButtonPressed(backButton,SW)){
            myWorkflow.previousStep();
            SW.clear(); //Clear all previous elements from the screen
        }

        //Rules Button
        if(EM.isButtonPressed(rulesButton,SW)){
            myWorkflow.getRuleMenus().setFromWhere(myWorkflow.getStep());
            myWorkflow.goToStep(6);
            SW.clear();
        }

        //############################## BOARD IMAGE #########################################

        SW.image(imageBoard,x1,y1, xSize, ySize);

        //############################ PALETTE BUTTONS ##################################
        for(int i = 0; i< paletteButtons.size(); i++){

            paletteButtons.get(i).showButton();

            if(EM.isButtonPressed(paletteButtons.get(i),SW)){
                PB.setSelectedColor(i);
            }
        }

        //################################# SLOTS ################################################

        for (Slot[] slot_set : slots) {
            for (int j = 0; j < slots[0].length; j++) {

                slot_set[j].showSlot();

                if (EM.isSlotPressed(slot_set[j], SW) && slot_set[j].isActiveSlot()) {

                    if (slot_set[j].isSlotEmpty() && PB.getSelectedColor() >= 0) {
                        slot_set[j].fillSlot(PB.getSelectedColor());
                        slot_set[j].setFillingColor(palette[PB.getSelectedColor()]);
                    }
                    else {
                        slot_set[j].emptySlot();
                    }
                }

            }
        }

        //################################ FEEDBACK SLOTS #############################################

        for (Slot[] feedbackSlot : feedbackSlots) {
            for (int j = 0; j < feedbackSlots[0].length; j++) {
                feedbackSlot[j].showSlot();
            }
        }

    }

}

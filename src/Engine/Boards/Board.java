package Engine.Boards;

import Engine.*;
import Engine.GraphicElements.Button;
import Engine.GraphicElements.RGB;
import Engine.GraphicElements.Slot;
import Engine.Playboards.GenericPlayBoard;
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

    //Used to refer to the Processing Application. It's protected to be accessible to the subclasses
    protected final PApplet SW;

    //Used to refer to the workflow
    private final WorkFlow myWorkflow;

    //Event Manger, needed to manage the Button events
    private final Events_Manager EM = new Events_Manager();

    //Used to refer to the PlayBoard (SP or MP) in which the board is loaded
    private final GenericPlayBoard PB;

    //######################## COLOR PALETTE VARS ##################################################
    //All of these variables are used to manage and characterize the palette of color used to play and also
    //the color selector square buttons shows with the board
    // ALL OF THESE ARE ASSIGNED IN THE SUBCLASS CONSTRUCTOR

    private RGB[] Palette; //The color palette
    private int[][] PalettePositions; //The position of the color square buttons
    private final List<Button> PaletteButtons= new ArrayList<>(); //The set of color square buttons

    //###################### FEEDBACK VARS #################################################
    //These are the variables associated to the representation of the feedback slots (used to show the computed
    //feedbacks)

    //The set of all feedback slots. Has to be determined by the subclasses
    private Slot[][] feedback_slots;

    //The set of colors used to represent the three different feedback values
    private final RGB[] feedback_color = new RGB[3];

    //####################### BOARD IMAGE VARS #####################################################
    //These are all the variables related to the image. The PImage one has to be defined by the subclass
    //and also the position of the left corner (x1,y1) and the image extension on the screen as to be
    //computed and given. The int vars are set protected because needed for computations in subclasses.

    private PImage image_board;

    protected int x1,y1, x_size, y_size;

    //##################### BUTTON VARS  #################################################
    //This sections contains the vars for the instances of all the buttons inside the board

    private final Button BackButton; //Button to go back to difficulty choice screen
    private final Button RulesButton; //Button to show the rules of the game and how to play

    //###################### SLOTS #########################################################
    //Here we have the sets of slots needed to interact with the game and to give the guesses

    private Slot[][] slots;


    //##################### CLEAN CONSTRUCTOR ######################################################
    public Board(PApplet arg1,WorkFlow WF,GenericPlayBoard arg2){
        //Clean the memory of the previous boards and games
        System.gc();

        //######################## FUNDAMENTAL VARS #########################################
        //Set the reference to the workflow, Processing application and the PlayBoard where the Board is loaded

        SW = arg1;
        myWorkflow = WF;
        PB = arg2;

        //####################### FEEDBACK COLORS ###########################################
        //Set the feedback colors for the three feedback values (0,1,2)

        feedback_color[0] = new RGB(255,0,0); //RED
        feedback_color[1] = new RGB(255,255,255); //WHITE
        feedback_color[2] = new RGB(0,0,0); //BLACK

        //Instantiate back button and set characteristics
        BackButton = new Button(SW.loadImage("Images/back.png"),SW);
        BackButton.setPosition(Math.round(SW.width*0.04F),SW.height/16);
        BackButton.setButtonColor(0,0,0);
        BackButton.setSize(Math.round(SW.width*0.08F),SW.height/8);

        //Instantiate rules button and set characteristics
        RulesButton = new Button(SW.loadImage("question_mark.png"),SW);
        RulesButton.setPosition(Math.round(SW.width*0.88F),SW.height/16);
        RulesButton.setButtonColor(0,0,0);
        RulesButton.setSize(Math.round(SW.width*0.08F),SW.height/8);

    }

    //################### SET FUNCTIONS FOR THE SUBCLASSES ###################################
    //These protected functions are used by the subclasses to set the Board characteristics. This procedure it's
    //necessary because the characteristics of the board depends on the difficulty.

    //Set the colors of the palette
    protected void setPalette(RGB[] specPalette){
        Palette = specPalette;
    }

    //Set the position of the color square buttons
    protected void setPalettePositions(int[][] PP){
        PalettePositions = PP;
    }

    //Set the image of the board to load
    protected void setImage_board(PImage P){
        image_board = P;
    }

    //################## PALETTE FUNCTIONS #################################################
    //All of this functions are used to set up and interact with the Palette and the Palette Buttons

    //Using various Palette data, the color square buttons or Palette Buttons are instantiate and
    //their graphic characteristics set. Then they are added to the ArrayList var PaletteButtons
    protected void setupPaletteButtons(){
        for(int i=0;i<Palette.length;i++){
            Button PB = new Button(SW);
            PB.setButtonColor(Palette[i].getR(),Palette[i].getG(),Palette[i].getB());
            PB.setPosition(PalettePositions[i][0],PalettePositions[i][1]);
            PB.setSize(Math.round(SW.width*0.12F),Math.round(SW.height*0.19F));
            PaletteButtons.add(PB); //Each instance it's then added to the ArrayList
        }
    }

    //This function simply return the Palette
    public RGB[] getPalette(){
        return Palette;
    }

    //########################### SLOTS FUNCTIONS #########################################
    //All of this functions are used to set up the Slots and the Feedback slots characteristics by the subclasses.
    //Other methods are used to interact with those sets.

    //Using the Slots data given by the subclass, the slots are instantiate and the characteristics set
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

    //Using the Slots data given by the subclass, the feedback slots are instantiate and the characteristics set
    protected void setupFeedbackSlots(int[][][] positions,int RX,int RY){
        feedback_slots = new Slot[positions.length][positions[0].length];
        RGB c = new RGB(255,0,0);
        for(int i=0;i<positions.length;i++){
            for(int j=0;j<positions[0].length;j++){
                feedback_slots[i][j] = new Slot(SW);
                feedback_slots[i][j].setPosition(positions[i][j][0],positions[i][j][1]);
                feedback_slots[i][j].setRadius(RX,RY);
                feedback_slots[i][j].setFillColor(c);
            }
        }
    }

    //Controlling if the index is meaningful, the slots of that subset are activated
    public void slotGroupActivation(int i){
        if(i>=0 && i<slots.length){
            for(int j=0;j<slots[i].length;j++){
                slots[i][j].SlotActivation();
            }
        }
        else{
            throw new RuntimeException("Board: slotGroupActivation: Activated not existing group of slots");
        }
    }

    //Controlling if the index is meaningful, the slots of that subset are deactivated
    public void slotGroupDeactivation(int i){
        if(i>=0 && i<slots.length){
            for(int j=0;j<slots[i].length;j++){
                slots[i][j].SlotDeactivation();
            }
        }
        else{
            throw new RuntimeException("Board: slotGroupActivation: Deactivated not existing group of slots");
        }
    }


    //Given i, if it's valid, the method will return the content of the 4 slots associated in the subset i
    public int[] getSlotsContent(int i){
        int[] slots_content = new int[4];
        if(i>=0 && i<slots.length) {
            for (int j = 0; j < slots[i].length; j++) {
                slots_content[j] = slots[i][j].getContent();
            }
            return slots_content;
        }
        else{
            throw new RuntimeException("Board: getSlotsContent: subset of slots not existing");
        }
    }

    //Given the feedback and the subset of feedback slots, their content is set. The activation and deactivation
    //is needed to allow to fill the slots but without let them become interactive.
    public void setFeedbackSlots(int[] feedback,int count){
        for(int j=0;j<feedback_slots[count].length;j++) {
            feedback_slots[count][j].SlotActivation();
            feedback_slots[count][j].fillSlot(feedback[j]);
            feedback_slots[count][j].setFillColor(feedback_color[feedback[j]]);
            feedback_slots[count][j].SlotDeactivation();
        }
    }

    //################################## SHOW FUNCTIONS ########################################

    //Board show function
    public void showBoard(){

        //Show general buttons
        BackButton.showButton();
        RulesButton.showButton();

        //General Button Events: what happen when each general button is pressed

        //Back Button -> go back to difficulty choice menu
        if(EM.Button_Pressed(BackButton,SW)){
            myWorkflow.previousStep();
            SW.clear(); //Clear all previous elements from the screen
        }

        //Rules Button -> Go to the rules menu and show the game rules
        if(EM.Button_Pressed(RulesButton,SW)){
            myWorkflow.getRuleMenus().setFromWhere(myWorkflow.getStep());
            myWorkflow.GoToStep(6);
            SW.clear();
        }

        //############################## BOARD IMAGE #########################################
        //Render the image of the board with the computed positions and proportions
        SW.image(image_board,x1,y1, x_size, y_size);

        //############################ PALETTE BUTTONS ##################################
        //Now for each one of the buttons show them and check if they are press to select the color
        for(int i=0;i<PaletteButtons.size();i++){

            PaletteButtons.get(i).showButton(); //show the Palette buttons

            if(EM.Button_Pressed(PaletteButtons.get(i),SW)){ //and check the press
                PB.setSelectedColor(i);
            }
        }

        //################################# SLOTS ################################################
        //Again, for each slot subset show each slots and check if it's pressed and active. If that happened
        //and the slot is empty fill it with the correct color. Otherwise, empty it.

        for (Slot[] slot_set : slots) {
            for (int j = 0; j < slots[0].length; j++) {

                slot_set[j].showSlot(); //show the slot j of the subset

                if (EM.SlotPressed(slot_set[j], SW) && slot_set[j].isActiveSlot()) { //if pressed and active

                    if (slot_set[j].isSlotEmpty() && PB.getSelectedColor() >= 0) { //if empty
                        slot_set[j].fillSlot(PB.getSelectedColor()); //fill it!
                        slot_set[j].setFillColor(Palette[PB.getSelectedColor()]);
                    }
                    else { //otherwise, empty it!
                        slot_set[j].emptySlot();
                    }
                }

            }
        }

        //################################ FEEDBACK SLOTS #############################################
        //For each feedback slots subset show each slots

        for (Slot[] feedbackSlot : feedback_slots) {
            for (int j = 0; j < feedback_slots[0].length; j++) {
                feedbackSlot[j].showSlot();
            }
        }

    }

}

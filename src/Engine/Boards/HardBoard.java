package Engine.Boards;

import Engine.Playboards.GenericPlayBoard;
import Engine.GraphicElements.RGB;
import Engine.WorkFlow;
import processing.core.PApplet;
import processing.core.PImage;

//############################### CLASS DESCRIPTION #############################################################
/*
This subclass of Board is made to specify all the board characteristics when the game difficulty is HARD.
 */

public class HardBoard extends Board{

    //################################### COLOR PALETTE VARS ##########################################################
    //This section contain all the information about the Palette such as the colors and the position of color buttons.

    //The game in Easy mode has siz colors so this is its palette
    RGB[] hardPal = new RGB[]{new RGB(0,0,255), //blue
            new RGB(255,255,0), //yellow
            new RGB(0,255,0), //green
            new RGB(235, 145, 205), //pink
            new RGB(77, 28, 5), //brown
            new RGB(255, 0, 102) //magenta
    };

    //The positions of the Palette Buttons on the screen
    int[][] hardPalPos = new int[][]{{Math.round(SW.width*0.04F),SW.height/4},
            {Math.round(SW.width*0.16F),SW.height/4},
            {Math.round(SW.width*0.28F),SW.height/4},
            {Math.round(SW.width*0.40F),SW.height/4},
            {Math.round(SW.width*0.52F), SW.height/4},
            {Math.round(SW.width*0.64F), SW.height/4}
    };

    //################################### BOARD IMAGE #######################################################
    //This PImage variable the image of the board from the resources
    PImage hardBoardImage = SW.loadImage("Images/hardboard.png");

    //################################### SLOTS VARS #######################################################
    //This sections contain the slots features

    //The slots are elliptical so are characterize by two radius. We pose default value equal to 48.
    int SlotRadiusX = 48; //horizontal radius
    int SlotRadiusY = 48; //vertical radius

    //In Hard Mode, we have 7 trials each of them is a guess, so we have 4 slots for 7 trials. Each slots
    //needs two values to identify the position of the centre of the ellipse. The positions will be computed
    //using ComputeSlotPositions() below.
    int[][][] SlotsPositions = new int[7][4][2]; //0: x, 1: y

    //############################# FEEDBACK SLOTS VARS #####################################################
    //Similarly, this section contain the feedback slots

    //The feedback slots, as the slots, are elliptical so are characterize by two radius.
    //We pose default value equal to 20.
    int FeedbackSlotRadiusX = 20;
    int FeedbackSlotRadiusY = 20;

    //In Hard Mode, we have 7 trials each of them have a feedback of the guess, so we have 4 slots for 7 trials. Each slots
    //needs two values to identify the position of the centre of the ellipse. The positions will be computed
    //using ComputeFeedbackSlotPositions() below.
    int[][][] FeedbackSlotsPositions = new int[7][4][2]; //0: x, 1: y

    //##################################### PRIVATE SETUP FUNCTIONS ######################################
    //Some of the features correlated to the difficulty mode can be difficult to hard code so some functions are
    //used to compute them automatically more easily.

    //This function compute the position of all the Slots. The position of a Slot is based on the Board Image which
    //depends on the game difficulty. All the formulas are obtained starting from the image structure.
    private void ComputeSlotPositions(){
        int AreaSlotY = 742;
        float y_ratio = (float) y_size /hardBoardImage.height;
        float x_ratio = (float) x_size /hardBoardImage.width;
        for(int i=0;i<SlotsPositions.length;i++){
            for(int j=0;j<SlotsPositions[0].length;j++){
                SlotsPositions[i][j][1]= (int) Math.round(y1 + 20.0*y_ratio + y_ratio*AreaSlotY/8.0F + j*y_ratio*AreaSlotY/4.0F);
                SlotsPositions[i][j][0]= (int) Math.round(x1+ 20.0*x_ratio + 115.0*x_ratio + i*250.0*x_ratio);
            }
        }
    }

    //This function compute the position of all the Feedback Slots. The position of a Slot is based on the Board Image
    //which depends on the game difficulty. All the formulas are obtained starting from the image structure.
    private void ComputeFeedbackSlotPositions(){
        int AreaSlotY = 178;
        float y_ratio = (float) y_size /hardBoardImage.height;
        float x_ratio = (float) x_size /hardBoardImage.width;
        for(int i=0;i<FeedbackSlotsPositions.length;i++) {
            for (int j = 0; j < FeedbackSlotsPositions[0].length; j++) {
                FeedbackSlotsPositions[i][j][1] = (int) Math.round(y1 + 782.0 * y_ratio + y_ratio * AreaSlotY / 4.0F
                        + Math.floor(j/2.0F) * y_ratio * AreaSlotY / 2.0F);
                FeedbackSlotsPositions[i][j][0] = (int) Math.round(x1 + 77.5F * x_ratio + (j%2)*x_ratio*115.0 + i * 250.0 * x_ratio);
            }
        }
    }

    //Given the board image it's necessary to integrate it correctly in the graphic of the screen without letting
    //some fragment go out of the screen. To do this some computation and check are necessaries.
    private void calculateImagePadding(){
        if(SW.width>=SW.height){
            float ratio = (float) 1.0*hardBoardImage.width /hardBoardImage.height;
            y1 = SW.height/2;
            y_size = (SW.height/2)- SW.height/20;
            x_size = (int) (ratio* y_size);
            x1 = (SW.width- x_size)/2;
        }
        else{
            float ratio = (float) hardBoardImage.height/hardBoardImage.width;
            x1 = (int) (SW.width/38.4F);
            x_size = (int) (SW.width-2*SW.width/38.4F);
            y_size = (int) (ratio* x_size);
            y1 = SW.height/2;
        }
    }

    //The radius of the slot can be set manually accessing directly to the slots independently however this function
    //is made, given the image board, to take advantage of its characteristics to automatically compute the right
    //radius.
    private void ComputeSlotRadius(){
        SlotRadiusY = Math.round(y_size *1.0F/(2*(SlotsPositions[0].length+1)));
        SlotRadiusX = SlotRadiusY;
    }

    //Similarly of the Slot Radius, this method actuate a similar method to compute automatically the radius of the
    //feedback slots basing on the image.
    private void ComputeFeedbackSlotRadius(){
        FeedbackSlotRadiusY = Math.round(y_size *1.0F/(2*2*(SlotsPositions[0].length+2)));
        FeedbackSlotRadiusX = FeedbackSlotRadiusY;
    }

    //########################### CLASS CONSTRUCTOR ###############################################

    public HardBoard(PApplet sw, WorkFlow wf, GenericPlayBoard pb) {
        super(sw,wf,pb); //Board Class Constructor

        setPalette(hardPal); //Set Board Palette
        setImage_board(hardBoardImage); //Set Board image_board
        calculateImagePadding(); //Compute x,y,x_size and y_size of the Board to fit the image
        ComputeSlotPositions(); //Compute the position of the slots
        ComputeSlotRadius(); //Compute the radius of the slots
        ComputeFeedbackSlotPositions(); //Compute the position of the feedback slots
        ComputeFeedbackSlotRadius(); //Compute the radius of the feedback slots
        setPalettePositions(hardPalPos); //Set the positions of Palette Buttons
        setupPaletteButtons(); //set up the Palette Buttons
        setupSlots(SlotsPositions,SlotRadiusX,SlotRadiusY); //set up the Slots
        setupFeedbackSlots(FeedbackSlotsPositions,FeedbackSlotRadiusX,FeedbackSlotRadiusY); //set up the feedback slots
    }
}

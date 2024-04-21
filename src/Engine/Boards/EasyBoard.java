package Engine.Boards;

import Engine.Playboards.GenericPlayboard;
import Engine.GraphicElements.RGB;
import Engine.Workflow;
import processing.core.PApplet;
import processing.core.PImage;

//############################### CLASS DESCRIPTION #############################################################
/*
This subclass of Board is made to specify all the board characteristics when the game difficulty is EASY.
 */

public class EasyBoard extends Board{

    //################################### COLOR PALETTE VARS ##########################################################
    //This section contain all the information about the Palette such as the colors and the position of color buttons.

    private final RGB[] EASY_PALETTE = new RGB[]{new RGB(0,0,255), //blue
            new RGB(255,255,0), //yellow
            new RGB(0,255,0)}; //green

    private final int[][] EASY_PALETTE_POS = new int[][]{{Math.round(SW.width*0.04F),SW.height/4},
            {Math.round(SW.width*0.16F),SW.height/4},
            {Math.round(SW.width*0.28F),SW.height/4}};

    //################################### BOARD IMAGE #######################################################

    private final PImage EASY_BOARD_IMAGE = SW.loadImage("Images/easyBoard.png");

    //################################### SLOTS VARS #######################################################
    //This sections contain the slots features

    //Default = 48
    private int slotRadiusX = 48;
    private int slotRadiusY = 48;

    private int[][][] slotsPositions = new int[15][4][2]; //15*4 slots,0: x, 1: y

    //############################# FEEDBACK SLOTS VARS #####################################################
    //Similarly, this section contain the feedback slots

    //Default = 20
    private int feedbackSlotRadiusX = 20;
    private int feedbackSlotRadiusY = 20;

    private int[][][] feedbackSlotsPositions = new int[15][4][2]; //7*4 slots,0: x, 1: y

    //############################# PRIVATE SETUP FUNCTIONS ##########################################
    //Some of the features correlated to the difficulty mode can be difficult to hard code so some functions are
    //used to compute them automatically more easily.

    private void computeSlotPositions(){
        int areaSlotY = 742;
        float yRatio = (float) ySize / EASY_BOARD_IMAGE.height;
        float xRatio = (float) xSize / EASY_BOARD_IMAGE.width;
        for(int i = 0; i< slotsPositions.length; i++){
            for(int j = 0; j< slotsPositions[0].length; j++){
                slotsPositions[i][j][1]= (int) Math.round(y1 + 20.0*yRatio + yRatio*areaSlotY/8.0F + j*yRatio*areaSlotY/4.0F);
                slotsPositions[i][j][0]= (int) Math.round(x1+ 20.0*xRatio + 115.0*xRatio + i*250.0*xRatio);
            }
        }
    }

    private void computeFeedbackSlotPositions(){
        int areaSlotY = 178;
        float yRatio = (float) ySize / EASY_BOARD_IMAGE.height;
        float xRatio = (float) xSize / EASY_BOARD_IMAGE.width;
        for(int i = 0; i< feedbackSlotsPositions.length; i++) {
            for (int j = 0; j < feedbackSlotsPositions[0].length; j++) {
                feedbackSlotsPositions[i][j][1] = (int) Math.round(y1 + 782.0 * yRatio + yRatio * areaSlotY / 4.0F
                                                            + Math.floor(j/2.0F) * yRatio * areaSlotY / 2.0F);
                feedbackSlotsPositions[i][j][0] = (int) Math.round(x1 + 77.5F * xRatio + (j%2)*xRatio*115.0 + i * 250.0 * xRatio);
            }
        }
    }

    private void calculateImagePadding(){
        if(SW.width>=SW.height && EASY_BOARD_IMAGE.width<=SW.width){
            float ratio = (float) 1.0* EASY_BOARD_IMAGE.width / EASY_BOARD_IMAGE.height;
            y1 = SW.height/2;
            ySize = (SW.height/2)- SW.height/20;
            xSize = (int) (ratio* ySize);
            x1 = (SW.width- xSize)/2;
        }
        else{
            float ratio = (float) EASY_BOARD_IMAGE.height/ EASY_BOARD_IMAGE.width;
            x1 = (int) (SW.width/38.4F);
            xSize = (int) (SW.width-2*SW.width/38.4F);
            ySize = (int) (ratio* xSize);
            y1 = SW.height/2;
        }
    }

    private void computeSlotRadius(){
        slotRadiusY = Math.round(ySize *1.0F/(2*(slotsPositions[0].length+1)));
        slotRadiusX = slotRadiusY;
    }

    private void computeFeedbackSlotRadius(){
        feedbackSlotRadiusY = Math.round(ySize *1.0F/(2*2*(slotsPositions[0].length+2)));
        feedbackSlotRadiusX = feedbackSlotRadiusY;
    }

    //##################################### CLASS CONSTRUCTOR #################################################
    public EasyBoard(PApplet arg1, Workflow WF, GenericPlayboard arg2) {
        super(arg1,WF,arg2);

        setPalette(EASY_PALETTE);
        setImageBoard(EASY_BOARD_IMAGE);
        calculateImagePadding();
        computeSlotPositions();
        computeSlotRadius();
        computeFeedbackSlotPositions();
        computeFeedbackSlotRadius();
        setPalettePositions(EASY_PALETTE_POS);
        setupPaletteButtons();
        setupSlots(slotsPositions, slotRadiusX, slotRadiusY);
        setupFeedbackSlots(feedbackSlotsPositions, feedbackSlotRadiusX, feedbackSlotRadiusY);
    }
}

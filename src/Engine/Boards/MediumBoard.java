package Engine.Boards;

import Engine.Playboards.GenericPlayBoard;
import Engine.RGB;
import Engine.WorkFlow;
import processing.core.PApplet;
import processing.core.PImage;

public class MediumBoard extends Board{

    RGB[] medPal = new RGB[]{new RGB(0,0,255),
            new RGB(255,255,0),
            new RGB(0,255,0),
            new RGB(235, 145, 205),
            new RGB(77, 28, 5),
            new RGB(255, 0, 102)
    }; //"blue", "yellow", "green", "pink", "brown", "magenta"
    int medNOT = 12; //number of trials

    int[][] medPalPos = new int[][]{{Math.round(SW.width*0.04F),SW.height/4},
            {Math.round(SW.width*0.16F),SW.height/4},
            {Math.round(SW.width*0.28F),SW.height/4},
            {Math.round(SW.width*0.40F),SW.height/4},
            {Math.round(SW.width*0.52F), SW.height/4},
            {Math.round(SW.width*0.64F), SW.height/4}
    };

    PImage medBoardImage = SW.loadImage("Images/mediumBoard.png");

    int SlotRadiusX = 48;
    int SlotRadiusY = 48;

    int FeedbackSlotRadiusX = 20;
    int FeedbackSlotRadiusY = 20;

    int[][][] SlotsPositions = new int[12][4][2];
    int[][][] FeedbackSlotsPositions = new int[12][4][2];

    //############################## PRIVATE SETUP FUNCTIONS ################################################

    private void ComputeSlotPositions(){
        int AreaSlotY = 742;
        float yratio = (float) ysize/medBoardImage.height;
        float xratio = (float) xsize/medBoardImage.width;
        for(int i=0;i<SlotsPositions.length;i++){
            for(int j=0;j<SlotsPositions[0].length;j++){
                SlotsPositions[i][j][1]= (int) Math.round(y1 + 20.0*yratio + yratio*AreaSlotY/8.0F + j*yratio*AreaSlotY/4.0F);
                SlotsPositions[i][j][0]= (int) Math.round(x1+ 20.0*xratio + 115.0*xratio + i*250.0*xratio);
            }
        }
    }

    private void ComputeFeedbackSlotPositions(){
        int AreaSlotY = 178;
        float yratio = (float) ysize/medBoardImage.height;
        float xratio = (float) xsize/medBoardImage.width;
        for(int i=0;i<FeedbackSlotsPositions.length;i++) {
            for (int j = 0; j < FeedbackSlotsPositions[0].length; j++) {
                FeedbackSlotsPositions[i][j][1] = (int) Math.round(y1 + 782.0 * yratio + yratio * AreaSlotY / 4.0F
                        + Math.floor(j/2.0F) * yratio * AreaSlotY / 2.0F);
                FeedbackSlotsPositions[i][j][0] = (int) Math.round(x1 + 77.5F * xratio + (j%2)*xratio*115.0 + i * 250.0 * xratio);
            }
        }
    }

    private void calculateImagePadding(){
        if(SW.width>=SW.height){
            float ratio = (float) 1.0*medBoardImage.width /medBoardImage.height;
            y1 = SW.height/2;
            ysize = (SW.height/2)- SW.height/20;
            xsize = (int) (ratio*ysize);
            x1 = (int) (SW.width-xsize)/2;
        }
        else{
            float ratio = (float) medBoardImage.height/medBoardImage.width;
            x1 = (int) (SW.width/38.4F);
            xsize = (int) (SW.width-2*SW.width/38.4F);
            ysize = (int) (ratio*xsize);
            y1 = SW.height/2;
        }
    }

    private void ComputeSlotRadius(){
        SlotRadiusY = Math.round(ysize*1.0F/(2*(SlotsPositions[0].length+1)));
        SlotRadiusX = SlotRadiusY;
    }

    private void ComputeFeedbackSlotRadius(){
        FeedbackSlotRadiusY = Math.round(ysize*1.0F/(2*2*(SlotsPositions[0].length+2)));
        FeedbackSlotRadiusX = FeedbackSlotRadiusY;
    }

    //############################## CONSTRUCTOR ##############################################

    public MediumBoard(PApplet sw, WorkFlow wf, GenericPlayBoard pb) {
        super(sw, wf, pb);
        setPalette(medPal);
        setNumberOfTrials(medNOT);
        setImageboard(medBoardImage);
        calculateImagePadding();
        ComputeSlotPositions();
        ComputeSlotRadius();
        ComputeFeedbackSlotPositions();
        ComputeFeedbackSlotRadius();
        setPalettePositions(medPalPos);

        setupPaletteButtons();
        setupSlots(SlotsPositions, SlotRadiusX, SlotRadiusY);
        setupFeedbackSlots(FeedbackSlotsPositions, FeedbackSlotRadiusX, FeedbackSlotRadiusY);
    }
}

package Engine.Boards;

import Engine.PlayBoard;
import Engine.RGB;
import Engine.WorkFlow;
import processing.core.PApplet;
import processing.core.PImage;

public class EasyBoard extends Board{

    RGB[] easyPal = new RGB[]{new RGB(0,0,255),new RGB(255,255,0),new RGB(0,255,0)}; //blue,yellow,green
    int easyNOT = 15; //number of trials

    int[][] easyPalPos = new int[][]{{50,SW.height/4},{250,SW.height/4},{450,SW.height/4}};

    PImage easyBoardImage = SW.loadImage("Images/easyBoard.png");

    int SlotRadiusX = 48;
    int SlotRadiusY = 48;

    int[][][] SlotsPositions = new int[15][4][2];

    private void ComputeSlotPositions(){
        int AreaSlotY = 742;
        float yratio = (float) ysize/easyBoardImage.height;
        float xratio = (float) xsize/easyBoardImage.width;
        for(int i=0;i<SlotsPositions.length;i++){
            for(int j=0;j<SlotsPositions[0].length;j++){
                SlotsPositions[i][j][1]= (int) Math.round(y1 + 20.0*yratio + yratio*AreaSlotY/8.0F + j*yratio*AreaSlotY/4.0F);
                SlotsPositions[i][j][0]= (int) Math.round(x1+ 20.0*xratio + 115.0*xratio + i*250.0*xratio);
            }
        }
    }

    private void calculateImagePadding(){
        if(SW.width>=SW.height){
            float ratio = (float) 1.0*easyBoardImage.width /easyBoardImage.height;
            y1 = SW.height/2;
            ysize = (SW.height/2)- SW.height/20;
            xsize = (int) (ratio*ysize);
            x1 = (int) (SW.width-xsize)/2;
        }
        else{
            float ratio = (float) easyBoardImage.height/easyBoardImage.width;
            x1 = (int) (SW.width/38.4F);
            xsize = (int) (SW.width-2*SW.width/38.4F);
            ysize = (int) (ratio*xsize);
            y1 = SW.height/2;
        }
    }

    public EasyBoard(PApplet arg1, WorkFlow WF, PlayBoard arg2) {
        super(arg1,WF,arg2);
        setPalette(easyPal);
        setNumberOfTrials(easyNOT);
        setImageboard(easyBoardImage);
        calculateImagePadding();
        ComputeSlotPositions();
        setPalettePositions(easyPalPos);

        setupPaletteButtons();
        setupSlots(SlotsPositions,SlotRadiusX,SlotRadiusY);
    }
}

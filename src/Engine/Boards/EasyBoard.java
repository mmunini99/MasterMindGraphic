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

    int SlotRadius = 50;

    int[][][] SlotsPositions = new int[15][4][2];

    private void ComputeSlotPositions(){
        int a = 50;
        int b = 50;
        int effectivewidth = SW.width-2*a;
        int effectiveheight = SW.height/2-b;
        for(int i=0;i<SlotsPositions.length;i++){
            for(int j=0;j<SlotsPositions[0].length;j++){
                SlotsPositions[i][j][1]=Math.round(SW.height/2.0F + effectiveheight/10.0F + j*effectiveheight/5.0F);
                SlotsPositions[i][j][0]=Math.round(a+ effectivewidth/30.0F + i*effectivewidth/15.0F);
            }
        }
    }

    public EasyBoard(PApplet arg1, WorkFlow WF, PlayBoard arg2) {
        super(arg1, WF,arg2);
        ComputeSlotPositions();
        setPalette(easyPal);
        setNumberOfTrials(easyNOT);
        setImageboard(easyBoardImage);
        setPalettePositions(easyPalPos);

        setupPaletteButtons();
        setupSlots(SlotsPositions,SlotRadius);
    }
}

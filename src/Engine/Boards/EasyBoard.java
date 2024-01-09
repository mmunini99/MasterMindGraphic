package Engine.Boards;

import Engine.RGB;
import Engine.WorkFlow;
import processing.core.PApplet;
import processing.core.PImage;

public class EasyBoard extends Board{

    RGB[] easyPal = new RGB[]{new RGB(0,0,255),new RGB(255,255,0),new RGB(0,255,0)}; //blue,yellow,green
    int easyNOT = 15; //number of trials

    int[][] easyPalPos = new int[][]{{50,SW.height/4},{250,SW.height/4},{450,SW.height/4}};

    PImage easyBoardImage = SW.loadImage("Images/easyBoard.png");

    public EasyBoard(PApplet arg1, WorkFlow WF) {
        super(arg1, WF);
        setPalette(easyPal);
        setNumberOfTrials(easyNOT);
        setImageboard(easyBoardImage);
        setPalettePositions(easyPalPos);

        setupPaletteButtons();
    }
}

package Engine;

import Engine.GraphicElements.RGB;
import processing.core.PApplet;

public class GameText {

    //###################### FUNDAMENTAL VARS #############################################

    private PApplet SW;

    //###################### TEXT VARS ###############################################

    private String text = "Hello World!";

    private RGB TextColor = new RGB(255,255,255);

    private int x=0;
    private int y=0;//positions

    private int xsize=200;
    private float textSize=1;

    private float TextWidth;

    //########################### CONSTRUCTOR ############################################
    public GameText(PApplet arg1){
        SW = arg1;
    }

    //############################# SET FUNCTIONS #####################################

    public void setText(String s){
        text = s;
        TextWidth = SW.textWidth(text);
    }

    public void setTextColor(int r,int g,int b){
        TextColor = new RGB(r,g,b);
    }

    public void setPositions(long a, long b){
        x = (int) a;
        y = (int) b;
    }

    public void setSize(long a){
        xsize = (int) a;
    }

    public void setTextSize(float a){
        textSize = a;
    }

    public void showText(){
        SW.fill(TextColor.getR(),TextColor.getG(),TextColor.getB());
        SW.textSize(textSize * xsize*1.0F/TextWidth);
        SW.text(text, x, y,xsize,SW.height);
    }

}

package Engine.GraphicElements;

import processing.core.PApplet;

//############################### CLASS DESCRIPTION #############################################################
/*
The GameText class is used to render text on the screen. There are a lot of settings and customizable characteristics
in Processing to personalize a text but all of them are uncorrelated and linked all to the PApplet. This class is made
to speed up text additions to the graphics. The characteristic of a text, beside the text content itself, are more or
less the same of other graphics elements such as image. We have the top left corner position, the color of the text
and the horizontal size of the text box in which the text is enclosed. Moreover, the size of the font give the vertical
extension of the text.
 */


public class GameText {

    //###################### FUNDAMENTAL VARS #############################################
    //This section contains the fundamental vars necessary for the class to work properly.

    private final PApplet SW;

    //###################### CONTENT VARS ###############################################
    //This var contains the "text content" of the Game text alias the text to show.

    private String text = "Hello World!";

    //####################### RENDERING VARS ##############################################
    //All of these variable are needed to render the text and can be changed to change the graphic aspect of the text
    //All the vars have a default value to produce no errors.

    private RGB textColor = new RGB(255,255,255);

    //Position of the slot (top left corner)
    private int x=0;
    private int y=0;

    private int xSize =200;

    private float textSize=1;

    private float textWidth;

    //########################### CLASS CONSTRUCTOR ############################################
    public GameText(PApplet arg1){
        SW = arg1;
    }

    //############################# SET FUNCTIONS #####################################
    //This function are simply used to set GameText characteristics. No control is made because also negative values
    //are accepted by the engine and are not blocking.

    public void setText(String s){
        text = s;
        textWidth = SW.textWidth(text);
    }

    public void setTextColor(int r,int g,int b){ textColor = new RGB(r,g,b); }

    public void setPositions(long a, long b){
        x = (int) a;
        y = (int) b;
    }

    public void setSize(long a){
        xSize = (int) a;
    }

    public void setTextSize(float a){
        textSize = a;
    }

    //###################################### SHOW FUNCTION ###################################################

    public void showText(){
        SW.fill(textColor.getR(), textColor.getG(), textColor.getB());
        SW.textSize(textSize * xSize *1.0F/ textWidth);
        SW.text(text, x, y, xSize,SW.height);
    }

}

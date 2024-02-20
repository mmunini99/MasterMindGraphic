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

    //Used to refer to the Processing Application
    private final PApplet SW;

    //###################### CONTENT VARS ###############################################
    //This var contains the "text content" of the Game text alias the text to show.

    private String text = "Hello World!";

    //####################### RENDERING VARS ##############################################
    //All of these variable are needed to render the text and can be changed to change the graphic aspect of the text
    //All the vars have a default value to produce no errors.

    //The color of the text
    private RGB TextColor = new RGB(255,255,255);

    //Position of the slot (top left corner)
    private int x=0;
    private int y=0;

    //The horizontal extension of the text
    private int x_size =200;

    //The textSize vertically or, in other words, the font size
    private float textSize=1;

    //The actual extension of the text with a set font
    private float TextWidth;

    //########################### CLASS CONSTRUCTOR ############################################
    public GameText(PApplet arg1){
        //Set the reference to the Processing application
        SW = arg1;
    }

    //############################# SET FUNCTIONS #####################################
    //This function are simply used to set GameText characteristics. No control is made because also negative values
    //are accepted by the engine and are not blocking.

    //Set the text that to show when rendered. TextWidth is adapted with the new text.
    public void setText(String s){
        text = s;
        TextWidth = SW.textWidth(text);
    }

    //Set the color for the displaying of the text
    public void setTextColor(int r,int g,int b){ TextColor = new RGB(r,g,b); }

    //Set the two position variables for the text. They are the coordinates of the top left corner
    public void setPositions(long a, long b){
        x = (int) a;
        y = (int) b;
    }

    //Set the horizontal extension of the text
    public void setSize(long a){
        x_size = (int) a;
    }

    //Set the font size of the text
    public void setTextSize(float a){
        textSize = a;
    }

    //###################################### SHOW FUNCTION ###################################################

    //This function will simply show the text with the given characteristics
    public void showText(){
        SW.fill(TextColor.getR(),TextColor.getG(),TextColor.getB());
        SW.textSize(textSize * x_size *1.0F/TextWidth);
        SW.text(text, x, y, x_size,SW.height);
    }

}

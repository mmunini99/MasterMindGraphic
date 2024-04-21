package Engine.GraphicElements;

import processing.core.PApplet;
import processing.core.PImage;

//############################### CLASS DESCRIPTION #############################################################
/*
The Button is an important graphic element with a fundamental role under the interactivity point of view. The Button
appear as a filled rectangular and when the Player pass the cursor upon it will change slightly the color. Using the
Event Manager the Button can be pressed to produce some kind of reactions or events. On the Button is also possible
to render texts or substitute entirely the filled rectangular with an image.
 */

public class Button {

    //###################### FUNDAMENTAL VARS #############################################
    //This section contains the fundamental vars necessary for the class to work properly.

    private final PApplet SW;

    //########################### BUTTON COLORS #########################################
    //These vars are used to manage the colors of the Button.

    private final RGB baseColor = new RGB(0,0,0);
    private final RGB hooColor = new RGB(0,0,0);
    private final RGB textColor = new RGB(255,255,255);
    private final RGB borderColor = new RGB(0,0,0);

    private final float hooLighting = 0.50F;

    //####################### TEXT SETTINGS ###########################
    //If a text is present, these settings are used to characterize the text rendering on the Button.

    private int textSize = 12;
    private int xPadding = 0;
    private int yPadding = 0;

    //######################### TYPE OF BUTTON ####################################
    //These two boolean vars are used to register the type of the button. If both are false is a normal Button.

    private boolean textButton = false;
    private boolean imageButton = false;

    //######################## STORAGE VARS ###############################################
    //These two are used similarly to storage the possible text or image if the Button is a text Button or an image
    //Button.

    private String buttonText;
    private PImage buttonImage;

    //########################## POS AND SIZE VARS ###########################################
    //In this part, there are the vars for the position (top left corner) and size of the Button.

    private int x = 0;
    private int y = 0;
    private int xSize = 100;
    private int ySize = 50;

    //################### CLASS CONSTRUCTORS ####################################################
    //Plain Buttons.
    public Button(PApplet arg){
        SW = arg;
    }

    //Text Buttons.
    public Button(String text, PApplet arg){
        textButton = true;
        buttonText = text;
        SW = arg;
    }

    //Image Buttons.
    public Button(PImage image, PApplet arg){
        imageButton = true;
        buttonImage = image;
        SW = arg;
    }

    //################### COLORS FUNCTIONS ######################################################
    //In this part we have all the functions used to interact with the colors of the Button.

    public void setButtonColor(int r,int g,int b){
        baseColor.setRGB(r,g,b);
        hooColor.setRGB(Math.round(hooLighting *r),Math.round(hooLighting *g),Math.round(hooLighting *b));
    }

    public void setTextColor(int r, int g, int b){
        textColor.setRGB(r,g,b);
    }

    //NOTE: not used in the project but left inside because can be useful in other projects.
    public void setBorderColor(int r,int g,int b){
        borderColor.setRGB(r,g,b);
    }

    //################## POS AND SIZE FUNCTIONS ################################################
    //These functions are used to interact with the position and the size of the Button.

    //NOTE: out of screen value are meaningful
    public void setPosition(int a,int b){
        x = a;
        y = b;
    }

    public void setSize(int a,int b){
        xSize = a;
        ySize = b;
    }

    //################## TEXT FEATURES FUNCTIONS ###############################################
    //These functions are used to interact with the text features .

    //NOTE: is not used but left because can be useful.
    public void setTextSize(int s){
        if(s>=0) {
            textSize = s;
        }
        else{
            throw new RuntimeException("Button:setTextSize: given negative value for text size");
        }
    }

    public void setTextSizePct(float s){
        if(s>=0 && s<=1){
            textSize = Math.round(ySize *s);
        }
        else{
            throw new RuntimeException("Button:setTextSizePct: insert pct value is not between 0 and 1");
        }
    }

    //NOTE: is not used but left because can be useful
    public void setPaddings(int a, int b){
        xPadding = a;
        yPadding = b;
    }

    public void setPaddingsPct(float a, float b){
        if(Math.abs(a)<=1 && Math.abs(b)<=1){
            xPadding = Math.round(a* xSize);
            yPadding = Math.round(b* ySize);
        }
        else{
            throw new RuntimeException("Button: setPaddingsPct: impossible padding percentage");
        }
    }

    //################# HOOVERING AND ON THE BUTTON ##################################
    //These two functions are used to check if the mouse is upon the Button and to change the color consequently.

    public boolean onTheButton(){
        if(SW.mouseX>=x && SW.mouseX<=(x+ xSize) && SW.mouseY>=y && SW.mouseY<=(y+ ySize)){
            return true;
        }
        return false;
    }

    private void hoovering(){
        if(onTheButton()){
            SW.fill(hooColor.getR(), hooColor.getG(), hooColor.getB());
        }
    }

    //################# SHOW FUNCTION #######################################

    public void showButton(){
        SW.fill(baseColor.getR(), baseColor.getG(), baseColor.getB());
        hoovering();
        SW.stroke(borderColor.getR(), borderColor.getG(), borderColor.getB());
        SW.rect(x,y, xSize, ySize);

        if(textButton){
            SW.fill(textColor.getR(), textColor.getG(), textColor.getB());
            SW.textSize(textSize);
            SW.text(buttonText,x+ xPadding,y+ yPadding,x+ xSize,y+ ySize);
        }

        if(imageButton){
            SW.image(buttonImage,x,y, xSize, ySize);
        }
    }

}

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

    //Used to refer to the Processing Application
    private final PApplet SW;

    //########################### BUTTON COLORS #########################################
    //These vars are used to manage the colors of the Button.

    //If the Button is not an image one this will be the color of the Button
    private final RGB base_color = new RGB(0,0,0);
    //When hoovered, the Button will have this color
    private final RGB hoo_color = new RGB(0,0,0);
    //The color of the text (if present) shown on the Button.
    private final RGB text_color = new RGB(255,255,255);
    //The color of the border of the Button.
    private final RGB border_color = new RGB(0,0,0);

    //####################### TEXT SETTINGS ###########################
    //If a text is present, these settings are used to characterize the text rendering on the Button.

    private int text_size = 12; //The font size of the text

    private int x_padding = 0; //The padding along the horizontal
    private int y_padding = 0; //The padding along the vertical

    //######################### TYPE OF BUTTON ####################################
    //These two boolean vars are used to register the type of the button. If both are false is a normal Button.

    private boolean textButton = false; //Is a text Button?
    private boolean imageButton = false; //Is a image Button?

    //######################## STORAGE VARS ###############################################
    //These two are used similarly to storage the possible text or image if the Button is a text Button or an image
    //Button.

    private String ButtonText; //The text shown on the Button.
    private PImage ButtonImage; //The image shown on the Button.

    //########################## POS AND SIZE VARS ###########################################
    //In this part, there are the vars for the position (upper left corner) and size of the Button.

    private int x = 0; //x of the upper left corner
    private int y = 0; //y of the upper left corner
    private int x_size = 100; //size of the Button horizontally
    private int y_size = 50; //size of the Button vertically

    //################### CLASS CONSTRUCTORS ####################################################
    //Constructor for plain Buttons.
    public Button(PApplet arg){
        SW = arg;
    }

    //Constructor for text Buttons.
    public Button(String text, PApplet arg){
        textButton = true;
        ButtonText = text;
        SW = arg;
    }

    //Constructor for image Buttons.
    public Button(PImage image, PApplet arg){
        imageButton = true;
        ButtonImage = image;
        SW = arg;
    }

    //################### COLORS FUNCTIONS ######################################################
    //In this part we have all the functions used to interact with the colors of the Button.

    //Set the filling color of the Button (used for text and plain Buttons).
    public void setButtonColor(int r,int g,int b){
        base_color.setRGB(r,g,b); //The filling color is set
        float hoo_light = 0.50F; //<- Can be changed for different tone
        //and also the hoovering color (a darker version)
        hoo_color.setRGB(Math.round(hoo_light *r),Math.round(hoo_light *g),Math.round(hoo_light *b));
    }

    //Set the color of the text (used for text Buttons).
    public void setTextColor(int r, int g, int b){
        text_color.setRGB(r,g,b);
    }

    //Set the color of the Border of the Button.
    //NOTE: not used in the project but left inside because can be useful in other projects.
    public void setBorderColor(int r,int g,int b){
        border_color.setRGB(r,g,b);
    }

    //################## POS AND SIZE FUNCTIONS ################################################
    //These functions are used to interact with the position and the size of the Button.

    //Set the position of the Button.
    //NOTE: No check is done because out of screen values are meaningful for Processing.
    public void setPosition(int a,int b){
        x = a;
        y = b;
    }

    //Set the size of the Button. Again no check is performed.
    public void setSize(int a,int b){
        x_size = a;
        y_size = b;
    }

    //################## TEXT FEATURES FUNCTIONS ###############################################
    //These functions are used to interact with the text features .

    //Set the font size of the text.
    //NOTE: is not used but left because can be useful.
    public void setTextSize(int s){
        if(s>=0) {
            text_size = s;
        }
        else{
            throw new RuntimeException("Button:setTextSize: given negative value for text size");
        }
    }

    //Set the font size
    public void setTextSizePct(float s){
        if(s>=0 && s<=1){
            text_size = Math.round(y_size *s);
        }
        else{
            throw new RuntimeException("Button:setTextSizePct: insert pct value is not between 0 and 1");
        }
    }

    //Set the paddings of the text inside the Button.
    //NOTE: is not used but left because can be useful
    public void setPaddings(int a, int b){
        x_padding = a;
        y_padding = b;
    }

    //Set the paddings of the text inside the Button proportionately according to the Button size.
    public void setPaddingsPct(float a, float b){
        if(Math.abs(a)<=1 && Math.abs(b)<=1){
            x_padding = Math.round(a* x_size);
            y_padding = Math.round(b* y_size);
        }
        else{
            throw new RuntimeException("Button: setPaddingsPct: impossible padding percentage");
        }
    }

    //################# HOOVERING AND ON THE BUTTON ##################################
    //These two functions are used to check if the mouse is upon the Button and to change the color consequently.

    //Check if the mouse cursor is on the Button.
    public boolean on_the_button(){
        if(SW.mouseX>=x && SW.mouseX<=(x+ x_size) && SW.mouseY>=y && SW.mouseY<=(y+ y_size)){
            return true;
        }
        return false;
    }

    //If on the Button, this function will change the color with the hoo_color.
    private void hoovering(){
        if(on_the_button()){
            SW.fill(hoo_color.getR(),hoo_color.getG(),hoo_color.getB());
        }
    }

    //################# SHOW FUNCTION #######################################
    //This function is used to render the Button.

    public void showButton(){
        SW.fill(base_color.getR(), base_color.getG(), base_color.getB()); //set the filling color
        hoovering(); //Check if the cursor is hoovering and eventually adjust the color
        SW.stroke(border_color.getR(), border_color.getG(), border_color.getB()); //set the color for the border
        SW.rect(x,y, x_size, y_size); //draw the rectangular base of the Button

        //If it is a text Button show also the text
        if(textButton){
            SW.fill(text_color.getR(), text_color.getG(), text_color.getB());
            SW.textSize(text_size);
            SW.text(ButtonText,x+x_padding,y+y_padding,x+ x_size,y+ y_size);
        }

        //And if it is an image Button show the image.
        if(imageButton){
            SW.image(ButtonImage,x,y, x_size, y_size);
        }
    }

}

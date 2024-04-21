package Engine.GraphicElements;

import processing.core.PApplet;

//############################### CLASS DESCRIPTION #############################################################
/*
The Slot is an important graphic elements with a fundamental role under the interactivity point of view. The Slot is
an elliptic element which can have stored inside of it a numerical content. If something is stored inside of it, the
Slot will display a color which, as the other graphics characteristics, can be set easily. The Slot class presents
two possible behaviour namely the Activated or Deactivated one. While a slot is active an interaction is possible,
and it will be visible by hoovering over it. In Deactivated mode the Slot, if filled with a content, will continue
to display it's filled color but no interactions are possible.
 */

public class Slot {

    //############################ FUNDAMENTAL VARS ############################################
    //This section contains the fundamental vars necessary for the class to work properly.

    private final PApplet SW;

    //################################## STATUS VARIABLES #####################################################
    //These boolean vars are used to store if the slot is active (it's possible to interact with it) and if it has
    //a content stored inside it.

    private boolean activeSlot=false;
    private boolean emptySlot =true;

    //############################# POSITION, SIZE AND COLOR VARIABLES ####################################
    //These vars are strictly linked to the graphics characteristic of the slot when rendered.
    //To let the code run anyway some default parameter are set.

    private int x0 = 0; //Cords for ellipse center
    private int y0 = 0;

    private int rx = 5;
    private int ry = 5;

    private RGB fillingColor =new RGB(255,255,255);

    //################################ CONTENT VARIABLES ################################################
    //This var is made to store the content of the slot. The slot content is an int positive number. Again we use -1 as
    //default value to raise errors if not modified.

    private int content=-1;

    //################################# CLASS CONSTRUCTOR ########################################################
    public Slot(PApplet arg1){
        SW = arg1;
    }

    //####################################### PRIVATE FUNCTIONS ##################################################
    //Some internal function used inside the public function to make the code lighter.

    public boolean onTheSlot(){
        if(((Math.sqrt(Math.pow(SW.mouseX-x0,2)+Math.pow(SW.mouseY-y0,2)))<=rx) ||
                ((Math.sqrt(Math.pow(SW.mouseX-x0,2)+Math.pow(SW.mouseY-y0,2)))<=ry)
        ){
            return true;
        }
        return false;
    }

    private void hoovering(){
        if(onTheSlot()){
            SW.stroke(255,255,255,192);
            SW.fill(255,255,255,192);
            SW.ellipse(x0,y0,2*rx,2*ry);
        }
    }

    //#################################### SET FUNCTIONS ########################################################
    //No control is made because also negative values are accepted by the engine and are not blocking.

    public void setPosition(int a,int b){
        x0 = a;
        y0 = b;
    }

    public void setRadius(int RX,int RY){
        rx = RX;
        ry = RY;
    }

    public void setFillingColor(RGB c){
        fillingColor = c;
    }

    //################################### GET FUNCTIONS###############################################
    public boolean isActiveSlot(){
        return activeSlot;
    }

    public boolean isSlotEmpty(){
        return emptySlot;
    }

    //################################### CONTENT FUNCTION #####################################################
    //These set of functions are used to interact with content of the slot and content related features.

    public void fillSlot(int c){
        if(c>=0 && activeSlot) {
            content = c;
            emptySlot = false;
        }
        else{
            throw new RuntimeException("Slot: fillSlot: Invalid content (negative value) or not active slot");
        }
    }

    public void emptySlot(){
        content=-1;
        fillingColor =new RGB(255,255,255);
        emptySlot =true;
    }

    public int getContent(){
        return content;
    }

    //##################################### ACTIVATION FUNCTION #################################################
    //These two functions are made to activate or deactivate the Slot a.k.a. to allow/prohibit the interaction with it

    public void slotActivation(){
        activeSlot = true;
    }

    public void slotDeactivation(){
        activeSlot = false;
    }

    //################################### SHOW FUNCTIONS #####################################################

    public void showSlot(){
        if(emptySlot && activeSlot){
            hoovering();
        }

        if(!emptySlot){
            SW.stroke(fillingColor.getR(), fillingColor.getG(), fillingColor.getB());
            SW.fill(fillingColor.getR(), fillingColor.getG(), fillingColor.getB());
            SW.ellipse(x0,y0,2*rx,2*ry);
        }
    }

}

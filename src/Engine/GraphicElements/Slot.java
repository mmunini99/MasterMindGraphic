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

    //Used to refer to the Processing Application
    private final PApplet SW;

    //################################## STATUS VARIABLES #####################################################
    //These boolean vars are used to store if the slot is active (it's possible to interact with it) and if it has
    //a content stored inside it.

    private boolean activeSlot=false; //Is the slot active?
    private boolean slot_is_empty=true; //Is the slot empty?

    //############################# POSITION, SIZE AND COLOR VARIABLES ####################################
    //These vars are strictly linked to the graphics characteristic of the slot when rendered.
    //To let the code run anyway some default parameter are set.

    //Position on the screen (center of the ellipse)
    private int x0 = 0;
    private int y0 = 0;

    //All the slots are elliptic, so we have a radius along x and along y
    private int rx = 5;
    private int ry = 5;

    //The color displayed by the slot when filled
    private RGB FillColor =new RGB(255,255,255);

    //################################ CONTENT VARIABLES ################################################
    //This var is made to store the content of the slot. The slot content is an int positive number. Again we use -1 as
    //default value to raise errors if not modified.

    private int content=-1;

    //################################# CLASS CONSTRUCTOR ########################################################
    public Slot(PApplet arg1){
        //Save the link to the PApplet (needed for render)
        SW = arg1;
    }

    //####################################### PRIVATE FUNCTIONS ##################################################
    //Some internal function used inside the public function to make the code lighter.

    //This function will simply check if the mouse cursor is on the slot or not.
    public boolean on_the_slot(){
        if(((Math.sqrt(Math.pow(SW.mouseX-x0,2)+Math.pow(SW.mouseY-y0,2)))<=rx) ||
                ((Math.sqrt(Math.pow(SW.mouseX-x0,2)+Math.pow(SW.mouseY-y0,2)))<=ry)
        ){
            return true;
        }
        return false;
    }

    //This graphic function, if it's called will highlight the slot when the mouse cursor it's on it.
    private void hoovering(){
        if(on_the_slot()){
            SW.stroke(255,255,255,192);
            SW.fill(255,255,255,192);
            SW.ellipse(x0,y0,2*rx,2*ry);
        }
    }

    //#################################### SET FUNCTIONS ########################################################
    //This function are simply used to set Slot graphic characteristics. No control is made because also negative values
    //are accepted by the engine and are not blocking.

    //Set the position of the slot (center of the ellipse)
    public void setPosition(int a,int b){
        x0 = a;
        y0 = b;
    }

    //Set Slot's ellipse's radius
    public void setRadius(int RX,int RY){
        rx = RX;
        ry = RY;
    }

    //Set FillColor
    public void setFillColor(RGB c){
        FillColor = c;
    }

    //################################### GET FUNCTIONS###############################################
    //These functions are used to return the Slot status.

    //Return if the Slot is active or not
    public boolean isActiveSlot(){
        return activeSlot;
    }

    //Return if the slot is empty or not
    public boolean isSlotEmpty(){
        return slot_is_empty;
    }

    //################################### CONTENT FUNCTION #####################################################
    //These set of functions are used to interact with content of the slot and content related features.

    //Given a positive content and if the slot is activated (necessary condition) the slot is filled with that
    //content. Otherwise, error.
    public void fillSlot(int c){
        if(c>=0 && activeSlot) {
            content = c;
            slot_is_empty = false;
        }
        else{
            throw new RuntimeException("Slot: fillSlot: Invalid content (negative value) or not active slot");
        }
    }

    //With this function the Slot is emptied and the characteristics are reset to default value
    public void emptySlot(){
        content=-1;
        FillColor =new RGB(255,255,255);
        slot_is_empty=true;
    }

    //Return the content of the slot
    public int getContent(){
        return content;
    }

    //##################################### ACTIVATION FUNCTION #################################################
    //These two functions are made to activate or deactivate the Slot a.k.a. to allow/prohibit the interaction with it

    public void SlotActivation(){
        activeSlot = true;
    }

    public void SlotDeactivation(){
        activeSlot = false;
    }

    //################################### SHOW FUNCTIONS #####################################################

    //Show the slots on the screen
    public void showSlot(){
        //If the slot it's empty and active moving over have to produce the hoovering effect
        if(slot_is_empty && activeSlot){
            hoovering();
        }

        //Independently if active or not a filled Slot has to show it's color
        if(!slot_is_empty){
            SW.stroke(FillColor.getR(), FillColor.getG(), FillColor.getB());
            SW.fill(FillColor.getR(), FillColor.getG(), FillColor.getB());
            SW.ellipse(x0,y0,2*rx,2*ry);
        }
    }

}

package Engine;

import processing.core.PApplet;

public class Slot {

    private final PApplet SW;

    //################################## STATUS VARIABLES #####################################################
    private boolean activeSlot=false;
    private boolean slot_is_empty=true;

    //############################# POSITION, SIZE AND COLOR VARIABLES ####################################
    private int x0 = 0;
    private int y0 = 0;
    private int rx = 5;
    private int ry = 5;

    private RGB activeColor=new RGB(255,255,255);

    //################################ CONTENT VARIABLES ################################################
    private int content=-1;

    //######################################## CONSTRUCTOR ########################################################
    public Slot(PApplet arg1){
        SW = arg1;
    }

    //####################################### PRIVATE FUNCTIONS ##################################################
    public boolean on_the_slot(){
        if(((Math.sqrt(Math.pow(SW.mouseX-x0,2)+Math.pow(SW.mouseY-y0,2)))<=rx) ||
                ((Math.sqrt(Math.pow(SW.mouseX-x0,2)+Math.pow(SW.mouseY-y0,2)))<=ry)
        ){
            return true;
        }
        return false;
    }

    private void hoovering(){
        if(on_the_slot()){
            SW.stroke(255,255,255,192);
            SW.fill(255,255,255,192);
            SW.ellipse(x0,y0,2*rx,2*ry);
        }
    }

    //#################################### SET FUNCTIONS ########################################################

    public void setPosition(int a,int b){
        x0 = a;
        y0 = b;
    }

    public void setRadius(int RX,int RY){
        rx = RX; ry=RY;
    }

    public void setActiveColor(RGB c){
        activeColor = c;
    }

    //################################### GET FUNCTIONS###############################################

    public boolean isActiveSlot(){
        return activeSlot;
    }

    public boolean isSlotEmpty(){
        return slot_is_empty;
    }

    //################################### CONTENT FUNCTION #####################################################

    public void fillSlot(int c){
        if(c>=0 && activeSlot) {
            content = c;
            slot_is_empty = false;
        }
        else{
            throw new RuntimeException("Invalid content (negative value) or not active slot");
        }
    }

    public void emptySlot(){
        content=-1;
        activeColor=new RGB(255,255,255);
        slot_is_empty=true;
    }

    public int getContent(){
        return content;
    }

    //##################################### ACTIVATION FUNCTION #################################################
    public void SlotActivation(){
        activeSlot = true;
    }

    public void SlotDeactivation(){
        activeSlot = false;
    }

    //################################### SHOW FUNCTIONS #####################################################

    public void showSlot(){
        if(slot_is_empty && activeSlot){
            hoovering();
        }
        if(!slot_is_empty){
            SW.stroke(activeColor.getR(),activeColor.getG(),activeColor.getB());
            SW.fill(activeColor.getR(),activeColor.getG(),activeColor.getB());
            SW.ellipse(x0,y0,2*rx,2*ry);
        }
    }

}

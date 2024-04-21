package Engine;

import Engine.GraphicElements.Button;
import Engine.GraphicElements.Slot;
import processing.core.PApplet;

//############################### CLASS DESCRIPTION #############################################################
/*
The Event Manager is a fundamental class used to manage all kinds of events in the game such as Time Events (wait for
a certain time) or Button Event (check if the button is pressed). The Event Manager idea comes from much more used
graphic engines such as Unreal Engine. The objective of this entity is to control at every frame if the button on the
screen are pressed and give response if such event happen. Similarly works for time events.
 */

public class EventsManager {

    //########################### TIME VARS ###############################################
    //All of these vars are used to manage the time events
    private boolean activeTimeEvent = false;

    private long timeMeasure =0;

    private long timeLimit =0;

    //###########################  TIME EVENT  ############################################
    //This function are used to activate, reset and check the time event

    public void resetTimeEvent(){
        timeMeasure = 0;
        timeLimit = 0;
        activeTimeEvent = false;
    }

    public boolean checkTimeEvent(){
        if(activeTimeEvent){
            if(System.currentTimeMillis()- timeMeasure >= timeLimit){
                return true;
            }
        }
        return false;
    }

    public void activateTimeEvent(int TL){
        if(!activeTimeEvent) {
            activeTimeEvent = true;
            timeLimit = TL;
            timeMeasure = System.currentTimeMillis();
        }
    }

    //#################### BUTTON EVENT ###########################################
    //Another kind of event is the Button Event. This Event will simply wait for a certain button to be pressed.
    //To press a button is obviously necessary to be over it.

    public boolean isButtonPressed(Button B, PApplet SW){
        if(B.onTheButton() && SW.mousePressed){
            SW.mousePressed = false; //It's necessary to reset the mouse to have no double-clicking
            return true;
        }
        return false;
    }

    //###################### SLOT EVENT #########################################
    //The Slot event functions are very similar of the button one.

    public boolean isSlotPressed(Slot S, PApplet SW){
        if(S.onTheSlot() && SW.mousePressed){
            SW.mousePressed = false;
            return true;
        }
        return false;
    }

}

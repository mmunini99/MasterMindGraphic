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

public class Events_Manager {

    //########################### TIME VARS ###############################################
    //All of these vars are used to manage the time events

    //Is a time event active?
    private boolean time_event_active = false;

    //How much time is passed from the start of the time event?
    private long time_measure=0;

    //What is the time limit for the event?
    private long time_limit=0;

    //###########################  TIME EVENT  ############################################
    //This function are used to activate, reset and check the time event

    //This function will simply reset the time event to its default condition (see above)
    public void reset_time_Event(){
        time_measure = 0;
        time_limit = 0;
        time_event_active = false;
    }

    //When a time event is active, this function will check if the time limit has been surpassed the function will
    //respond positively.
    public boolean Time_Event(){
        if(time_event_active){
            if(System.currentTimeMillis()-time_measure>=time_limit){
                return true;
            }
        }
        return false;
    }

    //This function is used to activate a time event. A single time event can be active for each Event Manager.
    //A time limit is requested to use this function.
    public void Activate_Time_Event(int TL){
        if(!time_event_active) {
            time_event_active = true;
            time_limit = TL;
            time_measure = System.currentTimeMillis();
        }
    }

    //#################### BUTTON EVENT ###########################################
    //Another kind of event is the Button Event. This Event will simply wait for a certain button to be pressed.
    //To press a button is obviously necessary to be over it.

    public boolean Button_Pressed(Button B, PApplet SW){
        if(B.on_the_button() && SW.mousePressed){
            SW.mousePressed = false; //It's necessary to reset the mouse to have no double-clicking
            return true;
        }
        return false;
    }

    //###################### SLOT EVENT #########################################
    //The Slot event functions are very similar of the button one.

    public boolean SlotPressed(Slot S, PApplet SW){
        if(S.on_the_slot() && SW.mousePressed){
            SW.mousePressed = false;
            return true;
        }
        return false;
    }

}

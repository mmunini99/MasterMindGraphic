package Engine;

import processing.core.PApplet;

public class Events_Manager {

    private boolean time_event_active = false;
    private long time_measure=0;
    private long time_limit=0;


    //###########################  TIME EVENT  ############################################

    public void reset_time_Event(){
        time_measure = 0;
        time_limit = 0;
        time_event_active = false;
    }

    public boolean Time_Event(){
        boolean event_ended = false;
        if(time_event_active){
            if(System.currentTimeMillis()-time_measure>=time_limit){
                event_ended = true;
            }
        }

        return event_ended;
    }

    public void Activate_Time_Event(int TL){
        if(!time_event_active) {
            time_event_active = true;
            time_limit = TL;
            time_measure = System.currentTimeMillis();
        }
    }

    //#################### BUTTON EVENT ###########################################

    public boolean Button_Pressed(Button B, PApplet SW){
        if(B.on_the_button() && SW.mousePressed){
            return true;
        }
        return false;
    }

}

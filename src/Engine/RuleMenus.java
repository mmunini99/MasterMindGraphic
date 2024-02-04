package Engine;

import processing.core.PApplet;

public class RuleMenus {

    //################################ FUNDAMENTAL VARS ########################################################

    private final PApplet SW;
    private final WorkFlow myWorkflow;
    private int FromWhere = 0;

    private final Events_Manager EM = new Events_Manager();

    //################################ BUTTONS #############################################################

    private final Button backbutton;

    //############################### CONSTRUCTOR ###########################################################

    public RuleMenus(PApplet arg1,WorkFlow arg2){
        SW = arg1;
        myWorkflow = arg2;

        backbutton = new Button(SW.loadImage("Images/back.png"),SW);
        backbutton.setPosition(100,50);
        backbutton.setButtonColor(0,0,0);
        backbutton.setSize(100,100);
    }

    //############################## SET FUNCTIONS ################################################

    public void setFromWhere(int s){
        if(s>=0 && s<myWorkflow.getWorkflow().length){
            FromWhere = s;
        }
        else{
            throw new RuntimeException("Rules Menu: invalid previous step");
        }
    }

    //############################## SHOW FUNCTION ###############################################

    public void showRuleMenus(){
        SW.background(0);
        backbutton.showButton();

        SW.fill(0,255,217);
        String message = "RULES";
        SW.textSize(Math.round(0.15*SW.height));
        SW.text(message,
                Math.round(SW.width*0.35),
                Math.round(SW.height/32.0),
                Math.round(SW.width*0.75),
                Math.round(SW.height*0.375));

        //########################### BUTTONS INTERACTION #################################
        if(EM.Button_Pressed(backbutton,SW)){
            myWorkflow.GoToStep(FromWhere);
            SW.clear();
        }
    }


}

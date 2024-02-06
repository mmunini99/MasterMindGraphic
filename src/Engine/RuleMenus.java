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

    //############################# TITLE TEXT ##############################################

    private GameText TitleText;

    //############################### CONSTRUCTOR ###########################################################

    public RuleMenus(PApplet arg1,WorkFlow arg2){
        SW = arg1;
        myWorkflow = arg2;

        TitleText = new GameText(SW);
        TitleText.setText("RULES");
        TitleText.setTextColor(0,255,217);
        TitleText.setPositions(Math.round(SW.width*0.355F),SW.height/32);
        TitleText.setSize(Math.round(SW.width*0.34));
        TitleText.setTextSize(10F);

        backbutton = new Button(SW.loadImage("Images/back.png"),SW);
        backbutton.setPosition(Math.round(SW.width*0.04F),SW.height/16);
        backbutton.setButtonColor(0,0,0);
        backbutton.setSize(Math.round(SW.width*0.08F),SW.height/8);
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
        TitleText.showText();

        //########################### BUTTONS INTERACTION #################################
        if(EM.Button_Pressed(backbutton,SW)){
            myWorkflow.GoToStep(FromWhere);
            SW.clear();
        }
    }


}

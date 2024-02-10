package Engine;

import processing.core.PApplet;
import processing.core.PImage;

public class RuleMenus {

    //################################ FUNDAMENTAL VARS ########################################################

    private final PApplet SW;
    private final WorkFlow myWorkflow;
    private int FromWhere = 0;

    private int RulesToLoad = -1;

    private final Events_Manager EM = new Events_Manager();

    //################################ BUTTONS #############################################################

    private final Button backbutton;

    //############################# TITLE TEXT ##############################################

    private GameText TitleText;

    //################################# IMAGES ###########################################

    private PImage[] images = new PImage[2];

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

        images[0] = SW.loadImage("Images/difficulty_rules.png");
        images[1] = SW.loadImage("Images/game_rules.png");
    }

    //############################## SET FUNCTIONS ################################################

    public void setFromWhere(int s){
        if(s>=0 && s<myWorkflow.getWorkflow().length){
            FromWhere = s;
            if(s == 2){
                RulesToLoad = 0;
            } else if (s == 3) {
                RulesToLoad = 1;
            }
            else{
                throw new RuntimeException("Rules Menu: no rules to load from that screen");
            }
        }
        else{
            throw new RuntimeException("Rules Menu: invalid previous step");
        }
    }

    //############################## SHOW FUNCTION ###############################################

    private void showRules(){
        int x = Math.round(SW.width*0.04F);
        int y = Math.round(SW.height*0.25F);
        int xsize = Math.round(SW.width*0.92F);
        int ysize = Math.round(images[RulesToLoad].height*xsize*1.0F/images[RulesToLoad].width);

        SW.image(images[RulesToLoad],x,y,xsize,ysize);
    }

    public void showRuleMenus(){
        SW.background(0);
        backbutton.showButton();
        TitleText.showText();

        showRules();
        //SW.image(images[RulesToLoad],x,y);

        //########################### BUTTONS INTERACTION #################################
        if(EM.Button_Pressed(backbutton,SW)){
            myWorkflow.GoToStep(FromWhere);
            SW.clear();
        }
    }


}

package Engine.Menus;

import Engine.GraphicElements.Button;
import Engine.EventsManager;
import Engine.GraphicElements.GameText;
import Engine.Workflow;
import processing.core.PApplet;
import processing.core.PImage;

//############################### CLASS DESCRIPTION #############################################################
/*
This class contains the Rules menu. This menu presents a simple title and a back button moreover an image used to
presents some kind of rules for the game or similar. This menu is a bit different from the other because can be
accessed from different steps of the workflow and for different steps is coded to show different sets of rules.
For this reason we used some kind of variable such FromWhere which store the previous screen from which we came from,
and it used with the Workflow.GoStep function to go back in the correct point of the flow.
 */

public class RuleMenus {

    //################################ FUNDAMENTAL VARS ########################################################
    //This section contains the fundamental vars necessary for the class to work properly.

    private final PApplet SW;
    private final Workflow myWorkflow;

    private int fromWhere = 0; //Which step we came from

    private int rulesToLoad = -1;

    private final EventsManager EM = new EventsManager();

    //################################ BUTTONS VARS #############################################################
    //This sections contains the vars for the instances of all the buttons inside the single-player menu

    private final Button backButton;

    //############################# TEXT VARS ################################################################
    //Vars to contains texts rendered by the engine in the screens of this menu

    private final GameText titleText;

    //################################# IMAGES #####################################################################
    //Vars to contains images rendered by the engine in the screens of this menu

    private final PImage[] RULES_IMAGES = new PImage[2];

    //############################### CONSTRUCTOR ###########################################################
    public RuleMenus(PApplet arg1, Workflow arg2){
        SW = arg1;
        myWorkflow = arg2;

        titleText = new GameText(SW);
        titleText.setText("RULES");
        titleText.setTextColor(0,255,217);
        titleText.setPositions(Math.round(SW.width*0.355F),SW.height/32);
        titleText.setSize(Math.round(SW.width*0.34));
        titleText.setTextSize(10F);

        backButton = new Button(SW.loadImage("Images/back.png"),SW);
        backButton.setPosition(Math.round(SW.width*0.04F),SW.height/16);
        backButton.setButtonColor(0,0,0);
        backButton.setSize(Math.round(SW.width*0.08F),SW.height/8);

        //WHY CONSTANT? It's constant but the loading can happen only for defined SW. Apart this, has all constant features
        RULES_IMAGES[0] = SW.loadImage("Images/difficulty_rules.png");
        RULES_IMAGES[1] = SW.loadImage("Images/game_rules.png");
    }

    //############################## SET FUNCTIONS ################################################
    //This function are simply used to set the FromWhere variable necessary to be able to come back to the previous
    //screen.

    public void setFromWhere(int s){
        if(s>=0 && s<myWorkflow.getWorkflow().length){ //Only from some step we have defined behaviour
            fromWhere = s;

            //Difficulty menus
            if(s == 2 || s==8){
                rulesToLoad = 0;
            } else if (s == 3 || s==9) { //Boards
                rulesToLoad = 1;
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
        int xSize = Math.round(SW.width*0.92F);
        int ySize = Math.round(RULES_IMAGES[rulesToLoad].height*xSize*1.0F/ RULES_IMAGES[rulesToLoad].width);

        SW.image(RULES_IMAGES[rulesToLoad],x,y,xSize,ySize);
    }


    public void showRuleMenus(){
        SW.background(0);  //set the background color

        backButton.showButton();
        titleText.showText();

        showRules();

        //Back Button
        if(EM.isButtonPressed(backButton,SW)){
            myWorkflow.goToStep(fromWhere);
            SW.clear(); //Clear all previous elements from the screen
        }
    }


}

package Engine.Menus;

import Engine.GraphicElements.Button;
import Engine.Events_Manager;
import Engine.GraphicElements.GameText;
import Engine.WorkFlow;
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

    //Used to refer to the Processing Application and software workflow
    private final PApplet SW;
    private final WorkFlow myWorkflow;

    //This var will contain the point of the workflow from which I arrived to this menu. It's needed to come back.
    private int FromWhere = 0;

    //This menu can load different rules according to the step of arriving. This slot regulate the set of rules to
    //show
    private int RulesToLoad = -1;

    //Event Manger, needed to manage the Button events
    private final Events_Manager EM = new Events_Manager();

    //################################ BUTTONS VARS #############################################################
    //This sections contains the vars for the instances of all the buttons inside the single-player menu

    private final Button backbutton; //Button to go back to the screen from which we arrived

    //############################# TEXT VARS ################################################################
    //Vars to contains texts rendered by the engine in the screens of this menu

    //Text of the title of the screen
    private final GameText TitleText;

    //################################# IMAGES #####################################################################
    //Vars to contains images rendered by the engine in the screens of this menu

    //These images are used to store the different sets of rules. For big texts it is easier to store it in images
    private final PImage[] images = new PImage[2];

    //############################### CONSTRUCTOR ###########################################################
    public RuleMenus(PApplet arg1,WorkFlow arg2){
        //Save the links to the PApplet (needed for render) and the Workflow (to go back to the game screens)
        SW = arg1;
        myWorkflow = arg2;

        //Title of the screen ("RULES") characteristics setup
        TitleText = new GameText(SW);
        TitleText.setText("RULES");
        TitleText.setTextColor(0,255,217);
        TitleText.setPositions(Math.round(SW.width*0.355F),SW.height/32);
        TitleText.setSize(Math.round(SW.width*0.34));
        TitleText.setTextSize(10F);

        //Back button setup
        backbutton = new Button(SW.loadImage("Images/back.png"),SW);
        backbutton.setPosition(Math.round(SW.width*0.04F),SW.height/16);
        backbutton.setButtonColor(0,0,0);
        backbutton.setSize(Math.round(SW.width*0.08F),SW.height/8);

        //Load the rule images. This preloading, as the other, improves the performances of the game.
        images[0] = SW.loadImage("Images/difficulty_rules.png"); //difficulty description
        images[1] = SW.loadImage("Images/game_rules.png"); //game rules
    }

    //############################## SET FUNCTIONS ################################################
    //This function are simply used to set the FromWhere variable necessary to be able to come back to the previous
    //screen.

    public void setFromWhere(int s){
        //Firstly we will check that the step from which we came has at least meaning
        if(s>=0 && s<myWorkflow.getWorkflow().length){
            FromWhere = s;

            //Now from the SP and MP menu (difficulty description needed)
            if(s == 2 || s==8){
                RulesToLoad = 0;
            } else if (s == 3 || s==9) { //or if from game boards (rules needed)
                RulesToLoad = 1;
            }
            else{ //if we came from an unexpected screen -> throw an error
                throw new RuntimeException("Rules Menu: no rules to load from that screen");
            }
        }
        else{
            throw new RuntimeException("Rules Menu: invalid previous step");
        }
    }

    //############################## SHOW FUNCTION ###############################################

    //This function is used to render correctly the rule images
    private void showRules(){
        int x = Math.round(SW.width*0.04F);
        int y = Math.round(SW.height*0.25F);
        int x_size = Math.round(SW.width*0.92F);
        int y_size = Math.round(images[RulesToLoad].height*x_size*1.0F/images[RulesToLoad].width);

        SW.image(images[RulesToLoad],x,y,x_size,y_size);
    }


    //This function will show the rules menu with all of its features.
    public void showRuleMenus(){
        SW.background(0);  //set the background color (0=BLACK)

        //Show the back button and the text
        backbutton.showButton();
        TitleText.showText();

        //Call the function above
        showRules();

        //Button Events: what happen when each button is pressed

        //Back Button -> Go back to the screen from which we came from
        if(EM.Button_Pressed(backbutton,SW)){
            myWorkflow.GoToStep(FromWhere);
            SW.clear(); //Clear all previous elements from the screen
        }
    }


}

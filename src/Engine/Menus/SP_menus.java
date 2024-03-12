package Engine.Menus;

import Engine.GraphicElements.Button;
import Engine.Events_Manager;
import Engine.GraphicElements.GameText;
import Engine.WorkFlow;
import processing.core.PApplet;

//############################### CLASS DESCRIPTION #############################################################
/*
This class contains the Single-player menu. In this menu it's possible to start a game choosing the difficulty for
this game simply pressing on the difficulty buttons. A text asking the difficulty and a button to go back to the
main menu is provided. It's also possible to see the differences of the difficulty mode through a rules button.
 */

public class SP_menus {

    //######################### FUNDAMENTALS VARIABLES ######################################
    //This section contains the fundamental vars necessary for the class to work properly.

    //Used to refer to the Processing Application
    private final PApplet SW;

    //Event Manger, needed to manage the Button events
    private final Events_Manager EM = new Events_Manager();

    //Used to refer to the workflow
    private final WorkFlow myWorkflow;

    //######################## BUTTON VARS #########################################
    //This sections contains the vars for the instances of all the buttons inside the single-player menu

    private final Button EasyButton; //Button to launch the game in Easy mode
    private final Button MediumButton; //Button to launch the game in Medium mode
    private final Button HardButton; //Button to launch the game in Hard mode
    private final Button RulesButton; //Button to show the rules
    private final Button BackButton; //Button to go back to the main menu

    //########################## TEXT VARS #################################################
    //Vars to contains texts rendered by the engine in the screens of this menu

    private final GameText QuestionText; //Text of the question to choose difficulty

    //################################# CLASS CONSTRUCTOR #############################################
    public SP_menus(PApplet arg1, WorkFlow arg2){

        //Set the reference to the workflow and Processing application
        SW = arg1;
        myWorkflow = arg2;

        //Instantiate all the Text Buttons
        EasyButton = new Button("EASY",SW);
        MediumButton = new Button("MEDIUM",SW);
        HardButton = new Button("HARD",SW);

        //Instantiate all the Image Buttons
        RulesButton = new Button(SW.loadImage("question_mark.png"),SW);
        BackButton = new Button(SW.loadImage("Images/back.png"),SW);

        //Instantiate the Text for the question
        QuestionText = new GameText(SW);

        //Question GameText characteristics setup
        QuestionText.setText("Select the difficulty level");
        QuestionText.setTextColor(0,255,217);
        QuestionText.setPositions(Math.round(SW.width*0.16),SW.height/16);
        QuestionText.setSize(Math.round(SW.width*0.68));
        QuestionText.setTextSize(12F);

        //Easy mode button setup
        EasyButton.setPosition(Math.round(SW.width*0.08F),2*SW.height/3);
        EasyButton.setButtonColor(0,150,0);
        EasyButton.setTextColor(0,0,0);
        EasyButton.setSize(Math.round(SW.width*0.225F),SW.height/5);
        EasyButton.setTextSizePct(0.35F);
        EasyButton.setPaddingsPct(0.26F,0.25F);

        //Medium mode button setup
        MediumButton.setPosition(Math.round(SW.width*0.385F),2*SW.height/3);
        MediumButton.setButtonColor(255,255,0);
        MediumButton.setTextColor(0,0,0);
        MediumButton.setSize(Math.round(SW.width*0.225F),SW.height/5);
        MediumButton.setTextSizePct(0.35F);
        MediumButton.setPaddingsPct(0.13F,0.25F);

        //Hard mode button setup
        HardButton.setPosition(Math.round(SW.width*0.69F),2*SW.height/3);
        HardButton.setButtonColor(255,0,0);
        HardButton.setTextColor(0,0,0);
        HardButton.setSize(Math.round(SW.width*0.225F),SW.height/5);
        HardButton.setTextSizePct(0.35F);
        HardButton.setPaddingsPct(0.225F,0.25F);

        //Rules button setup
        RulesButton.setPosition(Math.round(SW.width*0.88F),SW.height/16);
        RulesButton.setButtonColor(0,0,0);
        RulesButton.setSize(Math.round(SW.width*0.08F),SW.height/8);

        //Go Back to main menu button setup
        BackButton.setPosition(Math.round(SW.width*0.04F),SW.height/16);
        BackButton.setButtonColor(0,0,0);
        BackButton.setSize(Math.round(SW.width*0.08F),SW.height/8);
    }

    //################################### SHOW FUNCTIONS ####################################################

    //Single player difficulty choice menu show function
    public void difficulty_menu(){
        SW.background(0); //set the background color (0=BLACK)

        QuestionText.showText(); //show the question

        //Show all the buttons
        EasyButton.showButton();
        MediumButton.showButton();
        HardButton.showButton();
        RulesButton.showButton();
        BackButton.showButton();

        //Button Events: what happen when each button is pressed

        //Back Button -> Go back to previous step a.k.a. the main menu
        if(EM.Button_Pressed(BackButton,SW)){
            myWorkflow.previousStep();
            SW.clear(); //Clear all previous elements from the screen
        }

        //Rules Button -> pass where we are (single player menu) to be able to come back
        //and move to the rules menu loading the correct rules for this place.
        if(EM.Button_Pressed(RulesButton,SW)){
            myWorkflow.getRuleMenus().setFromWhere(myWorkflow.getStep());
            myWorkflow.GoToStep(6);
            SW.clear();
        }

        //Easy Button -> start a game in easy mode, prepare the board and move to the show board step
        if(EM.Button_Pressed(EasyButton,SW)){
            myWorkflow.setDifficulty(1);
            myWorkflow.getPlayboard().initializePlayBoard();
            myWorkflow.nextStep();
            SW.clear();
        }

        //Medium Button -> start a game in medium mode, prepare the board and move to the show board step
        if(EM.Button_Pressed(MediumButton,SW)){
            myWorkflow.setDifficulty(2);
            myWorkflow.getPlayboard().initializePlayBoard();
            myWorkflow.nextStep();
            SW.clear();
        }

        //Hard Button -> start a game in hard mode, prepare the board and move to the show board step
        if(EM.Button_Pressed(HardButton,SW)){
            myWorkflow.setDifficulty(3);
            myWorkflow.getPlayboard().initializePlayBoard();
            myWorkflow.nextStep();
            SW.clear();
        }
    }


}

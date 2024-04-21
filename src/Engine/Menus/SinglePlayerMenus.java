package Engine.Menus;

import Engine.GraphicElements.Button;
import Engine.EventsManager;
import Engine.GraphicElements.GameText;
import Engine.Workflow;
import processing.core.PApplet;

//############################### CLASS DESCRIPTION #############################################################
/*
This class contains the Single-player menu. In this menu it's possible to start a game choosing the difficulty for
this game simply pressing on the difficulty buttons. A text asking the difficulty and a button to go back to the
main menu is provided. It's also possible to see the differences of the difficulty mode through a rules button.
 */

public class SinglePlayerMenus {

    //######################### FUNDAMENTALS VARIABLES ######################################
    //This section contains the fundamental vars necessary for the class to work properly.

    private final PApplet SW;

    private final EventsManager EM = new EventsManager();

    private final Workflow myWorkflow;

    //######################## BUTTON VARS #########################################
    //This sections contains the vars for the instances of all the buttons inside the single-player menu

    private final Button easyButton;
    private final Button mediumButton;
    private final Button hardButton;
    private final Button rulesButton;
    private final Button backButton;

    //########################## TEXT VARS #################################################
    //Vars to contains texts rendered by the engine in the screens of this menu

    private final GameText questionText;

    //################################# CLASS CONSTRUCTOR #############################################
    public SinglePlayerMenus(PApplet arg1, Workflow arg2){

        SW = arg1;
        myWorkflow = arg2;

        easyButton = new Button("EASY",SW);
        mediumButton = new Button("MEDIUM",SW);
        hardButton = new Button("HARD",SW);

        rulesButton = new Button(SW.loadImage("question_mark.png"),SW);
        backButton = new Button(SW.loadImage("Images/back.png"),SW);

        questionText = new GameText(SW);

        questionText.setText("Select the difficulty level");
        questionText.setTextColor(0,255,217);
        questionText.setPositions(Math.round(SW.width*0.16),SW.height/16);
        questionText.setSize(Math.round(SW.width*0.68));
        questionText.setTextSize(12F);

        easyButton.setPosition(Math.round(SW.width*0.08F),2*SW.height/3);
        easyButton.setButtonColor(0,150,0);
        easyButton.setTextColor(0,0,0);
        easyButton.setSize(Math.round(SW.width*0.225F),SW.height/5);
        easyButton.setTextSizePct(0.35F);
        easyButton.setPaddingsPct(0.26F,0.25F);

        mediumButton.setPosition(Math.round(SW.width*0.385F),2*SW.height/3);
        mediumButton.setButtonColor(255,255,0);
        mediumButton.setTextColor(0,0,0);
        mediumButton.setSize(Math.round(SW.width*0.225F),SW.height/5);
        mediumButton.setTextSizePct(0.35F);
        mediumButton.setPaddingsPct(0.13F,0.25F);

        hardButton.setPosition(Math.round(SW.width*0.69F),2*SW.height/3);
        hardButton.setButtonColor(255,0,0);
        hardButton.setTextColor(0,0,0);
        hardButton.setSize(Math.round(SW.width*0.225F),SW.height/5);
        hardButton.setTextSizePct(0.35F);
        hardButton.setPaddingsPct(0.225F,0.25F);

        rulesButton.setPosition(Math.round(SW.width*0.88F),SW.height/16);
        rulesButton.setButtonColor(0,0,0);
        rulesButton.setSize(Math.round(SW.width*0.08F),SW.height/8);

        backButton.setPosition(Math.round(SW.width*0.04F),SW.height/16);
        backButton.setButtonColor(0,0,0);
        backButton.setSize(Math.round(SW.width*0.08F),SW.height/8);
    }

    //################################### SHOW FUNCTIONS ####################################################

    public void showSPDifficultyMenu(){
        SW.background(0); //set the background colo

        questionText.showText();

        easyButton.showButton();
        mediumButton.showButton();
        hardButton.showButton();
        rulesButton.showButton();
        backButton.showButton();

        //Back Button
        if(EM.isButtonPressed(backButton,SW)){
            myWorkflow.previousStep();
            SW.clear(); //Clear all previous elements from the screen
        }

        //Rules Button
        if(EM.isButtonPressed(rulesButton,SW)){
            myWorkflow.getRuleMenus().setFromWhere(myWorkflow.getStep());
            myWorkflow.goToStep(6);
            SW.clear();
        }

        //Easy Button
        if(EM.isButtonPressed(easyButton,SW)){
            myWorkflow.setDifficulty(1);
            myWorkflow.getSinglePlayerPlayboard().initializePlayBoard();
            myWorkflow.nextStep();
            SW.clear();
        }

        //Medium Button
        if(EM.isButtonPressed(mediumButton,SW)){
            myWorkflow.setDifficulty(2);
            myWorkflow.getSinglePlayerPlayboard().initializePlayBoard();
            myWorkflow.nextStep();
            SW.clear();
        }

        //Hard Button
        if(EM.isButtonPressed(hardButton,SW)){
            myWorkflow.setDifficulty(3);
            myWorkflow.getSinglePlayerPlayboard().initializePlayBoard();
            myWorkflow.nextStep();
            SW.clear();
        }
    }


}

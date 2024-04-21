package Engine.Menus;

import Engine.EventsManager;
import Engine.GraphicElements.GameText;
import Engine.GraphicElements.Button;
import Engine.Workflow;
import processing.core.PApplet;
import processing.core.PImage;

//############################### CLASS DESCRIPTION #############################################################
/*
This class contains the starting menus for a Multiplayer game. In this class are reported two menus. The first one
is the introductory menu where the rules and how the multiplayer game will be conducted is explained. From this menu
it's possible to go back to the main menu with a back button. Clicking on start game, we will be conducted to the
difficulty choice menu. In this menu it's possible to start a game choosing the difficulty for this game simply
pressing on the difficulty buttons. A text asking the difficulty and a button to go back to the introductory menu is
provided. It's also possible to see the differences of the difficulty mode through a rules button.
 */


public class MultiStartMenus {

    //############################ FUNDAMENTAL VARS ############################################
    //This section contains the fundamental vars necessary for the class to work properly.

    private final PApplet SW;

    private final EventsManager EM = new EventsManager();

    private final Workflow myWorkflow;

    //############################ BUTTON VARS ##############################################
    //This sections contains the instances of all the buttons inside the menus

    // Multiplayer introduction screen
    private final Button backButton;
    private final Button startButton;

    //Multiplayer difficulty screen
    private final Button easyButton;
    private final Button mediumButton;
    private final Button hardButton;
    private final Button rulesButton;

    //########################## TEXT VARS #################################################
    //Vars to contains texts rendered by the engine in the screens of this menu

    //Multiplayer introduction screen
    private final GameText titleText;

    //Multiplayer difficulty screen
    private final GameText questionText;

    //############################ IMAGES #############################################
    //Vars to contains images rendered by the engine in the screens of these menus

    private final PImage RULES_IMAGE;

    //########################## CLASS CONSTRUCTOR #############################################
    public MultiStartMenus(PApplet arg1, Workflow arg2){
        SW = arg1;
        myWorkflow = arg2;

        //######################## INTRODUCTION MENU ######################################################
        //In this part are instanced all the elements for the introduction menu

        titleText = new GameText(SW);
        titleText.setText("MULTIPLAYER RULES");
        titleText.setTextColor(0,255,217);
        titleText.setPositions(Math.round(SW.width*0.16F),SW.height/16);
        titleText.setSize(Math.round(SW.width*0.68F));
        titleText.setTextSize(12F);

        backButton = new Button(SW.loadImage("Images/back.png"),SW);
        backButton.setPosition(Math.round(SW.width*0.04F),SW.height/16);
        backButton.setButtonColor(0,0,0);
        backButton.setSize(Math.round(SW.width*0.08F),SW.height/8);

        startButton = new Button("START GAME",SW);
        startButton.setPosition(Math.round(SW.width/3.0F),Math.round(SW.height*0.75F));
        startButton.setButtonColor(0,255,217);
        startButton.setTextColor(150,0,150);
        startButton.setSize(Math.round(SW.width/3.0F),Math.round(SW.height*0.16F));
        startButton.setTextSizePct(0.35F);
        startButton.setPaddingsPct(0.175F,0.25F);

        RULES_IMAGE = SW.loadImage("Images/multiplayer_rules.png");

        //################################# CHOOSE DIFFICULTY MENU ########################################
        //In this part are instanced all the elements for the difficulty menu

        easyButton = new Button("EASY",SW);
        mediumButton = new Button("MEDIUM",SW);
        hardButton = new Button("HARD",SW);
        rulesButton = new Button(SW.loadImage("question_mark.png"),SW);
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
    }

    //######################### SHOW FUNCTIONS ##############################################

    public void showMultiPlayerIntroMenu(){
        SW.background(0);  //set the background color

        backButton.showButton();
        startButton.showButton();

        titleText.showText();

        SW.image(RULES_IMAGE,Math.round(SW.width*0.04F),
                SW.height/4.0F,
                SW.width*0.92F,
                Math.round(RULES_IMAGE.height*SW.width*0.92F*1.0F/ RULES_IMAGE.width));

        //Back button
        if(EM.isButtonPressed(backButton,SW)){
            myWorkflow.goToStep(1);
            SW.clear(); //Clear all previous elements from the screen
        }

        //Start button
        if(EM.isButtonPressed(startButton,SW)){
            myWorkflow.nextStep();
            SW.clear();
        }
    }

    public void showMPDifficultyMenu(){
        SW.background(0); //set the background color

        questionText.showText();

        easyButton.showButton();
        mediumButton.showButton();
        hardButton.showButton();
        rulesButton.showButton();
        backButton.showButton();

        //Back button
        if(EM.isButtonPressed(backButton,SW)){
            myWorkflow.previousStep();
            SW.clear(); //Clear all previous elements from the screen
        }

        //Rules menu
        if(EM.isButtonPressed(rulesButton,SW)){
            myWorkflow.getRuleMenus().setFromWhere(myWorkflow.getStep());
            myWorkflow.goToStep(6);
            SW.clear();
        }

        //Easy Button
        if(EM.isButtonPressed(easyButton,SW)){
            myWorkflow.setDifficulty(1);
            myWorkflow.getMultiPlayerPlayboard().initializePlayBoard();
            myWorkflow.nextStep();
            SW.clear();
        }

        //Medium Button
        if(EM.isButtonPressed(mediumButton,SW)){
            myWorkflow.setDifficulty(2);
            myWorkflow.getMultiPlayerPlayboard().initializePlayBoard();
            myWorkflow.nextStep();
            SW.clear();
        }

        //Hard Button
        if(EM.isButtonPressed(hardButton,SW)){
            myWorkflow.setDifficulty(3);
            myWorkflow.getMultiPlayerPlayboard().initializePlayBoard();
            myWorkflow.nextStep();
            SW.clear();
        }

    }

}

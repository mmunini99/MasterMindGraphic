package Engine.Menus;

import Engine.GraphicElements.Button;
import Engine.EventsManager;
import Engine.GraphicElements.GameText;
import Engine.Workflow;
import processing.core.*;

//############################### CLASS DESCRIPTION #############################################################
/*
This class contains the starting menus a.k.a. the menus associated with the main menu and show in the first phases
of the software. In particular, in this class we have the splash screen of the game, the main menu and the credit
screen which are described using 3 show function which are passed to the workflow when necessary.
 */

public class StartMenus {

    //############################ FUNDAMENTAL VARS ############################################
    //This section contains the fundamental vars necessary for the class to work properly.

    private final PApplet SW;

    private final Workflow myWorkflow;

    private final EventsManager EM = new EventsManager();

    //############################ IMAGE VARS #################################################
    //Vars to contains images rendered by the engine in the screens of this menu

    private final PImage SPLASH_ART;
    private final PImage CREDITS_IMAGE;

    //############################# BUTTON VARS #################################################
    //This sections contains the instances of all the buttons inside the main menu

    private final Button singlePlayerButton;
    private final Button multiPlayerButton;
    private final Button exitButton;
    private final Button creditsButton;
    private final Button backButton;

    //########################## TEXT VARS #################################################
    //Vars to contains texts rendered by the engine in the screens of this menu

    private final GameText gameTitle;
    private final GameText creditsTitle;

    //################################# CLASS CONSTRUCTOR #############################################
    public StartMenus(PApplet arg1, Workflow arg2){
        SW = arg1;
        myWorkflow = arg2;

        SPLASH_ART = SW.loadImage("Images/splash.png");
        CREDITS_IMAGE = SW.loadImage("Images/credits.png");

        gameTitle = new GameText(SW);
        gameTitle.setText("Mastermind");
        gameTitle.setTextColor(0,255,217);
        gameTitle.setPositions(Math.round(SW.width*0.2), Math.round(SW.height/8.0));
        gameTitle.setSize(Math.round(SW.width*0.6));
        gameTitle.setTextSize(12F);

        creditsTitle = new GameText(SW);
        creditsTitle.setText("CREDITS");
        creditsTitle.setTextColor(0,255,217);
        creditsTitle.setPositions(Math.round(SW.width*0.285F),SW.height/32);
        creditsTitle.setSize(Math.round(SW.width*0.5F));
        creditsTitle.setTextSize(10F);

        singlePlayerButton = new Button("SINGLE-PLAYER",SW);
        singlePlayerButton.setPosition(SW.width/5,SW.height/2);
        singlePlayerButton.setButtonColor(0,255,217);
        singlePlayerButton.setTextColor(150,0,150);
        singlePlayerButton.setSize(Math.round(SW.width*0.23F),Math.round(SW.height*0.16F));
        singlePlayerButton.setTextSizePct(0.25F);
        singlePlayerButton.setPaddingsPct(0.11F,0.30F);

        multiPlayerButton = new Button("MULTIPLAYER",SW);
        multiPlayerButton.setPosition(Math.round(SW.width-(SW.width/5.0F+SW.width*0.23F)),SW.height/2);
        multiPlayerButton.setButtonColor(0,255,217);
        multiPlayerButton.setTextColor(150,0,150);
        multiPlayerButton.setSize(Math.round(SW.width*0.23F),Math.round(SW.height*0.16F));
        multiPlayerButton.setTextSizePct(0.25F);
        multiPlayerButton.setPaddingsPct(0.15F,0.30F);

        exitButton = new Button("EXIT GAME",SW);
        exitButton.setPosition(SW.width/5,Math.round(SW.height*0.75F));
        exitButton.setButtonColor(0,255,217);
        exitButton.setTextColor(150,0,150);
        exitButton.setSize(Math.round(SW.width*0.23F),Math.round(SW.height*0.16F));
        exitButton.setTextSizePct(0.25F);
        exitButton.setPaddingsPct(0.215F,0.30F);

        creditsButton = new Button("CREDITS",SW);
        creditsButton.setPosition(Math.round(SW.width-(SW.width/5.0F+SW.width*0.23F)),Math.round(SW.height*0.75F));
        creditsButton.setButtonColor(0,255,217);
        creditsButton.setTextColor(150,0,150);
        creditsButton.setSize(Math.round(SW.width*0.23F),Math.round(SW.height*0.16F));
        creditsButton.setTextSizePct(0.25F);
        creditsButton.setPaddingsPct(0.275F,0.30F);

        backButton = new Button(SW.loadImage("Images/back.png"),SW);
        backButton.setPosition(Math.round(SW.width*0.04F),SW.height/16);
        backButton.setButtonColor(0,0,0);
        backButton.setSize(Math.round(SW.width*0.08F),SW.height/8);
    }

    //################################### SHOW FUNCTIONS ####################################################

    public void showSplashScreen(){
        EM.activateTimeEvent(3000);

        SW.image(SPLASH_ART,0,0,SW.width,SW.height);

        if(EM.checkTimeEvent()){
            EM.resetTimeEvent();
            myWorkflow.nextStep();
            SW.clear();
        }

    }

    public void showMainMenu(){
        SW.background(0); //set the background color

        gameTitle.showText();

        singlePlayerButton.showButton();
        multiPlayerButton.showButton();
        exitButton.showButton();
        creditsButton.showButton();

        //Exit button
        if(EM.isButtonPressed(exitButton,SW)){
            SW.exit();
        }

        //Single-player button
        if(EM.isButtonPressed(singlePlayerButton,SW)){
            myWorkflow.nextStep();
            SW.clear(); //Clear all previous elements from the screen
        }

        //Multiplayer button
        if(EM.isButtonPressed(multiPlayerButton,SW)){
            myWorkflow.goToStep(7);
            SW.clear();
        }

        //Credits button
        if(EM.isButtonPressed(creditsButton,SW)){
            myWorkflow.goToStep(12);
            SW.clear();
        }

    }

    public void showCreditsMenu(){
        SW.background(0);  //set the background color

        SW.image(CREDITS_IMAGE,Math.round(SW.width*0.04F),Math.round(SW.height*0.29F),Math.round(SW.width*0.92F),Math.round(SW.height*0.66F));

        backButton.showButton();

        creditsTitle.showText();

        //Back button
        if(EM.isButtonPressed(backButton,SW)){
            myWorkflow.goToStep(1);
            SW.clear();
        }
    }


}

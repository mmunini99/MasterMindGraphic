package Engine.Menus;

import Engine.GraphicElements.Button;
import Engine.Events_Manager;
import Engine.GameText;
import Engine.WorkFlow;
import processing.core.*;

//############################### CLASS DESCRIPTION #############################################################
/*
This class contains the starting menus a.k.a. the menus associated with the main menu and show in the first phases
of the software. In particular, in this class we have the splash screen of the game, the main menu and the credit
screen which are described using 3 show function which are passed to the workflow when necessary.
 */

public class Start_Menus {

    //############################ FUNDAMENTAL VARS ############################################
    //This section contains the fundamental vars necessary for the class to work properly.

    //Used to refer to the Processing Application
    private final PApplet SW;

    //Used to refer to the workflow
    private final WorkFlow myWorkFlow;

    //Event Manger, needed to manage the Button and Time events
    private final Events_Manager EM = new Events_Manager();

    //############################ IMAGE VARS #################################################
    //Vars to contains images rendered by the engine in the screens of this menu
    private final PImage splash_art;

    //############################# BUTTON VARS #################################################
    //This sections contains the instances of all the buttons inside the main menu

    private final Button SP_Button; //Button for the Single-player mode
    private final Button MP_Button; //Button for the Multiplayer mode
    private final Button Exit_Button; //Button to exit from the game
    private final Button Credits_Button; //Button to show the credits

    //########################## TEXT VARS #################################################
    //Vars to contains texts rendered by the engine in the screens of this menu

    private final GameText TitleScreen; //Text of the title of the game

    //################################# CLASS CONSTRUCTOR #############################################
    public Start_Menus(PApplet arg1, WorkFlow arg2){
        //Save the links to the PApplet (needed for render) and the Workflow (to move to the other screens)
        SW = arg1;
        myWorkFlow = arg2;

        //Load image from game files for the splash screen
        splash_art = SW.loadImage("Images/splash.png");

        //Title GameText characteristics setup
        TitleScreen = new GameText(SW);
        TitleScreen.setText("Mastermind");
        TitleScreen.setTextColor(0,255,217);
        TitleScreen.setPositions(Math.round(SW.width*0.2), Math.round(SW.height/8.0));
        TitleScreen.setSize(Math.round(SW.width*0.6));
        TitleScreen.setTextSize(12F);

        //Single-player button setup
        SP_Button = new Button("SINGLE-PLAYER",SW);
        SP_Button.setPosition(SW.width/5,SW.height/2);
        SP_Button.setButtonColor(0,255,217);
        SP_Button.setTextcolor(150,0,150);
        SP_Button.setSize(Math.round(SW.width*0.23F),Math.round(SW.height*0.16F));
        SP_Button.setTextsizePerc(0.25F);
        SP_Button.setPaddingsPerc(0.11F,0.30F);

        //Multiplayer button setup
        MP_Button = new Button("MULTIPLAYER",SW);
        MP_Button.setPosition(Math.round(SW.width-(SW.width/5.0F+SW.width*0.23F)),SW.height/2);
        MP_Button.setButtonColor(0,255,217);
        MP_Button.setTextcolor(150,0,150);
        MP_Button.setSize(Math.round(SW.width*0.23F),Math.round(SW.height*0.16F));
        MP_Button.setTextsizePerc(0.25F);
        MP_Button.setPaddingsPerc(0.15F,0.30F);

        //Exit button setup
        Exit_Button = new Button("EXIT GAME",SW);
        Exit_Button.setPosition(SW.width/5,Math.round(SW.height*0.75F));
        Exit_Button.setButtonColor(0,255,217);
        Exit_Button.setTextcolor(150,0,150);
        Exit_Button.setSize(Math.round(SW.width*0.23F),Math.round(SW.height*0.16F));
        Exit_Button.setTextsizePerc(0.25F);
        Exit_Button.setPaddingsPerc(0.215F,0.30F);

        //Credits button setup
        Credits_Button = new Button("CREDITS",SW);
        Credits_Button.setPosition(Math.round(SW.width-(SW.width/5.0F+SW.width*0.23F)),Math.round(SW.height*0.75F));
        Credits_Button.setButtonColor(0,255,217);
        Credits_Button.setTextcolor(150,0,150);
        Credits_Button.setSize(Math.round(SW.width*0.23F),Math.round(SW.height*0.16F));
        Credits_Button.setTextsizePerc(0.25F);
        Credits_Button.setPaddingsPerc(0.275F,0.30F);
    }

    //################################### SHOW FUNCTIONS ####################################################

    //Splash screen show function
    public void splashing(){
        //Set the time to wait before showing the starting menu
        EM.Activate_Time_Event(3000);

        //Meanwhile show the splash image
        SW.image(splash_art,0,0,SW.width,SW.height);

        //When the time has come go to the main menu
        if(EM.Time_Event()){
            EM.reset_time_Event();
            myWorkFlow.nextStep();
            SW.clear();
        }

    }

    //Main menu screen show function
    public void main_menu1(){
        SW.background(0); //set the background color (0=BLACK)

        TitleScreen.showText(); //show the title of the game

        //Show the buttons
        SP_Button.showButton();
        MP_Button.showButton();
        Exit_Button.showButton();
        Credits_Button.showButton();

        //Button Events: what happen when each button is pressed

        //Exit button -> End program
        if(EM.Button_Pressed(Exit_Button,SW)){
            SW.exit();
        }

        //Single-player button -> Next Step alias go to Single Player menu
        if(EM.Button_Pressed(SP_Button,SW)){
            myWorkFlow.nextStep();
            SW.clear(); //Clear all previous elements from the screen
        }

        //Multiplayer button -> Go to the Multiplayer menu
        if(EM.Button_Pressed(MP_Button,SW)){
            myWorkFlow.GoToStep(7);
            SW.clear();
        }

        //Credits button -> Go to the Credits menu
        if(EM.Button_Pressed(Credits_Button,SW)){
            myWorkFlow.GoToStep(12);
            SW.clear();
        }

    }

    //Credits show function
    public void Credits_menu(){
        SW.background(0);
        //TODO: fare i crediti del gioco, i nostri nomi più le licenze
    }

}

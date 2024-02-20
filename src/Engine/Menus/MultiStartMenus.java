package Engine.Menus;

import Engine.Events_Manager;
import Engine.GraphicElements.GameText;
import Engine.GraphicElements.Button;
import Engine.WorkFlow;
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

    //Used to refer to the Processing Application
    private final PApplet SW;

    //Event Manger, needed to manage the Button and Time events
    private final Events_Manager EM = new Events_Manager();

    //Used to refer to the workflow
    private final WorkFlow myWorkflow;

    //############################ BUTTON VARS ##############################################
    //This sections contains the instances of all the buttons inside the menus

    // Multiplayer introduction screen
    private final Button backbutton; //Button to go back to the main menu
    private final Button startbutton; //Button to start a multiplayer game

    //Multiplayer difficulty screen
    private final Button EasyButton; //Button to start the multiplayer game in easy mode
    private final Button MediumButton; //Button to start the multiplayer game in medium mode
    private final Button HardButton; //Button to start the multiplayer game in hard mode
    private final Button RulesButton; //Button to visualize the difficulty rules

    //########################## TEXT VARS #################################################
    //Vars to contains texts rendered by the engine in the screens of this menu

    //Multiplayer difficulty screen
    private final GameText QuestionText; //Choose the difficulty question

    //Multiplayer introduction screen
    private final GameText TitleText; //Text that appear in the multiplayer introduction screen

    //############################ IMAGES #############################################
    //Vars to contains images rendered by the engine in the screens of these menus

    //Image with the rules of how multiplayer works
    private final PImage RulesImage;

    //########################## CLASS CONSTRUCTOR #############################################
    public MultiStartMenus(PApplet arg1, WorkFlow arg2){
        //Save the links to the PApplet (needed for render) and the Workflow (to move to the other screens)
        SW = arg1;
        myWorkflow = arg2;

        //######################## INTRODUCTION MENU ######################################################
        //In this part are instanced all the elements for the introduction menu

        //Title GameText characteristics setup
        TitleText = new GameText(SW);
        TitleText.setText("MULTIPLAYER RULES");
        TitleText.setTextColor(0,255,217);
        TitleText.setPositions(Math.round(SW.width*0.16F),SW.height/16);
        TitleText.setSize(Math.round(SW.width*0.68F));
        TitleText.setTextSize(12F);

        //Back button setup
        backbutton = new Button(SW.loadImage("Images/back.png"),SW);
        backbutton.setPosition(Math.round(SW.width*0.04F),SW.height/16);
        backbutton.setButtonColor(0,0,0);
        backbutton.setSize(Math.round(SW.width*0.08F),SW.height/8);

        //Start button setup
        startbutton = new Button("START GAME",SW);
        startbutton.setPosition(Math.round(SW.width/3.0F),Math.round(SW.height*0.75F));
        startbutton.setButtonColor(0,255,217);
        startbutton.setTextcolor(150,0,150);
        startbutton.setSize(Math.round(SW.width/3.0F),Math.round(SW.height*0.16F));
        startbutton.setTextsizePerc(0.35F);
        startbutton.setPaddingsPerc(0.175F,0.25F);

        //Load image from game files for the multiplayer rules
        RulesImage = SW.loadImage("Images/multiplayer_rules.png");

        //################################# CHOOSE DIFFICULTY MENU ########################################
        //In this part are instanced all the elements for the difficulty menu

        //First let's instantiate all
        EasyButton = new Button("EASY",SW);
        MediumButton = new Button("MEDIUM",SW);
        HardButton = new Button("HARD",SW);
        RulesButton = new Button(SW.loadImage("question_mark.png"),SW);
        QuestionText = new GameText(SW);

        //QuestionText setup
        QuestionText.setText("Select the difficulty level");
        QuestionText.setTextColor(0,255,217);
        QuestionText.setPositions(Math.round(SW.width*0.16),SW.height/16);
        QuestionText.setSize(Math.round(SW.width*0.68));
        QuestionText.setTextSize(12F);

        //EasyButton setup
        EasyButton.setPosition(Math.round(SW.width*0.08F),2*SW.height/3);
        EasyButton.setButtonColor(0,150,0);
        EasyButton.setTextcolor(0,0,0);
        EasyButton.setSize(Math.round(SW.width*0.225F),SW.height/5);
        EasyButton.setTextsizePerc(0.35F);
        EasyButton.setPaddingsPerc(0.26F,0.25F);

        //MediumButton setup
        MediumButton.setPosition(Math.round(SW.width*0.385F),2*SW.height/3);
        MediumButton.setButtonColor(255,255,0);
        MediumButton.setTextcolor(0,0,0);
        MediumButton.setSize(Math.round(SW.width*0.225F),SW.height/5);
        MediumButton.setTextsizePerc(0.35F);
        MediumButton.setPaddingsPerc(0.13F,0.25F);

        //HardButton setup
        HardButton.setPosition(Math.round(SW.width*0.69F),2*SW.height/3);
        HardButton.setButtonColor(255,0,0);
        HardButton.setTextcolor(0,0,0);
        HardButton.setSize(Math.round(SW.width*0.225F),SW.height/5);
        HardButton.setTextsizePerc(0.35F);
        HardButton.setPaddingsPerc(0.225F,0.25F);

        //RulesButton setup
        RulesButton.setPosition(Math.round(SW.width*0.88F),SW.height/16);
        RulesButton.setButtonColor(0,0,0);
        RulesButton.setSize(Math.round(SW.width*0.08F),SW.height/8);
    }

    //######################### SHOW FUNCTIONS ##############################################

    //Multiplayer Introduction menu show function
    public void MultiIntroMenu(){
        SW.background(0);  //set the background color (0=BLACK)

        //Show the buttons
        backbutton.showButton();
        startbutton.showButton();

        //Show the text
        TitleText.showText();

        //Render the image with the rules written on
        SW.image(RulesImage,Math.round(SW.width*0.04F),
                SW.height/4.0F,
                SW.width*0.92F,
                Math.round(RulesImage.height*SW.width*0.92F*1.0F/RulesImage.width));

        //Button Events: what happen when each button is pressed

        //Back button -> Go back to the main menu
        if(EM.Button_Pressed(backbutton,SW)){
            myWorkflow.GoToStep(1);
            SW.clear(); //Clear all previous elements from the screen
        }

        //Start button -> go to the next menu a.k.a. difficulty choice menu
        if(EM.Button_Pressed(startbutton,SW)){
            myWorkflow.nextStep();
            SW.clear();
        }
    }

    //Multiplayer difficulty choice menu show function
    public void mp_difficulty_menu(){
        SW.background(0); //set the background color (0=BLACK)

        //Show the text
        QuestionText.showText();

        //Show the buttons
        EasyButton.showButton();
        MediumButton.showButton();
        HardButton.showButton();
        RulesButton.showButton();
        backbutton.showButton();

        //Button Events: what happen when each button is pressed

        //Back button -> Go back to the multiplayer introduction menu
        if(EM.Button_Pressed(backbutton,SW)){
            myWorkflow.previousStep();
            SW.clear(); //Clear all previous elements from the screen
        }

        //Rules menu -> Go to the Rules Menu and show the difficulty explanations
        if(EM.Button_Pressed(RulesButton,SW)){
            myWorkflow.getRuleMenus().setFromWhere(myWorkflow.getStep());
            myWorkflow.GoToStep(6);
            SW.clear();
        }

        //Easy Button -> start a multiplayer game in easy mode, prepare the board and move to
        //the show multiplayer board step
        if(EM.Button_Pressed(EasyButton,SW)){
            myWorkflow.setDifficulty(1);
            myWorkflow.getMp_playboard().initializePlayBoard();
            myWorkflow.nextStep();
            SW.clear();
        }

        //Medium Button -> start a multiplayer game in medium mode, prepare the board and move to
        //the show multiplayer board step
        if(EM.Button_Pressed(MediumButton,SW)){
            myWorkflow.setDifficulty(2);
            myWorkflow.getMp_playboard().initializePlayBoard();
            myWorkflow.nextStep();
            SW.clear();
        }

        //Hard Button -> start a multiplayer game in hard mode, prepare the board and move to
        //the show multiplayer board step
        if(EM.Button_Pressed(HardButton,SW)){
            myWorkflow.setDifficulty(3);
            myWorkflow.getMp_playboard().initializePlayBoard();
            myWorkflow.nextStep();
            SW.clear();
        }

    }

}

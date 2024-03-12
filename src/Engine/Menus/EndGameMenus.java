package Engine.Menus;

import Engine.Events_Manager;
import Engine.GraphicElements.Button;
import Engine.GraphicElements.GameText;
import Engine.GraphicElements.RGB;
import Engine.GraphicElements.Slot;
import Engine.WorkFlow;
import processing.core.PApplet;
import processing.core.PImage;

//############################### CLASS DESCRIPTION #############################################################
/*
This class contain all the screens that appear after the end of a game, both single-player and multiplayer.
There are two common elements: the main menu button (to go back to the main menu) and the exit button to quit the
game. The texts are different from single player and multiplayer so are instantiate separately. In case of defeat
(SP) or draw (MP) the undiscovered secret code will also be displayed.
 */

public class EndGameMenus {

    //########################## FUNDAMENTAL VARS ########################################
    //This section contains the fundamental vars necessary for the class to work properly.

    //Used to refer to the Processing Application
    private final PApplet SW;

    //Used to refer to the workflow
    private final WorkFlow myWorkflow;

    //Event Manger, needed to manage the Button events
    private final Events_Manager EM = new Events_Manager();

    //############################## CODE VARS ##########################################
    //In case of defeat (SP) or draw (MP) these vars are used to render the secret code

    //THe secret code itself express through int
    private int[] secret_code;

    //The palette of color used in the game. Needed to pass from the secret_code to the graphics
    private RGB[] Palette;

    //As for the playboard, the secret code is show in some slots
    private final Slot[] codeSlots = new Slot[4];

    //An image frame used to render inside the secret code
    private final PImage CodeDisplay;

    //############################### MULTIPLAYER VARS ######################################

    //In case of a win in MP mode, this var is used to register which player has win. True is Player 1 and
    //False is Player 2.
    private boolean winner = true; // default = Player 1

    //############################## BUTTONS VARS ############################################
    //This sections contains the vars for the instances of all the buttons inside the end game menus

    private final Button MainMenuButton; //Button to go back to the main menu
    private final Button ExitButton; //Button to quit the game

    //############################ TEXT VARS #########################################
    //Vars to contains texts rendered by the engine in the screens of this menu

    GameText WinText; //Used in SP: will show the victory text
    GameText Player1WinText; //Used in MP: will show the victory of the player 1 text
    GameText Player2WinText; //Used in MP: will show the victory of the player 2 text

    GameText LoseUpperText; //Used in SP: it's the upper text of the screen
    GameText LoseLowerText; //Used in SP: it's the lower text of the screen
    GameText DrawUpperText; //Used in MP: it's the upper text of the screen
    GameText DrawLowerText; //Used in MP: it's the lower text of the screen

    //################################# CLASS CONSTRUCTOR #############################################
    public EndGameMenus(PApplet arg1,WorkFlow arg2){

        //Set the reference to the workflow and Processing application
        SW = arg1;
        myWorkflow = arg2;

        //Load the image used as a frame for the secret code displaying
        CodeDisplay = SW.loadImage("code_display.png");

        //Instantiate all the buttons and all the texts
        MainMenuButton = new Button("MAIN MENU",SW);
        ExitButton = new Button("EXIT GAME", SW);
        WinText = new GameText(SW);
        LoseUpperText = new GameText(SW);
        LoseLowerText = new GameText(SW);
        Player1WinText = new GameText(SW);
        Player2WinText = new GameText(SW);
        DrawUpperText = new GameText(SW);
        DrawLowerText = new GameText(SW);

        //Set main menu button characteristics
        MainMenuButton.setPosition(Math.round(SW.width*0.08F),2*SW.height/3);
        MainMenuButton.setButtonColor(0,255,217);
        MainMenuButton.setTextColor(150,0,150);
        MainMenuButton.setSize(Math.round(SW.width*0.225F),SW.height/5);
        MainMenuButton.setTextSizePct(0.225F);
        MainMenuButton.setPaddingsPct(0.135F,0.33F);

        //Set exit button characteristics
        ExitButton.setPosition(Math.round(SW.width*0.695F),2*SW.height/3);
        ExitButton.setButtonColor(0,255,217);
        ExitButton.setTextColor(150,0,150);
        ExitButton.setSize(Math.round(SW.width*0.225F),SW.height/5);
        ExitButton.setTextSizePct(0.225F);
        ExitButton.setPaddingsPct(0.15F,0.33F);


        //Set win text (SP) button characteristics
        WinText.setText("YOU WIN!");
        WinText.setPositions(Math.round(SW.width*0.2),SW.height/8);
        WinText.setTextColor(0,255,217);
        WinText.setSize(Math.round(SW.width*0.6F));
        WinText.setTextSize(11.8F);

        //Set the upper text for the defeat menu (SP) characteristics (GAME OVER)
        LoseUpperText.setText("GAME OVER");
        LoseUpperText.setPositions(Math.round(SW.width*0.2),SW.height/24);
        LoseUpperText.setTextColor(0,255,217);
        LoseUpperText.setSize(Math.round(SW.width*0.6F));
        LoseUpperText.setTextSize(11.8F);

        //Set the lower text for the defeat menu (SP) characteristics (The secret code)
        LoseLowerText.setText("The secret code was:");
        LoseLowerText.setPositions(Math.round(SW.width*0.3),Math.round(SW.height*0.3F));
        LoseLowerText.setTextColor(0,255,217);
        LoseLowerText.setSize(Math.round(SW.width*0.4F));
        LoseLowerText.setTextSize(11.8F);

        //Set player 1 victory text (MP) characteristics
        Player1WinText.setText("PLAYER 1 WINS!");
        Player1WinText.setPositions(Math.round(SW.width*0.2),SW.height/8);
        Player1WinText.setTextColor(0,255,217);
        Player1WinText.setSize(Math.round(SW.width*0.6F));
        Player1WinText.setTextSize(12F);

        //Set player 2 victory text (MP) characteristics
        Player2WinText.setText("PLAYER 2 WINS!");
        Player2WinText.setPositions(Math.round(SW.width*0.2),SW.height/8);
        Player2WinText.setTextColor(0,255,217);
        Player2WinText.setSize(Math.round(SW.width*0.6F));
        Player2WinText.setTextSize(12F);

        //Set the upper text for the draw menu (MP) characteristics (DRAW)
        DrawUpperText.setText("DRAW!");
        DrawUpperText.setPositions(Math.round(SW.width*0.275),SW.height/24);
        DrawUpperText.setTextColor(0,255,217);
        DrawUpperText.setSize(Math.round(SW.width*0.6F));
        DrawUpperText.setTextSize(9F);

        //Set the lower text for the draw menu (MP) characteristics (The secret code)
        DrawLowerText.setText("The secret code was:");
        DrawLowerText.setPositions(Math.round(SW.width*0.3),Math.round(SW.height*0.3F));
        DrawLowerText.setTextColor(0,255,217);
        DrawLowerText.setSize(Math.round(SW.width*0.4F));
        DrawLowerText.setTextSize(11.8F);

    }

    //########################### OTHER FUNCTIONS #######################################

    //Internal function used to prepare the slots for the secret code (so defeat or draw) giving the correct colors and
    //computing their characteristics
    private void prepareSlots(){
        for(int i = 0; i< secret_code.length; i++){
            codeSlots[i] = new Slot(SW);
            codeSlots[i].setPosition(Math.round(0.305F*SW.width+(i+0.5F)*SW.width*0.0975F),
                    Math.round(2*SW.height/5.0F+CodeDisplay.height*0.195F*SW.width/CodeDisplay.width));
            codeSlots[i].setRadius(Math.round(CodeDisplay.height*0.39F*0.5F*SW.width/CodeDisplay.width),
                    Math.round(CodeDisplay.height*0.39F*0.5F*SW.width/CodeDisplay.width));
            codeSlots[i].SlotActivation();
            codeSlots[i].fillSlot(secret_code[i]);
            codeSlots[i].setFillColor(Palette[secret_code[i]]);
            codeSlots[i].SlotDeactivation();
        }
    }

    //######################### SET FUNCTION ###################################

    //This function will simply set the secret code and so the palette. A control over the extension of the code is done.
    public void setSecretCode(int[] s,RGB[] P){
        if(s.length==4) {
            secret_code = s;
            Palette = P;
            prepareSlots();
        }
        else{
            throw new RuntimeException("EndGameMenus: setSecretCode: Secret code is too long!");
        }
    }

    //This one will set the value of the boolean winner
    public void setWinner(boolean w){
        winner = w;
    }

    //######################### COMMON SHOW ###################################
    //All of the endgame menus are some common characteristics: the two buttons and the background so, we use
    //a separate method to render them.

    private void CommonShow(){
        SW.background(0); //set the background color (0=BLACK)

        //Show the buttons
        MainMenuButton.showButton();
        ExitButton.showButton();

        //Button Events: what happen when each button is pressed

        //MainMenuButton -> Go back to the main menu
        if(EM.Button_Pressed(MainMenuButton,SW)){
            System.gc();
            myWorkflow.GoToStep(1);
            SW.clear();
        }

        //ExitButton -> exit game
        if(EM.Button_Pressed(ExitButton,SW)){
            SW.exit();
        }
    }

    //######################### VICTORY MENU ##################################
    //This screen are displayed when the game concludes in a victory

    //SINGLE-PLAYER: show the buttons and the victory text
    public void VictoryMenu(){
        CommonShow();
        WinText.showText();
    }

    //MULTIPLAYER: show the buttons and the correct text according to the winner
    public void MPVictoryMenu(){
        CommonShow();
        if(winner){
            Player1WinText.showText(); //Player 1
        }
        else{
            Player2WinText.showText(); //Player 2
        }
    }

    //############################## DEFEAT MENU ########################################

    //SINGLE-PLAYER: show the defeat menu
    public void DefeatMenu(){
        CommonShow(); //Show all the common buttons

        //This control is made to assure that the secret code has been given to the menu class
        if(secret_code ==null){
            throw new RuntimeException("EndGameMenus: DefeatMenu: No secret code given to the End Menu");
        }

        //Show the texts
        LoseUpperText.showText();
        LoseLowerText.showText();

        //Render the image to frame the slots of the secret code
        SW.image(CodeDisplay,Math.round(0.305F*SW.width),
                2.0F*SW.height/5.0F,
                0.39F*SW.width,
                CodeDisplay.height*0.39F*SW.width/CodeDisplay.width);

        //And render then the slots
        for (Slot codeSlot : codeSlots) {
            codeSlot.showSlot();
        }

    }

    //######################## MP DRAW MENU ############################################

    //MULTIPLAYER: show the draw menu
    public void MPDrawMenu(){
        CommonShow(); //Show all the common buttons

        //This control is made to assure that the secret code has been given to the menu class
        if(secret_code ==null){
            throw new RuntimeException("EndGameMenus: MPDrawMenu: No secret code given to the End Menu");
        }

        //Show the texts
        DrawUpperText.showText();
        DrawLowerText.showText();

        //Render the image to frame the slots of the secret code
        SW.image(CodeDisplay,Math.round(0.305F*SW.width),
                2.0F*SW.height/5.0F,
                0.39F*SW.width,
                CodeDisplay.height*0.39F*SW.width/CodeDisplay.width);

        //And render then the slots
        for (Slot codeSlot : codeSlots) {
            codeSlot.showSlot();
        }
    }

}

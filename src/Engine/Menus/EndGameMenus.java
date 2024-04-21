package Engine.Menus;

import Engine.EventsManager;
import Engine.GraphicElements.Button;
import Engine.GraphicElements.GameText;
import Engine.GraphicElements.RGB;
import Engine.GraphicElements.Slot;
import Engine.Workflow;
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

    private final PApplet SW;

    private final Workflow myWorkflow;

    private final EventsManager EM = new EventsManager();

    //############################## CODE VARS ##########################################
    //In case of defeat (SP) or draw (MP) these vars are used to render the secret code

    private int[] secretCode;

    private RGB[] palette;

    private final Slot[] codeSlots = new Slot[4];

    private final PImage CODE_DISPLAY;

    //############################### MULTIPLAYER VARS ######################################
    //Vars used for MP end games.

    private boolean winner = true; // True: Player 1, False: Player 2

    //############################## BUTTONS VARS ############################################
    //This sections contains the vars for the instances of all the buttons inside the end game menus

    private final Button mainMenuButton;
    private final Button exitButton;

    //############################ TEXT VARS #########################################
    //Vars to contains texts rendered by the engine in the screens of this menu

    //Single Player
    GameText winText;

    GameText loseUpperText;
    GameText loseLowerText;

    //Multiplayer

    GameText winTextPlayer1;
    GameText winTextPlayer2;

    GameText drawUpperText;
    GameText drawLowerText;

    //################################# CLASS CONSTRUCTOR #############################################
    public EndGameMenus(PApplet arg1, Workflow arg2){
        SW = arg1;
        myWorkflow = arg2;

        CODE_DISPLAY = SW.loadImage("code_display.png");

        mainMenuButton = new Button("MAIN MENU",SW);
        exitButton = new Button("EXIT GAME", SW);
        winText = new GameText(SW);
        loseUpperText = new GameText(SW);
        loseLowerText = new GameText(SW);
        winTextPlayer1 = new GameText(SW);
        winTextPlayer2 = new GameText(SW);
        drawUpperText = new GameText(SW);
        drawLowerText = new GameText(SW);

        //Any case:

        mainMenuButton.setPosition(Math.round(SW.width*0.08F),2*SW.height/3);
        mainMenuButton.setButtonColor(0,255,217);
        mainMenuButton.setTextColor(150,0,150);
        mainMenuButton.setSize(Math.round(SW.width*0.225F),SW.height/5);
        mainMenuButton.setTextSizePct(0.225F);
        mainMenuButton.setPaddingsPct(0.135F,0.33F);

        exitButton.setPosition(Math.round(SW.width*0.695F),2*SW.height/3);
        exitButton.setButtonColor(0,255,217);
        exitButton.setTextColor(150,0,150);
        exitButton.setSize(Math.round(SW.width*0.225F),SW.height/5);
        exitButton.setTextSizePct(0.225F);
        exitButton.setPaddingsPct(0.15F,0.33F);

        //Single player:

        winText.setText("YOU WIN!");
        winText.setPositions(Math.round(SW.width*0.2),SW.height/8);
        winText.setTextColor(0,255,217);
        winText.setSize(Math.round(SW.width*0.6F));
        winText.setTextSize(11.8F);

        loseUpperText.setText("GAME OVER");
        loseUpperText.setPositions(Math.round(SW.width*0.2),SW.height/24);
        loseUpperText.setTextColor(0,255,217);
        loseUpperText.setSize(Math.round(SW.width*0.6F));
        loseUpperText.setTextSize(11.8F);

        loseLowerText.setText("The secret code was:");
        loseLowerText.setPositions(Math.round(SW.width*0.3),Math.round(SW.height*0.3F));
        loseLowerText.setTextColor(0,255,217);
        loseLowerText.setSize(Math.round(SW.width*0.4F));
        loseLowerText.setTextSize(11.8F);

        //Multiplayer:

        winTextPlayer1.setText("PLAYER 1 WINS!");
        winTextPlayer1.setPositions(Math.round(SW.width*0.2),SW.height/8);
        winTextPlayer1.setTextColor(0,255,217);
        winTextPlayer1.setSize(Math.round(SW.width*0.6F));
        winTextPlayer1.setTextSize(12F);

        winTextPlayer2.setText("PLAYER 2 WINS!");
        winTextPlayer2.setPositions(Math.round(SW.width*0.2),SW.height/8);
        winTextPlayer2.setTextColor(0,255,217);
        winTextPlayer2.setSize(Math.round(SW.width*0.6F));
        winTextPlayer2.setTextSize(12F);

        drawUpperText.setText("DRAW!");
        drawUpperText.setPositions(Math.round(SW.width*0.275),SW.height/24);
        drawUpperText.setTextColor(0,255,217);
        drawUpperText.setSize(Math.round(SW.width*0.6F));
        drawUpperText.setTextSize(9F);

        drawLowerText.setText("The secret code was:");
        drawLowerText.setPositions(Math.round(SW.width*0.3),Math.round(SW.height*0.3F));
        drawLowerText.setTextColor(0,255,217);
        drawLowerText.setSize(Math.round(SW.width*0.4F));
        drawLowerText.setTextSize(11.8F);

    }

    //########################### OTHER FUNCTIONS #######################################

    private void prepareSlots(){
        for(int i = 0; i< secretCode.length; i++){
            codeSlots[i] = new Slot(SW);
            codeSlots[i].setPosition(Math.round(0.305F*SW.width+(i+0.5F)*SW.width*0.0975F),
                    Math.round(2*SW.height/5.0F+ CODE_DISPLAY.height*0.195F*SW.width/ CODE_DISPLAY.width));
            codeSlots[i].setRadius(Math.round(CODE_DISPLAY.height*0.39F*0.5F*SW.width/ CODE_DISPLAY.width),
                    Math.round(CODE_DISPLAY.height*0.39F*0.5F*SW.width/ CODE_DISPLAY.width));
            codeSlots[i].slotActivation();
            codeSlots[i].fillSlot(secretCode[i]);
            codeSlots[i].setFillingColor(palette[secretCode[i]]);
            codeSlots[i].slotDeactivation();
        }
    }

    //######################### SET FUNCTION ###################################
    public void setSecretCode(int[] s,RGB[] P){
        if(s.length==4) {
            secretCode = s;
            palette = P;
            prepareSlots();
        }
        else{
            throw new RuntimeException("EndGameMenus: setSecretCode: Secret code is too long!");
        }
    }

    public void setWinner(boolean w){
        winner = w;
    }

    //######################### COMMON SHOW ###################################
    //All of the endgame menus are some common characteristics!

    private void commonShow(){
        SW.background(0); //set the background color

        mainMenuButton.showButton();
        exitButton.showButton();

        //MainMenuButton
        if(EM.isButtonPressed(mainMenuButton,SW)){
            System.gc();
            myWorkflow.goToStep(1);
            SW.clear();
        }

        //ExitButton
        if(EM.isButtonPressed(exitButton,SW)){
            SW.exit();
        }
    }

    //######################### VICTORY MENU ##################################
    //This screen are displayed when the game concludes in a victory

    //SINGLE PLAYER
    public void showSPVictoryMenu(){
        commonShow();
        winText.showText();
    }

    //MULTIPLAYER
    public void showMPVictoryMenu(){
        commonShow();
        if(winner){
            winTextPlayer1.showText();
        }
        else{
            winTextPlayer2.showText();
        }
    }

    //############################## DEFEAT MENU ########################################

    //SINGLE-PLAYER
    public void showDefeatMenu(){
        commonShow();

        if(secretCode ==null){
            throw new RuntimeException("EndGameMenus: DefeatMenu: No secret code given to the End Menu");
        }

        loseUpperText.showText();
        loseLowerText.showText();

        SW.image(CODE_DISPLAY,Math.round(0.305F*SW.width),
                2.0F*SW.height/5.0F,
                0.39F*SW.width,
                CODE_DISPLAY.height*0.39F*SW.width/ CODE_DISPLAY.width);

        for (Slot codeSlot : codeSlots) {
            codeSlot.showSlot();
        }

    }

    //######################## MP DRAW MENU ############################################

    public void showMPDrawMenu(){
        commonShow();

        if(secretCode ==null){
            throw new RuntimeException("EndGameMenus: MPDrawMenu: No secret code given to the End Menu");
        }

        drawUpperText.showText();
        drawLowerText.showText();

        SW.image(CODE_DISPLAY,Math.round(0.305F*SW.width),
                2.0F*SW.height/5.0F,
                0.39F*SW.width,
                CODE_DISPLAY.height*0.39F*SW.width/ CODE_DISPLAY.width);

        for (Slot codeSlot : codeSlots) {
            codeSlot.showSlot();
        }
    }

}

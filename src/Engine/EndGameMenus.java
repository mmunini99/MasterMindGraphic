package Engine;

import processing.core.PApplet;
import processing.core.PImage;

public class EndGameMenus {

    //########################## FUNDAMENTAL VARS ########################################
    private final PApplet SW;
    private final WorkFlow myWorkflow;

    private final Events_Manager EM = new Events_Manager();

    private int[] secretcode;
    private RGB[] Palette;

    private Slot[] codeSlots = new Slot[4];

    //############################## BUTTONS ############################################

    Button MainMenuButton;
    Button ExitButton;

    //############################ TEXTS #########################################

    GameText WinText;

    GameText LoseUpperText;
    GameText LoseLowerText;

    //############################ IMAGES #############################################

    PImage CodeDisplay;

    public EndGameMenus(PApplet arg1,WorkFlow arg2){
        SW = arg1;
        myWorkflow = arg2;

        //######################## PIMAGE ###########################################

        CodeDisplay = SW.loadImage("Images/codedisplay.png");

        //###################### INITIALIZE BUTTONS AND TEXTS #################################

        MainMenuButton = new Button("MAIN MENU",SW);
        ExitButton = new Button("EXIT GAME", SW);
        WinText = new GameText(SW);
        LoseUpperText = new GameText(SW);
        LoseLowerText = new GameText(SW);

        //#################### MAIN MENU BUTTON ###############################

        MainMenuButton.setPosition(Math.round(SW.width*0.08F),2*SW.height/3);
        MainMenuButton.setButtonColor(0,255,217);
        MainMenuButton.setTextcolor(150,0,150);
        MainMenuButton.setSize(Math.round(SW.width*0.225F),SW.height/5);
        MainMenuButton.setTextsizePerc(0.225F);
        MainMenuButton.setPaddingsPerc(0.135F,0.33F);

        //####################### EXIT BUTTON #########################################

        ExitButton.setPosition(Math.round(SW.width*0.695F),2*SW.height/3);
        ExitButton.setButtonColor(0,255,217);
        ExitButton.setTextcolor(150,0,150);
        ExitButton.setSize(Math.round(SW.width*0.225F),SW.height/5);
        ExitButton.setTextsizePerc(0.225F);
        ExitButton.setPaddingsPerc(0.15F,0.33F);

        //####################### WIN TEXT ###############################################

        WinText.setText("YOU WIN!");
        WinText.setPositions(Math.round(SW.width*0.2),SW.height/8);
        WinText.setTextColor(0,255,217);
        WinText.setSize(Math.round(SW.width*0.6F));
        WinText.setTextSize(11.8F);

        //######################### LOSE TEXTS ##########################################

        LoseUpperText.setText("GAMEOVER");
        LoseUpperText.setPositions(Math.round(SW.width*0.2),SW.height/24);
        LoseUpperText.setTextColor(0,255,217);
        LoseUpperText.setSize(Math.round(SW.width*0.6F));
        LoseUpperText.setTextSize(11.8F);

        LoseLowerText.setText("The secret code was:");
        LoseLowerText.setPositions(Math.round(SW.width*0.3),Math.round(SW.height*0.3F));
        LoseLowerText.setTextColor(0,255,217);
        LoseLowerText.setSize(Math.round(SW.width*0.4F));
        LoseLowerText.setTextSize(11.8F);

    }

    //########################### OTHER FUNCTIONS #######################################

    private void prepareSlots(){
        for(int i=0;i<secretcode.length;i++){
            codeSlots[i] = new Slot(SW);
            codeSlots[i].setPosition(Math.round(0.305F*SW.width+(i+0.5F)*SW.width*0.0975F),
                    Math.round(2*SW.height/5.0F+CodeDisplay.height*0.195F*SW.width/CodeDisplay.width));
            codeSlots[i].setRadius(Math.round(CodeDisplay.height*0.39F*0.5F*SW.width/CodeDisplay.width),
                    Math.round(CodeDisplay.height*0.39F*0.5F*SW.width/CodeDisplay.width));
            codeSlots[i].SlotActivation();
            codeSlots[i].fillSlot(secretcode[i]);
            codeSlots[i].setActiveColor(Palette[secretcode[i]]);
            codeSlots[i].SlotDeactivation();
        }
    }

    //######################### SET FUNCTION ###################################

    public void setSecretCode(int[] s,RGB[] P){
        if(s.length==4) {
            secretcode = s;
            Palette = P;
            prepareSlots();
        }
        else{
            throw new RuntimeException("Secret code is too long!");
        }
    }

    //######################### COMMON SHOW ###################################

    private void CommonShow(){
        SW.background(0);
        MainMenuButton.showButton();
        ExitButton.showButton();
        if(EM.Button_Pressed(MainMenuButton,SW)){
            System.gc();
            myWorkflow.GoToStep(1);
            SW.clear();
        }
        if(EM.Button_Pressed(ExitButton,SW)){
            //TODO: Sequenza di uscita sarà poi da sistemare per bene quando abbiamo l'interazione database
            SW.exit();
        }
    }

    //######################### VICTORY MENU ##################################

    public void VictoryMenu(){
        CommonShow();
        WinText.showText();
    }

    //############################## DEFEAT MENU ########################################

    public void DefeatMenu(){
        CommonShow();

        //########################### SECRET CODE CHECK #######################

        if(secretcode==null){
            throw new RuntimeException("No secret code given to the End Menu");
        }

        //########################## DEFEAT TEXT #############################

        LoseUpperText.showText();

        LoseLowerText.showText();

        SW.image(CodeDisplay,Math.round(0.305F*SW.width),
                2*SW.height/5,
                0.39F*SW.width,
                CodeDisplay.height*0.39F*SW.width/CodeDisplay.width);

        for(int i=0;i<codeSlots.length;i++){
            codeSlots[i].showSlot();
        }

    }

}

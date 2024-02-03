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

    //############################ IMAGES #############################################

    PImage CodeDisplay;

    public EndGameMenus(PApplet arg1,WorkFlow arg2){
        SW = arg1;
        myWorkflow = arg2;

        //######################## PIMAGE ###########################################

        CodeDisplay = SW.loadImage("Images/codedisplay.png");

        //###################### INITIALIZE BUTTONS #####################################

        MainMenuButton = new Button("MAIN MENU",SW);
        ExitButton = new Button("EXIT GAME", SW);

        //#################### MAIN MENU BUTTON ###############################

        MainMenuButton.setPosition(200,3*SW.height/4);
        MainMenuButton.setButtonColor(0,255,217);
        MainMenuButton.setTextcolor(150,0,150);
        MainMenuButton.setSize(400,200);
        MainMenuButton.setTextsizePerc(0.25F);
        MainMenuButton.setPaddingsPerc(0.15F,0.3F);

        //####################### EXIT BUTTON #########################################

        ExitButton.setPosition(SW.width-600,3*SW.height/4);
        ExitButton.setButtonColor(0,255,217);
        ExitButton.setTextcolor(150,0,150);
        ExitButton.setSize(400,200);
        ExitButton.setTextsizePerc(0.25F);
        ExitButton.setPaddingsPerc(0.175F,0.3F);

    }

    //########################### OTHER FUNCTIONS #######################################

    private void prepareSlots(){
        for(int i=0;i<secretcode.length;i++){
            codeSlots[i] = new Slot(SW);
            codeSlots[i].setPosition(Math.round(SW.width/3.0F+(i+0.5F)*SW.width/12.0F),
                    Math.round(SW.height/2.0F+CodeDisplay.height*SW.width/(6.0F*CodeDisplay.width)));
            codeSlots[i].setRadius(Math.round(SW.width/24.0F),Math.round(SW.width/24.0F));
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

        //########################## VICTORY TEXT #############################

        SW.fill(0,255,217);
        String message = "YOU WIN!";
        SW.textSize(Math.round(0.15*SW.height));
        SW.text(message,
                Math.round(SW.width*0.3),
                Math.round(SW.height/8.0),
                Math.round(SW.width*0.75),
                Math.round(SW.height*0.375));

    }

    //############################## DEFEAT MENU ########################################

    public void DefeatMenu(){
        CommonShow();

        //########################### SECRET CODE CHECK #######################

        if(secretcode==null){
            throw new RuntimeException("No secret code given to the End Menu");
        }

        //########################## DEFEAT TEXT #############################

        SW.fill(0,255,217);
        String message = "YOU LOSE!";
        SW.textSize(Math.round(0.15*SW.height));
        SW.text(message,
                Math.round(SW.width*0.3),
                Math.round(0),
                Math.round(SW.width*0.75),
                Math.round(SW.height*0.375));

        String message2 = "The secret code was:";
        SW.textSize(Math.round(0.075*SW.height));
        SW.text(message2,
                Math.round(SW.width*0.3),
                Math.round(0.25*SW.height),
                Math.round(SW.width*0.75),
                Math.round(SW.height*0.375));

        SW.image(CodeDisplay,SW.width/3,SW.height/2,SW.width/3,CodeDisplay.height*SW.width/(3.0F*CodeDisplay.width));

        for(int i=0;i<codeSlots.length;i++){
            codeSlots[i].showSlot();
        }

    }

}

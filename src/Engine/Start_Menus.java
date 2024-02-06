package Engine;

import processing.core.*;

public class Start_Menus {

    PImage splash_art;
    PApplet SW;
    WorkFlow myWorkFlow;
    Events_Manager EM = new Events_Manager();

    Button SP_Button;
    Button MP_Button;
    Button Statistics_button;
    Button Exit_Button;
    Button Credits_Button;

    GameText TitleScreen;

    public Start_Menus(PApplet arg1, WorkFlow arg2){
        SW = arg1;
        myWorkFlow = arg2;
        splash_art = SW.loadImage("Images/splash.png");

        //GameText
        TitleScreen = new GameText(SW);
        TitleScreen.setText("Mastermind");
        TitleScreen.setTextColor(0,255,217);
        TitleScreen.setPositions(Math.round(SW.width*0.2), Math.round(SW.height/8.0));
        TitleScreen.setSize(Math.round(SW.width*0.6));
        TitleScreen.setTextSize(12F);

        // SP Button
        SP_Button = new Button("Single Player",SW);
        SP_Button.setPosition(SW.width/5,SW.height/2);
        SP_Button.setButtonColor(0,255,217);
        SP_Button.setTextcolor(150,0,150);
        SP_Button.setSize(Math.round(SW.width*0.23F),Math.round(SW.height*0.16F));
        SP_Button.setTextsizePerc(0.25F);
        SP_Button.setPaddingsPerc(0.175F,0.30F);

        //MP Button
        MP_Button = new Button("Multiplayer",SW);
        MP_Button.setPosition(Math.round(SW.width-(SW.width/5.0F+SW.width*0.23F)),SW.height/2);
        MP_Button.setButtonColor(0,255,217);
        MP_Button.setTextcolor(150,0,150);
        MP_Button.setSize(Math.round(SW.width*0.23F),Math.round(SW.height*0.16F));
        MP_Button.setTextsizePerc(0.25F);
        MP_Button.setPaddingsPerc(0.20F,0.30F);

        //EXIT Button
        Exit_Button = new Button(SW.loadImage("Images/back.png"),SW);
        Exit_Button.setPosition(Math.round(SW.width*0.04F),SW.height/16);
        Exit_Button.setButtonColor(0,0,0);
        Exit_Button.setSize(Math.round(SW.width*0.08F),SW.height/8);

        //Statistics Button
        Statistics_button = new Button("Statistics",SW);
        Statistics_button.setPosition(SW.width/5,Math.round(SW.height*0.75F));
        Statistics_button.setButtonColor(0,255,217);
        Statistics_button.setTextcolor(150,0,150);
        Statistics_button.setSize(Math.round(SW.width*0.23F),Math.round(SW.height*0.16F));
        Statistics_button.setTextsizePerc(0.25F);
        Statistics_button.setPaddingsPerc(0.25F,0.30F);

        //Credits Button
        Credits_Button = new Button("Credits",SW);
        Credits_Button.setPosition(Math.round(SW.width-(SW.width/5.0F+SW.width*0.23F)),Math.round(SW.height*0.75F));
        Credits_Button.setButtonColor(0,255,217);
        Credits_Button.setTextcolor(150,0,150);
        Credits_Button.setSize(Math.round(SW.width*0.23F),Math.round(SW.height*0.16F));
        Credits_Button.setTextsizePerc(0.25F);
        Credits_Button.setPaddingsPerc(0.30F,0.30F);
    }

    public void splashing(){ //SHOW THE SPLASH IMAGE OF THE GAME
        EM.Activate_Time_Event(2000);
        SW.image(splash_art,0,0,SW.width,SW.height);
        if(EM.Time_Event()){
            EM.reset_time_Event();
            myWorkFlow.nextStep();
            SW.clear();
        }
    }

    public void main_menu1(){
        SW.background(0);

        TitleScreen.showText();

        //Buttons
        SP_Button.showButton();
        MP_Button.showButton();
        Exit_Button.showButton();
        Statistics_button.showButton();
        Credits_Button.showButton();

        //Events
        //TODO: Multiplayer, Statistics e Credits button sono ancora inerti

        if(EM.Button_Pressed(Exit_Button,SW)){
            end_program();
        }
        if(EM.Button_Pressed(SP_Button,SW)){
            myWorkFlow.nextStep();
            SW.clear();
        }
    }

    private void end_program(){
        System.exit(0);
    }

}

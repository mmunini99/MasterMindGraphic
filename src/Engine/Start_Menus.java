package Engine;

import processing.core.*;

public class Start_Menus {

    PImage splash_art;
    PApplet SW;
    WorkFlow myWorkFlow;
    Events_Manager EM = new Events_Manager();

    Button SP_Button;
    Button MP_Button;
    Button Exit_Button;

    public Start_Menus(PApplet arg1, WorkFlow arg2){
        SW = arg1;
        myWorkFlow = arg2;
        splash_art = SW.loadImage("Images/splash.png");

        // SP Button
        SP_Button = new Button("Single Player",SW);
        SP_Button.setPosition(200,4*SW.height/10);
        SP_Button.setButtonColor(0,0,255);
        SP_Button.setTextcolor(150,0,150);
        SP_Button.setSize(400,200);
        SP_Button.setTextsizePerc(0.25F);
        SP_Button.setPaddingsPerc(0.11F,0.30F);

        //MP Button
        MP_Button = new Button("Multiplayer",SW);
        MP_Button.setPosition(SW.width-600,4*SW.height/10);
        MP_Button.setButtonColor(0,0,255);
        MP_Button.setTextcolor(150,0,150);
        MP_Button.setSize(400,200);
        MP_Button.setTextsizePerc(0.25F);
        MP_Button.setPaddingsPerc(0.14F,0.30F);

        //EXIT Button
        Exit_Button = new Button("EXIT",SW);
        Exit_Button.setPosition((SW.width/2)-200,3*SW.height/4);
        Exit_Button.setButtonColor(0,0,255);
        Exit_Button.setTextcolor(150,0,150);
        Exit_Button.setSize(400,200);
        Exit_Button.setTextsizePerc(0.25F);
        Exit_Button.setPaddingsPerc(0.35F,0.30F);
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
        SW.background(255);
        //Title
        SW.fill(39,100,0); //dark green
        String title = "Mastermind";
        SW.textSize(Math.round(0.15*SW.height));
        SW.text(title,
                Math.round(SW.width/4.0),
                Math.round(SW.height/8.0),
                Math.round(SW.width*0.75),
                Math.round(SW.height*0.375));

        //Buttons
        SP_Button.showButton();
        MP_Button.showButton();
        Exit_Button.showButton();

        //Events

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

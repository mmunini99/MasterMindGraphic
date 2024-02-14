package Engine;

import processing.core.PApplet;
import processing.core.PImage;

public class MultiStartMenus {

    //######################### FUNDAMENTALS VARIABLES ######################################
    private final PApplet SW;

    private final Events_Manager EM = new Events_Manager();

    private final WorkFlow myWorkflow;

    //############################ ELEMENTS ##############################################

    private final GameText TitleText;

    private final Button backbutton;
    private final Button startbutton;

    private Button EasyButton;
    private Button MediumButton;
    private Button HardButton;

    private Button RulesButton;

    private GameText QuestionText;

    private PImage RulesImage;

    //########################## CONSTRUCTOR #############################################

    public MultiStartMenus(PApplet arg1, WorkFlow arg2){
        SW = arg1;
        myWorkflow = arg2;

        //######################## INTRODUCTION MENU ######################################################

        TitleText = new GameText(SW);
        TitleText.setText("MULTIPLAYER RULES");
        TitleText.setTextColor(0,255,217);
        TitleText.setPositions(Math.round(SW.width*0.16F),SW.height/16);
        TitleText.setSize(Math.round(SW.width*0.68F));
        TitleText.setTextSize(12F);

        backbutton = new Button(SW.loadImage("Images/back.png"),SW);
        backbutton.setPosition(Math.round(SW.width*0.04F),SW.height/16);
        backbutton.setButtonColor(0,0,0);
        backbutton.setSize(Math.round(SW.width*0.08F),SW.height/8);

        startbutton = new Button("START GAME",SW);
        startbutton.setPosition(Math.round(SW.width/3.0F),Math.round(SW.height*0.75F));
        startbutton.setButtonColor(0,255,217);
        startbutton.setTextcolor(150,0,150);
        startbutton.setSize(Math.round(SW.width/3.0F),Math.round(SW.height*0.16F));
        startbutton.setTextsizePerc(0.35F);
        startbutton.setPaddingsPerc(0.175F,0.25F);

        RulesImage = SW.loadImage("Images/multiplayer_rules.png");

        //################################# CHOOSE DIFFICULTY MENU ########################################

        EasyButton = new Button("EASY",SW);
        MediumButton = new Button("MEDIUM",SW);
        HardButton = new Button("HARD",SW);
        RulesButton = new Button(SW.loadImage("Images/questionmark.png"),SW);
        QuestionText = new GameText(SW);

        //QuestionText

        QuestionText.setText("Select the difficulty level");
        QuestionText.setTextColor(0,255,217);
        QuestionText.setPositions(Math.round(SW.width*0.16),SW.height/16);
        QuestionText.setSize(Math.round(SW.width*0.68));
        QuestionText.setTextSize(12F);

        //EasyButton

        EasyButton.setPosition(Math.round(SW.width*0.08F),2*SW.height/3);
        EasyButton.setButtonColor(0,150,0);
        EasyButton.setTextcolor(0,0,0);
        EasyButton.setSize(Math.round(SW.width*0.225F),SW.height/5);
        EasyButton.setTextsizePerc(0.35F);
        EasyButton.setPaddingsPerc(0.26F,0.25F);

        //MediumButton

        MediumButton.setPosition(Math.round(SW.width*0.385F),2*SW.height/3);
        MediumButton.setButtonColor(255,255,0);
        MediumButton.setTextcolor(0,0,0);
        MediumButton.setSize(Math.round(SW.width*0.225F),SW.height/5);
        MediumButton.setTextsizePerc(0.35F);
        MediumButton.setPaddingsPerc(0.13F,0.25F);

        //HardButton

        HardButton.setPosition(Math.round(SW.width*0.69F),2*SW.height/3);
        HardButton.setButtonColor(255,0,0);
        HardButton.setTextcolor(0,0,0);
        HardButton.setSize(Math.round(SW.width*0.225F),SW.height/5);
        HardButton.setTextsizePerc(0.35F);
        HardButton.setPaddingsPerc(0.225F,0.25F);

        //RulesButton

        RulesButton.setPosition(Math.round(SW.width*0.88F),SW.height/16);
        RulesButton.setButtonColor(0,0,0);
        RulesButton.setSize(Math.round(SW.width*0.08F),SW.height/8);

    }

    //######################### SHOW FUNCTIONS ##############################################

    public void MultiIntroMenu(){
        SW.background(0);
        backbutton.showButton();
        startbutton.showButton();
        TitleText.showText();

        SW.image(RulesImage,Math.round(SW.width*0.04F),
                SW.height/4,
                SW.width*0.92F,
                Math.round(RulesImage.height*SW.width*0.92F*1.0F/RulesImage.width));

        if(EM.Button_Pressed(backbutton,SW)){
            myWorkflow.GoToStep(1);
            SW.clear();
        }
        if(EM.Button_Pressed(startbutton,SW)){
            myWorkflow.nextStep();
            SW.clear();
        }

    }

    public void mp_difficulty_menu(){
        SW.background(0);

        //QUESTIONTEXT

        QuestionText.showText();

        //SHOW DIFFICULTY AND RULES BUTTONS

        EasyButton.showButton();
        MediumButton.showButton();
        HardButton.showButton();
        RulesButton.showButton();
        backbutton.showButton();

        if(EM.Button_Pressed(backbutton,SW)){
            myWorkflow.previousStep();
            SW.clear();
        }
        if(EM.Button_Pressed(RulesButton,SW)){
            myWorkflow.getRuleMenus().setFromWhere(myWorkflow.getStep());
            myWorkflow.GoToStep(6);
            SW.clear();
        }
        if(EM.Button_Pressed(EasyButton,SW)){
            myWorkflow.difficulty = 1;
            myWorkflow.mp_playboard.initializePlayBoard();
            myWorkflow.nextStep();
            SW.clear();
        }
        if(EM.Button_Pressed(MediumButton,SW)){
            myWorkflow.difficulty=2;
            myWorkflow.mp_playboard.initializePlayBoard();
            myWorkflow.nextStep();
            SW.clear();
        }
        if(EM.Button_Pressed(HardButton,SW)){
            myWorkflow.difficulty=3;
            myWorkflow.mp_playboard.initializePlayBoard();
            myWorkflow.nextStep();
            SW.clear();
        }

    }

}

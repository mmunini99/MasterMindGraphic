package Engine;

import processing.core.PApplet;

public class SP_menus {

    //######################### FUNDAMENTALS VARIABLES ######################################
    private final PApplet SW;

    private final Events_Manager EM = new Events_Manager();

    private final WorkFlow myWorkflow;

    //######################## DIFFICULTY BUTTONS #########################################

    Button EasyButton;
    Button MediumButton;
    Button HardButton;

    Button RulesButton;
    Button BackButton;

    GameText QuestionText;


    public SP_menus(PApplet arg1, WorkFlow arg2){
        SW = arg1;
        myWorkflow = arg2;
        EasyButton = new Button("EASY",SW);
        MediumButton = new Button("MEDIUM",SW);
        HardButton = new Button("HARD",SW);
        RulesButton = new Button(SW.loadImage("Images/questionmark.png"),SW);
        BackButton = new Button(SW.loadImage("Images/back.png"),SW);
        QuestionText = new GameText(SW);

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

        //BackButton

        BackButton.setPosition(Math.round(SW.width*0.04F),SW.height/16);
        BackButton.setButtonColor(0,0,0);
        BackButton.setSize(Math.round(SW.width*0.08F),SW.height/8);

        //QuestionText

        QuestionText.setText("Select the difficulty level");
        QuestionText.setTextColor(0,255,217);
        QuestionText.setPositions(Math.round(SW.width*0.16),SW.height/16);
        QuestionText.setSize(Math.round(SW.width*0.68));
        QuestionText.setTextSize(12F);

    }

    public void difficulty_menu(){
        SW.background(0);

        //QUESTIONTEXT

        QuestionText.showText();

        //SHOW DIFFICULTY AND RULES BUTTONS

        EasyButton.showButton();
        MediumButton.showButton();
        HardButton.showButton();
        RulesButton.showButton();
        BackButton.showButton();

        //TODO: attiva l'interattività dei bottoni (BACK ✓, EASY ✓, MEDIUM ✓, HARD ✓, RULES ✓)

        if(EM.Button_Pressed(BackButton,SW)){
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
            myWorkflow.playboard.initializePlayBoard();
            myWorkflow.nextStep();
            SW.clear();
        }
        if(EM.Button_Pressed(MediumButton,SW)){
            myWorkflow.difficulty=2;
            myWorkflow.playboard.initializePlayBoard();
            myWorkflow.nextStep();
            SW.clear();
        }
        if(EM.Button_Pressed(HardButton,SW)){
            myWorkflow.difficulty=3;
            myWorkflow.playboard.initializePlayBoard();
            myWorkflow.nextStep();
            SW.clear();
        }

    }


}

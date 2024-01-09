package Engine;

import processing.core.PApplet;

public class SP_menus {

    //######################### FUNDAMENTALS VARIABLES ######################################
    private final PApplet SW;

    private Events_Manager EM = new Events_Manager();

    private WorkFlow myWorkflow;

    //######################## DIFFICULTY BUTTONS #########################################

    Button EasyButton;
    Button MediumButton;
    Button HardButton;

    Button RulesButton;
    Button BackButton;



    public SP_menus(PApplet arg1, WorkFlow arg2){
        SW = arg1;
        myWorkflow = arg2;
        EasyButton = new Button("EASY",SW);
        MediumButton = new Button("MEDIUM",SW);
        HardButton = new Button("HARD",SW);
        RulesButton = new Button(SW.loadImage("Images/questionmark.png"),SW);
        BackButton = new Button(SW.loadImage("Images/back.jpg"),SW);

        //EasyButton

        EasyButton.setPosition(200,3*SW.height/4);
        EasyButton.setButtonColor(0,150,0);
        EasyButton.setTextcolor(0,0,0);
        EasyButton.setSize(400,200);
        EasyButton.setTextsizePerc(0.25F);
        EasyButton.setPaddingsPerc(0.35F,0.30F);

        //MediumButton

        MediumButton.setPosition((SW.width/2)-200,3*SW.height/4);
        MediumButton.setButtonColor(255,255,0);
        MediumButton.setTextcolor(0,0,0);
        MediumButton.setSize(400,200);
        MediumButton.setTextsizePerc(0.25F);
        MediumButton.setPaddingsPerc(0.25F,0.30F);

        //HardButton

        HardButton.setPosition(SW.width-600,3*SW.height/4);
        HardButton.setButtonColor(255,0,0);
        HardButton.setTextcolor(0,0,0);
        HardButton.setSize(400,200);
        HardButton.setTextsizePerc(0.25F);
        HardButton.setPaddingsPerc(0.30F,0.30F);

        //RulesButton

        RulesButton.setPosition(SW.width-200,50);
        RulesButton.setButtonColor(0,0,0);
        RulesButton.setSize(100,100);

        //BackButton

        BackButton.setPosition(100,50);
        BackButton.setButtonColor(0,0,0);
        BackButton.setSize(100,100);

    }

    public void difficulty_menu(){
        SW.background(0);

        //DOMANDA

        SW.fill(39,100,0);
        String title = "Select the difficulty level";
        SW.textSize(Math.round(0.05*SW.height));
        SW.text(title,
                Math.round(SW.width/3.0),
                Math.round(SW.height/8.0),
                Math.round(SW.width*0.75),
                Math.round(SW.height*0.375));

        //SHOW DIFFICULTY AND RULES BUTTONS

        EasyButton.showButton();
        MediumButton.showButton();
        HardButton.showButton();
        RulesButton.showButton();
        BackButton.showButton();

        //TODO: attiva l'interattività dei bottoni (BACK ✓, EASY ✓, MEDIUM ✓, HARD ✓)

        if(EM.Button_Pressed(BackButton,SW)){
            myWorkflow.previousStep();
            SW.clear();
        }
        if(EM.Button_Pressed(EasyButton,SW)){
            myWorkflow.game.setDifficulty(1);
            myWorkflow.game.initializeGame();
            myWorkflow.nextStep();
            SW.clear();
        }
        if(EM.Button_Pressed(MediumButton,SW)){
            myWorkflow.game.setDifficulty(2);
            myWorkflow.game.initializeGame();
            myWorkflow.nextStep();
            SW.clear();
        }
        if(EM.Button_Pressed(HardButton,SW)){
            myWorkflow.game.setDifficulty(3);
            myWorkflow.game.initializeGame();
            myWorkflow.nextStep();
            SW.clear();
        }

    }


}

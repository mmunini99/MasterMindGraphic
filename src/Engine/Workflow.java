package Engine;

import Engine.Menus.*;
import Engine.Playboards.MultiPlayboard;
import Engine.Playboards.SinglePlayboard;
import processing.core.PApplet;

//############################### CLASS DESCRIPTION #############################################################
/*
This class contains the workflow of the software with all the show function to pass to the StartingClass.draw
and the maneuvers to pass from a screen/part of the game to another.
 */

public class Workflow {

    //############################ FUNDAMENTAL VARS ############################################
    //This section contains the fundamental vars necessary for the class to work properly.

    private int step=0; //Step in workflow
    private final Function[] workflow; //Set of functions

    //############################# MENU VARS #################################################
    //This sections contains the instances of all used menus.

    private final StartMenus startMenus;
    private final SinglePlayerMenus singlePlayerMenus;
    private final MultiStartMenus multiPlayerStartMenus;
    private final RuleMenus ruleMenus;
    private final SinglePlayboard singlePlayerPlayboard;
    private final MultiPlayboard multiPlayerPlayboard;
    private final EndGameMenus endGameMenus;

    //#################################### TRANSPORT VARS ########################################
    //Vars accessed in multiple class and needed to be transport from a class to others
    private int difficulty = -1; //Game difficulty (1: EASY, 2: MEDIUM, 3: HARD, -1: DEFAULT (ERROR) )
    private final String[] DIFFICULTY_STRINGS = new String[]{"EASY","MEDIUM","HARD"};

    //################################# CLASS CONSTRUCTOR #############################################
    public Workflow(PApplet SW){
        startMenus = new StartMenus(SW,this);
        singlePlayerMenus = new SinglePlayerMenus(SW,this);
        singlePlayerPlayboard = new SinglePlayboard(SW,this);
        endGameMenus = new EndGameMenus(SW,this);
        ruleMenus = new RuleMenus(SW,this);
        multiPlayerStartMenus = new MultiStartMenus(SW,this);
        multiPlayerPlayboard = new MultiPlayboard(SW,this);

        workflow = new Function[]{
                startMenus::showSplashScreen,
                startMenus::showMainMenu,
                singlePlayerMenus::showSPDifficultyMenu,
                singlePlayerPlayboard::showPlayBoard,
                endGameMenus::showSPVictoryMenu,
                endGameMenus::showDefeatMenu,
                ruleMenus::showRuleMenus,
                multiPlayerStartMenus::showMultiPlayerIntroMenu,
                multiPlayerStartMenus::showMPDifficultyMenu,
                multiPlayerPlayboard::showPlayBoard,
                endGameMenus::showMPVictoryMenu,
                endGameMenus::showMPDrawMenu,
                startMenus::showCreditsMenu
        };
    }

    //######################### GET FUNCTIONS #################################

    public EndGameMenus getEndGameMenus(){return endGameMenus;}

    public int getDifficulty(){return difficulty;}

    public String getDifficultyAsString(){
        return DIFFICULTY_STRINGS[difficulty-1];
    }

    public SinglePlayboard getSinglePlayerPlayboard() {return singlePlayerPlayboard;}

    public MultiPlayboard getMultiPlayerPlayboard(){return multiPlayerPlayboard;}

    public RuleMenus getRuleMenus(){ return ruleMenus;}

    public Function[] getWorkflow(){return workflow;}

    //############################## SET FUNCTIONS ##############################################

    public void setDifficulty(int d){
        if((d>=1)&&(d<=3)){
            difficulty = d;
        }
        else{
            throw new RuntimeException("Workflow: setDifficulty: inserted difficulty does not exist.; d="+d);
        }
    }

    // ####################### FUNCTIONS FOR STEP #####################################
    // This functions are used to change the Step of our workflow in different way or to obtain the actual step.

    public int getStep(){
        return step;
    }

    public void nextStep(){
        step++;
    }

    public void goToStep(int s){
        if(s>=0 && s<workflow.length){
            step = s;
        }
        else{
            throw new java.lang.RuntimeException("Workflow: GoToStep: pointing to a non existent step; s="+s);
        }
    }

    public void previousStep(){ step--; }

}

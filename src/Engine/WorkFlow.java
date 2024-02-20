package Engine;

import Engine.Menus.*;
import Engine.Playboards.MultiPlayBoard;
import Engine.Playboards.SinglePlayBoard;
import processing.core.PApplet;

//############################### CLASS DESCRIPTION #############################################################
/*
This class contains the workflow of the software with all the show function to pass to the StartingClass.draw
and the maneuvers to pass from a screen/part of the game to another.
 */

public class WorkFlow {

    //############################ FUNDAMENTAL VARS ############################################
    //This section contains the fundamental vars necessary for the class to work properly.

    private int step=0; //Actual step in the workflow of the software
    private final Function[] workflow; //The set of show functions that will be loaded in StartingClass.draw

    //############################# MENU VARS #################################################
    //This sections contains the instances of all used menus. The preloading of all menus fundamental characteristics
    //allows to improve the performance of the code despite an augment in the memory usage. However, for this code the
    //first issue is more relevant than the second.

    //This annotation is used to prevent that the 3 first menu give the warning. This is only a consistency choice.
    //If needed can be freely remove without problems or refactors.
    @SuppressWarnings("FieldCanBeLocal")
    private final Start_Menus start_menus; //Start menus
    @SuppressWarnings("FieldCanBeLocal")
    private final SP_menus sp_menus; //Single Player menus
    @SuppressWarnings("FieldCanBeLocal")
    private final MultiStartMenus mp_menus; //Multiplayer menus
    private final RuleMenus rulemenus; //Rules menus
    private final SinglePlayBoard playboard; //Single-player playboard
    private final MultiPlayBoard mp_playboard; //Multiplayer playboard
    private final EndGameMenus endgamemenus; //End Game menus

    //#################################### TRANSPORT VARS ########################################
    //Vars accessed in multiple class and needed to be transport from a class to others
    private int difficulty = -1; //Game difficulty (1: EASY, 2: MEDIUM, 3: HARD, -1: DEFAULT (ERROR) )

    //################################# CLASS CONSTRUCTOR #############################################
    public WorkFlow(PApplet SW){
        //Initialize all menus instances
        start_menus = new Start_Menus(SW,this);
        sp_menus = new SP_menus(SW,this);
        playboard = new SinglePlayBoard(SW,this);
        endgamemenus = new EndGameMenus(SW,this);
        rulemenus = new RuleMenus(SW,this);
        mp_menus = new MultiStartMenus(SW,this);
        mp_playboard = new MultiPlayBoard(SW,this);

        //Set up the workflow for StartingClass.draw with the show function
        workflow = new Function[]{
                start_menus::splashing,
                start_menus::main_menu1,
                sp_menus::difficulty_menu,
                playboard::showPlayBoard,
                endgamemenus::VictoryMenu,
                endgamemenus::DefeatMenu,
                rulemenus::showRuleMenus,
                mp_menus::MultiIntroMenu,
                mp_menus::mp_difficulty_menu,
                mp_playboard::showPlayBoard,
                endgamemenus::MPVictoryMenu,
                endgamemenus::MPDrawMenu,
                start_menus::Credits_menu
        };
    }

    //######################### GET FUNCTIONS #################################

    public EndGameMenus getEndgamemenus(){return endgamemenus;}

    public int getDifficulty(){return difficulty;}

    public SinglePlayBoard getPlayboard() {return playboard;}

    public MultiPlayBoard getMp_playboard(){return mp_playboard;}

    public RuleMenus getRuleMenus(){ return rulemenus;}

    public Function[] getWorkflow(){return workflow;}

    //############################## SET FUNCTIONS ##############################################

    //Set the Difficulty vars. A meaningful check is performed and eventually raise an error.
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

    //Give actual step
    public int getStep(){
        return step;
    }

    //Move to next step of the workflow
    public void nextStep(){
        step = step+1;
    }

    //Move to a certain step, controls are made
    public void GoToStep(int s){
        if(s>=0 && s<workflow.length){
            step = s;
        }
        else{
            throw new java.lang.RuntimeException("Workflow: GoToStep: pointing to a non existent step; s="+s);
        }
    }

    //Move to the previous step in the workflow
    public void previousStep(){ step = step-1; }

}

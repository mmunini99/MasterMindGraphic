package Engine;

import Engine.Playboards.MultiPlayBoard;
import Engine.Playboards.PlayBoard;
import processing.core.PApplet;

public class WorkFlow {

    private int step=0;

    private final Function[] workflow;
    private final Start_Menus start_menus;
    private final SP_menus sp_menus;

    private final MultiStartMenus mp_menus;

    private final RuleMenus rulemenus;

    //#################################### TRANSPORT VAR ####################################
    public int difficulty = -1;


    protected PlayBoard playboard;
    protected MultiPlayBoard mp_playboard;
    protected final EndGameMenus endgamemenus;

    public WorkFlow(PApplet SW){
        start_menus = new Start_Menus(SW,this);
        sp_menus = new SP_menus(SW,this);
        playboard = new PlayBoard(SW,this);
        endgamemenus = new EndGameMenus(SW,this);
        rulemenus = new RuleMenus(SW,this);
        mp_menus = new MultiStartMenus(SW,this);
        mp_playboard = new MultiPlayBoard(SW,this);

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
                endgamemenus::MPDrawMenu
        };
    }

    //######################### GET FUNCTIONS #################################

    public EndGameMenus getEndgamemenus(){
        return endgamemenus;
    }

    // ####################### FUNCTIONS FOR STEP #####################################

    public int getStep(){
        return step;
    }

    public void nextStep(){
        step = step+1;
    }

    public void GoToStep(int s){
        if(s>=0 && s<workflow.length){
            step = s;
        }
        else{
            throw new java.lang.RuntimeException("Workflow: GoToStep points to a non existent step; s="+s);
        }
    }

    public void previousStep(){ step = step-1; }

    //##################### WORKFLOW FUNCTION ###########################

    public Function[] getWorkflow(){
        return workflow;
    }

    public RuleMenus getRuleMenus(){ return rulemenus;}


}

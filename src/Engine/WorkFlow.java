package Engine;

import processing.core.PApplet;

public class WorkFlow {

    private int step=0;

    private final Function[] workflow;
    private final Start_Menus start_menus;
    private final SP_menus sp_menus;

    private final RuleMenus rulemenus;

    //#################################### TRANSPORT VAR ####################################
    public int difficulty = -1;


    protected PlayBoard playboard;
    protected final EndGameMenus endgamemenus;

    public WorkFlow(PApplet SW){
        start_menus = new Start_Menus(SW,this);
        sp_menus = new SP_menus(SW,this);
        playboard = new PlayBoard(SW,this);
        endgamemenus = new EndGameMenus(SW,this);
        rulemenus = new RuleMenus(SW,this);

        workflow = new Function[]{
                start_menus::splashing,
                start_menus::main_menu1,
                sp_menus::difficulty_menu,
                playboard::showPlayBoard,
                endgamemenus::VictoryMenu,
                endgamemenus::DefeatMenu,
                rulemenus::showRuleMenus
        };
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

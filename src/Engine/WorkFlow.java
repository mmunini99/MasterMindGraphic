package Engine;

import processing.core.PApplet;
import LetGameRun.Play;

import java.util.concurrent.ExecutionException;

public class WorkFlow {

    private int step=0;

    private final Function[] workflow;

    private final Start_Menus start_menus;
    private final SP_menus sp_menus;
    protected PlayBoard playboard;
    protected Play game;

    public WorkFlow(PApplet SW){
        game = new Play();
        start_menus = new Start_Menus(SW,this);
        sp_menus = new SP_menus(SW,this);
        playboard = new PlayBoard(SW,game,this);

        workflow = new Function[]{
                start_menus::splashing,
                start_menus::main_menu1,
                sp_menus::difficulty_menu,
                playboard::showPlayBoard
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
        if(s>=0){
            step = s;
        }
        else{
            throw new java.lang.RuntimeException("GoToStep points to a non existent step; s="+s);
        }
    }

    public void previousStep(){ step = step-1; }

    //##################### WORKFLOW FUNCTION ###########################

    public Function[] getWorkflow(){
        return workflow;
    }


}

package Engine.Playboards;

import Engine.Boards.Board;
import Engine.Boards.EasyBoard;
import Engine.Boards.HardBoard;
import Engine.Boards.MediumBoard;
import Engine.Button;
import Engine.Events_Manager;
import Engine.GameText;
import Engine.WorkFlow;
import LetGameRun.Play;
import Settings.TemplateMatrix;
import processing.core.PApplet;

public class MultiPlayBoard extends GenericPlayBoard{
    private final PApplet SW;
    private final WorkFlow myWorkflow;

    private Play actualgame1,actualgame2;
    private Board gameboard1,gameboard2;
    private TemplateMatrix mat1,mat2;
    private GameText P1text,P2text;

    private boolean actualPlayer = true; //TRUE: P1, FALSE: P2

    protected final Events_Manager EM = new Events_Manager();

    //####################### VARIABLE TO PLAY ########################################
    private int selected_color=-1;

    //########################## BUTTONS ##############################################

    protected final Button CheckButton;

    //############################ CONSTRUCTOR ######################################

    public MultiPlayBoard(PApplet arg1, WorkFlow WF){
        SW = arg1;
        myWorkflow = WF;

        //CHECK BUTTON
        CheckButton = new Button("CHECK",SW);
        CheckButton.setPosition(Math.round(SW.width*0.16F),SW.height/16);
        CheckButton.setButtonColor(0,255,217);
        CheckButton.setTextcolor(0,0,0);
        CheckButton.setSize(Math.round(SW.width*0.68F),SW.height/8);
        CheckButton.setTextsizePerc(0.6F);
        CheckButton.setPaddingsPerc(0.375F,0.15F);

        P1text = new GameText(SW);
        P1text.setText("P1");
        P1text.setTextColor(255,0,0);
        P1text.setPositions(Math.round(SW.width*0.80F),SW.height/4);
        P1text.setSize(Math.round(SW.width*0.08F));
        P1text.setTextSize(12F);

        P2text = new GameText(SW);
        P2text.setText("P2");
        P2text.setTextColor(0,0,255);
        P2text.setPositions(Math.round(SW.width*0.80F),SW.height/4);
        P2text.setSize(Math.round(SW.width*0.08F));
        P2text.setTextSize(12F);
    }

    //########################## PLAYBOARD FUNCTIONS ###############################
    @Override
    public void initializePlayBoard(){
        System.gc();
        actualgame1 = new Play();
        actualgame1.setDifficulty(myWorkflow.difficulty);
        actualgame1.initializeGame();
        actualgame2 = new Play();
        actualgame2.setDifficulty(myWorkflow.difficulty);
        actualgame2.initializeGame();
        System.out.println(actualgame1.getSecretCode()[0]+" "+actualgame1.getSecretCode()[1]+" "+actualgame1.getSecretCode()[2]+" "+actualgame1.getSecretCode()[3]+" ");
        actualgame2.setSecretCode(actualgame1.getSecretCode());

        switch (actualgame1.getInput()){
            case "EASY":
                gameboard1 = new EasyBoard(SW,myWorkflow,this);
                gameboard2 = new EasyBoard(SW,myWorkflow,this);
                break;
            case "MEDIUM":
                gameboard1 = new MediumBoard(SW,myWorkflow,this);
                gameboard2 = new MediumBoard(SW,myWorkflow,this);
                break;
            case "HARD":
                gameboard1 = new HardBoard(SW,myWorkflow,this);
                gameboard2 = new HardBoard(SW,myWorkflow,this);
                break;
            default:
                throw new RuntimeException("MultiPlayBoard: not defined input");
        }
        gameboard1.slotGroupActivation(0);
        gameboard2.slotGroupActivation(0);
        mat1 = new TemplateMatrix(actualgame1.getTrials(),
                actualgame1.getSecretCode().length,
                actualgame1.getSecretCode().length);
        mat2 = new TemplateMatrix(actualgame2.getTrials(),
                actualgame2.getSecretCode().length,
                actualgame2.getSecretCode().length);
    }
    @Override
    protected void FeedbackCheck(){
        if(actualPlayer) {
            int[] playerguess = gameboard1.getSlotsContent(actualgame1.getCount());
            actualgame1.FeedbackManager(playerguess);

            //TODO: print da rimuovere serve solo per testing
            System.out.println("PLAYER GUESS:");
            for (int i = 0; i < playerguess.length; i++) {
                System.out.println(playerguess[i]);
            }
            System.out.println("FEEDBACK");
            for (int i = 0; i < actualgame1.getFeedback().length; i++) {
                System.out.println(actualgame1.getFeedback()[i]);
            }

            gameboard1.setFeedbackSlots(actualgame1.getFeedback(), actualgame1.getCount());
            mat1.setguessandfeed(playerguess, actualgame1.getFeedback(), actualgame1.getCount());
        }
        else{
            int[] playerguess = gameboard2.getSlotsContent(actualgame2.getCount());
            actualgame2.FeedbackManager(playerguess);

            //TODO: print da rimuovere serve solo per testing
            System.out.println("PLAYER GUESS:");
            for (int i = 0; i < playerguess.length; i++) {
                System.out.println(playerguess[i]);
            }
            System.out.println("FEEDBACK");
            for (int i = 0; i < actualgame2.getFeedback().length; i++) {
                System.out.println(actualgame2.getFeedback()[i]);
            }

            gameboard2.setFeedbackSlots(actualgame2.getFeedback(), actualgame2.getCount());
            mat2.setguessandfeed(playerguess, actualgame2.getFeedback(), actualgame2.getCount());
        }
    }

    @Override
    protected boolean ValidGuess(){

        int[] playerguess;

        if(actualPlayer){
            playerguess = gameboard1.getSlotsContent(actualgame1.getCount());
        }
        else{
            playerguess = gameboard2.getSlotsContent(actualgame2.getCount());
        }

        for(int j=0; j<playerguess.length;j++){
            if(playerguess[j]==-1){
                return false;
            }
        }

        return true;
    }

    //########################### SHOW #############################################

    private void showPlayBoardP1(){
        if(actualgame1.getInitialized()){
            SW.background(0);

            gameboard1.showBoard();
            CheckButton.showButton();
            P1text.showText();

            if(EM.Button_Pressed(CheckButton,SW) && ValidGuess()){
                FeedbackCheck();
                gameboard1.slotGroupDeactivation(actualgame1.getCount());
                actualgame1.AugCount();
                //Winning
                if(actualgame1.getwinningstatus()){
                    System.out.println("P1 VINCE!!!!");
                    //TODO: ci sarà da aggiungere poi la registrazione dei punteggi e altre cose
                    myWorkflow.getEndgamemenus().setWinner(actualPlayer);
                    myWorkflow.nextStep();
                    SW.clear();
                }
                //Losing
                if(actualgame1.getCount()>actualgame1.getTrials()-1){
                    System.out.println("PAREGGIO!!!!");
                    //TODO: ci sarà da aggiungere poi la registrazione dei punteggi e altre cose
                    myWorkflow.getEndgamemenus().setSecretCode(actualgame1.getSecretCode(),gameboard1.getPalette());
                    myWorkflow.GoToStep(11);
                    SW.clear();
                }
                else {
                    gameboard1.slotGroupActivation(actualgame1.getCount());
                }
                actualPlayer = false;
            }
        }
        else{
            throw new RuntimeException("Playboard: Game not initialized");
        }
    }

    private void showPlayBoardP2() {
        if(actualgame2.getInitialized()){
            SW.background(0);
            gameboard2.showBoard();
            CheckButton.showButton();
            P2text.showText();

            if(EM.Button_Pressed(CheckButton,SW) && ValidGuess()){
                FeedbackCheck();
                gameboard2.slotGroupDeactivation(actualgame2.getCount());
                actualgame2.AugCount();
                //Winning
                if(actualgame2.getwinningstatus()){
                    System.out.println("P2 HA VINTO!!!!");
                    //TODO: ci sarà da aggiungere poi la registrazione dei punteggi e altre cose
                    myWorkflow.getEndgamemenus().setWinner(actualPlayer);
                    myWorkflow.nextStep();
                    SW.clear();
                }
                //Losing
                if(actualgame2.getCount()>actualgame2.getTrials()-1){
                    System.out.println("PAREGGIO!!!!");
                    //TODO: ci sarà da aggiungere poi la registrazione dei punteggi e altre cose
                    myWorkflow.getEndgamemenus().setSecretCode(actualgame2.getSecretCode(),gameboard2.getPalette());
                    myWorkflow.GoToStep(11);
                    SW.clear();
                }
                else {
                    gameboard2.slotGroupActivation(actualgame2.getCount());
                }
                actualPlayer = true;
            }
        }
        else{
            throw new RuntimeException("Playboard: Game not initialized");
        }
    }

    @Override
    public void showPlayBoard(){
        if(actualPlayer){
            showPlayBoardP1();
        }
        else{
            showPlayBoardP2();
        }
    }

    //############################ SET METHODS  ###########################################

    @Override
    public void setSelectedColor(int i) {
        if(i>=0) {
            selected_color = i;
        }
        else{
            throw new RuntimeException("Selected Color cannot be a negative number");
        }
    }

    //########################### GET METHODS ########################################

    @Override
    public int getSelectedColor(){
        return selected_color;
    }

}

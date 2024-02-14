package LetGameRun;
import java.util.Random;
import Settings.SetUp;
import Settings.Round;

public class Play {

    //CODICE AGGIUSTATO PER LA GRAFICA

    //################# GAME VARIABLES ############################################
    private boolean initializedGame = false;
    private String input = "EASY"; //input nel codice vecchio
    private SetUp gamesetup;
    private int lengthofseq;
    private int trials;
    private int lengthoftemplate;

    private int[] secretCode;
    private int[] feedback;

    private int count;

    private boolean winningstatus;

    //################# GET FUNCTIONS #############################################

    public int getLengthofseq(){
        return lengthofseq;
    }

    public int getTrials(){
        return trials;
    }

    public int getLengthoftemplate(){
        return lengthoftemplate;
    }

    public boolean getInitialized(){
        return initializedGame;
    }

    public String getInput(){
        return input;
    }

    public int getCount(){
        return count;
    }

    public int[] getFeedback(){
        return feedback;
    }
    public boolean getwinningstatus() { return winningstatus;}

    public int[] getSecretCode(){
        return secretCode;
    }

    //####################### COUNT FUNCTIONS #######################################

    public void AugCount(){
        count++;
    }

    //################# SETUP FUNCTIONS ##############################################

    public void setDifficulty(int d){
        switch (d){
            case 1:
                input = "EASY";
                break;
            case 2:
                input = "MEDIUM";
                break;
            case 3:
                input = "HARD";
                break;
            default:
                throw new RuntimeException("Difficulty inserted does not exist");
        }
    }

    public void initializeGame(){
        if(input!=null) {
            gamesetup = new SetUp(input);
        }
        else{
            throw new RuntimeException("Not decided difficulty");
        }
        lengthofseq = 4;
        trials = gamesetup.getNumberoftrials();
        lengthoftemplate = gamesetup.getLengthTemplate();
        secretCode = generateSecretCode(lengthofseq, lengthoftemplate);
        //TODO: da rimuovere, è solo per testing
        System.out.println("SECRET CODE:");
        for(int i=0;i<secretCode.length;i++) {
            System.out.println(secretCode[i]);
        }
        //TODO: FINO A QUI!
        initializedGame = true;
    }

    public void setSecretCode(int[] newcode){
        if(newcode.length==lengthofseq){
            for(int i=0;i<lengthofseq;i++){
                if((newcode[i]>=0)&&(newcode[i]<lengthoftemplate)){
                    secretCode[i]=newcode[i];
                }
                else{
                    throw new RuntimeException("Play: invalid number in given new secret code");
                }
            }
        }
        else{
            throw new RuntimeException("Play: given new secret code is too long");
        }
    }

    //####################### GAME FUNCTIONS ###########################################

    // Generates a random 4-digit secret code
    private static int[] generateSecretCode(int LOS, int LOT) {
        int[] code = new int[LOS];
        Random random = new Random();

        for (int i = 0; i < LOS; i++) {
            code[i] = random.nextInt(LOT);
        }

        return code;

    }

    public Play(){}

    public void FeedbackManager( int[] guessplayer) {

        this.feedback = provideFeedback(secretCode, guessplayer);

        this.winningstatus = isGameOver(feedback, lengthofseq);

    }

    private static int[] provideFeedback( int[] secretCode, int[] playerGuess) {

        int[] feedbackarray = new int[4];

        int position = 0;


        Round provideroffeedback = new Round(secretCode, playerGuess);


        int[] feedbackcompressed = provideroffeedback.getFeedback();

        for (int i = 0; i < 3; i++) {
            int categoryfeedback = feedbackcompressed[i];

            for (int j = 0; j < categoryfeedback; j++ ) {

                feedbackarray[position] = i;  //if 0 right color/position, if 1 right color only, if 2 wrong

                position += 1;


            }
        }

        return feedbackarray;

    }



    private static boolean isGameOver(int[] feedback, int lengthofseq) {
        boolean winning = false;

        int counter = 0;

        for (int i = 0; i < lengthofseq; i++){

            if (feedback[i] == 0) {
                counter += 1;
            }

        }
        if (counter == lengthofseq) {

            winning = true;

        }


        return winning;
    }





}

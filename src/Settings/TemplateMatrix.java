package Settings;

public class TemplateMatrix {

    private int lengthoftrials;

    private int lengthofguess;

    private int lengthoffeedback;

    private int[] guessofplayer;

    private int[] feedback;

    private int[][] template;

    private int round;

    private int getLengthofarray(int[] array) {
        return array.length;
    }

    private int getrowsoftemplate() {
        return template.length;
    }

    private int[][] gettemplate() {
        return template;
    }


    private void setguessandfeed(int[] guess, int[] feed, int count) {

        int[][] matrixold = template;

        int nrow = lengthofguess + lengthoffeedback;

        for (int i = 0; i < nrow; i++) {

            if (i < 4) {
                matrixold[count][i] = guess[i];
            } else {
                matrixold[count][i] = feed[i - 4];
            }
        }

        this.template = matrixold;


    }


    public TemplateMatrix(int lengthoftrials,
                          int[] guessofplayer,
                          int[] feedback) {

        this.lengthofguess = getLengthofarray(guessofplayer);

        this.lengthoffeedback = getLengthofarray(feedback);

        int nrow = lengthofguess + lengthoffeedback;

        int ncolumn = lengthoftrials;

        this.template = new int[ncolumn][nrow];


    }





// Allora, la matrice ha come colonne il numero di tentativi, mentre il numero di righe sono il guess + feedback,
// così hai tutto insieme. Ho messo a 0, tutti i valori, qui scegli te come ti va più comodo.
// Qui sono un esempio di come funziona.

//    public static void main(String[] args) {
//
//        int a = 10;  //n tentativi
//
//        int[] b = {1,2,3,4};  // esempio lunghezza risposta
//
//        int[] c = {1,2,3,4};  // esempio lunghezza feedback
//
//
//        int[] b1 = {1,2,3,4};  // esempio risposta
//
//        int[] c1 = {11,21,31,41};  // esempio feedback
//
//
//        int[] b2 = {221,222,223,224};  // esempio risposta
//
//        int[] c2 = {111,121,131,141};  // esempio feedback
//
//        TemplateMatrix prova = new TemplateMatrix(a ,b, c);
//
//        int len = prova.getrowsoftemplate();
//
//        int[][] matrix = prova.gettemplate();
//
////        System.out.println(len);
////
////        System.out.println(matrix[1].length);
//
//
//        prova.setguessandfeed(b1,c1,0);
//
//
//
//        for (int i = 0; i < len; i++ ){
//
//            for (int j = 0; j < matrix[i].length; j++ ){
//
//                System.out.printf("%4d", matrix[i][j]);
//
//            }
//
//            System.out.println();
//
//        }
//
//
//        System.out.println("seconda risposta");
//
//
//        prova.setguessandfeed(b2,c2,1);
//
//
//
//        for (int i = 0; i < len; i++ ){
//
//            for (int j = 0; j < matrix[i].length; j++ ){
//
//                System.out.printf("%4d", matrix[i][j]);
//
//            }
//
//            System.out.println();
//
//        }
//
//
//    }

}

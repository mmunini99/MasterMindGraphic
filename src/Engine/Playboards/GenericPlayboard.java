package Engine.Playboards;

//############################### CLASS DESCRIPTION #############################################################
/*
In this game there are two kinds of playboard: one for the Single-Player mode and one for the Multiplayer. These two
play boards have a bunch of public and protected methods, but they are substantially different so have to be defined
in different way. On the other hand we have the Boards. The PlayBoards are the link between the game mechanics and the
Board (strongly linked to the graphical aspect). The major problem is that the Board are independently by the game mode
but need a link to a PlayBoard to work. This abstract class is used to allow the Board to link both to the Single-Player
and Multiplayer PlayBoards but giving the possibility to differentiate the functions according to the game mode.
 */

public abstract class GenericPlayboard {

    public abstract void initializePlayBoard();

    protected abstract void feedbackComputation();

    protected abstract boolean validGuess();

    public abstract void showPlayBoard();

    public abstract void setSelectedColor(int i);

    public abstract int getSelectedColor();

    //WHY: Backend code use a different representation. BE: how much slot per type. FE: vector of slots
    static protected int[] adaptFeedback(int[] fb){
        int[] adapted = new int[4];

        int counter = 0;
        int value = 0;

        for(int i=0;i<fb.length;i++){
            for(int j=0;j<fb[i];j++){
                adapted[counter] = value;
                counter++;
            }
            value++;
        }

        return adapted;
    }

}

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

public abstract class GenericPlayBoard {

    //PlayBoard initialization
    public abstract void initializePlayBoard();

    //Compute the feedback
    protected abstract void FeedbackCheck();

    //Check if it's a valid guess
    protected abstract boolean ValidGuess();

    //Show the playboard
    public abstract void showPlayBoard();

    //Set the actual color selected by the player to fill the slots
    public abstract void setSelectedColor(int i);

    //Get the actual color selected by the player to fill the slots
    public abstract int getSelectedColor();

}

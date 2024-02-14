package Engine.Playboards;

public abstract class GenericPlayBoard {

    public abstract void initializePlayBoard();

    protected abstract void FeedbackCheck();

    protected abstract boolean ValidGuess();

    public abstract void showPlayBoard();

    public abstract void setSelectedColor(int i);

    public abstract int getSelectedColor();

}

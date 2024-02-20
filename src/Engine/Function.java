package Engine;

//############################### CLASS DESCRIPTION #############################################################
/*
This compact and most simple interface is fundamental to allow our software to work. Processing is base on a draw function
which is repeated at every frame in its content and there are no other ways to let the game visualize something. To
be able to render in the right instant different menus we have a function-passing script and a workflow to load the
correct screen. To do this all the show class methods has to be generalized in a class to allow us to collect them into
an array for the workflow. This interface work exactly as this allowing us to generalize the show methods and applying
them when needed.
 */


@FunctionalInterface
public interface Function {
    void apply();
}

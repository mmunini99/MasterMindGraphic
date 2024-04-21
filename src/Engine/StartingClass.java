package Engine;

import processing.core.*;

//############################### CLASS DESCRIPTION #############################################################
/*
StartingClass is the main class of the software. It's base on the PApplet class which is the core class of Processing.
It's composed by a fixed main that has not to be changed which starts the Graphic application. A setting function
to set up screen's features. The setup function will be performed only at the start of the application while the draw
will be repeated identically at every frame.
So the graphic engine works reloading at every frame what is inside the draw function.
NOTE: DOES NOT MODIFY ANY DESCRIPTOR INSIDE THE CLASS
 */

public class StartingClass extends PApplet {

    //############################ FUNDAMENTAL VARS ############################################
    //This section contains the fundamental vars necessary for the class to work properly.

    private Workflow mainWorkflow;
    private Function[] localWorkflow;

    //################################# CLASS CONSTRUCTOR #############################################
    //DO NOT MODIFY ANYTHING!!!!!!!

    public static void main(String[] args) {
        PApplet.main("Engine.StartingClass");
    }

    //################################# SOFTWARE GRAPHICS FUNDAMENTAL FUNCTIONS ####################################

    //Set screen's characteristics
    public void settings() {
        fullScreen();
    }

    //Set up all instances and things necessary for the software workflow
    public void setup() {
        frameRate(15);

        mainWorkflow = new Workflow(this);
        localWorkflow = mainWorkflow.getWorkflow();
    }

    //load the show function of the workflow associated to the current step
    public void draw() {
        runFunction(localWorkflow[mainWorkflow.getStep()]);
    }

    private static void runFunction(Function func) {
        func.apply();
    }


}

package Engine;

import processing.core.*;

public class StartingClass extends PApplet {

    WorkFlow main_work_flow;
    Function[] workflow;

    public static void main(String args[]) {
        PApplet.main("Engine.StartingClass");
    }

    public void settings() {
        //size(1920, 1080);
        fullScreen();
    }

    public void setup() {
        frameRate(20);
        main_work_flow = new WorkFlow(this);
        workflow = main_work_flow.getWorkflow();
    }

    public void draw() {
        runFunction(workflow[main_work_flow.getStep()]);
    }

    //QUESTA PARTE SERVE AL PASSATORE DI FUNZIONI. IL PASSATORE DELLE FUNZIONI SERVE A METTERE IN CICLO CERTI MENU
    //E COSì GESTIRE LE FASI DEL GIOCO

    private static void runFunction(Function func) {
        func.apply();
    }


}

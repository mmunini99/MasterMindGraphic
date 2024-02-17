package Engine.GraphicElements;

//############################### CLASS DESCRIPTION #############################################################
/*
This useful class is made to treat the color in the game in compact and easy way without having to save all the time
the three variable separately. The color is represented using the RGB form so with three integer component with a value
bounded between 0 and 255. This number represent the "quantity" of each color component in the color.
It's completely independent by the engine so can be used in a lot of scopes.
 */

public class RGB {

    //############################### COLOR VARS ############################################
    //The three values representing an RGB color. Default is all 0 so black.

    private int R=0; //Red
    private int G=0; //Green
    private int B=0; //Blue

    //#################################### CLASS CONSTRUCTOR #############################################
    public RGB(int r,int g,int b){
        //set the color vars with the input
        setRGB(r,g,b);
    }

    //################################## GET FUNCTIONS #############################################
    //These function allow to get the single color components

    public int getR(){return R;} //Red
    public int getG(){return G;} //Green
    public int getB(){return B;} //Blue

    //############################## SET FUNCTIONS ######################################

    //Using this function it's possible to set the three color component. Controls are made to exclude meaningless
    //values
    public void setRGB(int r,int g,int b){
        if((r>=0 && b>=0 && g>=0) && (r<=255 && g<=255 & b<=255)) {
            R = r;
            G = g;
            B = b;
        }
        else{
            throw new RuntimeException("RGB: setRGB: A given value is outside the permitted range");
        }
    }

}

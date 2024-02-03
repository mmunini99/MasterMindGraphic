package Engine;

public class RGB {

    private int R=0;
    private int G=0;
    private int B=0;

    public RGB(int r,int g,int b){
        setRGB(r,g,b);
    }

    public int getR(){ return R;
    }

    public int getG(){
        return G;
    }

    public int getB(){
        return B;
    }

    public void setRGB(int r,int g,int b){
        if((r>=0 && b>=0 && g>=0) && (r<=255 && g<=255 & b<=255)) {
            R = r;
            G = g;
            B = b;
        }
        else{
            throw new RuntimeException("The value associated to the color is outside the permitted range");
        }
    }

}

package Engine;

import processing.core.PApplet;
import processing.core.PImage;

public class Button {

    private final PApplet SW;

    //########################### COLORI #########################################
    private final RGB basecolor= new RGB(0,0,0);
    private final RGB hoo_color = new RGB(0,0,0);
    private RGB textcolor= new RGB(255,255,255);
    private RGB bordercolor = new RGB(0,0,0);

    private final float hoo_light = 0.50F;

    //####################### IMPOSTAZIONI DEI TESTI ###########################

    private int textsize = 12;

    private int x_padding = 0;
    private int y_padding = 0;

    //######################### TIPO DI BOTTONE ####################################

    private boolean textButton = false;
    private boolean imageButton = false;

    //######################## VARIABILI STORAGE TESTI E IMMAGINI #######################

    private String ButtonText;
    private PImage ButtonImage;

    //########################## VARIABILI DI POSIZIONE E DIMENSIONE DEL BOTTONE ######################

    private int x = 0;
    private int y = 0;
    private int xsize = 100;
    private int ysize = 50;

    //################### COSTRUTTORI ##########################

    public Button(PApplet arg){
        SW = arg;
    }

    public Button(String text, PApplet arg){
        textButton = true;
        ButtonText = text;
        SW = arg;
    }

    public Button(PImage image, PApplet arg){
        imageButton = true;
        ButtonImage = image;
        SW = arg;
    }

    public  Button(String text, PImage image, PApplet arg){
        textButton = true;
        ButtonText = text;
        imageButton = true;
        ButtonImage = image;
        SW = arg;
    }

    //################### FUNZIONI PER I COLORI ########################

    public void setButtonColor(int r,int g,int b){
        basecolor.setRGB(r,g,b);
        hoo_color.setRGB(Math.round(hoo_light*r),Math.round(hoo_light*g),Math.round(hoo_light*b));
    }

    public void setTextcolor(int r,int g,int b){
        textcolor.setRGB(r,g,b);
    }

    public void setBordercolor(int r,int g,int b){
        bordercolor.setRGB(r,g,b);
    }

    //################## FUNZIONI DIMENSIONE E POSIZIONE ############################

    public void setPosition(int a,int b){
        if(a>=0 && b>=0){
            x = a;
            y = b;
        }
        else{
            throw new RuntimeException("Posizione del bottone negativa");
        }
    }

    public void setSize(int a,int b){
        if(a>=0 && b>=0){
            xsize = a;
            ysize = b;
        }
        else{
            throw new RuntimeException("Dimensione del bottone negativa");
        }
    }

    //################## FUNZIONI IMPOSTAZIONI DEI TESTI ###############################################

    public void setTextsize(int s){
        if(s>=0) {
            textsize = s;
        }
        else {
            throw new RuntimeException("Dimensione del testo negativa");
        }
    }

    public void setTextsizePerc(float s){
        if(s>=0 && s<=1){
            textsize = Math.round(ysize*s);
        }
        else{
            throw new RuntimeException("Dimensione del testo percentuale insensata");
        }
    }

    public void setPaddings(int a, int b){
        x_padding = a;
        y_padding = b;
    }

    public void setPaddingsPerc(float a,float b){
        if(Math.abs(a)<=1 && Math.abs(b)<=1){
            x_padding = Math.round(a*xsize);
            y_padding = Math.round(b*ysize);
        }
        else{
            throw new RuntimeException("Percentuale del padding insensata");
        }
    }

    //################# HOOVERING AND ON THE BUTTON ##################################

    protected boolean on_the_button(){
        if(SW.mouseX>=x && SW.mouseX<=(x+xsize) && SW.mouseY>=y && SW.mouseY<=(y+ysize)){
            return true;
        }

        return false;
    }

    private void hoovering(){
        if(on_the_button()){
            SW.fill(hoo_color.getR(),hoo_color.getG(),hoo_color.getB());
        }
    }

    //################# SHOW #######################################

    public void showButton(){
        SW.fill(basecolor.getR(),basecolor.getG(),basecolor.getB());
        hoovering();
        SW.stroke(bordercolor.getR(),bordercolor.getG(),bordercolor.getB());
        SW.rect(x,y,xsize,ysize);

        if(textButton){
            SW.fill(textcolor.getR(),textcolor.getG(),textcolor.getB());
            SW.textSize(textsize);
            SW.text(ButtonText,x+x_padding,y+y_padding,x+xsize+x_padding,y+ysize+y_padding);
        }

        if(imageButton){
            SW.image(ButtonImage,x,y,xsize,ysize);
        }
    }

}

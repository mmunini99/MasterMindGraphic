package Settings;

public class Color extends Trials {

    private final String[] template; // String[] containing the series of colors that will be used

    //The color template used for game in Medium and Hard mode
    private final String[] HardTemplate = { "blue", "yellow", "green", "pink", "brown", "magenta" };

    //The color template used for game in Easy mode
    private final String[] EasyTemplate = { "blue", "yellow", "green" };

    //Class constructor. Color is an extension of Trials so Trials parameter are set and then the template info.
    public Color(String lvl) {
        super(lvl);
        this.template = setTemplate(lvl);
    }

    //Get the length of the template a.k.a. the number of colors
    public int getLengthTemplate() {
        return template.length;
    }

    //According to the difficulty, the template is set.
    private String[] setTemplate(String lvl) {
        if (lvl.equals(type_hard)) {
            return HardTemplate;
        } else if (lvl.equals(type_medium)) {
            return HardTemplate;
        } else if (lvl.equals(type_easy)) {
            return EasyTemplate;
        } else {
            throw new RuntimeException("Color: setTemplate: non existing difficulty level");
        }

    }

}

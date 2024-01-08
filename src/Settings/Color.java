package Settings;

public class Color extends Trials { // TODO: vedere se si può mettere questa classe come private

    private String lvlofdifficulty;
    private String[] template; // String[] containing the series of colors that will be used

    static private String type_hard = "HARD";
    static private String type_medium = "MEDIUM";
    static private String type_easy = "EASY";

    private final String[] hardtemplate = { "blue", "yellow", "green", "pink", "brown", "magenta" };

    private final String[] easytemplate = { "blue", "yellow", "green" };

    public Color(String lvl) {
        super(lvl);
        this.lvlofdifficulty = lvl;
        this.template = setTemplate(lvl);
    }

    public String getLvlofdifficulty() {
        return lvlofdifficulty;
    }

    public String[] getTemplate() {
        return template;
    }

    public int getLengthTemplate() {
        return template.length;
    }

    public void setLvlofdifficulty(String lvl) {
        this.lvlofdifficulty = lvl;
    }

    private String[] setTemplate(String lvl) {
        if (lvl.equals(type_hard)) {
            return hardtemplate;
        } else if (lvl.equals(type_medium)) {
            return hardtemplate;
        } else if (lvl.equals(type_easy)) {
            return easytemplate;
        } else {
            return null; // probably it won't be used, because the program will keep asking for a valid answer
        }

    }

    public String getColorFromNumber(int position) {

        return template[position];

    }

    public int getNumberFromColor(String ExtractedColor) {

        int indexFound = -1;

        for (int i = 0; i < template.length; i++) {
            if (template[i].equals(ExtractedColor)) {
                indexFound += i + 1;
                break;
            }
        }

        return indexFound; // if indexFound == -1, the selected color is not in the list
    }

    /*
     * public static void main(String[] args) {
     * // String input = System.console().readLine();
     * Color templatemode = new Color("HARD");
     * System.out.println(templatemode.getLvlofdifficulty());
     * System.out.println(templatemode.getLengthTemplate());
     * System.out.println(templatemode.getColorFromNumber(2));
     * System.out.println(templatemode.getNumberFromColor("magenta"));
     * System.out.println("-----------");
     * System.out.println(templatemode.getNumberofcolor());
     * System.out.println(templatemode.getNumberoftrials());
     * }
     */

}

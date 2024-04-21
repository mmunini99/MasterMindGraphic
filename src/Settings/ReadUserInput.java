package mastermindgame_java.Settings;

import java.util.Scanner;

public class ReadUserInput {

    String guessofplayer;

    public ReadUserInput() {

        Scanner scanner = new Scanner(System.in);

        this.guessofplayer = readtheplayerinput(scanner);

        scanner.close();


    }



    private String readtheplayerinput(Scanner scanner) {

        String tentativeguessofplayer = scanner.nextLine();

        return tentativeguessofplayer;
    }

    public String getTheUserInput() {

        return guessofplayer;

    }
}

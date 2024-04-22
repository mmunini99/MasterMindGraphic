# MasterMindGraphic: graphic version of Mastermind
### Group: [Cabriel Lorenzo](https://github.com/lcabriel) [SM3500518] | [Da Vinchie Lisa](https://github.com/LisaDaVinchie) [SM3500574] | [Marsich Gaia](https://github.com/gmarsich) [SM3500600] | [Munini Matteo](https://github.com/mmunini99) [SM3600006]

This repository contains the graphic version of Mastermind. The game is implemented using Java and the IntelliJ version of Processing 3 (see paragraph **Engine** for the engine repository).


## Usage

To play Mastermind on your device a Java distribution is required. Personally, we suggest as distribution the [Semeru 1.8.0_402](https://developer.ibm.com/languages/java/semeru-runtimes/downloads/) that is the best in rendering Processing 3 graphics, however also the native distributions and other ones are compatible.

The problematic distributions are only the OpenJDK ones which create problems with the structure of Processing 3. Using these distributions the game will not run totally. If you are able to find other incompatible or problematic distributions, please notify it to us. This software cannot work properly also on iOS so it's not usable on Mac.

Having a suitable distribution, Mastermind can be played simply running `Mastermind.jar` which is located inside the directory `~/out/artifacts/Mastermind_jar/`. To run the file from terminal you have to travel to the directory where the .jar file is located and then use the command:

```bash
java -jar Mastermind.jar
```

**NOTE**: The game presents some graphical distortions that may affect the gaming experience for screen with a width/height ratio greater than 16:9. The more the ratio is higher than this limit the more the graphical problems will be relevant.


## Rules of the table game
The codemaker creates a secret code respecting a certain degree of difficulty, defined by the length of the secret code, the number of available colors and the amount of possible trials. Remark that a secret code could be created using multiple times the same color. 

You have to give a guess using the colored pegs (in our implementation numbers replace colors). When the guess is submitted, the codemaker will provide a feedback that will be displayed on the board, near the guess.

Each peg of the guess receives a feedback, one of the following three:
- **white peg**: the color is present in the secret code and it is in the correct position
- **black peg**: the color is present in the secret code but was placed in the wrong position
- **hole**: the color is not present in the secret code
However, you are just given the amount of white pegs, black pegs and holes, not the exact correspondence between a peg of the guess and its feedback.

The goal of the game is to guess the secret code before the number of trials expires.


This Mastermind videogame is a digital porting of the traditional Mastermind game so the rules are mostly the same. To learn more about it, two links can be helpful:

- How To Play: [Mastermind : WikiHow](https://www.wikihow.com/Play-Mastermind)
- Mastermind Game: [Mastermind : Wikipedia](https://en.wikipedia.org/wiki/Mastermind_(board_game))


## Our implementation

Talking about our digital porting, running the game, after a small splashscreen, you will arrive to the Main Menu:

![App Screenshot](https://github.com/mmunini99/MasterMindGraphic/blob/main/readme_images/mainmenu.PNG)

From here you will able to

- *Play a Singleplayer Game*: play a traditional game of Mastermind trying to guess a random generated code. You can choose between three difficulties to play the game. For more information you can find an extensive description of the difficulties in-game pressing on the "?" button in the Difficulty choice screen.
- *Play a Multiplayer Game*: challenge your friends in a 2-player local mode. The code is the same for both of the players and your have to guess the code before your opponent.
- *See the credits of the game*
- *Exit from the game*

For major information such as how to interact with the board during a game and other gameplay elements, you can find an ever-consultable in-game guide clicking over the "?" button in the top right corner of the screen. 

 

## Engine

Mastermind is powered by **processing-intellij** 

GitHub: https://github.com/mkj-is/processing-intellij

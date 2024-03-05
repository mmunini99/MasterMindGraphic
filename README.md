# Mastermind: Graphic Version

This repository contains the graphic version of Mastermind. The game is implemented using Java and the IntelliJ version of Processing 3 (see Engine for the engine repository).






## Usage

To play Mastermind on your device a Java distribution it's firstly required. Personally, we suggest as distribution the [Semeru 1.8.0_402](https://developer.ibm.com/languages/java/semeru-runtimes/downloads/) however also the native distributions and other are compatible.

The only problematic distribution are the OpenJDK ones which create problems with Processing 3 structure. Using these distributions the game will not run totally.

Having a suitable distribution, Mastermind can be play simply running "Mastermind.jar" which is located inside the directory "~/out/artifacts/Mastermind_jar/". To run the file from terminal you have to travel to the directory where the .jar file is located and than using the command:

```bash
java -jar Mastermind.jar
```

**NOTE**: The game presents some graphical distortions that may affect the gaming experience for screen with a width/hegith ratio greater than 16:9. More the ratio is higher than this limit more the graphical problems will be relevant.
## Playing Mastermind

This Mastermind videogame is a digital porting of the traditional Mastermind game so the rules are mostly the same. To learn Mastermind rules, how to play and some other information about the game these two links can be helpful:

- How To Play: [Mastermind : WikiHow](https://www.wikihow.com/Play-Mastermind)
- Mastermind Game: [Mastermind : Wikipedia](https://en.wikipedia.org/wiki/Mastermind_(board_game))

Talking about our digital porting, running the game, after a small splashscreen, you will arrive to the Main Menu:

![App Screenshot](https://imgur.com/yUbcak2)

From here you will able to

- *Play a Singleplayer Game*: play a traditional game of Mastermind trying to guess a random generated code. There are three difficulties to play the game. To more information you can find an extensive description of the difficulties in-game pressing on the "?" button in the Difficulty choice screen.
- *Play a Multiplayer Game*: challenge your friends in a 2-player local mode. The code it's the same for both of the players and your have to guess the code before your opponent.
- *See the credits of the game*
- *Exit from the game*

For major information such as how to interact with the board during a game and other gameplay elements, you can find an ever-consultable in-game guide clicking over the "?" button in the top right corner of the screen.  
## Engine

Mastermind is powered by **processing-intellij** 

GitHub: https://github.com/mkj-is/processing-intellij


## Authors

- [Lorenzo Cabriel](https://github.com/lcabriel)
- [Matteo Munini](https://github.com/mmunini99)
- [Lisa Da Vinchie](https://github.com/LisaDaVinchie)
- [Gaia Marsich](https://github.com/gmarsich)

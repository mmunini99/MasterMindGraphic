# Mastermind: Graphic Version

This repository contains the graphic version of Mastermind. The game is implemented using Java and the IntelliJ version of Processing 3 (see Engine for the engine repository).

## Usage

To play Mastermind on your device a Java distribution it's firstly required. Personally, we suggest as distribution the [Semeru 1.8.0_402](https://developer.ibm.com/languages/java/semeru-runtimes/downloads/) however also the native distributions and other are compatible.

The only problematic distribution are the OpenJDK ones which create problems with Processing 3 structure. Using these distributions the game will not run totally.

Having a suitable distribution, Mastermind can be play simply running "Mastermind.jar" which is located inside the directory "~/out/artifacts/Mastermind_jar/". To run the file from terminal you have to travel to the directory where the .jar file is located and than using the command

```shell
java -jar Mastermind.jar
```

NOTE: The game presents some graphical distortions that may affect the gaming experience for screen with a width/hegith ratio greater than 16:9. More the ratio is higher than this limit more the graphical problems will be relevant.
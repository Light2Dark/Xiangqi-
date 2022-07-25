# 象棋 （Chinese Chess)

A ScalaFX desktop app for the game of Xiangqi or Chinese Chess made for OOP class.
![image](https://user-images.githubusercontent.com/19585239/180686954-d744428f-cbc4-4f07-9666-eccdcc75109c.png)
![image](https://user-images.githubusercontent.com/19585239/180687207-89e8cb2d-2b42-4b68-a61e-6e3ececc2ccc.png)

## Features

- Interactive piece handling
- Drag to move pieces
- Timer for each player with variable times
- Gameboard can be screenshotted at any point


## Deployment

To run this project, several things are needed for installation
- JDK (project version: 8)
- sbt (project version: 1.6.2
[https://www.scala-sbt.org/1.x/docs/Setup.html](Installation Links)

1. Add sbt and java to Path
2. Open command prompt 
```bash
  sbt --version #displays version of your sbt
  java -version #displays version of your java

  # if both commands above work
  # cd into the directory of this project

  sbt run #run this to compile the project and run it
```


## Contributions

Contributions are always welcome!
This game lacks game logic for all chess pieces and checkmate logic.

# Pişti

A Java terminal implementation of the Turkish card game Pişti. Choose cards from your hand, capture the table pile and play against a computer opponent. This was an SE115 course project.

## Run locally

From the repository root with a JDK installed:

```sh
mkdir -p build
javac -encoding UTF-8 -d build MyProject_Pisti_The_Game/src/*.java
java -cp build Main
```

The [game loop](MyProject_Pisti_The_Game/src/Main.java) reads choices and applies the scoring rules. The [hand](MyProject_Pisti_The_Game/src/Hand.java) and [deck](MyProject_Pisti_The_Game/src/Deck.java) classes manage card state.

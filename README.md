## Card Game - Poker

The game support 2 o more players.

### Prerequisites
- Install Java 8
- Install Maven

### Getting Started

Clone repository:
~~~~
git clone https://github.com/tonyprr/card-game.git
~~~~

Clean and build the project, run the command:
~~~~
mvn clean install
~~~~

### Run the project
The second **arg** is the output file path in your computer.
~~~~
- Execute example with 2 players
mvn exec:java -Dexec.args="'pokerdata.txt' 'F:/Antonio/demos/output1.txt'"
~~~~
~~~~
- Execute example with 3 players
mvn exec:java -Dexec.args="'pokerdata3Players.txt' 'F:/Antonio/demos/output2.txt'"
~~~~

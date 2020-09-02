## Card Game - Poker

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

~~~~
- Execute example with 2 players
mvn exec:java -Dexec.args="'pokerdata.txt' 'F:/Antonio/demos/output1.txt'"
~~~~
~~~~
- Execute example with 3 players
mvn exec:java -Dexec.args="'pokerdata3Players.txt' 'F:/Antonio/demos/output2.txt'"
~~~~
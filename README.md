# Threaded-Card-Game

## Running the Game
At the start of the game, the user will be prompted for the number of
players in the game. This input should be provided as a positive integer
which is greater than 1. Examples of valid inputs would be:
- 3
- 10
- 20

(NB: You should have a valid card pack for the number of players you
choose. Card packs must have the number of players multiplied by 8,
because each player will have a deck of four cards and a hand of four
cards in the game. For example, a pack for 4 players must include
4 * 8 = 32 cards.)

After the number of players has been inputted, the game will prompt the
user for the location of the pack of cards to load. This pack of cards
should be a .txt file, with a positive integer on each line, and each
integer separated by a new line. If the file is on the same directory as
the executable file, you only need to state the name of the file. For
example, you may input:
- two.txt
- five.txt
- 10.txt 

## Testing

### Prerequisites
All of the tests in the test suites provided use JUnit 4.12.

### Summary of Test Suites

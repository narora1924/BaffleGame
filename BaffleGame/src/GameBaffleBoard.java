
/*
 * Nikhil Arora
 * Mallard Creek High School
 * 05/22/15
 * Description: Creates a baffleboard game given a user board and user inputs
 * Difficulties:
 * What I Learned: This was by far the toughest program I have had to write. However, I enjoyed playing the game which helped me finish the program
 */

public class GameBaffleBoard {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		Baffle baf = new Baffle(); // Create baffle Board
		Player player = new Player(baf); // Player with the baffle board

		player.playGame();   // Play Game
			
	}

}
/*
 Sample Output:
 
Select the Game Level number.   1. Basic, 2. Advanced, 3. Expert 
1
*******************User  Board ******************
    10  11  12  13  14  15  16  17  18  19
9    .   .   .   .   .   .   .   .   .   .   20 
8    .   .   .   .   .   .   .   .   .   .   21 
7    .   .   .   .   .   .   .   .   .   .   22 
6    .   .   .   .   .   .   .   .   .   .   23 
5    .   .   .   .   .   .   .   .   .   .   24 
4    .   .   .   .   .   .   .   .   .   .   25 
3    .   .   .   .   .   .   .   .   .   .   26 
2    .   .   .   .   .   .   .   .   .   .   27 
1    .   .   .   .   .   .   .   .   .   .   28 
0    .   .   .   .   .   .   .   .   .   .   29 
    39  38  37  36  35  34  33  32  31  30
***********************************************
-----------------------------------------------------------------------------------------------------
Game stats: Shots=0  Guesses=0  TotalScore=0  Totalbaffles= 5 BafflesFound=0
-----------------------------------------------------------------------------------------------------
Select one of the following choices
1. Shoot Beam
2. Guess Baffle
3. Shot History
4. Refresh BaffleBoard
5. Reveal BaffleBoard
6. Quit
5
*****************Solution Board *****************
    10  11  12  13  14  15  16  17  18  19
9    .   .   .   .   .   .   .   .   .   .   20 
8    .   .   .   .   .   .   .   .   .   .   21 
7    .   .   .   .   .   /   .   .   .   .   22 
6    .   .   .   .   .   .   .   .   .   .   23 
5    .   /   /   .   .   .   .   .   /   .   24 
4    .   .   .   .   \   .   .   .   .   .   25 
3    .   .   .   .   .   .   .   .   .   .   26 
2    .   .   .   .   .   .   .   .   .   .   27 
1    .   .   .   .   .   .   .   .   .   .   28 
0    .   .   .   .   .   .   .   .   .   .   29 
    39  38  37  36  35  34  33  32  31  30
***********************************************
-----------------------------------------------------------------------------------------------------
Game stats: Shots=0  Guesses=0  TotalScore=0  Totalbaffles= 5 BafflesFound=0
-----------------------------------------------------------------------------------------------------
Select one of the following choices
1. Shoot Beam
2. Guess Baffle
3. Shot History
4. Refresh BaffleBoard
5. Reveal BaffleBoard
6. Quit
2
Enter Left Row number from 0-9 
7
Enter Left Row number from 10-19 
15
Enter 1 or 2 for BAFFLE type: 1. LEFT \ 2. Right / :
2
*** Absolutely right guess. You got 7 Points....
*******************User  Board ******************
    10  11  12  13  14  15  16  17  18  19
9    .   .   .   .   .   .   .   .   .   .   20 
8    .   .   .   .   .   .   .   .   .   .   21 
7    .   .   .   .   .   /   .   .   .   .   22 
6    .   .   .   .   .   .   .   .   .   .   23 
5    .   .   .   .   .   .   .   .   .   .   24 
4    .   .   .   .   .   .   .   .   .   .   25 
3    .   .   .   .   .   .   .   .   .   .   26 
2    .   .   .   .   .   .   .   .   .   .   27 
1    .   .   .   .   .   .   .   .   .   .   28 
0    .   .   .   .   .   .   .   .   .   .   29 
    39  38  37  36  35  34  33  32  31  30
***********************************************
-----------------------------------------------------------------------------------------------------
Game stats: Shots=0  Guesses=1  TotalScore=7  Totalbaffles= 5 BafflesFound=1
-----------------------------------------------------------------------------------------------------
Select one of the following choices
1. Shoot Beam
2. Guess Baffle
3. Shot History
4. Refresh BaffleBoard
5. Reveal BaffleBoard
6. Quit
6
*/
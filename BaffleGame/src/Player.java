

import java.util.Scanner;

public class Player {
		
	static final char BAFFLE_LEFT = '\\';
	static final char BAFFLE_RIGHT = '/';

	static final int BASIC = 1;
	static final int ADVANCED = 2;
	static final int EXPERT = 3;

	static final int MAXHISTORY = 100;  //Set to hundred
	public final int FAILURE = 0;
	public final int SUCCESS = 1;
	public final int NOCHANGE = 2;
	static final int SYSTEM_BOARD = 1;
	static final int USER_BOARD = 2;
	
	int totalScore = 0;
	int guessCount = 0;
	int totalShots = 0;
	int baffleFoundCount = 0;
	int [][]  shotHistory = new int [MAXHISTORY][2];
	Baffle baf;
	Scanner sc = new Scanner(System.in);

	public Player(Baffle baf)
	{
		this.baf = baf;

	}
	
	public void shootLaser(int hole)
	{
           int exitHole;
           
           exitHole = baf.beam(hole);
		   shotHistory[totalShots][0] = hole;
		   shotHistory[totalShots][1] = exitHole;
		   System.out.println("Start Hole = " + hole + "   Exit Hole = " +  exitHole);
		   totalShots++;   
	}
	
	public int  guessBaffle(int lefthole, int tophole, char d) 
	{
		int guessResult = baf.guess(lefthole, tophole, d);
		
		
		if (guessResult == SUCCESS)
		{
			   baffleFoundCount++;
	    	   totalScore += 7;  // Add 7 points
	    	   System.out.println("*** Absolutely right guess. You got 7 Points....");		   
		}
		else if (guessResult == FAILURE)
			{
		    	totalScore-=3;  // reduce 3 points
	 	        System.out.println("*** Incorrect guess. Lost 3 Points. Try again...");
                
			}
		else
		{
	     	   System.out.println("*** You had already guessed this before. Try other points....");
			
		}
				
		guessCount++;   // increment Guess Count
		
		return guessResult;
	}
	
	
	public void displayHistory()
	{
		    System.out.println("History of Shots you played so far :");

			for (int i = 0; i< totalShots; i++)
		        System.out.println("StartHole = " + shotHistory[i][0] + "  Exit Hole is " +  shotHistory[i][1]);

	}
	
	public void displayStats()
	{
		System.out.println("-----------------------------------------------------------------------------------------------------");
		System.out.println("Game stats: Shots="+ totalShots + "  Guesses=" + guessCount + "  TotalScore=" + totalScore + "  Totalbaffles= " + baf.getTotalBaffleCount() + " BafflesFound=" + baffleFoundCount);
		System.out.println("-----------------------------------------------------------------------------------------------------");
		
		
	}
	
	public int getScore () {
		// returns the current score
			return totalScore;
		}

		public int getShots () {
		// returns the current number of shots taken
			return totalShots;
		}

		public int getGuesses () {
		// returns the current number of guesses made
			return guessCount;
		}
	
		// This is a utility function which hecks that input is correct and withing the range.
		public int getUserInputInteger(int min, int max)
		{
		    int number;
		    boolean found;
		    int count = 0;
		    
		    found = false;
		    count=0;
		    do {
		    	if (count > 0)
		            System.out.println("Please enter a number between  " + min + " & " + max);
		        while (!sc.hasNextInt()) {
		            System.out.println("That's not a number!");
		            sc.next(); // this is important!
		        }
		        number = sc.nextInt();
		        
		        if (number >= min &&  number <= max )
		        	found = true;
		        count++;
		        
		    } while (!found);
		    
			return number;
		}

    //Play the game till the user quits th game or he finds all the baffles
	public void playGame()
	{
		boolean gameOver = false;
		int option = 0;
		int lefthole, tophole;
		char baffleChar;
		int guessResult = 0;
		
				
		//Setup Baffle Board
		
		System.out.println("Select the Game Level number.   1. Basic, 2. Advanced, 3. Expert ");
	    baf.setbafflesOnBoard(getUserInputInteger(1,3));

	    baf.printBoard(USER_BOARD);  // Display user board once
		
		while (!gameOver)
		{
			//Display the user board
			if (guessResult == 1) // User successfully guessed then show the board with new baffle		
			    baf.printBoard(USER_BOARD);
			
			guessResult = 0;
			//Display User score
			displayStats();
						
			System.out.println("Select one of the following choices");
			System.out.println("1. Shoot Beam");
			System.out.println("2. Guess Baffle");
			System.out.println("3. Shot History");
			System.out.println("4. Refresh BaffleBoard");
			System.out.println("5. Reveal BaffleBoard");
			System.out.println("6. Quit");
			option = getUserInputInteger(1,6);
			switch (option)
			{
			case 1 :  
				System.out.println("Enter the number from 0-39 to shoot laser");
				shootLaser(getUserInputInteger(0,39));
				break;
			case 2 :  
				System.out.println("Enter Left Row number from 0-9 ");
				lefthole = getUserInputInteger(0,9);
				System.out.println("Enter Left Row number from 10-19 ");
				tophole = getUserInputInteger(10,19);
				System.out.println("Enter 1 or 2 for BAFFLE type: 1. LEFT \\ 2. Right / :");
				if (getUserInputInteger(1,2) == 1)
					baffleChar = BAFFLE_LEFT;
				else
					baffleChar = BAFFLE_RIGHT;
					
				guessResult = guessBaffle(lefthole, tophole, baffleChar);
				
				if (baf.getTotalBaffleCount() == baffleFoundCount)
				{
					System.out.println("** You Found all the baffles ** Game Over");
					gameOver = true;
				}
				break;
			case 3 :
				   displayHistory();
				   break;
				   
			case 4 :
				baf.printBoard(USER_BOARD);
				break;

			case 5 :
				baf.printBoard(SYSTEM_BOARD);
				break;

			case 6 : 
				    gameOver = true;
				    break;
			}
			
		
				
						
		}
		
		sc.close();
		
	}
	
}

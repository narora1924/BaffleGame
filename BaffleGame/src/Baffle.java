import java.util.Random;

/* 
This is the class you will have to complete for Project 

*/

public class Baffle {

char[][] board = new char[10][10];    //system board - This board contains all the baffles generated randomly based on difficulty
char[][] userboard = new char[10][10];   //User Board - This board is updated with baffles as user uncovers baffles based on guesses. 

int laserBeamOrientation;
int totalBaffleCount = 0;

static final int SYSTEM_BOARD = 1;
static final int USER_BOARD = 2;

static final int BASIC = 1;
static final int ADVANCED = 2;
static final int EXPERT = 3;

static final int BASIC_BAFFLE_COUNT = 5;
static final int ADVANCED_BAFFLE_COUNT = 10;
static final int EXPERT_BAFFLE_COUNT = 15;

static final int MAX_GRID_ROW = 9;
static final int MIN_GRID_ROW = 0;
static final int MAX_GRID_COLUMN = 9;
static final int MIN_GRID_COLUMN = 0;

static final char BAFFLE_LEFT = '\\';
static final char BAFFLE_RIGHT = '/';
static final int LEFT = 1;
static final int RIGHT = 2;
static final int UP = 3;
static final int DOWN = 4;


public Baffle () {
	//Initialize both System & user Board
	for (int i=0; i<10;i++)
		for (int j=0;j<10;j++)
		{	
		    board[i][j] = '.';
            userboard[i][j] = '.';
		}
}

public void setbafflesOnBoard(int level)
{
	char d;
	int row, col;
	
	switch (level)
	{
	case BASIC    : totalBaffleCount = BASIC_BAFFLE_COUNT; break;
	case ADVANCED : totalBaffleCount = ADVANCED_BAFFLE_COUNT; break;
	case EXPERT   : totalBaffleCount = EXPERT_BAFFLE_COUNT; break;
	default : break;
	}
	
	Random rand = new Random();
	int i = 0;
	while (i < totalBaffleCount)
	{
		// Generate randomly the baffle type
		if (rand.nextInt(2) == 0)  // 0 means LEFT BAFFLE & 1means right BAFFLE
			d = BAFFLE_LEFT;
		else
			d = BAFFLE_RIGHT;
		// Generate random row & Column numbers in array to fill baffle. Ensure that we have baffels on the board defined by dificulty level.
		row = rand.nextInt(10);		
		col = rand.nextInt(10);
		if (board[row][col] == '.')
		{
		    board[row][col] = d;
		    i++;
		}
	}
}

//Given the location (the variable "hole") where a laser beam would enter the baffle grid, this determines finds which hole the laser beam
//would come out after hitting the baffels in the path.
public int beam (int hole){
	
   //Get the hoe orientation in the baffl grid. Is it left , right, top or bottom numbers in the grid
   // LEFT orientation means that beam is moving from right to LEFT
   // RIGHT orientation means that beam is moving from left to right
   // UP orientation means that beam is moving from down to up
   // DOWN orientation means that beam is moving from UP to Down
   laserBeamOrientation = getLaserGridOrientation(hole);  
   int row =  convertGridLabelToArrayRow(hole); // Convert hole to row on the board
   int col = convertGridLabelToArrayCol(hole);  // Convert hole to column on the board
   int exitHole;
   
   do
   {
		if (laserBeamOrientation == LEFT)
		{
			// Hole correspond to the row in the Array
		    //check if baffle exist on the row & column & where it will deflect the beam
		    if (board[row][col] == BAFFLE_LEFT)
		    {
		    		//Deflect beam up 
                    row--;
                    laserBeamOrientation = UP;
		    }
		    else if (board[row][col] == BAFFLE_RIGHT)
		    {
		    		//Deflect Down
		    	    row++; 	
		    	    laserBeamOrientation = DOWN;
		    }
		    else
		    	col--; //Else continue the next column    			
		  }
		else	  //Check if the orienttaion is RIGHT
		if (laserBeamOrientation == RIGHT)
		{
		    if (board[row][col] == BAFFLE_LEFT)
		    {
		    		//Deflect Down 
                    row++;
                    laserBeamOrientation = DOWN;
		    }
		    else if (board[row][col] == BAFFLE_RIGHT)
		    {
		    		//Deflect up
		    	    row--; 	
		    	    laserBeamOrientation = UP;
		    }
		    else
		    	col++; //Else continue the next column    			
		  }
		else //Check if the orienttaion is UP
		if (laserBeamOrientation == UP)
		{
		    if (board[row][col] == BAFFLE_LEFT)
		    {
		    		//Deflect LEFT 
                    col--;
                    laserBeamOrientation = LEFT;
		    }
		    else if (board[row][col] == BAFFLE_RIGHT)
		    {
		    		//Deflect RIGHT
		    	    col++; 	
		    	    laserBeamOrientation = RIGHT;
		    }
		    else
		    	row--; //Else continue the up row    			
		  }
		else	//Check if the orientation is DOWN
		if (laserBeamOrientation == DOWN)
		{
		    if (board[row][col] == BAFFLE_LEFT)
		    {
		    		//Deflect right 
                    col++;
                    laserBeamOrientation = RIGHT;
		    }
		    else if (board[row][col] == BAFFLE_RIGHT)
		    {
		    		//Deflect LEFT
		    	    col--; 	
		    	    laserBeamOrientation = LEFT;
		    }
		    else
		    	row++; //Else continue the next column    			
		  }
			   
   }while (row <= MAX_GRID_ROW && row >= MIN_GRID_ROW && col <= MAX_GRID_COLUMN && col >= MIN_GRID_COLUMN);   // continue till the beam gos outside grid which is row/col reaching below maximum number  


   exitHole = convertArrayIndexToGridLabel(laserBeamOrientation, row, col); // Return exitHole

   return(exitHole);
     
}

// Convert the row & col of grid array to corresponding grid label. 
public int convertArrayIndexToGridLabel(int orientation, int row, int col)
{
    int hole = 0;
    
    if (row < 0)
    	row = 0;
    else if (row > 9)
    	row = 9;
    	
    if (col <0)
    	col = 0;
    else if (col > 9)
    	col = 9;
    
    switch (orientation)
    {
    case RIGHT : hole = row + 20; break;
    case LEFT : hole = 9 - row; break;
    case UP : hole = col + 10; break;
    case DOWN : hole = 39 - col; break;
    default :
    	System.out.println("Incorrect orientation passed");
    }
    
	return hole;
}

//Convert grid label to corresponding row in the array 
public int convertGridLabelToArrayRow(int hole)
{
	int row = 0;
	
	if (hole >=0 & hole <=9)
		row = 9-hole;
	else if (hole >=10 & hole <=19)
		row = 0;
	else if (hole >=20 & hole <=29)
		row = hole - 20;
	else if (hole >=30 & hole <=39)
		row = 9;
	
	return row;
}

//Convert grid label to corresponding col in the array 
public int convertGridLabelToArrayCol(int hole)
{
	int col = 0;
	
	if (hole >=0 & hole <=9)
		col = 0;
	else if (hole >=10 & hole <=19)
		col = hole - 10;
	else if (hole >=20 & hole <=29)
		col = 9;
	else if (hole >=30 & hole <=39)
		col = 39 - hole;
	
	return col;
}

//Find the direction of the path of laser beam traversal based on the grid label
public int getLaserGridOrientation(int hole)
{
	int orientation = 0;
	
	if (hole >=0 & hole <=9)
		orientation = RIGHT;
	else if (hole >=10 & hole <=19)
		orientation = DOWN;
	else if (hole >=20 & hole <=29)
		orientation = LEFT;
	else if (hole >=30 & hole <=39)
		orientation =  UP;
	
	return orientation;
}


//Guess functionas checked if user has guessed the baffle correctly or not.
public int guess (int lefthole, int tophole, char d) {
	int retval;
	
	int row = convertGridLabelToArrayRow(lefthole);    //Convert Hole to actual board row
	int col =  convertGridLabelToArrayCol(tophole);     // Convert Hole to actual board column

 	
    if (board[row][col] == d)
    {
    	if (userboard[row][col] != d)
    	{
    		retval = 1;               // SUCCESS
    	    userboard[row][col] = d;  // Show the guessed baffle on the board          
    	}
    	else	
     	   retval = 2; 		// User has already gussed this baffle
    }
    else
    	retval = 0;  //Failure
	
	return retval;
}


public int getTotalBaffleCount () {
	// returns total baffles on the board
		return totalBaffleCount;
}


public void printBoard(int brd)
{
//Print the game board
//print top Labels
if (brd == SYSTEM_BOARD)
    System.out.println("*****************Solution Board *****************");
else
    System.out.println("*******************User  Board ******************");
	

System.out.printf("   %3s %3s %3s %3s %3s %3s %3s %3s %3s %3s", "10", "11" ,"12", "13", "14", "15", "16", "17", "18", "19");
System.out.println();
//print each row
for (int i=0; i<10;i++)
{
	//print Left label
	System.out.print(9-i + "  ");
	
	for (int j=0;j<10;j++)
	{
		if (brd == SYSTEM_BOARD)
	       System.out.printf("%3s ", board[i][j]);
		else
		   System.out.printf("%3s ", userboard[i][j]);
			
	}
	//print Right label
	System.out.println("  " + Math.abs(29-(9-i)) + " ");
	
}
//Print Bottom Labels
System.out.printf("   %3s %3s %3s %3s %3s %3s %3s %3s %3s %3s", "39", "38" ,"37", "36", "35", "34", "33", "32", "31", "30");
System.out.println("");
System.out.println("***********************************************");

}

}
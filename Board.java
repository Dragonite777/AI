import java.util.Scanner;


public class Board 
{
	int[][] board;
	static final int x = 5;
	static final int y = 5;
	
	static final int blank = 0;
	static final int player = 1;
	static final int food = 2;
	
	static boolean win;
	static boolean dead;
	
	int hunger;
	AIState state;
	
	int playerX;
	int playerY;
	
	int foodX;
	int foodY;
	
	int[][] currMoves;
	Scanner in;
	
	public Board()
	{
		board = new int[x+2][y+2];
		board[3][3] = player;
		playerX = 2;
		playerY = 2;
		board[1][3] = food;
		foodX = 0;
		foodY = 2;
		
		update();
		
		win = false;
		dead = false;
		this.hunger = 0;
		in = new Scanner(System.in);
		state = AIState.RUNNING;
	}
	
	public void play()
	{
		
		while(!in.nextLine().equalsIgnoreCase("q") || state != AIState.WIN)
		{
			display();
			makeMove();
			hunger++;
			display();
		}
	}
	
	private void makeMove()
	{
		playerX = currMoves[minDist(getMoves())][0];
		playerY = currMoves[minDist(getMoves())][1];
	}
	
	private int[][] getMoves()
	{
		int r = 0;
		int[][] ret = new int[4][2];
		int[][] places = new int[][] 
				{{playerX + 1, playerY},{playerX - 1, playerY},{playerX, playerY + 1},{playerX, playerY + 1}};
		for (int x = 0; x < places.length; x++)
		{
			if (places[x][0] < 1 || places[x][1] < 1 || places[x][0] > 5 || places[x][1] > 5)
			{
				ret[r][0] = places[x][0];
				ret[r][1] = places[x][1];
				r++;
			}
		}
		return ret;
	}
	
	private double dist(int newX, int newY, int locX, int locY)
	{
		return Math.sqrt((newX-locX)*(newX-locX) + (newY-locY)*(newY-locY));
	}
	
	private int minDist(int[][] tries)
	{
		int min = 100;
		currMoves = getMoves();
		for (int x = 0; x < currMoves.length; x++)
		{
			if (min >= dist(currMoves[x][0], currMoves[x][1], foodX, foodY) 
					&& inBounds(currMoves[x][0], currMoves[x][1]))
			{
				min = x;
			}
		}
		return min;
	}
	
	private boolean inBounds(int x, int y)
	{
		return x > 0 && x < 6 && y > 0 && y < 6;
	}
	
	private void update()
	{
		board[playerX][playerY] = player;
		board[foodX][foodY] = food;
		
		for (int x = 0; x < board.length; x++)
			for (int y = 0; y < board[x].length; y++)
				if (x != playerX && x != foodX && y != playerX && y != foodY)
					board[x][y] = blank;
	}
	
	public void setPlayer(int x, int y)
	{
		playerX = x;
		playerY = y;
		update();
	}
	
	public void setFood(int x, int y)
	{
		foodX = x;
		foodY = y;
		update();
	}

	public void display()
	{
		for(int x = 0; x < board.length; x++)
		{
			for (int y = 0; y < board[x].length; y++)
			{
				System.out.print(board[x][y] + " ");
			}
			System.out.println();
		}
	}
}

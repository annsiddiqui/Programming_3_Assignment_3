import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;

/**
 * 
 */

/**
 * This is the class for the Maze Map. 
 * 
 * @author Qurrat-al-Ain Siddiqui
 * @date Nov 4th 2018 
 * @instructor Laura Marik
 * @class	COMP 2503-001
 * 
 * Assignment 3 Maze Map
 *
 */
public class MazeMap {

	//All the class variables - have to be private. 
	private static final char WALL = 'W';
	private static final char START = 'S';
	private static final char CURR_PATH = ' ';
	private static final char REJECTED = 'X';
	private static final char END_FOUND = 'S';
	private MazeCell[][] MazeMap;
	private int cols;
	private int rows; 
	private int steps = 0; 
	Scanner in = new Scanner(System.in);
	private MazeCell start;
	CellStack <MazeCell> mazeStack = new CellStack<MazeCell>();


	public MazeMap()
	{
		boolean valid = false;
		do
		{
			try
			{
				//File name IO
				System.out.println("Please enter the file name: ");
				String fileName = in.nextLine() + ".txt";
				File mazeFile = new File(fileName);
				Scanner in = new Scanner(mazeFile);

				loadMaze(in);//calls the method to load
				valid = true;
			}
			catch (FileNotFoundException e)
			{
				System.out.println("The file you've entered does not exist. Please try again.");
			}
			catch (IOException x)
			{
				System.out.println("The file cotains invald input.");
			}
		}
		while (valid == false);	
	}

	/**
	 * 	
	 * The method that handles loading in the maze from txt files 
	 * 
	 * @param Scanner in 	to read input
	 */
	public void loadMaze(Scanner in)
	{
		mazeSize(in.nextLine());//input from the file 
		MazeMap = new MazeCell[rows + 2][cols + 2];
		try
		{
			for (int row = 1; row < rows + 1; row++)
			{
				StringTokenizer st = new StringTokenizer(in.nextLine());//using the Tokenizer class (in Assignment guidelines)

				//Loop to add the char's to the maze via tokenizer
				for (int column = 1; column < cols + 1; column++)
				{
					String token = st.nextToken();
					MazeCell add = new MazeCell(token.charAt(0));
					MazeMap[row][column] = add;
					if (token.charAt(0) == 'M')
					{
						start = add;
					}
				}
			}
		}

		catch (Exception x)
		{
			x.getMessage();
		}
	} 



	/**
	 * Method to set rows and cols from first line of file
	 * 
	 * @param sizes	String
	 */

	public void mazeSize(String sizes)
	{
		//Declaring tokenizer variable
		StringTokenizer st = new StringTokenizer(sizes);
		try
		{
			cols = Integer.parseInt(st.nextToken()); //Turns cols into int 
			rows = Integer.parseInt(st.nextToken()); //Turns rows into int 
		}
		catch (NullPointerException i)//Error handle just in case it points to null
		{
			System.out.println("Missing row and/or column specfications.");
		}
		catch (Exception x)
		{
			System.out.println("An error has occured with the maze size speficications.");
		}
	}

	/**
	 * Method to draw the MazeMap Array
	 */
	public void drawMaze()
	{
		for (int row = 1; row < rows + 1; row++)
		{
			if (row % 2 == 0) //To indent the even rows of maze
			{
				System.out.println(" ");
			}
			for (int column = 1; column < rows + 1; column++)
			{
				if (column == cols) //Last column won't get space after
				{
					System.out.print(MazeMap[row][column].toString());
				}
				else
				{ 
					System.out.print(MazeMap[row][column].toString() + " ");
				}
			}

			System.out.println();
		}

	}

	/**
	 * Method for the cell neigbbors in the Maze
	 * 
	 * This method is courtesy of Leris Aradnia 
	 */
	public void cellNeigbours()
	{
		for (int row = 1; row < rows + 1; row++)
		{
			for (int column = 1; column < cols + 1; column++)
			{
				MazeCell curr = MazeMap[row][column];

				if (row % 2 == 0) //Even rows NEEDED offsett
				{
					curr.setNeighbour(MazeMap[row - 1][column + 1], 0);
					curr.setNeighbour(MazeMap[row][column + 1], 1);
					curr.setNeighbour(MazeMap[row + 1][column + 1], 2);
					curr.setNeighbour(MazeMap[row + 1][column], 3);
					curr.setNeighbour(MazeMap[row][column - 1], 4);
					curr.setNeighbour(MazeMap[row - 1][column], 5);
				}
				else // ODD Rows
				{
					curr.setNeighbour(MazeMap[row - 1][column], 0);
					curr.setNeighbour(MazeMap[row][column + 1], 1);
					curr.setNeighbour(MazeMap[row + 1][column], 2);
					curr.setNeighbour(MazeMap[row + 1][column - 1], 3);
					curr.setNeighbour(MazeMap[row][column - 1], 4);
					curr.setNeighbour(MazeMap[row - 1][column - 1], 5);
				}

			}
		}
	}

	/**
	 * Method to print the neighbors in Maze
	 */
	public void printNeigbours()
	{
		MazeCell curr = MazeMap[3][3];

		System.out.println(" " + curr.getNeighbour(5) + " " + curr.getNeighbour(0));
		System.out.println(curr.getNeighbour(4) + " " + curr + " " + curr.getNeighbour(1));
		System.out.println(" " + curr.getNeighbour(3) + " " + curr.getNeighbour(2));
		System.out.println("line");
	}
	//Pushing stack commands 
	public void startMaze()
	{
		mazeStack.push(start);
	}
	//Figuring out if the cheese is found or not 
	public boolean goMaze()
	{
		MazeCell currentCell =  new MazeCell();
		boolean cheeseFound = false;

		try {
			if (mazeStack != null)
			{
				//Using stack commands
				currentCell = mazeStack.peek();
				if (currentCell.getEnd() == true)
				{
					currentCell.setType(END_FOUND);
					cheeseFound = true;
				}
				else 
				{
					findNeighbour(currentCell);
					steps++;//increment the steps to findNeighbor.
				}
				drawMaze();
			}
		}
		catch (Exception e)
		{
			noSolFound();
		}
		return cheeseFound;
	}

	//This method is courtesy of Leris Arandia ***
	public void findNeighbour(MazeCell currCell)
	{
		MazeCell neighbourCell =  new MazeCell();
		int index = 0;
		boolean validNeighbour = false;

		while (index <= 5)
		{
			neighbourCell = currCell.getNeighbour(index);
			//This if statement is courtesy of Leris Arandia ***
			if (neighbourCell.getType() != WALL && neighbourCell.getType() != START &&
					neighbourCell.getType() != CURR_PATH && neighbourCell.getType() != REJECTED && 
					neighbourCell.getType() != '#')


			{
				mazeStack.push(neighbourCell);
				neighbourCell.setType(CURR_PATH);

				validNeighbour = true;
				index = 7;
			}
			else
			{
				index++;
			}
		}

		if (validNeighbour == false)
		{
			currCell.setType(REJECTED);
			mazeStack.pop();
		}
	}
//Method to let know how many steps it took for mazes to be solved
	public void totalSteps()
	{
		System.out.println("Maze has been solved in " + steps + " steps.");
	}

	public void noSolFound()
	{
		System.out.println("No solution found.");
		System.exit(0);
	}
}


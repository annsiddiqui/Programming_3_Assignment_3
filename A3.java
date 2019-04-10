import java.util.Scanner;

/**
 * 
 */

/**
 * This is the user interface class where the main program runs. 
 * 
 * @author Qurrat-al-Ain Siddiqui
 * @date Nov 4th 2018 
 * @instructor Laura Marik
 * @class	COMP 2503-001
 * 
 * Assignment 3 Main
 *
 */
public class A3 {

	//Declaring the MazeMap for the implementation of MazeMap class
	public static MazeMap m1 = new MazeMap();
	//Declaing Scanner to get user input 
	public static Scanner in = new Scanner(System.in);

	//Calls methods to run overall program 
	public static void main(String[] args) {
		loadArray();
		solveMaze();
		in.close();
	}

	public static void loadArray() {
	}
	{
		m1.drawMaze();
		m1.cellNeigbours();
	}

	public static void solveMaze() {
		m1.startMaze();
		boolean done = m1.goMaze();
		while (done == false)
		{
			System.out.println("PRESS ENTER TO CONTINUE.");
			in.nextLine();
			done = m1.goMaze();
		}
		m1.totalSteps();
	}

}

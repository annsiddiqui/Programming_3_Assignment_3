
/**
 * Maze cell class that uses CellStack class.
 * 
 * @author Qurrat-al-Ain Siddiqui
 * @date Nov 4th 2018 
 * @instructor Laura Marik
 * @class	COMP 2503-001
 * 
 * Assignment 3 MazeCell Class
 *
 */
public class MazeCell {
	private char type;
	private boolean end;
	private MazeCell[] neighbours = new MazeCell[6];

	public MazeCell(char t)
	{
		type = t;
		end = false;

		if (type == 'C')
		{
			end = true;
		}
	}

	public MazeCell()
	{
		type = 0; 
		end = false;
	}

	//This is all accessors
	public char getType()
	{
		return type;
	}

	public boolean getEnd()
	{
		return end;
	}
	
	//This is all mutators

	public void setEnd(boolean b)
	{
		end = b;
	}
	
	//toString method

	public String toString()
	{
		String line = Character.toString(getType());
		return line;
	}

	//Mutator method
	public void setNeighbour(MazeCell m, int index)
	{
		neighbours[index] = m;
	}
	
	//Accssor method

	public MazeCell getNeighbour(int index)
	{
		MazeCell x = new MazeCell();
		x = neighbours[index];

		if (x == null)
		{
			x = new MazeCell('#');
		}

		return x;

	}

	//Using char, a mutator method
	public void setType(char endFound) {
		type = endFound;
		
	}

}



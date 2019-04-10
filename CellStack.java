import java.util.ArrayList;

/**
 * CellStack represents generic stack class specifically for MazeCell.
 * 
 * @author Qurrat-al-Ain Siddiqui
 * @date Nov 4th 2018 
 * @instructor Laura Marik
 * @class	COMP 2503-001
 * 
 * Assignment 3 Cell Stakc Class - Used from lab 6 in class. Changed deque to ArrayList
 *
 */
public class CellStack <T> {
	
	private ArrayList<T> stack = new ArrayList<T>();
	private int stackSize = 0;

		
	public T peek() {
		
		return stack.get(stackSize - 1);
	}
	

	public T pop() {
		
		stackSize--;
		return stack.remove(stackSize);
	}
	

	public void push (T item) { 
	stack.add (stackSize++, item);
	}
	

	public boolean isEmpty() {
		
		return stack.isEmpty();
	}
	

	public int size() {
		
		return stackSize;
	}
}

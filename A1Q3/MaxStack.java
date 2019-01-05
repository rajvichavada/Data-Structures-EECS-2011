package A1Q3;

import java.util.*;

/**
 * Specializes the stack data structure for comparable elements, and provides a
 * method for determining the maximum element on the stack in O(1) time.
 * 
 * @author jameselder
 */
public class MaxStack<E extends Comparable<E>> extends Stack<E> {

	private Stack<E> maxStack;
	private Stack<E> tempStack;
	

	public MaxStack() {
		maxStack = new Stack<>();
		tempStack = new Stack<>();
	
	}

	/* must run in O(1) time */
	public E push(E element) {

		if (maxStack.size() == 0 || element.compareTo(max()) >= 0) {
			maxStack.push(element);
		}
		tempStack.push(element);
		return element;
	}

	/* @exception EmptyStackException if this stack is empty. */
	/* must run in O(1) time */
	public synchronized E pop() {

		if (tempStack.isEmpty() || maxStack.isEmpty()) {
			throw new EmptyStackException();
		}
		E result = maxStack.pop();
		if (result == max())
		{
			this.maxStack.pop();
		}
		return result;

	}

	/* Returns the maximum value currenctly on the stack. */
	/* @exception EmptyStackException if this stack is empty. */
	/* must run in O(1) time */
	public synchronized E max() {

		if (maxStack.isEmpty()) {
			throw new EmptyStackException();
		}
		return maxStack.peek();

	}
}
/*
 * Rajvi Chavada
 * 214239800
 * CSE: rajvic
 * Date: Nov 19th 2018
 * Assignment #2, Q2
 */
package assignmentTwo;

public class NodeDeque<E>
{
	

	public static class Node<E>
	{
		private E element;
		private Node<E> prev;
		private Node<E> next;
		
		public Node(E e, Node<E> p, Node<E> n)	//set constructor
		{
			element = e;
			prev = p;
			next = n;
		}
		
		public E getElement()
		{
			return element;
		}
		
		public Node<E> getPrev()
		{
			return prev;
		}
		
		public Node<E> getNext()
		{
			return next;
		}
		
		public void setPrev(Node<E> p)
		{
			prev = p;
		}
		
		public void setNext(Node<E> n)
		{
			next = n;
		}
	}//end
	
	
}

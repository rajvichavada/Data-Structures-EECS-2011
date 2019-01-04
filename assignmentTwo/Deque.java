/*
 * Rajvi Chavada
 * 214239800
 * CSE: rajvic
 * Date: Nov 19th 2018
 * Assignment #2, Q2
 */


package assignmentTwo;
//import java.util.*;

//extend my node class
public class Deque<E> extends NodeDeque<E>
{
	private Node<E> header, trailer; //declare a header and trailer dummy nodes
	private int size = 0;	//use a global size variable
	
	public Deque()
	{
		header = new Node<>(null,null,null);	//header is set to null, with nothing beside it and nothing ahead of it
		trailer = new Node<>(null, header, null); //trailer is set to null, with header before it and nothing after it
		header.setNext(trailer);	//set the header pointer to point to connect header and trailer 
	}

	//is the deque empty
	public boolean isEmpty()
	{
		return size == 0; //if size == 0 return true
		
	}
	
	//returns the # of items of the deque
	public int size()
	{
		return size;
	}
	
	//Method to add e between the given predecessor and successor
	private void addBetween(E e, Node<E> predecessor, Node<E> successor)
	{
		Node<E> newest = new Node<>(e, predecessor, successor);	//define a new node
		predecessor.setNext(newest);	//set new node's prev link to the predecessor 
		successor.setPrev(newest);		//set the node's next node to the sucessor 
		size++;	//increase the size
		
	}
	//insert item in front of the deque
	public void addFirst(E item)
	{
		addBetween(item, header, header.getNext());	//adds between the header node and the head node
		
	}
	
	//insert at the end of the deque
	public void addLast(E item)
	{
		addBetween(item, trailer.getPrev(), trailer);	//adds between the last node and the trailer node
	}
	
	private E remove(Node<E>node)
	{
		Node<E> predecessor = node.getPrev(); //gets the node before the to-be removed node
		Node<E> successor = node.getNext();		//gets the node after the to-be removed node
		predecessor.setNext(successor); //Set the pointers of the nodes to each other by disconnecting the to-be removed node
		successor.setPrev(predecessor);
		size--;	//decrease the size of the deque
		return node.getElement();	//return the removed node 
	}
	public E removeFirst()
	{
		return remove(header.getNext());	//removes the head
	}
	
	public E removeLast()
	{
		return remove(trailer.getPrev());	//removes the tail
	}
	
	public E first()
	{
		return header.getNext().getElement();	//gets the head
	}
	
	public E last()
	{
		return trailer.getPrev().getElement();	//returns the tail
	}
	
	public void printOutContent()
	{
		Node<E> iterator = header.getNext(); //equal to the head
		while(iterator.getNext() != null)
		{
			System.out.println(iterator.getElement());
			iterator = iterator.getNext();	//iterate through list 
		}
	}
	

}

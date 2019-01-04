/*
 * Rajvi Chavada
 * 214239800
 * CSE: rajvic
 * Date: Nov 19th 2018
 * Assignment #2, Q1
 */
package assignmentTwo;

public class ArrayWithMemory<E> implements ReadWriteCount<E> 
{
	private Object [] array;	//create a generic array of type object
	private int read = 0;
	private int write = 0;		//initialize variables
	public final int length;

	public ArrayWithMemory(int capacity)
	{
		array = new Object[capacity];	//initialize array with inputed capacity
		this.length = capacity;	//length of array is capacity
	}
	
	public void write(int i, E e)
	{
		array[i] = e;	//at index i, set the element to e
		write++;		//increment global write variable
		
	}
	
	public E read (int i)
	{
		E e2 = (E)array[i];	//read the element at i
		final E e = e2;
		read++;	//increment the read
		return e;
	}
	
	//getter methods for the global variables
	public int numberOfWrites()
	{
		return write;
	}
	
	public int numberOfReads()
	{
		return read;
	}
	
	//reset global variabes
	public void resetMemory()
	{
		read = 0;
		write = 0;
	}
	
	//print out contents of the array
	public void printOutContent()
	{
		for(int i = 0; i < array.length; i++)
		{
			System.out.println(array[i]);
		}
	}
}

package assignmentThree;

import java.io.*;
import java.util.*;

class MaxHeap  {
   private Node[] heapArray;
   
   private int maxSize;           // size of array
   private int currentSize;       // number of nodes in array
   
   
// constructor -------------------------------------------------------------
   public MaxHeap(int mx) {
      maxSize = mx;
      currentSize = 0;
      heapArray = new Node[maxSize];  // create array
      }
   
   
// check if heap is empty --------------------------------------------------
   public boolean isEmpty()  {
      return currentSize==0; }
   
   
// insert a new element into heap ------------------------------------------
   public boolean insert(int key)  {
      if(currentSize==maxSize)
         return false;
      Node newNode = new Node(key);
      heapArray[currentSize] = newNode;
      heapUp(currentSize);
      currentSize++;
      return true;
      }
   
   
// perform bubble-up procedure ----------------------------------------------
// @param index is the 'array location' of the heap-node which should be
// bubbled-up if heap property is not satisifed
   public void heapUp(int index)  
   {
	 
	   //first we need to find the index of the parent of the indexed value
	   int parentIndex;
	   
	   if(index % 2 == 0)
	   {
		   parentIndex = (index / 2) - 1;
	   }
	   else
	   {
		   parentIndex = index / 2;
	   }
	   
	  Node child = heapArray[index];
	  Node parent = heapArray[parentIndex];
	  
	  //compare the value of the child and its parent node 
	  if (index > 0 && child.getKey() < parent.getKey())
	  {
		  //since the child is smaller than parent we need to swap the two
		  	Node temp = heapArray[index];
	        heapArray[index] = heapArray[parentIndex];
	        heapArray[parentIndex] = temp;
	        index = parentIndex;
	        heapUp(index);
	  }
	
	   
   } 
   
   
// delete item with max key (assumes non-empty heap) -------------------------
   public Node remove()  {        
      Node root = heapArray[0];
      heapArray[0] = heapArray[--currentSize];
      heapDown(0);
      return root;
      }
   //remove method with the copy version of the array so we don't mutate the original array
   public Node removeCopy()  {     
	   Node[] copyHeapArray = heapArray;
	      Node root = copyHeapArray[0];
	      copyHeapArray[0] = copyHeapArray[--currentSize];
	      heapDown(0);
	      return root;
	      }
   
// perform bubble-down procedure ---------------------------------------------
// @param index is the 'array location' of the heap-node which should be
// bubbled-down if heap property is not satisifed
   public void heapDown(int index)  
   {
	   while(true)
	   {
	   
	   //Find the left and right child of n with index index if it exists
	   int leftChildIndex = (2 * index);
	   int rightChildIndex = (2 * index) + 1;
	   Node n = heapArray[index];
	   Node l = heapArray[leftChildIndex];
	   Node r = heapArray[rightChildIndex];
	   
	   
	   //if the indexes of the left n right child are equal to the max size then they are a leaf
	   if (leftChildIndex < maxSize && rightChildIndex < maxSize)
	   {
		   int greaterChildIndex;
		  
		   if(l.getKey() > r.getKey())
		   {
			   greaterChildIndex = leftChildIndex;
			  
		   }
		   else
		   {
			   greaterChildIndex = rightChildIndex;
		   }
		   Node child = heapArray[greaterChildIndex];
		   
		   if(n.getKey() > child.getKey())
		   {
			   //if the parent node is bigger then we must switch it with the child
			 	Node temp = heapArray[index];
		        heapArray[index] = heapArray[greaterChildIndex];
		        heapArray[greaterChildIndex] = temp;
		        index = greaterChildIndex;
		   }
		   else
		   {
			   break;
		   }
		
	   }
	   }   
   }
   
   
// display heap --------------------------------------------------------------
   public void displayHeap()
      {
      System.out.print("heapArray: ");    // array format
      for(int m=0; m<currentSize; m++)
         if(heapArray[m] != null)
            System.out.print( heapArray[m].getKey() + " ");
         else
            System.out.print( "-- ");
      System.out.println();
                                          // heap format
      int nBlanks = 32;
      int itemsPerRow = 1;
      int column = 0;
      int j = 0;                          // current item
      String dots = "...............................";
      System.out.println(dots+dots);      // dotted top line

      while(currentSize > 0)              // for each heap item
         {
         if(column == 0)                  // first item in row?
            for(int k=0; k<nBlanks; k++)  // preceding blanks
               System.out.print(' ');
                                          // display item
         System.out.print(heapArray[j].getKey());

         if(++j == currentSize)           // done?
            break;

         if(++column==itemsPerRow)        // end of row?
            {
            nBlanks /= 2;                 // half the blanks
            itemsPerRow *= 2;             // twice the items
            column = 0;                   // start over on
            System.out.println();         //    new row
            }
         else                             // next item on row
            for(int k=0; k<nBlanks*2-2; k++)
               System.out.print(' ');     // interim blanks
         }  // end for
      System.out.println("\n"+dots+dots); // dotted bottom line
      }  // end displayHeap()

   
// print heap sorted -------------------------------------------
// return a string of heap elements arranged as a sorted sequence
// IMPORTANT: be careful not to permanently 'destroy' the heap when
// printing out its elements
   public String printHeapSorted() {
	 
	   String sum = "";
	   for (int i = 0; i < currentSize; ++i)
	   {	
		   Node n =  remove();
		   int old = n.getKey();
		   
		   sum = sum + Integer.toString(old);
		    
	   }
	   return sum;
      }
   
// -------------------------------------------------------------
}  // end class Heap
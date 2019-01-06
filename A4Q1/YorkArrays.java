package A4Q1;
import java.util.*;

/**
 *
 * Provides two static methods for sorting Integer arrays (heapSort and mergeSort)
 * @author jameselder
 */
public class YorkArrays {

    /* Sorts the input array of Integers a using HeapSort.  Result is returned in a.
     * Makes use of java.util.PriorityQueue.  
       Sorting is NOT in place - PriorityQueue allocates a separate heap-based priority queue. 
       Not a stable sort, in general.  
       Throws a null pointer exception if the input array is null.  */
    public static void heapSort(Integer[] a) throws NullPointerException {
    	// implement this method.
    	if(a.length == 0)
    	{
    		throw new NullPointerException("Array is empty");
    	}
    	else
    	{
    		//Make a priority queue
    		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
    		for (int i = a.length - 1; i > 0; i--)
    		{
    			q.add(a[i]);
    		}
    		
    		//Add the elements back into the array
    		for(int i = 0; i < a.length; i++)
    		{
    			a[i] = q.poll(); //Which removes the smallest element first located at the root and adds it to beginning of array
    		}
    	}
    			
    }
    
    /* Sorts the input array of Integers a using mergeSort and returns result.
     * Sorting is stable.
       Throws a null pointer exception if the input array is null. */
    public static Integer[] mergeSort(Integer[] a)  throws NullPointerException {
        return(mergeSort(a, 0, a.length-1));
    }
    
    /* Sorts the input subarray of Integers a[p...q] using MergeSort and returns result.
     * Sorting is stable.
     */
    private static Integer[] mergeSort(Integer[] a, int p, int q) {
        //implement this method.
    	Integer [] left;
    	Integer [] right;
    	int len = a.length;
    	
    	//Base case
    	if (p == q) 
    	{
			left = new Integer[1];
			left[0] = a[p];
			return left;
		}
    	
    	if(len > 0);
    	{
    		int mid = (p + q)/2;
    		left = mergeSort(a, p, mid);
    		right = mergeSort(a, mid+1, q);
    		
    	}
    	return merge(left, right);
    }
    
    /* Merges two sorted arrays of Integers into a single sorted array.  Given two
     * equal elements, one in a and one in b, the element in a precedes the element in b.
     */
    private static Integer[] merge(Integer[] a, Integer[] b) {
        ///implement this method.   	
    	Integer[] result = new Integer[a.length + b.length];
    	int i = 0;
    	int j = 0;
    	
    	
    	int len = result.length - 1;
    	int lena = a.length - 1;
    	int lenb = b.length - 1;
    	
    	while(i < lena && j < lenb)
    	{
    		if (a[i] <= b[j])
    		{
    			result[len] = a[i];
    			i++;
    			len--;
    		}
    		else
    		{
    			result[len] = b[j];
    			j++;
    			len--;
    		}
    	}
    	while (i <= lena)
    	{
    		result[len] = a[i];
			i++;
			len--;
    	}
    	while(j <= lenb)
    	{
    		result[len] = b[j];
			j++;
			len--;
    	}
    	return result;
    }
}
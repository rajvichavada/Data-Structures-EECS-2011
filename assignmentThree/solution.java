// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");
package assignmentThree;
class solution {
    public int solutionz(int[] S, int[] E) {
        // write your code in Java SE 8
        int numOfChairs = 0;
        int arrival;
        int departure;
        int lengthS = S.length;
        int lengthE = E.length;
        
        int[] copyOfS = new int[lengthS];
        int[]copyOfE = new int[lengthE];
        
        int[] update = new int[lengthS];    //update array consisting of 0 (present) and 1 (left) of people who are at the party
        
        //next we need to initialize the copy arrays
        for (int i = 0; i < lengthS; i++)
        {
            copyOfS[i] = S[i];
            copyOfE[i] = S[i];
        }
        
        //next we need to sort these two arrays based on the time they arrived so it makes our job easier
       // sort(copyOfS,copyOfE);  //method is below]
        
        for (int i = 0; i < copyOfS.length; i++)
        {
            for (int j = 0; j < copyOfS.length-1; j++)
            {
                if(copyOfS[j] > copyOfS[j+1])
                {
                    //then we need to swap the 2 because j+1 is greater
                    swap(copyOfS[j], copyOfS[j+1]);
                    //also need to switch the same two in the other array so we are synchronized
                    swap(copyOfE[j], copyOfE[j+1]);

                }
            }
        }
        
        for (int i = 0; i < lengthS; i++)
        {
            arrival = copyOfS[i]; //gets the first arrival time 
            numOfChairs++;  //because a guest has arrived
            
            //Before we add the next guest to the party we need to see if any guest has left
            for(int x = 0; x <lengthE; x++)
            {
                if(copyOfE[x] <= arrival)
                {
                    //to check if this guest has not left yet
                    if (update[x] != 1)
                    {
                        numOfChairs--;
                        update[x] = 1; // to indicate that this guest has left
                    }
                   //else do nothing
                }
                
            }
        }
        
        
        return numOfChairs;
        
        
    }
    
  
    
    public void sort(int[] S, int[]E)
    {
        for (int i = 0; i < S.length; i++)
        {
            for (int j = 0; j < S.length-1; j++)
            {
                if(S[j] > S[j+1])
                {
                    //then we need to swap the 2 because j+1 is greater
                    swap(S[j], S[j+1]);
                    //also need to switch the same two in the other array so we are synchronized
                    swap(E[j], E[j+1]);

                }
            }
        }
    }
    
    public void swap(int i, int j)
    {
        int cp = i;
        i = j;
        j = cp;
        
    }
}
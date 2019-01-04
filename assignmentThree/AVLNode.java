package assignmentThree;

public class AVLNode  {
    /** The key stored in the node. */
    public int key;
    
    /** The data stored in the node. */
    public String data;
    
    /** The node's parent. */
    public AVLNode parent;
     
    /** The node's balanceFactor. */
    public int bf;
    
    public int height;
    
    /** The node's left child. */
    public AVLNode left;
    
    /** The node's right child. */
    public AVLNode right;
    
    /**
     * Node constructor that initializes the values of key and the data and
     * makes other pointers null. The Balance Factor is initialized to be 0. 
     */
    public AVLNode(int key, String data)  {
      this.key = key;
      this.data = data;
      this.parent = null;
      this.left = null;
      this.right = null;
      this.bf = 0;
      this.height = 1;
    }
    
    /**
     * Returns the node content as a string of the following form of: "key, data(bf)".
     */
    public String printNode()  {
      if (this == null)
        return "x";
      else
        return key + "," + data + "(" + bf + ")";
    }
  }

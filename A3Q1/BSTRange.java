package A3Q1;

import java.util.Comparator;

/**
 * Extends the TreeMap class to allow convenient access to entries
 * within a specified range of key values (findAllInRange).
 * @author jameselder
 */
public class BSTRange<K,V> extends TreeMap<K,V>{
	 private Comparator<K> comp;

    /* Returns the lowest (deepest) position in the subtree rooted at pos
     * that is a common ancestor to positions with
     * keys k1 and k2, or to the positions they would occupy were they present.
     */
    protected Position<Entry<K, V>> findLowestCommonAncestor(K k1, K k2,
            Position<Entry<K, V>> pos) {
 		//implement this method
    
    	
    	if(isExternal(pos))
    	{
    		return pos;
    	}
    	//pos is greayer than the key
    	else if((this.compare(pos.getElement().getKey(), k2) > 0))
    	{
    		pos = findLowestCommonAncestor(k1, k2, left(pos));
    	}
    	//pos less than key
    	else if((this.compare(pos.getElement().getKey(), k1) < 0))
    	{
    		pos = findLowestCommonAncestor(k1, k2, right(pos));
    	}
    	
    	return pos;
    	
    		
    			
    	
    }

    /* Finds all entries in the subtree rooted at pos  with keys of k or greater
     * and copies them to L, in non-decreasing order.
     */
    protected void findAllAbove(K k, Position<Entry<K, V>> pos,
            PositionalList<Entry<K, V>> L) 
    {
		//implement this method
    	
    	
    	if (isExternal(pos))
    	{
    		return;	// base case to allow for termination
    	}
    
    	else if((this.compare(pos.getElement().getKey(), k)) >= 0)
    	{
    		//pos is greater than/equal to key then go to the left and then right
    		findAllAbove(k, left(pos), L);
    		L.addLast(pos.getElement());
    		findAllAbove(k, right(pos), L);
    	}
    	else 
    		findAllAbove(k, right(pos), L);   //Just go to right if less than 0
    		
    	
    }

    /* Finds all entries in the subtree rooted at pos with keys of k or less
     * and copies them to L, in non-decreasing order.
     */
    protected void findAllBelow(K k, Position<Entry<K, V>> pos,
            PositionalList<Entry<K, V>> L) 
    {
 		//implement this method
    	//Position<Entry<K,V>> root = pos;
    	//Entry<K,V> elem = pos.getElement();
    	
    	if(isExternal(pos))
    	{
    		return;
    	}
    	else if(this.compare(pos.getElement(), k) <= 0)
    	{
    		//pos is less than/equal to key then go to the left and then right
    		findAllBelow(k, left(pos), L);
			L.addLast(pos.getElement());
			findAllBelow(k, right(pos), L);
    	}
    	else 
    		findAllBelow(k, left(pos), L);

    }

    /* Returns all entries with keys no less than k1 and no greater than k2,
     * in non-decreasing order.
     */
    public PositionalList<Entry<K, V>> findAllInRange(K k1, K k2) {
		//implement this method
    	
    	Position<Entry<K, V>> LCA = findLowestCommonAncestor(k1, k2, root());
    	PositionalList<Entry<K, V>> list = new LinkedPositionalList<Entry<K,V>>();
    	if(isExternal(LCA)){
    		return list;
    	}
    	
    	findAllAbove(k1, left(LCA), list);
    	list.addLast(LCA.getElement());
    	findAllBelow(k2, right(LCA), list);
    	return list;
    }
}
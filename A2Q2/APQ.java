package A2Q2;

import java.util.*;

/**
 * Adaptible priority queue using location-aware entries in a min-heap, based on
 * an extendable array. The order in which equal entries were added is
 * preserved.
 *
 * @author jameselder
 * @param <E>
 *            The entry type.
 */
public class APQ<E> {

	private final ArrayList<E> apq; // will store the min heap
	private final Comparator<E> comparator; // to compare the entries
	private final Locator<E> locator; // to locate the entries within the queue

	/**
	 * Constructor
	 * 
	 * @param comparator
	 *            used to compare the entries
	 * @param locator
	 *            used to locate the entries in the queue
	 * @throws NullPointerException
	 *             if comparator or locator parameters are null
	 */
	public APQ(Comparator<E> comparator, Locator<E> locator) throws NullPointerException {
		if (comparator == null || locator == null) {
			throw new NullPointerException();
		}
		apq = new ArrayList<>();
		apq.add(null); // dummy value at index = 0
		this.comparator = comparator;
		this.locator = locator;
	}

	/**
	 * Inserts the specified entry into this priority queue.
	 *
	 * @param e
	 *            the entry to insert
	 * @throws NullPointerException
	 *             if parameter e is null
	 */
	public void offer(E e) throws NullPointerException {
		if (e == null) {
			throw new NullPointerException();
		}
		// Adds the element and fixes order using upheap
		apq.add(e);
		int index = apq.indexOf(e);
		this.upheap(index);

	}

	/**
	 * Removes the entry at the specified location.
	 *
	 * @param pos
	 *            the location of the entry to remove
	 * @throws BoundaryViolationException
	 *             if pos is out of range
	 */
	public void remove(int pos) throws BoundaryViolationException {
		// HOW TO CHECK IF THE POS IS OUT OF RANGE???
		if (pos > apq.size() && pos < 0) {
			throw new BoundaryViolationException();
		}
		swap(pos, size());
		apq.remove(size());
		downheap(pos);

	}

	/**
	 * Removes the first entry in the priority queue.
	 */
	public E poll() {

		// ARE WE RETURNING WHAT WE REMOVED ??
		// ******************************************************************************yes??
		// Gets the entries at the beginning and end of arrayList
		E firstEntry = apq.get(1);
		E lastEntry = apq.get(apq.size() - 1);

		// Replace the root key with the last node/key
		locator.set(lastEntry, 1);
		// Removes the last node/key
		apq.remove(1);
		// Restores the order by swapping key along a downward path from the root/first
		// element of list

		this.downheap(1);

		return firstEntry;

	}

	/**
	 * Returns but does not remove the first entry in the priority queue.
	 */
	public E peek() {
		if (isEmpty()) {
			return null;
		}
		return apq.get(1);
	}

	public boolean isEmpty() {
		return (size() == 0);
	}

	public int size() {
		return apq.size() - 1; // dummy node at location 0
	}

	/**
	 * Shift the entry at pos upward in the heap to restore the minheap property
	 * 
	 * @param pos
	 *            the location of the entry to move
	 */
	private void upheap(int pos) {

		if (apq.get(pos) != null && apq.get(pos / 2) != null) {
			if ((comparator.compare(apq.get(pos), apq.get(pos / 2)) < 0) && pos > 0) // Child node is less than parent
																						// node
			{
				swap(pos, pos / 2); // switch the 2, and make the parent node = new child node
				pos = pos / 2;
				upheap(pos); // Recurse through until the parent node is smaller than child node
			}
		} else
			return;

	}

	/**
	 * Shift the entry at pos downward in the heap to restore the minheap property
	 * 
	 * @param pos
	 *            the location of the entry to move
	 */
	private void downheap(int pos) {

		int smallerC = pos;
		// Checks if left node exists
		// If it exists, check if the left node is smaller than current smallest
		if ((apq.size() - 1) >= (2 * pos) && (comparator.compare(apq.get(2 * pos), apq.get(smallerC)) < 0)) {
			smallerC = 2 * pos; // if smaller, the left node is the new smallest
		}
		// Checks if right node exists
		// If it exists, check if the right node is smaller than current smallest
		if (((apq.size() - 1) >= ((2 * pos) + 1))
				&& (comparator.compare(apq.get(2 * pos + 1), apq.get(smallerC)) < 0)) {
			smallerC = (2 * pos) + 1; // if smaller, the right node is the new smallest
		}
		// if current smallest node is not in the index of pos, method will swap the pos
		// with current
		// smallest and recurse through until pos is the smallest node
		if (smallerC != pos) {
			swap(pos, smallerC);
			downheap(smallerC);
		} else
			return;

	}

	/**
	 * Swaps the entries at the specified locations.
	 *
	 * @param pos1
	 *            the location of the first entry
	 * @param pos2
	 *            the location of the second entry
	 */
	private void swap(int pos1, int pos2) {
		E temp = apq.get(pos1);
		// locator.set(apq.get(pos2), pos1);
		// locator.set(temp, pos2);
		apq.set(pos1, apq.get(pos2));
		apq.set(pos2, temp);
	}
}
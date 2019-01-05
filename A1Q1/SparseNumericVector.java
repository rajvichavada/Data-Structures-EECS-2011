package A1Q1;

import java.util.*;

/**
 * Represents a sparse numeric vector. Elements are comprised of a (long)
 * location index an a (double) value. The vector is maintained in increasing
 * order of location index, which facilitates numeric operations like inner
 * products (projections). Note that location indices can be any integer from 1
 * to Long.MAX_VALUE. The representation is based upon a singly-linked list. The
 * following methods are supported: iterator, getSize, getFirst, add, remove,
 * and dot, which takes the dot product of the with a second vector passed as a
 * parameter.
 * 
 * @author jameselder
 */
public class SparseNumericVector implements Iterable {

	protected SparseNumericNode head = null;
	protected SparseNumericNode tail = null;
	protected long size;

	/**
	 * Iterator
	 */
	@Override
	public Iterator<SparseNumericElement> iterator() { // iterator
		return new SparseNumericIterator(this);
	}

	/**
	 * @return number of non-zero elements in vector
	 */
	public long getSize() {
		return size;
	}

	/**
	 * @return the first node in the list.
	 */
	public SparseNumericNode getFirst() {
		return head;

	}

	/**
	 * Add the element to the vector. It is inserted to maintain the vector in
	 * increasing order of index. If the element has zero value, or if an element
	 * with the same index already exists, an UnsupportedOperationException is
	 * thrown.
	 * 
	 * @param e
	 *            element to add
	 */
	public void add(SparseNumericElement e) throws UnsupportedOperationException {
		long index = e.getIndex();
		double value = e.getValue();

		if (value == 0) {
			throw new UnsupportedOperationException();

		}

		// ADD TO THE FONT
		else if (this.head == null) {
			this.head = new SparseNumericNode(e, null);
		}

		else if (e.getIndex() < this.head.getElement().getIndex()) {
			SparseNumericNode after = this.head;
			this.head = new SparseNumericNode(e, after);
			this.size++;

			if (this.size == 1) {
				this.tail = this.head;
			}
		}

		// ADD TO THE END
		else if (e.getIndex() > this.tail.getElement().getIndex()) {
			SparseNumericNode toAdd = new SparseNumericNode(e, null);
			/*
			 * if(this.size == 0) { this.head = toAdd; this.tail = toAdd; } else
			 */ // {
			toAdd.setNext(null);
			this.tail.setNext(toAdd);
			this.tail = toAdd;

			// }
			// this.size++;
		}

		// ADD IN MIDDLE
		else if (index > this.head.getElement().getIndex() && index < this.tail.getElement().getIndex()) {
			SparseNumericNode toAdd = new SparseNumericNode(e, null);

			SparseNumericNode before = this.getFirst();
			for (int i = 0; i < this.size - 1; i++) {
				before = before.getNext();
			}
			toAdd.setNext(before.getNext());
			before.setNext(toAdd);
			this.size++;

		}

	}

	/**
	 * If an element with the specified index exists, it is removed and the method
	 * returns true. If not, it returns false.
	 *
	 * @param index
	 *            of element to remove
	 * @return true if removed, false if does not exist
	 */
	public boolean remove(Long index) {
		Long indexFirst = this.head.getElement().getIndex();
		Long indexLast = this.tail.getElement().getIndex();

		if (this.tail == null || this.head == null) {
			return false;
		}

		// If we are removing the head node
		else if (index == indexFirst || this.size == 1) {
			SparseNumericNode nodeToRemove = this.head;
			this.head = nodeToRemove.getNext();
			nodeToRemove.setNext(null);
			this.size--;
			return true;
		}

		// If we are removing the last node
		else if (index == indexLast) {
			SparseNumericNode n = this.head;
			for (int i = 0; i < this.size - 1; i++) {
				n = n.getNext();
			}
			// SparseNumericNode nodeToRemove = this.tail;
			n.setNext(null);
			this.tail = n;
			this.size--;
			return true;

		} 
		else{
			
				SparseNumericNode before = this.head;
				for (int i = 1; i < index; i++) {
					before = before.getNext();
				}

				SparseNumericNode toRemove = before.getNext();
				SparseNumericNode next = toRemove.getNext();
				before.setNext(next);
				toRemove.setNext(null);
				this.size--;
			
			if (this.size == 0) {
				this.tail = null;
			}
			return true;
		}

	}

	/**
	 * Returns the inner product of the vector with a second vector passed as a
	 * parameter. The vectors are assumed to reside in the same space. Runs in
	 * O(m+n) time, where m and n are the number of non-zero elements in each
	 * vector.
	 * 
	 * @param Y
	 *            Second vector with which to take inner product
	 * @return result of inner product
	 */

	public double dot(SparseNumericVector Y) {
		double sum = 0;

		for (long i = this.head.getElement().getIndex(); i < this.tail.getElement().getIndex(); i++) {
			for (long j = Y.getFirst().getElement().getIndex(); j < Y.size; j++) {

				if (i == j) {
					sum = sum + this.head.getElement().getValue() * Y.getFirst().getElement().getValue();
					this.head = this.head.getNext();

				} else if (i > j) {
					SparseNumericNode yhead = Y.getFirst();
					yhead = yhead.getNext();
				} else if (i < j) {
					this.head = this.head.getNext();
				}
			}
		}
		return sum;

	}

	/**
	 * returns string representation of sparse vector
	 */

	@Override
	public String toString() {
		String sparseVectorString = "";
		Iterator<SparseNumericElement> it = iterator();
		SparseNumericElement x;
		while (it.hasNext()) {
			x = it.next();
			sparseVectorString += "(index " + x.getIndex() + ", value " + x.getValue() + ")\n";
		}
		return sparseVectorString;
	}
}

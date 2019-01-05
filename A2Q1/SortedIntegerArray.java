package A2Q1;

import java.util.*;

/**
 * Represents a sorted integer array. Provides a method, kpairSum, that
 * determines whether the array contains two elements that sum to a given
 * integer k. Runs in O(n) time, where n is the length of the array.
 * 
 * @author jameselder
 */
public class SortedIntegerArray {

	protected int[] sortedIntegerArray;
	// private int start = 0;
	// private int end = sortedIntegerArray.length;

	public SortedIntegerArray(int[] integerArray) {
		sortedIntegerArray = integerArray.clone();
		Arrays.sort(sortedIntegerArray);
	}

	/**
	 * Determines whether the array contains two elements that sum to a given
	 * integer k. Runs in O(n) time, where n is the length of the array.
	 * 
	 * @author jameselder
	 */
	public boolean kPairSum(Integer k) {
		int i = 0;
		int j = sortedIntegerArray.length - 1;
		return kPairSumInterval(k, i, j);

	}

	private boolean kPairSumInterval(Integer k, int i, int j) {

		if (sortedIntegerArray.length < 2) {
			return false;
		}
		if (sortedIntegerArray.length == 0) {
			return false;
		}
		if (i < 0 || i >= sortedIntegerArray.length) {
			return false;
		}
		if (j < 0 || j >= sortedIntegerArray.length) {
			return false;
		}
		if (i == j) {
			return false;
		}

		int currentS = sortedIntegerArray[i];
		int currentE = sortedIntegerArray[j];
		long sum = currentS + currentE;

		
			if (k == sum) {
				return true;
			} else if (sum < k) {
				return kPairSumInterval(k, i+1, j);
			} else if (sum > k) {

				return kPairSumInterval(k, i, j-1);
			} else
				return false;

	}
}
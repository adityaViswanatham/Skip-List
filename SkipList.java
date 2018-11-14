/** @author Aditya Viswanatham
 * 
 */

package arv160730;

import java.util.*;

// SkipList Class. 
public class SkipList<T extends Comparable<? super T>> {

	// Constant to initialize the next arrays of head and tail.
	static final int PossibleLevels = 33;

	// Entry Class for the Skip List.
	static class Entry<E> {

		// Entry Class Variables.
		E element;
		Entry<E>[] next;
		Entry<E> prev;
		int[] span;

		// Entry Class Constructor.
		@SuppressWarnings("unchecked")
		public Entry(E x, int lev) {
			element = x;
			next = new Entry[lev];
			span = new int[lev];
		}

		// getElement() to retrieve the element from the node.
		public E getElement() {
			return element;
		}
	}

	// Skip List Class Variables.
	Entry<T> head, tail;
	int size, maxLevel;
	// Last is used by find.
	Entry<T>[] last;
	Random random;

	// SkipList Class Constructor
	@SuppressWarnings("unchecked")
	public SkipList() {
		// Initializing the SkipList Class variables.
		head = new Entry<T>(null, PossibleLevels);
		tail = new Entry<T>(null, PossibleLevels);
		for (int i = 0; i < PossibleLevels; i++) {
			head.next[i] = tail;
			tail.prev = head;
		}
		size = 0;
		maxLevel = 1;
		last = new Entry[33];
		random = new Random();
	}

	/**
	 * Find method to find an element in the skip list.
	 * 
	 * @param T x
	 * @return Entry<T>
	 */
	public Entry<T> find(T x) {
		// p to traverse through the list.
		Entry<T> p = head;
		// Loops through the list by taking the express paths(Higher levels) first and
		// then comes down by a level.
		for (int i = PossibleLevels - 1; i >= 0; i--) {
			// While loop compares the current element to the target element and moves the
			// pointer accordingly.
			while (p.next[i] != tail && x.compareTo((T) p.next[i].getElement()) > 0) {
				p = p.next[i];
			}
			last[i] = p;
		}
		return p;
	}

	/**
	 * Does list contain x?
	 * 
	 * @param T x
	 * @return boolean.
	 */
	public boolean contains(T x) {
		// Calls find to see if the element exists in the list.
		find(x);
		// Calls the isEmpty function to check if the list is empty.
		if (isEmpty()) {
			return false;
		}
		if (last[0].next[0].getElement() == null) {
			return false;
		}
		return last[0].next[0].getElement().compareTo(x) == 0;
	}

	/**
	 * Add x to list. If x already exists, reject it. Returns true if new node is
	 * added to list.
	 * 
	 * @param T x
	 * @return boolean.
	 */
	public boolean add(T x) {
		// Calls contains to check if the element already exists in the list.
		if (contains(x)) {
			System.out.println("Element exists.");
			return false;
		}
		// "lev" stores the level returned by chooseLevel() and initializes the new
		// entry's next array.
		System.out.println("To be added: " + x);
		Integer lev = chooseLevel();
		System.out.println("Level is: " + lev);
		// Creates a new entry.
		Entry<T> ent = new Entry<T>(x, lev);
		// Sets the pointers of the last element and the new element.
		for (int i = 0; i <= lev - 1; i++) {
			ent.next[i] = last[i].next[i];
			last[i].next[i] = ent;
		}
		// Sets the previous pointer of the new node.
		ent.next[0].prev = ent;
		ent.prev = last[0];
		size = size + 1;
		return true;
	}

	/**
	 * Find smallest element that is greater or equal to x
	 * 
	 * @param T x
	 * @return T
	 */
	public T ceiling(T x) {
		// Calls the contains method to check if the element exists in the list.
		if (contains(x)) {
			return last[0].next[0].getElement();
		}
		// Deals with all the cases if the element does not exist in the list.
		else {
			if (isEmpty())
				return null;
			else if (last[0].next[0].getElement() == null) {
				return null;
			} else {
				return last[0].next[0].getElement();
			}
		}
	}

	/**
	 * Return first element of list
	 * 
	 * @return T
	 */
	public T first() {
		// Returning the first element of the list.
		if (head.next[0].getElement() == null)
			return null;
		return head.next[0].getElement();
	}

	/**
	 * Find largest element that is less than or equal to x
	 * 
	 * @param T x
	 * @return T
	 */
	public T floor(T x) {
		// Calls the contains method to check if the element exists in the list.
		if (contains(x)) {
			return last[0].next[0].getElement();
		}
		// Deals with all the cases if the element does not exist in the list.
		else {
			if (isEmpty())
				return null;
			else if (last[0].next[0].getElement() == null) {
				return last[0].getElement();
			} else
				return last[0].getElement();
		}
	}

	/**
	 * Return element at index n of list. First element is at index 0.
	 * 
	 * @param int n
	 * @return T
	 */
	public T get(int n) {
		// Checking to see if the index is out of the range.
		if (n < 0 || n > size - 1)
			throw new NoSuchElementException();
		// Creating a new cursor to iterate through the list.
		Entry<T> p = head;
		for (int i = 0; i <= n; i++) {
			p = p.next[0];
		}
		return p.getElement();
	}

	/**
	 * Is the list empty?
	 * 
	 * @return boolean.
	 */
	public boolean isEmpty() {
		if (size <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * Iterate through the elements of list in sorted order
	 * 
	 * @return Iterator<T>
	 */
	public Iterator<T> iterator() {
		class SkipListIterator implements Iterator<T> {

			Entry<T> cursor;

			// SkipListIterator Class Constructor.
			SkipListIterator() {
				cursor = head;
			}

			/**
			 * Checks to see if there is a valid next.
			 * 
			 * @return boolean.
			 */
			public boolean hasNext() {
				return cursor.next[0].getElement() != null;
			}

			/**
			 * Returns next element.
			 * 
			 * @return T
			 */
			public T next() {
				cursor = cursor.next[0];
				return cursor.getElement();
			}

			// Remove Method.
			public void remove() {
				throw new UnsupportedOperationException();
			}

		}
		return new SkipListIterator();
	}

	/**
	 * Return last element of list
	 * 
	 * @return T.
	 */
	public T last() {
		if (tail.prev.getElement() == null)
			return null;
		else
			return tail.prev.getElement();
	}

	/**
	 * Remove x from list. Removed element is returned. Return null if x not in list
	 * 
	 * @param T x
	 * @return T
	 */
	public T remove(T x) {
		if (!contains(x))
			return null;
		Entry<T> ent = last[0].next[0];
		for (int i = 0; i <= ent.next.length - 1; i++) {
			last[i].next[i] = ent.next[i];
		}
		ent.next[0].prev = ent.prev;
		size = size - 1;
		return ent.element;
	}

	/**
	 * ChooseLevel Method for adding nodes to the list.
	 * 
	 * @return Integer
	 */
	public Integer chooseLevel() {
		Integer lev = 1 + Integer.numberOfTrailingZeros(random.nextInt());
		if (lev > maxLevel) {
			maxLevel = lev;
		}
		return lev;
	}

	/**
	 * Return the number of elements in the list
	 * 
	 * @return int.
	 */
	public int size() {
		return size;
	}

	// Main Function.
	public static void main(String[] args) {
		SkipList<Integer> my_list = new SkipList<>();
    	my_list.add(1);
    	my_list.add(2);
    	my_list.add(4);
    	my_list.add(2);
    	System.out.println(my_list.size());
	}
}
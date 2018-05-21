package se.hh.imageEditor.commons;

import static java.util.Arrays.asList;

import java.util.List;

/**
 * A last-in, first-out collection of elements
 * 
 * @param <T>
 *            The type of the elements in the stack
 */
public final class Stack<T> {

	private T[] elements;
	private final int capacity;

	private int size;

	/**
	 * Constructs an empty stack
	 * 
	 * @param capacity
	 *            the maximum capacity of the stack
	 * @precondition capacity > 0
	 */
	@SuppressWarnings("unchecked")
	public Stack(int capacity) {
		assert capacity > 0 : "The capacity must be a positive integer.";
		this.capacity = capacity;
		elements = (T[]) new Object[capacity];
	}

	/**
	 * Push an element to the top
	 * 
	 * @param element
	 *            Element to be stored
	 * @precondition size < capacity
	 */
	public void push(T element) {
		assert size < capacity : "The stack is full.";
		elements[size++] = element;
	}

	/**
	 * @return the element on the top
	 * @precondition size > 0
	 */
	public T pop() {
		assert size > 0 : "The stack is empty.";
		return elements[--size];
	}

	/**
	 * Push multiple elements to the top
	 * 
	 * @param elements
	 *            Array of elements to be stored
	 * @precondition size + elements.length <= capacity
	 */
	@SuppressWarnings("unchecked")
	public void push(T... elements) {
		assert size + elements.length <= capacity : "Unsupported capacity.";
		for (int i = 0; i < elements.length; i++) {
			push(elements[i]);
		}
	}

	/**
	 * @return a list containing the 'n' number of elements
	 * @precondition n <= size
	 */
	@SuppressWarnings("unchecked")
	public List<T> pop(int n) {
		assert n <= size : "Not enough elements in the stack.";
		T[] result = (T[]) new Object[n];
		for (int i = n - 1; i >= 0; i--) {
			result[i] = pop();
		}
		return asList(result);
	}

	/**
	 * @return the number of elements stored
	 */
	public int getSize() {
		return size;
	}

	@SuppressWarnings("unchecked")
	public void clear() {
		if (!isEmpty()) {
			elements = (T[]) new Object[capacity];
		}
	}

	public boolean isEmpty() {
		return size < 1;
	}
}

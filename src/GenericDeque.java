public class GenericDeque<T> {
	private Node head;
	private Node tail;
	private int size;

	private class Node {
		T data;
		Node next;
		Node prev;

		Node(T data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
	}

	public GenericDeque() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	public void addFront(T item) {
		Node newNode = new Node(item);
		if (isEmpty()) {
			head = tail = newNode;
		} else {
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
		}
		size++;
	}

	public void addBack(T item) {
		Node newNode = new Node(item);
		if (isEmpty()) {
			head = tail = newNode;
		} else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		size++;
	}

	public T removeFront() {
		if (isEmpty()) {
			return null;
		}
		T data = head.data;
		head = head.next;
		if (head != null) {
			head.prev = null;
		} else {
			tail = null;
		}
		size--;
		return data;
	}

	public T removeBack() {
		if (isEmpty()) {
			return null;
		}
		T data = tail.data;
		tail = tail.prev;
		if (tail != null) {
			tail.next = null;
		} else {
			head = null;
		}
		size--;
		return data;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public void display() {
		Node current = head;
		int index = 1;
		while (current != null) {
			System.out.println(index + ". " + current.data);
			current = current.next;
			index++;
		}
	}

	public T[] getAll() {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[size];
		Node current = head;
		int index = 0;
		while (current != null) {
			result[index] = current.data;
			current = current.next;
			index++;
		}
		return result;
	}

	public int size() {
		return size;
	}
}

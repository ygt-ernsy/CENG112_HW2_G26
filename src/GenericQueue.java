public class GenericQueue<T> {
	private Node head;
	private Node tail;
	private int size;

	private class Node {
		T data;
		Node next;

		Node(T data) {
			this.data = data;
			this.next = null;
		}
	}

	public GenericQueue() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	public void enqueue(T item) {
		Node newNode = new Node(item);
		if (isEmpty()) {
			head = tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
		size++;
	}

	public T dequeue() {
		if (isEmpty()) {
			return null;
		}
		T data = head.data;
		head = head.next;
		if (head == null) {
			tail = null;
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
		if (size == 0) {
			@SuppressWarnings("unchecked")
			T[] emptyArray = (T[]) new Object[0];
			return emptyArray;
		}

		@SuppressWarnings("unchecked")
		T[] result = (T[]) java.lang.reflect.Array.newInstance(head.data.getClass(), size);
		Node current = head;
		int index = 0;
		while (current != null) {
			result[index++] = current.data;
			current = current.next;
		}
		return result;
	}

	public int size() {
		return size;
	}
}

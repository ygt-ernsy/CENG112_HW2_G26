public class GenericQueue<T> {
	private Node<T> firstNode;
	private Node<T> lastNode;

	public GenericQueue() {
		firstNode = null;
		lastNode = null;
	}

	public void enqueue(T newEntry) {
		Node<T> newNode = new Node<T>(newEntry, null);

		if (isEmpty()) {
			firstNode = newNode;
		} else {
			lastNode.setNext(newNode);
		}

		lastNode = newNode;
	}

	public T getFront() {
		if (isEmpty()) {
			throw new RuntimeException();
		}

		return firstNode.getData();
	}

	public boolean isEmpty() {
		return firstNode == null && lastNode == null;
	}

	public T dequeue() {
		T front = getFront();
		assert firstNode != null;
		firstNode.setData(null);
		firstNode = firstNode.getNext();
		if (firstNode == null) {
			lastNode = null;
		}
		return front;
	}

	public void clear() {
		firstNode = null;
		lastNode = null;
	}

	public void display() {
		if (isEmpty()) {
			System.out.println("Queue is empty");
			return;
		}

		Node<T> current = firstNode;
		int index = 1;
		while (current != null) {
			System.out.println(index + ". " + current.getData());
			current = current.getNext();
			index++;
		}
	}

	public T[] getAll() {
		if (isEmpty()) {
			return (T[]) new Object[0];
		}

		int size = 0;
		Node<T> current = firstNode;
		while (current != null) {
			size++;
			current = current.getNext();
		}

		T[] array = (T[]) new Object[size];
		current = firstNode;
		for (int i = 0; i < size; i++) {
			array[i] = current.getData();
			current = current.getNext();
		}

		return array;
	}

	private class Node<T> {
		private T data;
		private Node next;

		private Node(T data) {
			this(data, null);
		}

		private Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}

		private T getData() {
			return data;
		}

		private Node getNext() {
			return next;
		}

		private void setNext(Node next) {
			this.next = next;
		}

		private void setData(T data) {
			this.data = data;
		}
	}
}

public class GenericQueue<T> {
	private Node<T> firstNode;
	private Node<T> lastNode;

	public GenericQueue() {
		firstNode = null;
		lastNode = null;
	}

	public void enqueue(T newEntry) {
		Node newNode = new Node<T>(newEntry, null);

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

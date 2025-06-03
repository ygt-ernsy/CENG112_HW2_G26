
public class LList<T> {
	private Node<T> firstNode;
	private int numberOfEntries;

	public LList() {
		initializeDataFields();
	}

	private void initializeDataFields() {
		firstNode = null;
		numberOfEntries = 0;
	}

	public void add(T value) {
		Node<T> newNode = new Node<T>(value);
		if (numberOfEntries == 0) {
			firstNode = newNode; // Don't call setData on null
		} else {
			// Find the last node and link to it
			Node<T> currentNode = firstNode;
			while (currentNode.getNext() != null) {
				currentNode = currentNode.getNext();
			}
			currentNode.setNext(newNode);
		}
		numberOfEntries++; // Don't forget to increment!
	}

	public Node<T> getNodeAt(int index) {
		if (!(index >= 0 && index < numberOfEntries)) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			return firstNode;
		}

		Node<T> currentNode = firstNode;

		for (int i = 0; i < index; i++)
			currentNode = currentNode.getNext();

		if (currentNode == null)
			throw new IndexOutOfBoundsException();

		return currentNode;
	}

	public T remove(int index) {
		if (!(index >= 0 && index < numberOfEntries))
			throw new IndexOutOfBoundsException();

		if (index == 0) {
			Node<T> currentNode = firstNode;
			this.firstNode = firstNode.getNext();
			numberOfEntries--;
			return currentNode.getData();
		} else {
			T removedValue = getNodeAt(index).getData();
			Node<T> previousNode = getNodeAt(index - 1);
			Node<T> nextNode = getNodeAt(index).getNext();
			previousNode.setNext(nextNode);
			numberOfEntries--;
			return removedValue;
		}
	}

	public int getLength() {
		return numberOfEntries;
	}

	public boolean isEmpty() {
		return numberOfEntries == 0;
	}

	public T replace(int index, T newValue) {
		if (!(index >= 0 && index < numberOfEntries))
			throw new IndexOutOfBoundsException();
		if (isEmpty())
			throw new RuntimeException("List is empty");

		Node<T> replacedNode = getNodeAt(index);
		T originalValue = replacedNode.getData();
		replacedNode.setData(newValue);
		return originalValue;
	}

	public void add(int newIndex, T newValue) {
		if (!(newIndex >= 0 && newIndex < numberOfEntries))
			throw new IndexOutOfBoundsException();

		Node<T> newNode = new Node<>(newValue);

		if (newIndex == 0) {
			newNode.setNext(firstNode);
			this.firstNode = newNode;
		} else {
			Node<T> currentNode = getNodeAt(newIndex);
			Node<T> previousNode = getNodeAt(newIndex - 1);
			previousNode.setNext(newNode);
			newNode.setNext(currentNode);
		}
		numberOfEntries++;
	}

	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[numberOfEntries];

		for (int i = 0; i < numberOfEntries; i++) {
			array[i] = getNodeAt(i).getData();
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

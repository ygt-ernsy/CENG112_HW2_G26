public class GenericHistory<T> {
	private Node head;
	private int size;

	private class Node {
		T data;
		Node next;

		Node(T data) {
			this.data = data;
			this.next = null;
		}
	}

	public GenericHistory() {
		this.head = null;
		this.size = 0;
	}

	public void add(T item) {
		Node newNode = new Node(item);
		if (head == null) {
			head = newNode;
		} else {
			Node current = head;
			while (current.next != null) {
				current = current.next;
			}
			current.next = newNode;
		}
		size++;
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
	public void display() {
		Node current = head;
		int index = 1;
		while (current != null) {
			System.out.println(index + ". " + current.data);
			current = current.next;
			index++;
		}
	}

	public int size() {
		return size;
	}
}


// Custom LinkedList implementation
public class CustomLinkedList<T> {
	private Node<T> head;
	private Node<T> tail;
	private int size;

	public CustomLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	public void add(T data) {
		Node<T> newNode = new Node<>(data);
		if (head == null) {
			head = tail = newNode;
		} else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		size++;
	}

	public void addFirst(T data) {
		Node<T> newNode = new Node<>(data);
		if (head == null) {
			head = tail = newNode;
		} else {
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
		}
		size++;
	}

	public T removeFirst() {
		if (head == null)
			return null;
		T data = head.data;
		if (head == tail) {
			head = tail = null;
		} else {
			head = head.next;
			head.prev = null;
		}
		size--;
		return data;
	}

	public T removeLast() {
		if (tail == null)
			return null;
		T data = tail.data;
		if (head == tail) {
			head = tail = null;
		} else {
			tail = tail.prev;
			tail.next = null;
		}
		size--;
		return data;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public T[] toArray() {
		Object[] array = new Object[size];
		Node<T> current = head;
		int index = 0;
		while (current != null) {
			array[index++] = current.data;
			current = current.next;
		}
		return (T[]) array;
	}
}

// Node.java - Helper class for linked list implementation
public class Node<T> {
	T data;
	Node<T> next;
	Node<T> prev;

	public Node(T data) {
		this.data = data;
		this.next = null;
		this.prev = null;
	}
}

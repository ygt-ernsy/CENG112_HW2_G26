public class GenericDeque<T> {
	private DLNode firsNode;
	private DLNode lasNode;

	public GenericDeque() {
		firsNode = null;
		lasNode = null;
	}

	public void addToBack(T newEntry) {
		DLNode newNode = new DLNode(newEntry, lasNode);

		if (isEmpty())
			firsNode = newNode;
		else
			lasNode.setNextNode(newNode);

		lasNode = newNode;
	}

	public void addToFront(T newEntry) {
		DLNode newNode = new DLNode(newEntry, null, firsNode);

		if (isEmpty())
			firsNode = newNode;
		else
			firsNode.setPreviousNode(newNode);

		firsNode = newNode;
	}

	public T removeFront() {
		T front = firsNode.getData();

		assert firsNode != null;

		firsNode = firsNode.getNextNode();

		if (firsNode == null)
			lasNode = null;
		else
			firsNode.setPreviousNode(null);

		return front;
	}

	public T removeBack() {
		T back = lasNode.getData();

		assert lasNode != null;

		lasNode = lasNode.getPreviousNode();

		if (lasNode == null)
			firsNode = null;
		else
			lasNode.setNextNode(null);

		return back;
	}

	public boolean isEmpty() {
		return firsNode == null && lasNode == null;
	}

	private class DLNode {
		private T data;
		private DLNode previousNode;
		private DLNode nextNode;

		public DLNode() {
			this(null);
		}

		public DLNode(T data) {
			this(data, null);
		}

		public DLNode(T data, DLNode previousNode) {
			this(data, previousNode, null);
		}

		public DLNode(T data, DLNode previousNode, DLNode nextNode) {
			this.data = data;
			this.previousNode = previousNode;
			this.nextNode = nextNode;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public DLNode getPreviousNode() {
			return previousNode;
		}

		public void setPreviousNode(DLNode previousNode) {
			this.previousNode = previousNode;
		}

		public DLNode getNextNode() {
			return nextNode;
		}

		public void setNextNode(DLNode nextNode) {
			this.nextNode = nextNode;
		}
	}
}

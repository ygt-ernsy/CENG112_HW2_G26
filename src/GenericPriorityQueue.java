public class GenericPriorityQueue<T extends Comparable<? super T>> implements PriorityQueueInterface<T> {
	private GenericQueue<T> highPriorityQueue;
	private GenericQueue<T> mediumPriorityQueue;
	private GenericQueue<T> lowPriorityQueue;

	public GenericPriorityQueue() {
		highPriorityQueue = new GenericQueue<T>();
		mediumPriorityQueue = new GenericQueue<T>();
		lowPriorityQueue = new GenericQueue<T>();
	}

	public void add(T newEntry) {
		if (newEntry == null) {
			throw new IllegalArgumentException("Item cannot be null");
		}

		String priority = determinePriority(newEntry);

		switch (priority.toLowerCase()) {
			case "high":
				highPriorityQueue.enqueue(newEntry);
				break;
			case "medium":
				mediumPriorityQueue.enqueue(newEntry);
				break;
			case "low":
				lowPriorityQueue.enqueue(newEntry);
				break;
			default:
				lowPriorityQueue.enqueue(newEntry);
				break;
		}
	}

	public T remove() {
		if (!highPriorityQueue.isEmpty()) {
			return highPriorityQueue.dequeue();
		} else if (!mediumPriorityQueue.isEmpty()) {
			return mediumPriorityQueue.dequeue();
		} else if (!lowPriorityQueue.isEmpty()) {
			return lowPriorityQueue.dequeue();
		}
		throw new RuntimeException("Priority queue is empty");
	}

	public void peek() {
		T frontItem = null;
		if (!highPriorityQueue.isEmpty()) {
			frontItem = highPriorityQueue.getFront();
		} else if (!mediumPriorityQueue.isEmpty()) {
			frontItem = mediumPriorityQueue.getFront();
		} else if (!lowPriorityQueue.isEmpty()) {
			frontItem = lowPriorityQueue.getFront();
		}

		if (frontItem != null) {
			System.out.println("Next item to be processed: " + frontItem);
		} else {
			System.out.println("Priority queue is empty");
		}
	}

	public boolean isEmpty() {
		return highPriorityQueue.isEmpty() &&
				mediumPriorityQueue.isEmpty() &&
				lowPriorityQueue.isEmpty();
	}

	public int getSize() {
		return countAllItems();
	}

	public void clear() {
		highPriorityQueue.clear();
		mediumPriorityQueue.clear();
		lowPriorityQueue.clear();
	}

	private int countAllItems() {
		return countQueueItems(highPriorityQueue) +
				countQueueItems(mediumPriorityQueue) +
				countQueueItems(lowPriorityQueue);
	}

	public void offer(T item) {
		add(item);
	}

	public T poll() {
		try {
			return remove();
		} catch (RuntimeException e) {
			return null;
		}
	}

	public T[] getAll() {
		int totalSize = countAllItems();

		if (totalSize == 0) {
			return (T[]) new Comparable[0];
		}

		T[] allItems = (T[]) new Comparable[totalSize];
		int index = 0;

		index = addQueueItemsToArray(highPriorityQueue, allItems, index);

		index = addQueueItemsToArray(mediumPriorityQueue, allItems, index);

		addQueueItemsToArray(lowPriorityQueue, allItems, index);

		return allItems;
	}

	public void display() {
		T[] allItems = getAll();
		for (int i = 0; i < allItems.length; i++) {
			System.out.println((i + 1) + ". " + allItems[i]);
		}
	}

	private String determinePriority(T item) {
		String itemString = item.toString().toLowerCase();

		if (itemString.contains("high")) {
			return "high";
		} else if (itemString.contains("medium")) {
			return "medium";
		} else if (itemString.contains("low")) {
			return "low";
		}

		return "low";
	}

	private int addQueueItemsToArray(GenericQueue<T> queue, T[] array, int startIndex) {
		if (queue.isEmpty()) {
			return startIndex;
		}

		T[] tempItems = (T[]) new Comparable[countQueueItems(queue)];
		int tempIndex = 0;
		int arrayIndex = startIndex;

		while (!queue.isEmpty()) {
			T item = queue.dequeue();
			tempItems[tempIndex++] = item;
			array[arrayIndex++] = item;
		}

		for (int i = 0; i < tempIndex; i++) {
			queue.enqueue(tempItems[i]);
		}

		return arrayIndex;
	}

	private int countQueueItems(GenericQueue<T> queue) {
		if (queue.isEmpty()) {
			return 0;
		}

		int count = 0;
		T[] tempItems = (T[]) new Comparable[1000];

		while (!queue.isEmpty()) {
			tempItems[count] = queue.dequeue();
			count++;
		}

		for (int i = 0; i < count; i++) {
			queue.enqueue(tempItems[i]);
		}

		return count;
	}

}

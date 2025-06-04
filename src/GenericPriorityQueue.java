public class GenericPriorityQueue<T extends Comparable<T>> {
	private GenericQueue<T> highPriorityQueue;
	private GenericQueue<T> mediumPriorityQueue;
	private GenericQueue<T> lowPriorityQueue;

	public GenericPriorityQueue() {
		this.highPriorityQueue = new GenericQueue<>();
		this.mediumPriorityQueue = new GenericQueue<>();
		this.lowPriorityQueue = new GenericQueue<>();
	}

	public void offer(T item) {
		String priority = determinePriority(item);
		switch (priority.toLowerCase()) {
			case "high":
				highPriorityQueue.enqueue(item);
				break;
			case "medium":
				mediumPriorityQueue.enqueue(item);
				break;
			case "low":
				lowPriorityQueue.enqueue(item);
				break;
			default:
				lowPriorityQueue.enqueue(item);
				break;
		}
	}

	public T poll() {
		if (!highPriorityQueue.isEmpty()) {
			return highPriorityQueue.dequeue();
		} else if (!mediumPriorityQueue.isEmpty()) {
			return mediumPriorityQueue.dequeue();
		} else if (!lowPriorityQueue.isEmpty()) {
			return lowPriorityQueue.dequeue();
		}
		return null;
	}

	public boolean isEmpty() {
		return highPriorityQueue.isEmpty() &&
				mediumPriorityQueue.isEmpty() &&
				lowPriorityQueue.isEmpty();
	}

public T[] getAll() {
    // Calculate total size from all priority queues
    int totalSize = highPriorityQueue.size() + mediumPriorityQueue.size() + lowPriorityQueue.size();

    // Handle empty case
    if (totalSize == 0) {
        @SuppressWarnings("unchecked")
        T[] emptyArray = (T[]) new Object[0];
        return emptyArray;
    }

    // Determine component type from the first non-empty queue
    Class<?> componentType;
    if (highPriorityQueue.size() > 0) {
        componentType = highPriorityQueue.getAll()[0].getClass();
    } else if (mediumPriorityQueue.size() > 0) {
        componentType = mediumPriorityQueue.getAll()[0].getClass();
    } else {
        componentType = lowPriorityQueue.getAll()[0].getClass();
    }

    // Create a type-safe array
    @SuppressWarnings("unchecked")
    T[] result = (T[]) java.lang.reflect.Array.newInstance(componentType, totalSize);

    // Copy items from each queue in priority order
    int index = 0;
    for (T item : highPriorityQueue.getAll()) {
        result[index++] = item;
    }
    for (T item : mediumPriorityQueue.getAll()) {
        result[index++] = item;
    }
    for (T item : lowPriorityQueue.getAll()) {
        result[index++] = item;
    }

    return result;
}

	private String determinePriority(T item) {
		if (item instanceof Ticket) {
			return ((Ticket) item).getPriority();
		}

		String str = item.toString().toLowerCase();
		if (str.contains("high")) {
			return "high";
		} else if (str.contains("medium")) {
			return "medium";
		} else if (str.contains("low")) {
			return "low";
		}
		return "low";
	}
}

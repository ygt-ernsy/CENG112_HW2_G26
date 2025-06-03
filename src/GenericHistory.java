public class GenericHistory<T> {
	private LList<T> lList;

	public GenericHistory() {
		lList = new LList<>();
	}

	public void add(T newEntry) {
		lList.add(newEntry);
	}

	public Object[] getAll() {
		Object[] lListArray = lList.toArray();

		return lListArray;
	}

	public void display() {
		Object[] lListArray = lList.toArray();

		for (Object item : lListArray) {
			System.out.println(item);
		}
	}
}

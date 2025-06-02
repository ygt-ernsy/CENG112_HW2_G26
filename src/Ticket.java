
public class Ticket implements Comparable<Ticket> {

	String customerName;
	String issueDescription;
	String priority;
	int arrivalTime;

	public int compareTo(Ticket otherTicket) {

		int thisPriorityLevel = getPriorityValue(this.priority);
		int otherPriorityLevel = getPriorityValue(otherTicket.getPriority());

		int comparison = Integer.compare(thisPriorityLevel, otherPriorityLevel);

		if (comparison != 0) {
			return comparison;
		}

		return Integer.compare(this.arrivalTime, otherTicket.getArrivalTime());
	}

	private int getPriorityValue(String pString) {
		switch (pString.toLowerCase()) {
			case "high":
				return 3;
			case "medium":
				return 2;
			case "low":
				return 1;
			default:
				return 0;
		}
	}

	public String getPriority() {
		return priority;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public String toString() {
		return customerName + '-' + issueDescription + '-' + '[' + priority + ']';
	}
}

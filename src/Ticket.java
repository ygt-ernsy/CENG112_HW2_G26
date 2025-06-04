public class Ticket implements Comparable<Ticket> {
	private String customerName;
	private String issueDescription;
	private String priority;
	private long arrivalTime;

	public Ticket(String customerName, String issueDescription, String priority) {
		this.customerName = customerName;
		this.issueDescription = issueDescription;
		this.priority = priority;
		this.arrivalTime = System.currentTimeMillis();
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getIssueDescription() {
		return issueDescription;
	}

	public String getPriority() {
		return priority;
	}

	public long getArrivalTime() {
		return arrivalTime;
	}

	private int getPriorityValue() {
		switch (priority.toLowerCase()) {
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

	@Override
	public int compareTo(Ticket other) {
		int priorityComparison = Integer.compare(other.getPriorityValue(), this.getPriorityValue());
		if (priorityComparison != 0) {
			return priorityComparison;
		}
		return Long.compare(this.arrivalTime, other.arrivalTime);
	}

	@Override
	public String toString() {
		return customerName + " - " + issueDescription + " [" + priority + "]";
	}
}

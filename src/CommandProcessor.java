public class CommandProcessor {
	private GenericPriorityQueue<Ticket> activeTickets;
	private GenericHistory<Ticket> resolvedTickets;

	public CommandProcessor() {
		this.activeTickets = new GenericPriorityQueue<>();
		this.resolvedTickets = new GenericHistory<>();
	}

	public void processCommands(Command[] commands) {
		for (Command command : commands) {
			switch (command.getType().toLowerCase()) {
				case "new":
					addTicket(command);
					break;
				case "resolve":
					resolveTicket();
					break;
				case "display":
					displayTickets(command.getSortOrder());
					break;
				case "history":
					displayHistory(command.getSortOrder());
					break;
			}
		}
	}

	private void addTicket(Command command) {
		Ticket ticket = new Ticket(command.getCustomerName(),
				command.getIssueDescription(),
				command.getPriority());
		activeTickets.offer(ticket);
		System.out.println("Adding Ticket: " + ticket + " [" + ticket.getPriority() + " Priority]");
	}

	private void resolveTicket() {
		Ticket resolvedTicket = activeTickets.poll();
		if (resolvedTicket != null) {
			resolvedTickets.add(resolvedTicket);
			System.out.println("Resolving Ticket:");
			System.out.println("Resolved: " + resolvedTicket);
		}
	}

	private void displayTickets(String sortOrder) {
		Ticket[] tickets = activeTickets.getAll();

		if (sortOrder == null || sortOrder.equals("priority")) {
			System.out.println("--- Displaying Active Tickets (By Priority) ---");
			for (int i = 0; i < tickets.length; i++) {
				System.out.println((i + 1) + ". " + tickets[i]);
			}
		} else if (sortOrder.equals("asc")) {
			System.out.println("--- Displaying Active Tickets (By ASC - Oldest First) ---");
			sortByArrivalTime(tickets, true);
			for (int i = 0; i < tickets.length; i++) {
				System.out.println((i + 1) + ". " + tickets[i]);
			}
		} else if (sortOrder.equals("desc")) {
			System.out.println("--- Displaying Active Tickets (By DESC - Newest First) ---");
			sortByArrivalTime(tickets, false);
			for (int i = 0; i < tickets.length; i++) {
				System.out.println((i + 1) + ". " + tickets[i]);
			}
		}
	}

	private void displayHistory(String sortOrder) {
		Ticket[] history = resolvedTickets.getAll();

		if (sortOrder == null) {
			System.out.println("--- Resolved Ticket History (Sorted by Customer Name) ---");
			sortByCustomerName(history, true);
			for (int i = 0; i < history.length; i++) {
				System.out.println((i + 1) + ". " + history[i]);
			}
		} else if (sortOrder.equals("asc")) {
			System.out.println("--- Resolved Ticket History (ASC - Oldest First) ---");
			sortByArrivalTime(history, true);
			for (int i = 0; i < history.length; i++) {
				System.out.println((i + 1) + ". " + history[i]);
			}
		} else if (sortOrder.equals("desc")) {
			System.out.println("--- Resolved Ticket History (DESC - Newest First) ---");
			sortByArrivalTime(history, false);
			for (int i = 0; i < history.length; i++) {
				System.out.println((i + 1) + ". " + history[i]);
			}
		}
	}

	private void sortByCustomerName(Ticket[] tickets, boolean ascending) {
		for (int i = 0; i < tickets.length - 1; i++) {
			for (int j = 0; j < tickets.length - i - 1; j++) {
				int comparison = tickets[j].getCustomerName()
						.compareTo(tickets[j + 1].getCustomerName());
				if ((ascending && comparison > 0) || (!ascending && comparison < 0)) {
					Ticket temp = tickets[j];
					tickets[j] = tickets[j + 1];
					tickets[j + 1] = temp;
				}
			}
		}
	}

	private void sortByArrivalTime(Ticket[] tickets, boolean ascending) {
		for (int i = 0; i < tickets.length - 1; i++) {
			for (int j = 0; j < tickets.length - i - 1; j++) {
				int comparison = Long.compare(tickets[j].getArrivalTime(),
						tickets[j + 1].getArrivalTime());
				if ((ascending && comparison > 0) || (!ascending && comparison < 0)) {
					Ticket temp = tickets[j];
					tickets[j] = tickets[j + 1];
					tickets[j + 1] = temp;
				}
			}
		}
	}
}

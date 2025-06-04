public class Main {
	public static void main(String[] args) {
		String filename = "example_commands.csv";

		Command[] commands = FileIo.readCommands(filename);

		if (commands.length == 0) {
			System.out.println("No commands found or error reading file.");
			return;
		}

		CommandProcessor processor = new CommandProcessor();
		processor.processCommands(commands);
	}
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileIo {

	public static Command[] readCommands(String filename) {
		Command[] commands = new Command[100]; // Initial capacity
		int count = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (!line.isEmpty()) {
					Command command = parseCommand(line);
					if (command != null) {
						if (count >= commands.length) {
							commands = resizeArray(commands, commands.length * 2);
						}
						commands[count++] = command;
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Error reading file: " + e.getMessage());
			return new Command[0];
		}

		// Resize to actual size
		Command[] result = new Command[count];
		for (int i = 0; i < count; i++) {
			result[i] = commands[i];
		}

		return result;
	}

	private static Command parseCommand(String line) {
		String[] parts = line.split(",");

		if (parts.length == 0) {
			return null;
		}

		String type = parts[0].trim();

		switch (type.toLowerCase()) {
			case "new":
				if (parts.length >= 4) {
					return new Command(type, parts[1].trim(), parts[2].trim(), parts[3].trim());
				}
				break;
			case "resolve":
				return new Command(type);
			case "display":
				if (parts.length >= 2) {
					return new Command(type, parts[1].trim());
				} else {
					return new Command(type, "priority");
				}
			case "history":
				if (parts.length >= 2) {
					return new Command(type, parts[1].trim());
				} else {
					return new Command(type, (String) null);
				}
		}

		return null;
	}

	private static Command[] resizeArray(Command[] original, int newSize) {
		Command[] newArray = new Command[newSize];
		for (int i = 0; i < original.length; i++) {
			newArray[i] = original[i];
		}
		return newArray;
	}
}

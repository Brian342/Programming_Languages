import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Notes.java
 *
 * A simple interactive command-line tool for managing short, single-line
 * notes within a named "collection". Each collection is persisted to its
 * own text file (one note per line) in the current working directory.
 *
 * Usage:
 *   java Notes.java [COLLECTION]
 *   java Notes.java -h | --help
 */
public class notes {

    public static void main(String[] args) {
        if (args.length != 1 || args[0].equals("-h") || args[0].equals("--help")) {
            printUsage();
            return;
        }

        String collectionArg = args[0];
        String collectionTitle = toTitleCase(collectionArg);
        Path filePath = Paths.get(collectionArg + ".txt");

        List<String> notes = loadNotes(filePath);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the notes tool!");
        System.out.println();
        System.out.println("Collection: " + collectionTitle);

        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("Select operation:");
            System.out.println();
            System.out.println("1. Show notes");
            System.out.println("2. Add a note");
            System.out.println("3. Delete a note");
            System.out.println("4. Exit");
            System.out.print("$> ");

            if (!scanner.hasNextLine()) {
                // Input stream closed (e.g. piped input ran out) - exit cleanly.
                System.out.println();
                System.out.println("Bye!");
                break;
            }
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    showNotes(notes);
                    break;
                case "2":
                    addNote(scanner, notes, filePath, collectionTitle);
                    break;
                case "3":
                    deleteNote(scanner, notes, filePath, collectionTitle);
                    break;
                case "4":
                    System.out.println();
                    System.out.println("Bye!");
                    running = false;
                    break;
                default:
                    System.out.println();
                    System.out.println("Invalid option \"" + choice + "\". Please choose 1, 2, 3, or 4.");
                    System.out.println();
                    System.out.println("---");
            }
        }

        scanner.close();
    }

    // ---------------------------------------------------------------
    // CLI help
    // ---------------------------------------------------------------

    private static void printUsage() {
        System.out.println("Usage: java Notes.java [COLLECTION]");
        System.out.println();
        System.out.println("This tool allows users to manage short single-line notes within a collection.");
        System.out.println();
        System.out.println("Options:");
        System.out.println("-h, --help       Show this help message and exit");
        System.out.println("[COLLECTION]     The name of the collection to manage");
        System.out.println();
    }

    // ---------------------------------------------------------------
    // Formatting helpers
    // ---------------------------------------------------------------

    private static String toTitleCase(String input) {
        String[] parts = input.replace('_', ' ').replace('-', ' ').trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            if (part.isEmpty()) continue;
            if (sb.length() > 0) sb.append(' ');
            sb.append(Character.toUpperCase(part.charAt(0)));
            if (part.length() > 1) sb.append(part.substring(1).toLowerCase());
        }
        return sb.length() > 0 ? sb.toString() : input;
    }

    // ---------------------------------------------------------------
    // File / persistence handling
    // ---------------------------------------------------------------

    private static List<String> loadNotes(Path filePath) {
        List<String> notes = new ArrayList<>();
        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            } else {
                for (String line : Files.readAllLines(filePath)) {
                    if (!line.trim().isEmpty()) {
                        notes.add(line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Warning: could not access collection file (" + e.getMessage()
                    + "). Continuing with an empty in-memory collection; changes may not be saved.");
        }
        return notes;
    }

    private static void saveNotes(List<String> notes, Path filePath) {
        try {
            Files.write(filePath, notes);
        } catch (IOException e) {
            System.out.println("Error: could not save changes to file (" + e.getMessage() + ").");
        }
    }

    // ---------------------------------------------------------------
    // Operations
    // ---------------------------------------------------------------

    private static void showNotes(List<String> notes) {
        System.out.println();
        if (notes.isEmpty()) {
            System.out.println("No notes yet.");
        } else {
            System.out.println("Notes:");
            for (int i = 0; i < notes.size(); i++) {
                System.out.printf("%03d - %s%n", i + 1, notes.get(i));
            }
        }
        System.out.println();
        System.out.println("---");
    }

    private static void addNote(Scanner scanner, List<String> notes, Path filePath, String collectionTitle) {
        System.out.println();
        System.out.println("Enter the note:");
        System.out.print("$> ");
        String note = scanner.hasNextLine() ? scanner.nextLine().trim() : "";

        System.out.println();
        if (note.isEmpty()) {
            System.out.println("Note cannot be empty. Nothing was added.");
        } else {
            notes.add(note);
            saveNotes(notes, filePath);
            System.out.println("\"" + note + "\" added to " + collectionTitle);
        }
        System.out.println();
        System.out.println("---");
    }

    private static void deleteNote(Scanner scanner, List<String> notes, Path filePath, String collectionTitle) {
        System.out.println();
        if (notes.isEmpty()) {
            System.out.println("There are no notes to delete.");
            System.out.println();
            System.out.println("---");
            return;
        }

        System.out.println("Enter the number of the note to remove or 0 to cancel:");
        for (int i = 0; i < notes.size(); i++) {
            System.out.printf("%03d - %s%n", i + 1, notes.get(i));
        }
        System.out.print("$> ");
        String input = scanner.hasNextLine() ? scanner.nextLine().trim() : "0";

        System.out.println();
        int index;
        try {
            index = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("\"" + input + "\" is not a valid number. Nothing was deleted.");
            System.out.println();
            System.out.println("---");
            return;
        }

        if (index == 0) {
            System.out.println("Cancelled. Nothing was deleted.");
        } else if (index < 0 || index > notes.size()) {
            System.out.println("There is no note #" + index + ". Nothing was deleted.");
        } else {
            String removed = notes.remove(index - 1);
            saveNotes(notes, filePath);
            System.out.println("\"" + removed + "\" deleted from " + collectionTitle);
        }
        System.out.println();
        System.out.println("---");
    }
}
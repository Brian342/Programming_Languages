package notes;

// import packages
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Notes {
    private static final String HELP_TEXT =
    "Usage: Java Notes.java [COLLECTION]\n" +
    "This tool allows users to manage short single-line notes within a collection.\n"+
    "\n" +
    "Options:\n" +
    " -h, --help Show this help message and exit\n" +
    " [COLLECTION] The name of the collection to manage\n";

    public static void main(String[] args){
        if(args.length != 1 || args[0].equals("-h") || args[0].equals("--help")){
            System.out.println(HELP_TEXT);
            return;
        }

        String collection = args[0];
        String displayName = toDisplayName(collection);
        File collectionFile = new File(collection + ".txt");

        List<String> notes = loadNotes(collectionFile);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the notes tool!");
        System.out.println("Collection: " + displayName);

        boolean run = true;
        while (run){
            System.out.print("""
                Select Operation
                1. Show notes
                2. Add a note
                3. Delete a note
                4. Exit
                $>  """);

            String choice = scanner.hasNextLine() ? scanner.nextLine().trim() : "4";

            switch(choice) {
                case "1":
                    showNotes(notes);
                    break;
                case "2":
                    addNote(scanner, notes, collectionFile, displayName);
                    break;
                case "3":
                    deleteNote(scanner, notes, collectionFile, displayName);
                    break;
                case "4":
                    System.out.println("Good Bye!");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3 or 4");
            }

            if (run){
                System.out.println("---");
            }
        }

        scanner.close();
    }
    
    // changes words from package_list to package list
    private static String toDisplayName(String collection){
        String[] words = collection.replace('_', ' ').split(" ");
        StringBuilder sb = new StringBuilder();
        for(String word : words){
            if (word.isEmpty()) continue;
            if(sb.length() > 0) sb.append(' ');
            sb.append(Character.toUpperCase(word.charAt(0)));
            if(word.length() > 1){
                sb.append(word.substring(1));
            }
        }
        return sb.length() > 0 ? sb.toString() : collection;

    }
    // Loads notes from collection file and creates the file if it doesn't exist.
    private static List<String> loadNotes(File file){
        List<String> notes = new ArrayList<>();
        try{
            if (!file.exists()){
                boolean created = file.createNewFile();
                if (!created){
                    System.out.println("Warning: could not create collection file. Notes will not be saved.");

                }
                return notes;
            }
            List<String> lines = Files.readAllLines(file.toPath());
            for (String line: lines){
                if (!line.trim().isEmpty()){
                    notes.add(line);
                }
            }
        }catch (IOException e){
            System.out.println("Error loading collection file: " + e.getMessage());
        }
        return notes;
    }

    // Add the notes to disk and overwriting the file.
    private static boolean saveNotes(File file, List<String> notes){
        try (FileWriter writer = new FileWriter(file, false)) {
            for (String note : notes){
                writer.write(note);
                writer.write(System.lineSeparator());

            }
            return true;
        }catch (IOException e){
            System.out.println("Error saving notes: " + e.getMessage());
            return false;
        }
    }

    private static void showNotes(List<String> notes) {
        if (notes.isEmpty()) {
            System.out.println("No notes yet.");
            return;
        }
        System.out.println("Notes:");
        for(int i = 0; i < notes.size(); i++){
            System.out.printf("%03d - %s%n", i + 1, notes.get(i));
        }
    }

    private static void addNote(Scanner scanner, List<String> notes, File file, String displayName){
        System.out.println("Enter the note:");
        System.out.print("$> ");
        String note = scanner.hasNextLine() ? scanner.nextLine().trim() : "";

        if (note.isEmpty()){
            System.out.println("Note cannot be empty. Nothing was added");
            return;
        }
        if(note.contains("\n")){
            System.out.println("Notes must be single-line. Nothing was added");
            return;
        }

        notes.add(note);
        if (saveNotes(file, notes)){
            System.out.println("\"" + note + "\" added to " + displayName);
        }else {
            notes.remove(notes.size() - 1);
            System.out.println("Failded to save the note. it was not added.");
        }
    }

    private static void deleteNote(Scanner scanner, List<String> notes, File file, String displayName){
        if (notes.isEmpty()){
            System.out.println("No notes to delete. ");
            return;
        }

        System.out.println("Enter the number of the note to remove or 0 to cancel:");
        showNotes(notes);
        System.out.print("$> ");
        String input = scanner.hasNextLine() ? scanner.nextLine().trim() : "0";

        int index;
        try{
            index = Integer.parseInt(input);
        } catch (NumberFormatException e){
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        if(index == 0){
            System.out.println("Deletion cancelled.");
            return;
        }

        if(index< 0 || index > notes.size()){
            System.out.println("Invalid note number");
            return;
        }

        String removed = notes.get(index - 1);
        notes.remove(index - 1);

        if (saveNotes(file, notes)){
            System.out.println("\"" + removed + "\" deleted from " + displayName);
        }else{
            notes.add(index - 1, removed);
            System.out.println("Failed to save changes. The note was not deleted.");
        }
    }
}
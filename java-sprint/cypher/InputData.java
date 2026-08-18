public class InputData {
    private final int operation;
    private final int choice;
    private final String message;

    public InputData(int operation, int choice, String message) {
        this.message = message;
        this.operation = operation;
        this.choice = choice;
    }

    public int getOperation() {
        return operation;
    }

    public int getChoice() {
        return choice;
    }

    public String getMessage() {
        return message;
    }
}

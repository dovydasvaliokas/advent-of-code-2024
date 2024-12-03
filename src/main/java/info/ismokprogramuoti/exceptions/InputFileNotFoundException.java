package info.ismokprogramuoti.exceptions;

public class InputFileNotFoundException extends RuntimeException {
    public InputFileNotFoundException(String fileName, Throwable cause) {
        super("Input file + " + fileName + "  not found. Excepton message:\n", cause);
    }
}
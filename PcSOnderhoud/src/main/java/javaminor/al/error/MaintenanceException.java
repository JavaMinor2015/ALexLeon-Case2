package javaminor.al.error;

import javaminor.al.entities.concrete.MaintenanceAssignment;

/**
 * Created by alex on 11/24/15.
 */
public class MaintenanceException extends RuntimeException {
    private static final long serialVersionUID = 1090713797303666381L;
    private final MaintenanceAssignment assignment;

    /**
     * Create the exception.
     *
     * @param message the error message
     * @param assignment the assignment causing the exception
     */
    public MaintenanceException(final String message, final MaintenanceAssignment assignment) {
        super(message);
        this.assignment = assignment;
    }

    /**
     * Retrieve the assignment that caused this exception.
     *
     * @return the assignment that caused the exception.
     */
    public MaintenanceAssignment getAssignment() {
        return assignment;
    }
}

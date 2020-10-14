package seedu.duke.calendar.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected LocalDate by;
    protected String taskType;

    private static final String DEADLINE_FILE_SYMBOL = "D";
    private static final String SEPARATOR = "|";

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.taskType = "D";
    }

    /**
     * Returns a [D] icon to indicate task as a deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")) + ")";
    }

    @Override
    public String getDescription() {
        return "[D]" + super.toString();
    }

    @Override
    /** Returns the respective task type. */
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String printIntoFile() {
        return DEADLINE_FILE_SYMBOL + SEPARATOR + isDone + SEPARATOR + description + SEPARATOR + this.by;
    }

    @Override
    public LocalDate getDate() {
        return this.by;
    }

    @Override
    public LocalTime getTime() {
        return null;
    }
}

package seedu.duke;

import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.DoneCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.FindCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.PrintEventsCommand;
import seedu.duke.command.PrintProgressCommand;
import seedu.duke.command.PrintTasksCommand;
import seedu.duke.command.PrintTimelineCommand;

/**
 * Determines the type of command input by the user and calls for the respective command function.
 */
public class Parser {

    public static final String COMMAND_DELETE = "-";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_EXIT = "bye";
    public static final String COMMAND_FIND = "find";
    public static final String COMMAND_HELP = "help";
    public static final String COMMAND_PRINT_EVENTS = "print events";
    public static final String COMMAND_PRINT_TASKS = "print tasks";
    public static final String COMMAND_PRINT_TIMELINE = "print timeline";
    public static final String COMMAND_SHOW_PROGRESS = "print progress";

    public static Command handleUserInput(String userInput) {

        if (userInput.equals(COMMAND_EXIT)) {
            return new ExitCommand(userInput);
        } else if (userInput.equals(COMMAND_HELP)) {
            return new HelpCommand(userInput);
        } else if (userInput.equals(COMMAND_PRINT_TASKS)) {
            return new PrintTasksCommand(userInput);
        } else if (userInput.equals(COMMAND_PRINT_EVENTS)) {
            return new PrintEventsCommand(userInput);
        } else if (userInput.equals(COMMAND_PRINT_TIMELINE)) {
            return new PrintTimelineCommand(userInput);
        } else if (userInput.startsWith(COMMAND_DONE)) {
            return new DoneCommand(userInput);
        } else if (userInput.startsWith(COMMAND_DELETE)) {
            return new DeleteCommand(userInput);
        } else if (userInput.startsWith(COMMAND_FIND)) {
            return new FindCommand(userInput);
        } else if (userInput.startsWith(COMMAND_SHOW_PROGRESS)) {
            return new PrintProgressCommand(userInput);
        } else {
            /** An invalid command is catered for in AddCommand */
            return new AddCommand(userInput);
        }
    }
}

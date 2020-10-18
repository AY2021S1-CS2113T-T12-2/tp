package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.calendar.CalendarList;
import seedu.duke.calendar.event.Event;

import java.util.ArrayList;

public class ViewInfoCommand extends Command {

    public ViewInfoCommand(String command) {
        super(command);
    }

    /**
     * Prints the list of additional information of a particular event.
     *
     * @param calendarList the calendar list containing the event.
     * @param storage      not required.
     * @throws DukeException if the view info command is invalid.
     */
    @Override
    public void execute(CalendarList calendarList, Storage storage) throws DukeException {
        int eventNumber = 0;
        int calendarNumber;
        ArrayList<String> additionalInformation;
        try {
            eventNumber = Integer.parseInt(userInput.replace("/v", "").trim());
        } catch (Exception e) {
            throw new DukeException("invalid view info");
        }
        calendarNumber = CalendarList.convertEventNumberToCalendarNumber(eventNumber, calendarList);
        Event event = (Event) calendarList.getItem(calendarNumber);
        additionalInformation = event.getAdditionalInformation();
        Ui.printAdditionalInformation(additionalInformation, event);
    }
}

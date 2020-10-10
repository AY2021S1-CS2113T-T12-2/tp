package seedu.duke;

import seedu.duke.calendar.CalendarItem;
import seedu.duke.calendar.CalendarList;
import seedu.duke.calendar.task.Task;
import seedu.duke.calendar.task.Todo;
import seedu.duke.calendar.task.Deadline;
import seedu.duke.calendar.event.Activity;
import seedu.duke.calendar.event.Lecture;
import seedu.duke.calendar.task.Tutorial;
import seedu.duke.calendar.task.Lab;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Represents the local file used to store the task list data.
 */
public class Storage {

    private static final int TYPE = 0;
    private static final int DESCRIPTION = 2;
    private static final int DATE = 3;
    private static final int TIME = 4;
    private static final int IS_DONE = 1;
    public static final int VENUE = 5;
    private static ArrayList<CalendarItem> taskArrayList;
    private static String filePath;
    public static int countFileTasks = 0;

    /**
     * Constructor of the Storage class.
     * Initialize file f and file path, if f does not exists, creat a new file f.
     *
     * @param filePath the path that is the destination of the file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a new storage file if the file does not exists.
     *
     * @param output Storage file.
     */
    private static void createFile(File output) {
        try {
            if (output.exists()) {
                return;
            }
            if (!output.getParentFile().exists()) {
                output.getParentFile().mkdirs();
            }
            output.createNewFile();
        } catch (IOException e) {
            Ui.printFileCreateErrorMessage(e);
        }
    }

    /**
     * Write the data from taskList into file.
     *
     * @param calendarList the calendar list that the data is stored during running the program.
     */
    public static void writeToFile(CalendarList calendarList) {
        try {
            File output = new File(filePath);
            createFile(output);
            FileWriter fw = new FileWriter(output);
            taskArrayList = calendarList.getCalendarList();
            for (CalendarItem item : taskArrayList) {
                fw.write(item.printIntoFile() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing file");
        }
    }

    /**
     * Read data from file and store the data into the taskList.
     *
     * @param calendarList A taskList that store the data read from file.
     */
    public static void readFromFile(CalendarList calendarList) {
        LocalDate date;
        LocalTime time;
        File input = new File(filePath);
        createFile(input);
        Scanner sc = null;
        try {
            sc = new Scanner(input);
        } catch (FileNotFoundException e) {
            System.out.println("OOPs, file cannot be found.");
        }
        CalendarItem item = null;
        while (sc.hasNext()) {
            String[] taskInFile = sc.nextLine().split("\\|");
            switch (taskInFile[TYPE]) {
            case "T":
                item = new Todo(taskInFile[DESCRIPTION]);
                break;
            case "D":
                date = LocalDate.parse(taskInFile[DATE].trim());
                item = new Deadline(taskInFile[DESCRIPTION], date);
                break;
            case "E":
                date = LocalDate.parse(taskInFile[DATE].trim());
                time = LocalTime.parse(taskInFile[TIME].trim());
                item = new Activity(taskInFile[DESCRIPTION], date, time, taskInFile[VENUE]);
                break;
            case "LEC":
                date = LocalDate.parse(taskInFile[DATE].trim());
                time = LocalTime.parse(taskInFile[TIME].trim());
                item = new Lecture(taskInFile[DESCRIPTION], date, time, taskInFile[VENUE]);
                break;
            case "TUT":
                item = new Tutorial(taskInFile[DESCRIPTION], taskInFile[DATE], taskInFile[TIME]);
                break;
            case "LAB":
                item = new Lab(taskInFile[DESCRIPTION], taskInFile[DATE], taskInFile[TIME]);
                break;
            default:
                System.out.println("Invalid file command input");
            }
            countFileTasks++;
            if (taskInFile[IS_DONE].equals("true")) {
                if (item instanceof Task) {
                    ((Task) item).markAsDone();
                }
            }
            calendarList.addItem(item);
        }
    }
}

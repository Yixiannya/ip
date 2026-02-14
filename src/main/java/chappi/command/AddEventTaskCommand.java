package chappi.command;

import java.io.FileNotFoundException;

import chappi.exceptions.ChappiException;
import chappi.parser.Parser;
import chappi.storage.Storage;
import chappi.task.Task;
import chappi.tasklist.TaskList;
import chappi.ui.Ui;

/**
 * Represents the command to add an Event task
 * to the task list.
 */
public class AddEventTaskCommand extends Command {
    public AddEventTaskCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChappiException {
        Task eventTask = Parser.parseEvent(input);
        tasks.addTask(eventTask);
        try {
            storage.save(tasks);
        } catch (FileNotFoundException e) {
            throw new ChappiException(e.getMessage());
        }
        return ui.showNewTask(eventTask, tasks);
    }
}

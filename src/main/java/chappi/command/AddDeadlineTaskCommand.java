package chappi.command;

import java.io.FileNotFoundException;

import chappi.exceptions.ChappiException;
import chappi.parser.Parser;
import chappi.storage.Storage;
import chappi.task.Task;
import chappi.tasklist.TaskList;
import chappi.ui.Ui;

/**
 * Represents the command to add a Deadline task
 * to the task list.
 */
public class AddDeadlineTaskCommand extends Command {
    public AddDeadlineTaskCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChappiException {
        Task deadlineTask = Parser.parseDeadline(input);
        tasks.addTask(deadlineTask);
        try {
            storage.save(tasks);
        } catch (FileNotFoundException e) {
            throw new ChappiException(e.getMessage());
        }
        return ui.showNewTask(deadlineTask, tasks);
    }
}

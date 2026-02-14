package chappi.command;

import java.io.FileNotFoundException;

import chappi.exceptions.ChappiException;
import chappi.parser.Parser;
import chappi.storage.Storage;
import chappi.task.Task;
import chappi.tasklist.TaskList;
import chappi.ui.Ui;

/**
 * Represents the command to update a task
 * of a specific index in task list.
 */
public class UpdateTaskCommand extends Command {
    public UpdateTaskCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChappiException {
        Object[] info = Parser.parseUpdateTask(input);
        String indexString = (String) info[0];
        Task toBeUpdatedTask = Parser.parseTaskIndex(indexString, tasks);
        tasks.updateTask(toBeUpdatedTask, info);
        try {
            storage.save(tasks);
        } catch (FileNotFoundException e) {
            throw new ChappiException(e.getMessage());
        }
        return ui.showUpdatedTask(toBeUpdatedTask);
    }
}

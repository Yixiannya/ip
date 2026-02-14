package chappi.command;

import java.io.FileNotFoundException;

import chappi.exceptions.ChappiException;
import chappi.parser.Parser;
import chappi.storage.Storage;
import chappi.task.Task;
import chappi.tasklist.TaskList;
import chappi.ui.Ui;

/**
 * Represents the command to delete a task
 * of a specific index in task list.
 */
public class DeleteTaskCommand extends Command {
    public DeleteTaskCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChappiException {
        Task deletedTask = Parser.parseTaskIndex(Parser.parseDeleteTask(input), tasks);
        tasks.removeTask(deletedTask);
        try {
            storage.save(tasks);
        } catch (FileNotFoundException e) {
            throw new ChappiException(e.getMessage());
        }
        return ui.showDeletedTask(deletedTask);
    }
}

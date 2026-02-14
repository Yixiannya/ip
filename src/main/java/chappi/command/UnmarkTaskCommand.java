package chappi.command;

import java.io.FileNotFoundException;

import chappi.exceptions.ChappiException;
import chappi.parser.Parser;
import chappi.storage.Storage;
import chappi.task.Task;
import chappi.tasklist.TaskList;
import chappi.ui.Ui;

/**
 * Represents the command to unmark a task
 * of a specific index in task list.
 */
public class UnmarkTaskCommand extends Command {
    public UnmarkTaskCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChappiException {
        Task unmarkedTask = Parser.parseTaskIndex(Parser.parseUnmarkTask(input), tasks);
        unmarkedTask.markNotDone();
        try {
            storage.save(tasks);
        } catch (FileNotFoundException e) {
            throw new ChappiException("Save file missing while trying to save the list!");
        }
        return ui.showUnmarkedTask(unmarkedTask);
    }
}

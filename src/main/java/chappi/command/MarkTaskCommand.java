package chappi.command;

import java.io.FileNotFoundException;

import chappi.exceptions.ChappiException;
import chappi.parser.Parser;
import chappi.storage.Storage;
import chappi.task.Task;
import chappi.tasklist.TaskList;
import chappi.ui.Ui;

/**
 * Represents the command to mark a task
 * of a specific index in task list.
 */
public class MarkTaskCommand extends Command {
    public MarkTaskCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChappiException {
        Task markedTask = Parser.parseTaskIndex(Parser.parseMarkTask(this.input), tasks);
        markedTask.markDone();
        try {
            storage.save(tasks);
        } catch (FileNotFoundException e) {
            throw new ChappiException(e.getMessage());
        }
        return ui.showMarkedTask(markedTask);
    }
}

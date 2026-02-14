package chappi.command;

import chappi.storage.Storage;
import chappi.tasklist.TaskList;
import chappi.ui.Ui;

/**
 * Represents the command
 * show all tasks in the task list.
 */
public class ShowTaskListCommand extends Command {
    public ShowTaskListCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            return ui.showEmptyTaskList();
        } else {
            return ui.showTaskList(tasks);
        }
    }
}

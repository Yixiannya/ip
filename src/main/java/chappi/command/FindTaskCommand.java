package chappi.command;

import chappi.exceptions.ChappiException;
import chappi.parser.Parser;
import chappi.storage.Storage;
import chappi.tasklist.TaskList;
import chappi.ui.Ui;

/**
 * Represents the command to find a task in task list.
 */
public class FindTaskCommand extends Command {
    public FindTaskCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChappiException {
        String keyword = Parser.parseFindTask(input);
        TaskList foundTasks = tasks.findMatchingTasks(keyword);
        return ui.showFoundTasks(foundTasks);
    }
}

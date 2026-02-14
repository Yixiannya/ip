package chappi.command;

import chappi.exceptions.ChappiException;
import chappi.storage.Storage;
import chappi.tasklist.TaskList;
import chappi.ui.Ui;

/**
 * Represents the command to close the program.
 */
public class ExitCommand extends Command {
    public ExitCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChappiException {
        return "bye";
    }
}

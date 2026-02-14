package chappi.command;

import chappi.exceptions.ChappiException;
import chappi.storage.Storage;
import chappi.tasklist.TaskList;
import chappi.ui.Ui;

/**
 * Represents the various commands
 * that can be executed by the chatbot.
 */
public abstract class Command {
    protected String input;

    public Command(String input) {
        this.input = input;
    }

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws ChappiException;
}

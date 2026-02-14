package chappi.ui;

import chappi.command.Command;
import chappi.exceptions.ChappiException;
import chappi.parser.Parser;
import chappi.storage.Storage;
import chappi.tasklist.TaskList;

/**
 * Main class in which the Chappi chatbot is run on.
 */
public class Chappi {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Chappi object that uses the specified file path for storage.
     * @param filePath File path to be used for storage.
     */
    public Chappi(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (ChappiException e) {
            ui.showChappiException(e);
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message.
     * Message is dependent on what command the user includes in their input.
     * @param input String representation of user's command to Chappi.
     * @return String representation of Chappi's response to the user.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            assert c != null;
            return c.execute(tasks, ui, storage);
        } catch (ChappiException e) {
            return ui.showChappiException(e);
        }
    }

    /**
     * Gives a String representation of the greeting Chappi says.
     * @return String representation of greeting.
     */
    public String showGreeting() {
        return ui.showGreeting();
    }
}

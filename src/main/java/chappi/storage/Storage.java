package chappi.storage;

import chappi.parser.Parser;
import chappi.task.Task;
import chappi.chappiExceptions.ChappiException;
import chappi.tasklist.TaskList;

import java.io.*;

/**
 * Represents the memory related interactions of Chappi.
 * Mainly related to saving and loading the task list
 * that the user tells Chappi to create.
 * Contains a file path represented by a String.
 * File path indicates where the data of Chappi
 * is stored and loaded from.
 */
public class Storage {
    private String filePath;

    /**
     * Creates a storage object that
     * saves and loads to the specified file path.
     * @param filePath String representing a path directory.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes the specified task list as a String into a text file
     * and places the text file in the contained directory.
     * @param taskList Given task list to be saved.
     * @throws FileNotFoundException if specified file path does not exist.
     */
    public void save(TaskList taskList) throws FileNotFoundException {
        File saveFile = new File(this.filePath);
        saveFile.getParentFile().mkdirs();

        try (PrintWriter printWriter = new PrintWriter(saveFile)) {
            for (int i = 0; i < taskList.size(); i++) {
                Task t = taskList.getTask(i);
                printWriter.write(t.toFileString() + "\n");
            }
        }
    }

    /**
     * Finds a text file indicated in the given file path
     * and creates a task list based on the written tasks.
     * If no file is found, an empty task list is returned.
     * @return Task list object that represents the loaded text file.
     * @throws ChappiException if specified file has wrong formatting or does not exist.
     */
    public TaskList load() throws ChappiException {
        TaskList savedList = new TaskList();
        File saveFile = new File(this.filePath);

        if (!saveFile.exists()) {
            return savedList;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(saveFile))) {
            String line = br.readLine();
            while (line != null) {
                savedList.addTask(Parser.parseSavedTask(line));
                line = br.readLine();
            }
        } catch (IllegalArgumentException | IOException e) {
            throw new ChappiException(e.getMessage());
        }

        return savedList;
    }
}

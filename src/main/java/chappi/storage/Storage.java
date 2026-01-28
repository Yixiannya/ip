package chappi.storage;

import chappi.parser.Parser;
import chappi.task.Task;
import chappi.chappiExceptions.ChappiException;
import chappi.tasklist.TaskList;

import java.io.*;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
        } catch (IOException e) {
            throw new ChappiException(e.toString());
        }

        return savedList;
    }
}

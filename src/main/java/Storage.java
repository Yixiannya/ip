import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(ArrayList<Task> taskList) throws FileNotFoundException {
        File saveFile = new File(this.filePath);
        saveFile.getParentFile().mkdirs();

        try (PrintWriter printWriter = new PrintWriter(saveFile)) {
            for (Task t : taskList) {
                printWriter.write(t.toFileString() + "\n");
            }
        }
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> savedList = new ArrayList<>();
        File saveFile = new File(this.filePath);

        if (!saveFile.exists()) {
            return savedList;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(saveFile))) {
            String line = br.readLine();
            while (line != null) {
                savedList.add(Parser.parseTask(line));
                line = br.readLine();
            }
        }

        return savedList;
    }
}

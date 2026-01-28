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
                savedList.add(parseTask(line));
                line = br.readLine();
            }
        }

        return savedList;
    }

    private String trimPrefix(String input, String prefix) {
        return input.substring(prefix.length());
    }

    private Task parseTask(String line) {
        String[] splitLine = line.split(" \\| ");
        String taskType = splitLine[0];
        boolean isDone = splitLine[1].equals("1");
        String description = splitLine[2];

        switch (taskType) {
        case "T":
            return new ToDo(description, isDone);
        case "D":
            LocalDate deadlineEndDate = LocalDate.parse(trimPrefix(splitLine[3], "End: "));
            return new Deadline(description, isDone, deadlineEndDate);
        case "E":
            LocalDate eventStartDate = LocalDate.parse(trimPrefix(splitLine[3], "Start: "));
            LocalDate eventEndDate = LocalDate.parse(trimPrefix(splitLine[4], "End: "));
            return new Event(description, isDone, eventStartDate, eventEndDate);
        default:
            throw new IllegalArgumentException("Unknown task type");
        }
    }
}

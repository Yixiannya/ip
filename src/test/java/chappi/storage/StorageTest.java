package chappi.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import chappi.exceptions.ChappiException;
import chappi.tasklist.TaskList;

public class StorageTest {
    class TaskListStub extends TaskList {
        @Override
        public String toString() {
            return "      1.[D][X] a (by: Dec 20 2025)\n"
                    + "      2.[D][ ] b (by: Feb 2 2020)\n"
                    + "      3.[E][ ] c (from: Nov 11 1111 to: Dec 12 1212)\n"
                    + "      4.[T][ ] d\n";
        }
    }

    @Test
    public void testLoad_success() throws ChappiException {
        Storage storage = new Storage("./data/testLoad.txt");
        assertEquals(new TaskListStub().toString(), storage.load().toString());
    }

    @Test
    public void testLoad_fileNotFound() throws ChappiException {
        Storage storage = new Storage("./data/no.txt");
        assertEquals("", storage.load().toString());
    }

    @Test
    public void testLoad_fileIncorrectTaskFormat() {
        Storage storage = new Storage("./data/testLoad2.txt");
        try {
            TaskList result = storage.load();
            fail();
        } catch (ChappiException e) {
            assertEquals("There's been a problem.\n"
                    + "Unknown task type\n",
                    e.getMessage());
        }
    }
}

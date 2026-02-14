package chappi.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import chappi.exceptions.ChappiException;
import chappi.storage.Storage;
import chappi.task.Task;
import chappi.task.ToDo;
import chappi.tasklist.TaskList;
import chappi.ui.Ui;

// Used ChatGPT to help improve this test.
// ChatGPT suggested the following stubs for testing.
public class UpdateTaskCommandTest {
    class StorageStub extends Storage {
        protected boolean saveCalled = false;

        public StorageStub() {
            super("dummy.txt");
        }

        @Override
        public void save(TaskList tasks) throws FileNotFoundException {
            saveCalled = true;
        }
    }

    class FailingStorageStub extends Storage {
        public FailingStorageStub() {
            super("dummy.txt");
        }

        @Override
        public void save(TaskList tasks) throws FileNotFoundException {
            throw new FileNotFoundException();
        }
    }


    class UiStub extends Ui {
        @Override
        public String showUpdatedTask(Task task) {
            return "updated!";
        }
    }

    class TaskListStub extends TaskList {
        protected boolean updateCalled = false;
        protected Task receivedTask = null;
        protected Object[] receivedInfo = null;

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public int size() {
            return 999;
        }

        @Override
        public Task getTask(int index) {
            return new ToDo("dummy task");
        }

        @Override
        public void updateTask(Task task, Object[] info) {
            updateCalled = true;
            receivedTask = task;
            receivedInfo = info;
        }
    }


    @Test
    public void execute_success() throws Exception {
        UpdateTaskCommand cmd =
                new UpdateTaskCommand("update 1 /desc hello");

        TaskListStub tasks = new TaskListStub();
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();

        String result = cmd.execute(tasks, ui, storage);

        assertTrue(tasks.updateCalled, "TaskList.updateTask should be called");
        assertTrue(storage.saveCalled, "Storage.save should be called");
        assertEquals("updated!", result);
    }

    @Test
    public void execute_exception() throws Exception {
        UpdateTaskCommand cmd =
                new UpdateTaskCommand("update 1 /desc hello");

        TaskListStub tasks = new TaskListStub();
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();

        String result = cmd.execute(tasks, ui, storage);

        assertTrue(tasks.updateCalled, "TaskList.updateTask should be called");
        assertTrue(storage.saveCalled, "Storage.save should be called");
        assertEquals("updated!", result);
    }
}

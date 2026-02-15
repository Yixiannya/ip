# Chappi User Guide

Chappi is a simple chatbot with the ability to record and save tasks in a list!

![Screenshot of the GUI of Chappi.](/docs/Ui.png)

## Setting up the app

Prerequisites: Have Java 17 installed on your computer

1. Download the latest `.jar` file from here.
2. Copy the file to the folder you want to use as the home folder for Chappi.
3. Open a command terminal of your choice, `cd` into the folder the jar file is in, and use the `java -jar chappi-1.0.jar` command to run it.
4. The GUI of Chappi should show up.
5. Type a command into the text box and press enter or click the `Send` button to send the command.
   A list of available commands can be found below.

## Usage of Chappi
Add a deadline task to the task list. Chappi will record the task in a list for you!

Example: `deadline Assignment 5 /by 2026-02-23`

Chappi will tell you that it has recorded the task into its list, and display it to you as such:
```
Sure, added:
    [D][ ] Assignment 5 (by: Feb 23 2026)
There's 1 tasks in your list now.
```

## Commands
* `list`
* `find KEYPHRASE`
* `todo TASK_DESCRIPTION`
* `deadline TASK_DESCRIPTION /by END_DATE`
* `event TASK_DESCRIPTION /from START_DATE /to END_DATE`
* `mark INDEX`
* `unmark INDEX`
* `delete INDEX`
* `update INDEX [/desc TASK_DESCRIPTION] [/from START_DATE] [/to END_DATE]`
* `bye`

More details of the commands can be found below

## Features
> **Note:**
> * All command keywords have to be entered in **lower case only**.
> * Each `/` tag must be seperated from the words with a space.
> * All dates must be in the `YYYY-MM-DD` format.
> * Words in all upper case such as `KEYWORD` are parameters to be supplied by the user.
> * Words in square brackets like `[DATE]` are optional parameters.

### Showing the entire list: `list`
Shows the entire list that is saved.
Format: `list`

### Finding a specific task: `find`
Shows all tasks that match the given description
Format: `find KEYPHRASE`
* `find` command only searches for a single keyphrase.
* The command can match the phrase partially. e.g. searching for `an` will bring up tasks like `banana`.
* The command is case sensitive. e.g. `sleep` will not find tasks labelled `Sleep`.
* Spaces factor into the search keyphrase. e.g. searching `drink water` will not find tasks labelled only `drink` or only `water`. 
* Only the description of a task is searched.

### Adding a ToDo task: `todo`
Adds a ToDo type task to the list.
A ToDo task is a task without a start or end date.
Format: `todo TASK_DESCRIPTION`
* Spaces are allowed in the task description
* Only a single task is created per `todo` command.

Example command: `todo Have a bath`

### Adding a Deadline task: `deadline`
Adds a Deadline type task to the list.
A Deadline task is a task with only an end date.
Format: `deadline TASK_DESCRIPTION /by END_DATE`
* Spaces are allowed in the task description.
* Only a single task is created per `event` command.

Example command: `deadline Take a nap /by 2026-02-15`

### Adding an Event task: `event`
Adds an Event type task to the list.
An Event task is a task with both a start and end date.
Format: `event TASK_DESCRIPTION /from START_DATE /to END_DATE`
* Spaces are allowed in the task description
* Only a single task is created per `event` command.
* The order of `/from` and `/to` can be reversed.

Examples:
* `event Do homework /from 2026-01-31 /to 2026-02-15`
* `event Build a robot /to 2026-02-20 /from 2026-02-12`

### Marking a task as done: `mark`
Marks a task in the task list as done.
Format: `mark INDEX`
* `INDEX` refers to the index of the task in the list, which can be found using `list`
* `INDEX` cannot be negative or zero.
* `INDEX` cannot exceed the size of the list.
* Only a single task can be marked per command.
* Marking an already marked task will not change its marking.

### Unmarking a task: `unmark`
Unmarks a task in the task list, setting it to not done.
Format: `unmark INDEX`
* `INDEX` refers to the index of the task in the list, which can be found using `list`
* `INDEX` cannot be negative or zero.
* `INDEX` cannot exceed the size of the list.
* Only a single task can be unmarked per command.
* Unmarking an already unmarked task will not change its marking.

### Deleting a task: `delete`
Deletes a task in the task list.
Format: `delete INDEX`
* `INDEX` refers to the index of the task in the list, which can be found using `list`
* `INDEX` cannot be negative or zero.
* `INDEX` cannot exceed the size of the list.
* Only a single task can be deleted per command.

### Updating a task: `update`
Updates a task with the specified information.
Format: `update INDEX [/desc TASK_DESCRIPTION] [/from START_DATE] [/to END_DATE]`
* `INDEX` refers to the index of the task in the list, which can be found using `list`
* `INDEX` cannot be negative or zero.
* `INDEX` cannot exceed the size of the list.
* Only a single task can be updated per command.
* The command needs at least one `/desc`, `/from` or `/to` to update the task.
* The order of the 3 fields to update do not have to be in order.
* If the specified task does not have a start date or end date (like a ToDo task), the `/from` or `/to` fields will be ignored.

Examples: 
* `update 3 /desc Drink some water /from 2026-09-10 /to 2026-09-11`
* `update 1 /to 2026-10-10 /desc Take a walk /from 2026-10-08`

### Exiting the program: `bye`
Quits the program and closes the window.
Format: `bye`

### Saving the task list
There is no command to save manually.
Saving is automatically done after any command that modifies a task or the list.

### Manually editing the task list
The task list is saved under `./data/chappiSave.txt`.
Users can modify the list directly, but it is **not recommended**.
If any manual modifications are made, keep in mind to follow the task formatting in the file.
Any errors in formatting can cause the entire list to be discarded, or cause errors such that Chappi cannot run.

# Yow User Guide

Yow is a desktop application for managing tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

## Table of Contents
- [Quick Start](#quick-start)
- [Features](#features)
    - [Adding a Task](#adding-a-task)
    - [Viewing Help: `help`](#viewing-help-help)
    - [Listing All Tasks: `list`](#listing-all-tasks-list)
    - [Marking a Task as Done: `mark`](#marking-a-task-as-done-mark)
    - [Unmarking a Task: `unmark`](#unmarking-a-task-unmark)
    - [Deleting a Task: `delete`](#deleting-a-task-delete)
    - [Finding Tasks: `find`](#finding-tasks-find)
    - [Exiting the Program: `bye`](#exiting-the-program-bye)
- [Saving the Data](#saving-the-data)
- [Editing the Data File](#editing-the-data-file)
- [FAQ](#faq)
- [Command Summary](#command-summary)

---

## Quick Start

1. **Ensure** you have **Java 17 or above** installed on your computer.
2. **Download the latest `YowBot` JAR file** from the [releases page](https://github.com/FooNicholas/ip/releases).
3. **Copy the JAR file** to the folder you want to use as the home folder for Yow.
4. **Open a command terminal**, navigate (`cd`) into the folder containing the JAR file, and run:
   ```
   java -jar YowBot.jar
   ```
5. **Start entering commands**. Some example commands you can try:
    - `list` : Lists all tasks.
    - `todo Read a book` : Adds a To-Do task.
    - `delete 2` : Deletes the 2nd task.
    - `bye` : Exits the chatbot.

6. **Refer to the [Features](#features)** section below for details of each command.

---

## Features

**Notes about the command format:**

- Words in `UPPER_CASE` are parameters to be supplied by the user.
    - e.g., in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo Read a book`.
- Parameters must be in the specified order.
- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `bye`) will be ignored.

&nbsp;

### Adding a Task

Adds a task to Yow. There are four types of tasks:

1. **To-Do Task**
   ```
   todo Project3
   ```

2. **Deadline Task**
   ```
   deadline Submit report /by 2025-02-28 1800
   ```

3. **Event Task**
   ```
   event Meeting /from 2025-03-01 1400 /to 2025-03-01 1600
   ```

4. **Specific Duration Task**
   ```
   within Project work /from 2025-02-15 1000 /to 2025-02-20 1800
   ```

&nbsp;

### Viewing Help: `help`

Shows a message explaining how to access the help page.

Format: `help`

&nbsp;

### Listing All Tasks: `list`

Shows a list of all tasks in Yow.

Format: `list`

&nbsp;

### Marking a Task as Done: `mark`

Marks the specified task as completed.

Format: `mark INDEX`

&nbsp;

### Unmarking a Task: `unmark`

Marks the specified task as not completed.

Format: `unmark INDEX`

&nbsp;

### Deleting a Task: `delete`

Deletes the specified task from Yow.

Format: `delete INDEX`

&nbsp;

### Finding a Task: `find`

Finds tasks whose descriptions contain the given keyword.

Format: `find KEYWORD`

&nbsp;

### Exiting the Program: `bye`

Closes the YowBot window and exits the program.

Format: `bye`

---

## Saving the Data

Yow automatically saves tasks to `yow.txt` in the `data/` directory. Tasks will be loaded when you restart the chatbot.

## Editing the Data File

Tasks are stored in `yow.txt`. Advanced users can manually edit this file.

Incorrect edits can cause unexpected behavior.

## FAQ

**Q: How do I transfer my tasks to another computer?**  
A: Install Yow on the other computer and copy the `yow.txt` file to its `data/` folder.

## Command Summary

| Action            | Format                                             |
|-------------------|----------------------------------------------------|
| Add To-Do Task    | `todo DESCRIPTION`                                 |
| Add Deadline Task | `deadline DESCRIPTION /by DATE_TIME`               |
| Add Event Task    | `event DESCRIPTION /from START_TIME /to END_TIME`  |
| Add Duration Task | `within DESCRIPTION /from START_TIME /to END_TIME` |
| List Tasks        | `list`                                             |
| Mark Task as Done | `mark INDEX`                                       |
| Unmark Task       | `unmark INDEX`                                     |
| Delete Task       | `delete INDEX`                                     |
| Find Task         | `find KEYWORD`                                     |
| View Help         | `help`                                             |
| Exit              | `bye`                                              |

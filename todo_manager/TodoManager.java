import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task {
    private String description;
    private boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return (completed ? "[x] " : "[ ] ") + description;
    }
}

public class TodoManager {
    private static List<Task> tasks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getUserChoice();
            handleChoice(choice);
            running = choice != 5;
        }
    }

    private static void displayMenu() {
        System.out.println("\nTo-Do Manager");
        System.out.println("1. Add Task");
        System.out.println("2. View Tasks");
        System.out.println("3. Update Task");
        System.out.println("4. Delete Task");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        int choice = -1;
        try {
            choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character
        } catch (Exception e) {
            scanner.nextLine(); // consume the invalid input
            System.out.println("Invalid choice. Please try again.");
        }
        return choice;
    }

    private static void handleChoice(int choice) {
        switch (choice) {
            case 1:
                addTask();
                break;
            case 2:
                viewTasks();
                break;
            case 3:
                updateTask();
                break;
            case 4:
                deleteTask();
                break;
            case 5:
                System.out.println("Exiting To-Do Manager...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void addTask() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        Task task = new Task(description);
        tasks.add(task);
        System.out.println("Task added successfully.");
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            System.out.println("Your tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    private static void updateTask() {
        viewTasks();
        if (!tasks.isEmpty()) {
            System.out.print("Enter the task number to update: ");
            int taskNumber = getUserChoice();
            if (taskNumber >= 1 && taskNumber <= tasks.size()) {
                Task task = tasks.get(taskNumber - 1);
                System.out.print("Enter the new task description: ");
                String newDescription = scanner.nextLine();
                task.setDescription(newDescription);
                System.out.println("Task updated successfully.");
            } else {
                System.out.println("Invalid task number.");
            }
        }
    }

    private static void deleteTask() {
        viewTasks();
        if (!tasks.isEmpty()) {
            System.out.print("Enter the task number to delete: ");
            int taskNumber = getUserChoice();
            if (taskNumber >= 1 && taskNumber <= tasks.size()) {
                tasks.remove(taskNumber - 1);
                System.out.println("Task deleted successfully.");
            } else {
                System.out.println("Invalid task number.");
            }
        }
    }
}
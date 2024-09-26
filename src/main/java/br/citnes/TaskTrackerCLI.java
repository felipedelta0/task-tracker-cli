package br.citnes;

public class TaskTrackerCLI {
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Invalid arguments. Use commands add, list, update, delete and mark.");
            return;
        }
        
        TaskCommand command = TaskCommand.fromString(args[0]);

        TaskManager taskManager = new TaskManager();

        switch (command) {
            case ADD -> taskManager.addTask(args[1]);
            case UPDATE -> System.out.println("Not implemented.");
            case DELETE -> System.out.println("Not implemented.");
            case IN_PROGRESS -> System.out.println("Not implemented.");
            case DONE -> System.out.println("Not implemented.");
            case LIST -> {
                Status status = null;
                if (args.length > 1) {
                    status = Status.fromString(args[1]);
                }
                taskManager.listTasks(status);
            }
        }

        taskManager.saveTasks();
    }
}
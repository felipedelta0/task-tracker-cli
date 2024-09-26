package br.citnes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private final ArrayList<Task> taskList = new ArrayList<>();
    private final Path FILE_PATH = Path.of("tasks.json");
    private static int nextTaskId = 1;
    
    public TaskManager() {
        loadTasks();
    }

    private void loadTasks() {
        List<String> lines = new ArrayList<>();

        if (!Files.exists(FILE_PATH)){
            return;
        }

        try {
            lines = Files.readAllLines(FILE_PATH);
        } catch (IOException e) {
            System.out.println("Error opening file " + e.getMessage());
        }

        for (String line : lines) {
            if (line.equals("[") || line.equals("]")) {
                continue;
            }

            if (!(line.startsWith("{") && (line.endsWith("}") || line.endsWith("},")))) {
                continue;
            }

            String[] parts = line.replaceAll("\"", "")
                    .replaceAll("\\{", "")
                    .replaceAll("},", "")
                    .replaceAll("}", "")
                    .split(", ");

            Task task = new Task(parts);
            taskList.add(task);
            if (task.getId() <= nextTaskId) {
                nextTaskId = task.getId() + 1;
            }
        }
    }

    public void addTask(String description) {
        taskList.add(new Task(nextTaskId, description));
        nextTaskId += 1;
    }

    public void listTasks(Status status) {
        if (status == null) {
            taskList.forEach(System.out::println);
            return;
        }

        taskList.stream()
                .filter(task -> task.getStatus().equals(status))
                .forEach(System.out::println);
    }

    public void saveTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");

        for (int i = 0; i < taskList.size(); i++) {
            sb.append(taskList.get(i).toJson());
            if (i != taskList.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }
        sb.append("]");

        try {
            Files.writeString(FILE_PATH, sb.toString());
        } catch (IOException e) {
            System.out.println("Couldn't save tasks");
        }
    }
}

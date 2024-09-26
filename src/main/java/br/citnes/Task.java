package br.citnes;

import java.time.LocalDateTime;

public class Task {
    private int id;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.status = Status.TODO;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Task(String[] parts) {
        for (String part : parts) {
            String[] prop = part.split(": ");
            switch (prop[0]) {
                case "id" -> this.id = Integer.parseInt(prop[1]);
                case "description" -> this.description = prop[1];
                case "status" -> this.status = Status.valueOf(prop[1]);
                case "createdAt" -> this.createdAt = LocalDateTime.parse(prop[1]);
                case "updatedAt" -> this.updatedAt = LocalDateTime.parse(prop[1]);
            }
        }
    }

    public String toJson() {
        return "{" +
                "\"id\": " + id + ", " +
                "\"description\": \"" + description + "\", " +
                "\"status\": \"" + status.toString() + "\", " +
                "\"createdAt\": \"" + createdAt + "\", " +
                "\"updatedAt\": \"" + updatedAt + "\"" +
                "}";
    }

    public int getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}

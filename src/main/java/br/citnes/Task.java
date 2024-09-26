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
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"id\": ").append(id).append(", ");
        sb.append("\"description\": \"").append(description).append("\", ");
        sb.append("\"status\": \"").append(status.toString()).append("\", ");
        sb.append("\"createdAt\": \"").append(createdAt).append("\", ");
        sb.append("\"updatedAt\": \"").append(updatedAt).append("\"");
        sb.append("}");
        return sb.toString();
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

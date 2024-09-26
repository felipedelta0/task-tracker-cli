package br.citnes;

public enum TaskCommand {
    ADD ("add"),
    UPDATE ("update"),
    DELETE ("delete"),
    IN_PROGRESS ("mark-in-progress"),
    DONE ("mark-done"),
    LIST ("list");

    private final String command;

    private TaskCommand(String cmd) {
        this.command = cmd;
    }

    public static TaskCommand fromString(String command) {
        for (TaskCommand cmd : TaskCommand.values()) {
            if (cmd.command.equals(command)) {
                return cmd;
            }
        }
        
        throw new IllegalArgumentException("Command not found.");
    }
}

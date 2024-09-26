package br.citnes;

public enum Status {
    TODO ("todo"),
    IN_PROGRESS ("in-progress"),
    DONE ("done");

    private final String status;

    private Status(String status) {
        this.status = status;
    }

    public static Status fromString(String statusString) {
        for (Status status : Status.values()) {
            if (status.status.equals(statusString)) {
                return status;
            }
        }

        throw new IllegalArgumentException("Status not found.");
    }
}

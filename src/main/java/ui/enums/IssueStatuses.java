package ui.enums;

public enum IssueStatuses {
    NEW,
    OPEN,
    IN_PROGRESS,
    FIXED,
    IN_TESTING,
    REOPENED,
    DONE,
    RESOLVED,
    CLOSED;

    @Override
    public String toString(){
        return super.toString().replaceAll("_", " ");
    }


}

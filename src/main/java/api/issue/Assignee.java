package api.issue;

public class Assignee implements IssueInterface {
    public String name;

    public Assignee(String id) {
        this.name = id;
    }
}

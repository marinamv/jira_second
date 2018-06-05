package api.issue;

public class Issue implements IssueInterface {
    public Fields fields;

    public Issue(Fields fields) {
        this.fields = fields;
    }
}

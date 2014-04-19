package model;

public class FormRecord {
    private String name;
    private String label;
    private Object data;

    public FormRecord(String name, String label, Object data) {
        this.name = name;
        this.label = label;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public Object getData() {
        return this.data;
    }
}

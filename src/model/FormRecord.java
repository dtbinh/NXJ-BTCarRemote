package model;

public class FormRecord {
    private String name;
    private String label;

    public FormRecord(String name, String label) {
        this.name = name;
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }
}

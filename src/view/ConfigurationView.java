package view;

import javax.swing.JComponent;

import model.Configuration;
import model.FormRecord;
import util.PropertyAccessor;

public class ConfigurationView {
    private Configuration config;

    public ConfigurationView(Configuration config) {
        this.config = config;
    }

    public void render(JComponent component) {
        FormRecord[] formRecords = { new FormRecord("speed", "Speed", config),
                new FormRecord("rotationStep", "Rotation step", config),
                new FormRecord("originAngle", "Origin angle", config) };

        FormView formView = new FormView(formRecords, new PropertyAccessor());

        formView.render(component);
    }
}

package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Field;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

import model.FormRecord;
import util.PropertyAccessor;

public class FormView {
    private FormRecord[] formRecords;
    private final PropertyAccessor propertyAccessor;

    public FormView(FormRecord[] formRecords, PropertyAccessor propertyAccessor) {
        this.formRecords = formRecords;
        this.propertyAccessor = propertyAccessor;
    }

    public void render(JComponent component) {
        component.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;
        for (FormRecord record : this.formRecords) {
            gbc.gridx = 0;
            JLabel label = new JLabel(record.getLabel());
            component.add(label, gbc);

            gbc.gridx = 1;
            component.add(this.createFromObject(record.getData(), record.getName()), gbc);

            gbc.gridy++;
        }
    }

    private JComponent createFromObject(final Object o, final String fieldName) {
        JComponent component = null;

        try {
            Field field = o.getClass().getDeclaredField(fieldName);
            String value = this.propertyAccessor.getValue(fieldName, o);
            switch (field.getType().toString()) {
                case "int":
                    JFormattedTextField textField = new JFormattedTextField();
                    textField.setPreferredSize(new Dimension(100, 20));
                    textField.setValue(value);
                    textField.addPropertyChangeListener("value", new PropertyChangeListener() {

                        @Override
                        public void propertyChange(PropertyChangeEvent evt) {
                            FormView.this.propertyAccessor.setValue(fieldName, o, evt.getNewValue()
                                    .toString());
                        }
                    });

                    component = textField;
                    break;
                default:
                    throw new Exception("Unknown type " + field.getType().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return component;
    }
}

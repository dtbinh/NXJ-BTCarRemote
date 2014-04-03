package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

import model.Configuration;

public class ConfigurationView {
    private Configuration config;

    public ConfigurationView(Configuration config) {
	this.config = config;
    }

    public void render(JComponent component) {
	component.setLayout(new GridBagLayout());
	GridBagConstraints gbc = new GridBagConstraints();
	gbc.gridy = 0;
	gbc.insets = new Insets(0, 0, 0, 10);
	gbc.fill = GridBagConstraints.HORIZONTAL;
	this.addOption("Rotation step", this.config.getRotationStep(), component, gbc);
	this.addOption("Speed", this.config.getSpeed(), component, gbc);
	this.addOption("Origin angle", this.config.getOriginAngle(), component, gbc);
    }

    private void addOption(String name, int value, JComponent container, GridBagConstraints gbc) {
	gbc.gridx = 0;
	JLabel keyLabel = new JLabel(name);
	container.add(keyLabel, gbc);

	gbc.gridx = 1;
	JFormattedTextField key = new JFormattedTextField();
	key.setValue(value);
	container.add(key, gbc);

	gbc.gridy++;
    }
}

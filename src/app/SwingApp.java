package app;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import lejos.nxt.remote.NXTCommand;
import lejos.pc.comm.NXTCommandConnector;
import listener.MovementKeysListener;
import model.Configuration;
import view.ConfigurationView;

import communication.NxjConnector;

import controller.MovementController;
import dispatcher.KeyDispatcher;

public class SwingApp implements Runnable {

    @Override
    public void run() {
        int windowWidth = 800;
        int windowHeight = 600;

        final JFrame jFrame = new JFrame("BT Car remote");
        jFrame.getContentPane().setLayout(new GridBagLayout());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(windowWidth, windowHeight);
        jFrame.setVisible(true);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;

        JPanel configPanel = new JPanel();
        ConfigurationView configView = new ConfigurationView(new Configuration());
        configView.render(configPanel);
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        jFrame.getContentPane().add(configPanel, gbc);
        JButton connectButton = new JButton("Connect");
        connectButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    NxjConnector connector = new NxjConnector();
                    NXTCommandConnector.setNXTCommand(new NXTCommand(connector
                            .createLcpConnection()));
                    KeyboardFocusManager kfm = KeyboardFocusManager
                            .getCurrentKeyboardFocusManager();
                    kfm.addKeyEventDispatcher(new KeyDispatcher(new MovementKeysListener(
                            new MovementController())));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Cannot connect to NXT brick");
                    ex.printStackTrace();
                }
            }
        });
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.gridx = 1;
        gbc.gridy = 1;
        jFrame.getContentPane().add(connectButton, gbc);
    }
}

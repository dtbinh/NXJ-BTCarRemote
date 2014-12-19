package app;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

    private final Configuration configuration = new Configuration();

    @Override
    public void run() {
        int windowWidth = 800;
        int windowHeight = 600;

        final JFrame jFrame = new JFrame("BT Car remote");
        jFrame.getContentPane().setLayout(new GridBagLayout());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(windowWidth, windowHeight);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;

        JPanel configPanel = new JPanel();
        ConfigurationView configView = new ConfigurationView(this.configuration);
        configView.render(configPanel);
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        jFrame.getContentPane().add(configPanel, gbc);

        JButton connectButton = new JButton("Connect");
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.gridx = 1;
        gbc.gridy = 1;
        jFrame.getContentPane().add(connectButton, gbc);

        final JLabel status = new JLabel();
        status.setText("Unconnected");
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.gridx = 0;
        gbc.gridy = 1;
        jFrame.getContentPane().add(status, gbc);

        jFrame.setVisible(true);

        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(new KeyDispatcher(new KeyListener() {

                    @Override
                    public void keyTyped(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_ENTER:
                                jFrame.requestFocus();
                                break;
                        }
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }
                }));

        connectButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                status.setText("Connecting...");
                jFrame.requestFocus();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            NxjConnector connector = new NxjConnector();
                            NXTCommandConnector.setNXTCommand(new NXTCommand(connector
                                    .createLcpConnection()));
                            jFrame.addKeyListener(new MovementKeysListener(new MovementController(
                                    configuration)));
                            status.setText("Connected");
                        } catch (Exception ex) {
                            status.setText("Connection failed");
                            JOptionPane.showMessageDialog(null, "Cannot connect to NXT brick");
                            ex.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
}

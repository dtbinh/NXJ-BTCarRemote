package app;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lejos.nxt.remote.NXTCommand;
import lejos.pc.comm.NXTCommandConnector;
import listener.MovementKeysListener;
import model.Configuration;
import view.ConfigurationView;

import communication.NxjConnector;

import controller.MovementController;

public class SwingApp implements Runnable {

    @Override
    public void run() {
	int windowWidth = 800;
	int windowHeight = 600;

	JFrame jFrame = new JFrame("BT Car remote");
	jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	jFrame.setSize(windowWidth, windowHeight);
	jFrame.setVisible(true);

	JPanel configPanel = new JPanel();
	ConfigurationView configView = new ConfigurationView(new Configuration());
	;
	configView.render(configPanel);

	jFrame.getContentPane().add(configPanel);

	try {
	    NxjConnector connector = new NxjConnector();
	    NXTCommandConnector.setNXTCommand(new NXTCommand(connector.createLcpConnection()));
	    jFrame.addKeyListener(new MovementKeysListener(new MovementController()));
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
}

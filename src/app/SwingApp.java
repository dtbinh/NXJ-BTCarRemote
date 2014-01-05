package app;

import java.awt.GridBagLayout;

import javax.swing.JFrame;

import lejos.nxt.remote.NXTCommand;
import lejos.pc.comm.NXTCommandConnector;
import listener.MovementKeysListener;
import communication.NxjConnector;

public class SwingApp implements Runnable {

    @Override
    public void run() {
	int windowWidth = 800;
	int windowHeight = 600;

	JFrame jFrame = new JFrame("BT Car remote");
	jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	jFrame.setSize(windowWidth, windowHeight);
	jFrame.setLayout(new GridBagLayout());
	jFrame.setVisible(true);

	try {
	    NxjConnector connector = new NxjConnector();
	    NXTCommandConnector.setNXTCommand(new NXTCommand(connector
		    .createLcpConnection()));
	    jFrame.addKeyListener(new MovementKeysListener());
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

}

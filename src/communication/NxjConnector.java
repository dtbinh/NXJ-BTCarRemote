package communication;

import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;

public class NxjConnector {
    public NXTComm createLcpConnection() throws Exception {
	NXTComm nxtComm = NXTCommFactory
		.createNXTComm(NXTCommFactory.BLUETOOTH);
	NXTInfo nxtInfo[] = nxtComm.search("NXT");

	if (nxtInfo.length == 0) {
	    throw new Exception("NXT was not found");
	}

	if (!nxtComm.open(nxtInfo[0], NXTComm.LCP)) {
	    throw new Exception("Couldn't connect to NXT");
	}

	return nxtComm;
    }
}

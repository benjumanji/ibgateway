package io.artfuldodge.ibgateway;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		args = new String[1];
		args[0] = "~/.ib/ib.ini";
		String path = args[0].replace("~",System.getProperty("user.home"));
		BasicIniReader ini = new BasicIniReader(new File(path));
		IbGatewayListener listener = new IbGatewayListener(ini);
		Toolkit.getDefaultToolkit().addAWTEventListener(listener, AWTEvent.WINDOW_EVENT_MASK);
		String settings = "~/.ib/settings".replace("~",System.getProperty("user.home"));
		String[] args1 = { settings };
		ibgateway.GWClient.main(args1);

	}

}

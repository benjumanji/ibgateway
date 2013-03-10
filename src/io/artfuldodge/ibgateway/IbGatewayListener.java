package io.artfuldodge.ibgateway;

import java.awt.AWTEvent;
import java.awt.Window;
import java.awt.event.AWTEventListener;
import java.awt.event.WindowEvent;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class IbGatewayListener implements AWTEventListener {
	private final BasicIniReader _ini;
	
	private AtomicReference<JFrame> _mainWindow = new AtomicReference<JFrame>();
	
	public IbGatewayListener(BasicIniReader ini) {
		_ini = ini;
	}
	
	@Override
	public void eventDispatched(AWTEvent event) {
        int eventID = event.getID();
        if (eventID != WindowEvent.WINDOW_OPENED &&
                eventID != WindowEvent.WINDOW_ACTIVATED) return;

        Window window =((WindowEvent) event).getWindow();
        String title = Utils.getWindowTitle(window);
        
        if (title.equals("IB Gateway")) {
        	if (setMainWindow((JFrame)window))
        		login();
        } 
        else {
        	System.out.println("lol");
        }
	}
	
	private JFrame getMainWindow() {
		return _mainWindow.get();
	}
	
	private Boolean setMainWindow(JFrame window) {
		// if it isn't null we don't want to set it.
		return _mainWindow.compareAndSet(null, window);
	}
	
	private void login() {
		JFrame window = getMainWindow();
		JRadioButton rb = Utils.findRadioButton(window, "IB API");
		rb.doClick();
		JTextField tfUser = Utils.findTextField(window, 0);
		tfUser.setText(_ini.getUser());
		JTextField tfPass = Utils.findTextField(window, 1);
		tfPass.setText(_ini.getPassword());
		JButton btnLogin = Utils.findButton(window, "Login");
		btnLogin.setEnabled(true);
		btnLogin.doClick();
	}
}

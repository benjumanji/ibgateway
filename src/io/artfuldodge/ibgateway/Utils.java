package io.artfuldodge.ibgateway;

import java.awt.Component;
import java.awt.Container;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Utils {

	private Utils() {}
	
    static JTextField findTextField(Container container, int ith) {
    	
    	Component[] components = container.getComponents();
		for (Component component : components) {
			if (component instanceof JTextField && ith > 0) {
				ith--;
			} else if (component instanceof JTextField && ith == 0) {
				return (JTextField) component;
			} else if (component instanceof Container) {
				JTextField tf = findTextField((Container) component, ith);
				if (tf != null) {
					return tf;
				}
			}
		}
		return null;
    }
    
    public static boolean titleEquals(Window window, String text) {
        String title = getWindowTitle(window);
        return (title != null && title.equals(text));
    }
    
    public static String getWindowTitle(Window window) {
        String title = null;
        if (window instanceof JDialog) {
            title = ((JDialog)window).getTitle();
        } else  if (window instanceof JFrame) {
            title =((JFrame)window).getTitle();
        }
        return title;
    }
    
    static JButton findButton(Container container,
            String text) {
    	Component[] components = container.getComponents();

    	for (Component component : components) {
    		if (component instanceof JButton) {
    			JButton button = (JButton) component;
    			if (button.getText().equals(text)) {
    				return button;
    			}
    		} else if (component instanceof Container) {
    			JButton button = findButton((Container) component, text);
    			if (button != null) {
    				return button;
    			}
    		}
    	}

    	return null;
    }
    
    static JRadioButton findRadioButton(Container container,
            String text) {
    	Component[] components = container.getComponents();

    	for (Component component : components) {
    		if (component instanceof JRadioButton) {
    			JRadioButton button = (JRadioButton) component;
    			if (button.getText().equals(text)) {
    				return button;
    			}
    		} else if (component instanceof Container) {
    			JRadioButton button = findRadioButton((Container) component,
    					text);
    			if (button != null) {
    				return button;
    			}
    		}
    	}
    	
    	return null;
    }
    
}

package io.artfuldodge.ibgateway;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BasicIniReader {
	private static final Pattern title = Pattern.compile("\\[\\w+\\]");
	
	private final Scanner _scanner;
	private final String[] _securityTokenGrid = new String[224];
	private String _user;
	private String _password;
	
	public BasicIniReader(File file) throws FileNotFoundException {
		_scanner = new Scanner(file);
		scanFile();
	}
	
	public String getUser() {
		return _user;
	}
	
	public String getPassword() {
		return _password;
	}
	
	public String getTokenAt(int index) {
		return _securityTokenGrid[index - 1];
	}
	
	private void scanFile() {
		String title;
		while ((title = readTitle()) != null) {
			if (title.equals("[user]"))
				_user = _scanner.next();
			if (title.equals("[password]"))
				_password = _scanner.next();
		}
		
	}
	
	private String readTitle() {
		try {
			String ret = _scanner.next(title);
			return ret;
		}
		catch(NoSuchElementException e) {
			return null;
		}
	}
}

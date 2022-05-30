//package Project_2;

//Pedro Arredondo
//Houston Taylor
//Emmanuel Kpandeyenge
//Abiraam Nagarajan

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Input {
	private File file;
	private Scanner input;
	private String data;
	private int currentIndex;
	private String currentToken;//this variable is not needed. it is just used to check how the scanner is working
	
	public Input(String filename) {
		
		try {
			file = new File(filename);
			input = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Error when opening file");
			e.printStackTrace();
		}
		data = "";
		while (input.hasNextLine()) {
			data += input.nextLine() + "\n";
		}
		currentIndex = 0;
		System.out.println(data);
		currentToken = "";
	}
	
	public boolean hasNext() {
		if(currentIndex < data.length()) {
			return true;
		}
		else {
			return false;
		}
	}
	public char getNext() {
		if(hasNext()) {
			currentToken += data.charAt(currentIndex);
			//System.out.println("getNext: " + data.charAt(currentIndex));
			return data.charAt(currentIndex++);
		}
		else {
			//System.out.println("getNext: none");
			return '\0';
		}
		
	}

	public void decreaseIndex() {
		currentIndex--;
		currentToken = currentToken.substring(0, currentToken.length() - 1);//removing character
	}
	public int size() {
		return data.length();
	}
	

	public String getCurrentToken() {
		String s = currentToken;
		currentToken = "";
		s.replaceAll("\\s", "");//removing spaces
		return s;
	}
	public String getCurrentTokenAndDontReset() {
		return currentToken.replaceAll("\\s", "");
	}

}
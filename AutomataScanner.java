//package Project_2;

//Pedro Arredondo
//Houston Taylor
//Emmanuel Kpandeyenge
//Abiraam Nagarajan

import java.util.ArrayList;
import java.util.Scanner;

public class AutomataScanner {
	static Input input;// = new Input("project2.txt");
	static ArrayList<String> tokens = new ArrayList<String>();
	static ArrayList<String> tokenText = new ArrayList<String>();
	
	public static void main(String[] args) {
		
		start();
		System.out.println(tokens);
		System.out.println(tokenText);
	}
	
	public static void start() {
		char c = input.getNext();
		if(c == ' ' || c == '\t' || c == '\n') {
			start();
		}
		else if(c == '/') {
			div();
		}
		else if(c == '(') {
			lparen();
		}
		else if(c == ')') {
			rparen();
		}
		else if(c == '+') {
			plus();
		}
		else if(c == '-') {
			minus();
		}
		else if(c == '*') {
			times();
		}
		else if(c == ':') {
			assign();
		}
		else if(c == '.') {
			dot();
		}
		else if(Character.isDigit(c)) {
			integerNumber();
		}
		else if(Character.isLetter(c)) {
			id();
		}
		else if(input.hasNext() == false) {
			
		}
		else {
			System.out.println("error");
		}
	}
	
	public static void div() {
		char c = input.getNext();
		if(c == '/') {
			singleLineComment();
		}
		else if(c == '*') {
			multiLineComment();
		}
		else {
			if(input.hasNext()) {
				input.decreaseIndex();//TODO
			}
			tokens.add("div");
			tokenText.add(input.getCurrentToken());
			//System.out.println("currentToken: " + tokenText.get(tokenText.size() - 1) + " type: div");
			start();
		}
	}
	public static void singleLineComment() {
		char c = input.getNext();
		if(c == '\n') {
			//tokenText.add(input.getCurrentToken());
			//System.out.println("currentToken: " + tokenText.get(tokenText.size() - 1) + " type: div");
			start();
		}
		else {
			singleLineComment();
		}
	}
	public static void multiLineComment() {
		char c = input.getNext();
		if(c == '*') {
			multiLineCommentClose();
		}
		else {
			multiLineComment();
		}
	}
	public static void multiLineCommentClose() {
		char c = input.getNext();
		if(c == '*') {
			multiLineCommentClose();
		}
		else if(!(c == '/' || c == '*')) {
			multiLineComment();
		}
		else {
			//tokenText.add(input.getCurrentToken());
			//System.out.println("currentToken: " + tokenText.get(tokenText.size() - 1) + " type: div");
			start();
		}
	}
	
	public static void lparen() {
		tokens.add("lparen");
		tokenText.add(input.getCurrentToken());
		//System.out.println("currentToken: " + tokenText.get(tokenText.size() - 1) + " type: lparen");
		start();
	}
	public static void rparen() {
		tokens.add("rparen");
		tokenText.add(input.getCurrentToken());
		//System.out.println("currentToken: " + tokenText.get(tokenText.size() - 1) + " type: rparen");
		start();
	}
	public static void plus() {
		tokens.add("plus");
		tokenText.add(input.getCurrentToken());
		//System.out.println("currentToken: " + tokenText.get(tokenText.size() - 1) + " type: plus");
		start();
	}
	public static void minus() {
		tokens.add("minus");
		tokenText.add(input.getCurrentToken());
		//System.out.println("currentToken: " + tokenText.get(tokenText.size() - 1) + " type: minus");
		start();
	}
	public static void times() {
		tokens.add("times");
		tokenText.add(input.getCurrentToken());
		//System.out.println("currentToken: " + tokenText.get(tokenText.size() - 1) + " type: times");
		start();
	}
	public static void assign() {
		char c = input.getNext();
		if(c == '=') {
			tokens.add("assign");
			tokenText.add(input.getCurrentToken());
			//System.out.println("currentToken: " + tokenText.get(tokenText.size() - 1) + " type: assign");
			start();
		}
		else {
			System.out.println("Error");
		}
	}
	public static void dot() {
		char c = input.getNext();
		if(Character.isDigit(c)) {
			decimalNumber();
		}
		else {
			System.out.println("Error");
		}
	}
	public static void integerNumber() {
		char c = input.getNext();
		if(Character.isDigit(c)) {
			integerNumber();
		}
		else if(c == '.') {
			decimalNumber();
		}
		else {
			if(input.hasNext()) {
				input.decreaseIndex();//TODO
			}
			tokens.add("number");
			tokenText.add(input.getCurrentToken());
			//System.out.println("currentToken: " + tokenText.get(tokenText.size() - 1) + " type: number");
			start();
		}
	}
	public static void decimalNumber() {
		char c = input.getNext();
		if(Character.isDigit(c)) {
			decimalNumber();
		}
		else {
			if(input.hasNext()) {
				input.decreaseIndex();//TODO
			}
			tokens.add("number");
			tokenText.add(input.getCurrentToken());
			//System.out.println("currentToken: " + tokenText.get(tokenText.size() - 1) + " type: number");
			start();
		}
	}
	
	public static void id() {
		char c = input.getNext();
		if(input.getCurrentTokenAndDontReset().equals("read")) {//TODO
			tokens.add("read");
			tokenText.add(input.getCurrentToken());
			//System.out.println("currentToken: " + tokenText.get(tokenText.size() - 1) + " type: read");
			start();
		}
		else if(input.getCurrentTokenAndDontReset().equals("write")) {
			tokens.add("write");
			tokenText.add(input.getCurrentToken());
			//System.out.println("currentToken: " + tokenText.get(tokenText.size() - 1) + " type: write");
			start();
		}
		else if(Character.isLetter(c) || Character.isDigit(c)) {
			id();
		}
		else {
			if(input.hasNext()) {
				input.decreaseIndex();//TODO
			}
			
			tokens.add("id or keyword");
			tokenText.add(input.getCurrentToken());
			//System.out.println("currentToken: " + tokenText.get(tokenText.size() - 1) + " type: id or keyword");
			start();
		}
	}

}

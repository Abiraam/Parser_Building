//package Project_2;

//Pedro Arredondo
//Houston Taylor
//Emmanuel Kpandeyenge
//Abiraam Nagarajan

import java.util.ArrayList;
import java.util.Scanner;
public class Parser {
	public static void main(String args[]) {
		//String fileName;
		//Scanner keyboard = new Scanner(System.in);
		//System.out.print("Enter the filename: ");
		//fileName = keyboard.nextLine();
		AutomataScanner.input = new Input(args[0]);
		//AutomataScanner.input = new Input("project2.txt");
		AutomataScanner.start();//Scanning the input file to get the tokens
		AutomataScanner.tokens.add("$$");
		AutomataScanner.tokenText.add("$$");
		System.out.println(AutomataScanner.tokens);
		System.out.println(AutomataScanner.tokenText + "\n\n");
		//System.out.println("\n\nStart of parser begins now: \n\n");
		program(0);
	}
	
	public static boolean match(String expected, int tabs) {
		String token = AutomataScanner.tokens.get(0);
		if(expected.equals(AutomataScanner.tokens.remove(0))) {
			print(tabs, "<" + token + ">");//TODO arraylist of tokens and tokens types to print ids and numbers
			print(tabs + 1, AutomataScanner.tokenText.remove(0).replaceAll("\\n", ""));
			print(tabs, "</" + token + ">");
			return true;
		}
		else {
			print(tabs, "Error");
			System.exit(0);
			return false;
		}
	}
	public static void program(int tabs) {
		print(tabs, "<Program>");
		String token = AutomataScanner.tokens.get(0);
		if(token.equals("id or keyword") || token.equals("read") || token.equals("write") || token.equals("$$")) {
			stmt_list(tabs + 1);
			match("$$", tabs + 1);
		}
		print(tabs, "</Program>");
		
	}
	public static void stmt_list(int tabs) {
		print(tabs, "<stmt_list>");
		String token = AutomataScanner.tokens.get(0);
		if(token.equals("id or keyword") || token.equals("read") || token.equals("write")) {
			stmt(tabs + 1);
			stmt_list(tabs + 1);
		}
		else if(token.equals("$$")) {
			
		}
		else {
			print(tabs, "Error");
			System.exit(0);
		}
		print(tabs, "</stmt_list>");
	}
	public static void stmt(int tabs) {
		print(tabs, "<stmt>");
		String token = AutomataScanner.tokens.get(0);
		if(token.equals("id or keyword")) {
			match("id or keyword", tabs + 1);
			match("assign", tabs + 1);
			expr(tabs + 1);
		}
		else if(token.equals("read")) {
			match("read", tabs + 1);
			match("id or keyword", tabs + 1);
		}
		else if(token.equals("write")) {
			match("write", tabs + 1);
			expr(tabs + 1);
		}
		else {
			print(tabs, "Error");
			System.exit(0);
		}
		print(tabs, "</stmt>");
		
	}
	public static void expr(int tabs) {
		print(tabs, "<expr>");
		String token = AutomataScanner.tokens.get(0);
		if(token.equals("id or keyword") || token.equals("number") || token.equals("lparen")) {
			term(tabs + 1);
			term_tail(tabs + 1);
		}
		else {
			print(tabs, "Error");
			System.exit(0);
		}
		print(tabs, "</expr>");
	}
	public static void term_tail(int tabs) {
		print(tabs, "<term_tail>");
		String token = AutomataScanner.tokens.get(0);
		if(token.equals("plus") || token.equals("minus")) {
			add_op(tabs + 1);
			term(tabs + 1);
			term_tail(tabs + 1);
		}
		else if(token.equals("rparen") || token.equals("read") || token.equals("write") || token.equals("id or keyword") || token.equals("$$")) {

		}
		else {
			print(tabs, "Error");
			System.exit(0);
		}
		print(tabs, "</term_tail>");
	}
	public static void term(int tabs) {
		print(tabs, "<term>");
		String token = AutomataScanner.tokens.get(0);
		if(token.equals("id or keyword") || token.equals("number") || token.equals("lparen")) {
			factor(tabs + 1);
			factor_tail(tabs + 1);
		}
		else {
			print(tabs, "Error");
			System.exit(0);
		}
		print(tabs, "</term>");
	}
	public static void factor_tail(int tabs) {
		print(tabs, "<factor_tail>");
		String token = AutomataScanner.tokens.get(0);
		if(token.equals("times") || token.equals("div")) {
			mult_op(tabs + 1);
			factor(tabs + 1);
			factor_tail(tabs + 1);
		}
		else if(token.equals("plus") || token.equals("minus") || token.equals("rparen") || token.equals("id or keyword") || token.equals("read") || token.equals("write") || token.equals("$$")) {

		}
		else {
			print(tabs, "Error");
			System.exit(0);
		}
		print(tabs, "</factor_tail>");
	}
	public static void factor(int tabs) {
		print(tabs, "<factor>");
		String token = AutomataScanner.tokens.get(0);
		if(token.equals("id or keyword")) {
			match("id or keyword", tabs + 1);
		}
		else if(token.equals("number")) {
			match("number", tabs + 1);
		}
		else if(token.equals("lparen")) {
			match("lparen", tabs + 1);
			expr(tabs + 1);
			match("rparen", tabs + 1);
		}
		else {
			print(tabs, "Error");
			System.exit(0);
		}
		print(tabs, "</factor>");
	}
	public static void add_op(int tabs) {
		print(tabs, "<add_op>");
		String token = AutomataScanner.tokens.get(0);
		if(token.equals("plus")) {
			match("plus", tabs + 1);
		}
		else if(token.equals("minus")) {
			match("minus", tabs + 1);
		}
		else {
			print(tabs, "Error");
			System.exit(0);
		}
		print(tabs, "</add_op>");
	}
	public static void mult_op(int tabs) {
		print(tabs, "<mult_op>");
		String token = AutomataScanner.tokens.get(0);
		if(token.equals("times")) {
			match("times", tabs + 1);
		}
		else if(token.equals("div")) {
			match("div", tabs + 1);
		}
		else {
			print(tabs, "Error");
			System.exit(0);
		}
		print(tabs, "</mult_op>");
	}
	
	public static void print(int tabs, String text) {
		String temp = "";
		for(int i = 0; i < tabs; i++) {
			temp += "  ";
		}
		System.out.println(temp + text);
	}
}





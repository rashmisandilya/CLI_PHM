package com.db.phm;

import java.util.Scanner;

public class Helper {
	
	public static void printMessage(String message){
		System.out.println(message);
	}
	public static String getConsoleValue() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();
		if (str.isEmpty()) {
			printMessage("\n No value entered. Please try again..\n");
			return getConsoleValue();
		}
		return str;		
	}
	
	public static void printErrorMessage(){
		System.out.println("Something went wrong!!! Please try again...");
	}
}

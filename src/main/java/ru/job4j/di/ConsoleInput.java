package ru.job4j.di;

import java.util.Scanner;

public class ConsoleInput {

	private Scanner sc;

	public ConsoleInput() {
		sc = new Scanner(System.in);
	}

	public String askStr(String question) {
		System.out.print(question);
		return sc.nextLine();
	}

	public int askInt(String question) {
		return Integer.parseInt(askStr(question));
	}
}
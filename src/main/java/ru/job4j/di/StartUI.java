package ru.job4j.di;

public class StartUI {

	private Store store;
	private ConsoleInput input;

	public StartUI(Store store, ConsoleInput input) {
		this.store = store;
		this.input = input;
	}

	public void add(String value) {
		store.add(value);
	}

	public void addFromConsole(String prompt) {
		add(input.askStr(prompt));
	}

	public void print() {
		for (String value : store.getAll()) {
			System.out.println(value);
		}
	}
}
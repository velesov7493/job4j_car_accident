package ru.job4j.di;

public class Main {

	public static void main(String[] args) {
		Context context = Context.getInstance();
		context.reg(Store.class);
		context.reg(ConsoleInput.class);
		context.reg(StartUI.class);
		StartUI ui = context.get(StartUI.class);
		String prompt = "Введите имя: ";
		ui.addFromConsole(prompt);
		ui.addFromConsole(prompt);
		ui.print();
	}
}

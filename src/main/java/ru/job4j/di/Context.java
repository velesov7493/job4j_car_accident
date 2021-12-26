package ru.job4j.di;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Context {

	private static final Context INSTANCE = new Context();
	private Map<String, Object> els = new HashMap<>();

	private Context() { }

	public static Context getInstance() {
		return INSTANCE;
	}

	public void reg(Class cl) {
		Constructor[] constructors = cl.getDeclaredConstructors();
		if (constructors.length > 1) {
			throw new IllegalStateException(
				"У класса много конструкторов : " + cl.getCanonicalName()
			);
		}
		Constructor con = constructors[0];
		List<Object> args = new ArrayList<>();
		for (Class arg : con.getParameterTypes()) {
			if (!els.containsKey(arg.getCanonicalName())) {
				throw new IllegalStateException(
					"Объект не найден в контексте : " + arg.getCanonicalName()
				);
			}
			args.add(els.get(arg.getCanonicalName()));
		}
		try {
			els.put(cl.getCanonicalName(), con.newInstance(args.toArray()));
		} catch (Exception e) {
			throw new IllegalStateException(
				"Невозможно создать объект класса : " + cl.getCanonicalName(), e
			);
		}
	}

	public <T> T get(Class<T> inst) {
		return (T) els.get(inst.getCanonicalName());
	}
}

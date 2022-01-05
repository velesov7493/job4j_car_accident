package ru.job4j.accident.models;

import java.util.Objects;

public class AccidentType {

	private int id;
	private String name;

	public static AccidentType of(String name) {
		AccidentType type = new AccidentType();
		type.name = name;
		return type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		AccidentType that = (AccidentType) o;
		return id == that.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}

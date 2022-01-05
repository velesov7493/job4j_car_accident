package ru.job4j.accident.models;

import java.util.Objects;

public class Accident {

	private int id;
	private String name;
	private String text;
	private String address;
	private AccidentType type;

	public static Accident of(String aName, String aText, String aAddress, AccidentType aType) {
		Accident result = new Accident();
		result.name = aName;
		result.text = aText;
		result.address = aAddress;
		result.type = aType;
		return result;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public AccidentType getType() {
		return type;
	}

	public void setType(AccidentType type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Accident accident = (Accident) o;
		return id == accident.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}

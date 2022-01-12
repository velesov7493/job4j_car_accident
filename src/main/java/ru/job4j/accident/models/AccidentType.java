package ru.job4j.accident.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tz_accident_types")
public class AccidentType {

	@Id
	@SequenceGenerator(
		name = "typesIdSeq",
		sequenceName = "tz_accident_types_id_seq",
		allocationSize = 1
	)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "typesIdSeq")
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

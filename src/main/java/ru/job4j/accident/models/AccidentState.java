package ru.job4j.accident.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tz_accident_stats")
public class AccidentState {

	@Id
	@SequenceGenerator(
		name = "statsIdSeq",
		sequenceName = "tz_accident_stats_id_seq",
		allocationSize = 1
	)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "statsIdSeq")
	private int id;
	private String name;

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
		AccidentState that = (AccidentState) o;
		return id == that.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}

package ru.job4j.accident.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tz_rules")
public class Rule {

    @Id
    @SequenceGenerator(
            name = "rulesIdSeq",
            sequenceName = "tz_rules_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rulesIdSeq")
    private int id;
    private String name;

    public static Rule of(String aName) {
        Rule result = new Rule();
        result.name = aName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rule rule = (Rule) o;
        return id == rule.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

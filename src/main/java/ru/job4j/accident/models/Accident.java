package ru.job4j.accident.models;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "tj_accidents")
public class Accident {

	@Id
	@SequenceGenerator(
		name = "accidentsIdSeq",
		sequenceName = "tj_accidents_id_seq",
		allocationSize = 1
	)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accidentsIdSeq")
	private int id;
	private String name;
	@Column(name = "description")
	private String text;
	private String address;
	private String solution;
	private String actor1Number;
	private String actor2Number;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = false)
	private Date created;
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_type", nullable = false)
	private AccidentType type;
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_author", nullable = false)
	private User author;
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_inspector")
	private User inspector;
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_state", nullable = false)
	private AccidentState state;
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.LAZY)
	@JoinTable(
		name = "tr_accidents_rules",
		joinColumns = @JoinColumn(name = "id_accident"),
		inverseJoinColumns = @JoinColumn(name = "id_rule")
	)
	private Set<Rule> rules;
	@OneToMany(
		mappedBy = "accident",
		cascade = {CascadeType.MERGE, CascadeType.DETACH},
		fetch = FetchType.LAZY
	)
	private Set<Media> attachments;

	public Accident() {
		rules = new HashSet<>();
		attachments = new HashSet<>();
	}

	public static Accident of(String aName, String aText, String aAddress) {
		Accident result = new Accident();
		result.name = aName;
		result.text = aText;
		result.address = aAddress;
		return result;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AccidentState getState() {
		return state;
	}

	public void setState(AccidentState state) {
		this.state = state;
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

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getActor1Number() {
		return actor1Number;
	}

	public void setActor1Number(String actor1Number) {
		this.actor1Number = actor1Number;
	}

	public String getActor2Number() {
		return actor2Number;
	}

	public void setActor2Number(String actor2Number) {
		this.actor2Number = actor2Number;
	}

	public AccidentType getType() {
		return type;
	}

	public void setType(AccidentType type) {
		this.type = type;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public User getInspector() {
		return inspector;
	}

	public void setInspector(User inspector) {
		this.inspector = inspector;
	}

	public Set<Rule> getRules() {
		return rules;
	}

	public void addRule(Rule value) {
		rules.add(value);
	}

	public void removeRule(Rule value) {
		rules.remove(value);
	}

	public void setRules(Collection<Rule> value) {
		rules.clear();
		rules.addAll(value);
	}

	public Set<Media> getAttachments() {
		return attachments;
	}

	public void addAttachment(Media value) {
		value.setAccident(this);
		attachments.add(value);
	}

	public void removeAttachment(Media value) {
		attachments.remove(value);
	}

	public void setAttachments(Collection<Media> value) {
		attachments.clear();
		attachments.addAll(value);
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
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

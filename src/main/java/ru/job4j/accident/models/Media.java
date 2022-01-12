package ru.job4j.accident.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tj_media")
public class Media {

	@Id
	@SequenceGenerator(
		name = "mediaIdSeq",
		sequenceName = "tj_media_id_seq",
		allocationSize = 1
	)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mediaIdSeq")
	private int id;
	@Column(name = "is_video")
	private short isVideo;
	private String mimeType;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_accident")
	private Accident accident;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isVideo() {
		return isVideo == 1;
	}

	public void setIsVideo(boolean value) {
		isVideo = value ? (short) 1 : 0;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public Accident getAccident() {
		return accident;
	}

	public void setAccident(Accident accident) {
		this.accident = accident;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Media media = (Media) o;
		return id == media.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}

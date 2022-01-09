package ru.job4j.accident.models;

import java.util.Objects;

public class Media {

	private int id;
	private short isVideo;
	private String mimeType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public short getIsVideo() {
		return isVideo;
	}

	public void setIsVideo(short isVideo) {
		this.isVideo = isVideo;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
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

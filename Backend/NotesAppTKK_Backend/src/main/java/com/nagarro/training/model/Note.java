package com.nagarro.training.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "notes")
public class Note {

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUser() {
		return user != null ? user.getUsername() : null;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 500)
	private String content;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private Users user;

	@Column(nullable = false)
	private long timestamp;

	public Note() {
		// Default constructor
	}

	public Note(String content, Users user, long timestamp) {
		this.content = content;
		this.user = user;
		this.timestamp = timestamp;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Note note = (Note) o;
		return id.equals(note.id) && content.equals(note.content) && user.equals(note.user)
				&& timestamp == note.timestamp;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, content, user, timestamp);
	}

	@Override
	public String toString() {
		return "Note{" + "id=" + id + ", content='" + content + '\'' + ", user=" + user + ", timestamp=" + timestamp
				+ '}';
	}
}

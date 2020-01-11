package net.jin.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Answer {
	@Id
	@GeneratedValue
	private Long Id;
	
	// User 객체와 관계를 맺음
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
	private User writer;

	@Lob
	private String contents;
	
	private LocalDateTime createDate;
	
	public Answer() {
	}
	
	public Answer(User writer, String contents) {
		this.writer = writer;
		this.contents = contents;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Answer other = (Answer) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}

	//mouse right and source toString()
	@Override
	public String toString() {
		return "Answer [Id=" + Id + ", writer=" + writer + ", contents=" + contents + ", createDate=" + createDate
				+ "]";
	}
	
	
}


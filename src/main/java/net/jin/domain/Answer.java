package net.jin.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
	
	// linked User object
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
	private User writer;

	// Question Object dependency
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
	private Question question;
	
	@Lob
	private String contents;
	
	private LocalDateTime createDate;
	
	public Answer() {
	}
	
	public Answer(User writer, Question question, String contents) {
		this.writer = writer;
		this.question = question;
		this.contents = contents;
		this.createDate = LocalDateTime.now();
	}

	public String getFormattedCreateDate() {
		if (createDate == null) {
			return "";
		}
		return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
	}
	
	//mouse right and source-generate hashcode() and equals()	
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

	//mouse right and source-toString()
	@Override
	public String toString() {
		return "Answer [Id=" + Id + ", writer=" + writer + ", contents=" + contents + ", createDate=" + createDate
				+ "]";
	}
	
	
}


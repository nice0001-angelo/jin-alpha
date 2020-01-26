/*
 * This is a Jin-alpha Project
 * File name : Answer.java
 * Created by : Jinhyun
 * Created on : Jan 2020
 * Contents : for Answer table object
 */
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

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Answer extends AbstractEntity {
	// linked User object
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
	@JsonProperty
	private User writer;

	// Question Object dependency
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
	@JsonProperty
	private Question question;
	
	@Lob
	@JsonProperty
	private String contents;
	
	//basic Constructor
	public Answer() {
	}
	
	//Constructor
	public Answer(User writer, Question question, String contents) {
		this.writer = writer;
		this.question = question;
		this.contents = contents;
	}

	public boolean isSameWriter(User loginUser) {
		return loginUser.equals(this.writer);
	}


	//mouse right and source-toString()
	@Override
	public String toString() {
		return "Answer [" + super.toString() + ", writer=" + writer + ", question=" + question + ", contents=" + contents
				+ "]";
	}

	
}


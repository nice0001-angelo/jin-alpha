/*
 * This is a Jin-alpha Project
 * File name : AbstractEntity.java
 * Created by : Jinhyun
 * Created on : Jan 2020
 * Contents : for the AbstractEntity(for example createDate, modifiedDate)
 */
package net.jin.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonProperty;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {
	@Id
	@GeneratedValue
	@JsonProperty
	private Long id;
	
	@CreatedDate
	private LocalDateTime createDate;
	
	@LastModifiedDate
	private LocalDateTime modifiedDate;

	public Long getId() {
		return id;
	}
	
//	public String getFormattedCreateDate() {
//		if (createDate == null) {
//			return "";
//		}
//		return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
//	}
//	
//	public String getFormattedModifiedDate() {
//		if (modifiedDate == null) {
//			return "";
//		}
//		return modifiedDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
//	}
	
	public String getFormattedCreateDate() {
		return getFormattedDate(createDate, "yyyy.MM.dd HH:mm");
	}
	
	public String getFormattedModifiedDate() {
		return getFormattedDate(modifiedDate, "yyyy.MM.dd HH:mm");
	}
	
	public String getFormattedDate(LocalDateTime dateTime, String format) {
		if (dateTime == null) {
			return "";
		}
		return dateTime.format(DateTimeFormatter.ofPattern(format));
	}
	
	//mouse right and source-generate hashcode() and equals()
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		AbstractEntity other = (AbstractEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractEntity [id=" + id + ", createDate=" + createDate + ", modifiedDate=" + modifiedDate + "]";
	}
	
}

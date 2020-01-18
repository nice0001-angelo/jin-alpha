package net.jin.domain;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonProperty;

@MappedSuperclass
public class AbstractEntity {
	@Id
	@GeneratedValue
	@JsonProperty
	private Long id;
	
	@CreatedDate
	private LocalDateTime createDate;
	
	@LastModifiedDate
	private LocalDateTime modifiedDate;
}

package com.yumooklee.community.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class BoardContent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
	@Column(name="board_content_id", length=10)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="board_id")
	private Board board;
	
	private String content;
	
	private int contentSeq;
	
	private LocalDateTime registDate;
	
	private LocalDateTime updateDate;
	
	private LocalDateTime deleteDate;
}

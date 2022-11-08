package com.yumooklee.community.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
	@Column(name="board_id", length=10)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="member_id")
	private Member member;
	
	private String title;
	
	private String secretYn;
	
	private LocalDateTime registDate;
	
	private LocalDateTime updateDate;
	
	private LocalDateTime deleteDate;
	
	@OneToMany(mappedBy="board")
	List<BoardContent> boardContents = new ArrayList<BoardContent>();
	
	@OneToMany(mappedBy="board")
	List<BoardFile> boardFiles = new ArrayList<BoardFile>();
	
	@OneToMany(mappedBy="board")
	List<BoardComment> boardComments = new ArrayList<BoardComment>();
	
	@OneToMany(mappedBy="board")
	List<BoardLike> boardLikes = new ArrayList<BoardLike>();
}

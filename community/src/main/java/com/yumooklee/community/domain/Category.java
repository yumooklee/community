package com.yumooklee.community.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
	@Column(name="category_id", length=10)
	private Long id;
	
	private String categoryName;
	
	private Long parentId;
	
	private LocalDateTime registDate;
	
	private LocalDateTime updateDate;
	
	@OneToMany(mappedBy="category")
	List<Board> boards = new ArrayList<Board>();
}

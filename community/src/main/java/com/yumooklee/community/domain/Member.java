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
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
	@Column(name="member_id")
	private Long id;
	
	private String loginId;
	
	private String name;
	
	private String password;
	
	private String zipcode;
	
	private String address;
	
	private String addressDetail;
	
	private String email;
	
	private String phoneNumber;
	
	private int loginFailCount;
	
	private LocalDateTime loginDate;
	
	private LocalDateTime registDate;
	
	private LocalDateTime updateDate;
	
	private LocalDateTime deleteDate;
	
	@OneToMany(mappedBy="member")
	List<Board> boards = new ArrayList<Board>();
	
	@OneToMany(mappedBy="member")
	List<BoardComment> boardComments = new ArrayList<BoardComment>();
	
	@OneToMany(mappedBy="member")
	List<BoardLike> boardLikes = new ArrayList<BoardLike>();
	
	@OneToMany(mappedBy="member")
	List<BoardSearch> boardSearchs = new ArrayList<BoardSearch>();
}

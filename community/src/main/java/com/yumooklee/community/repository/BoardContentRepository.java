package com.yumooklee.community.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.yumooklee.community.domain.Board;
import com.yumooklee.community.domain.BoardContent;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardContentRepository {

	private final EntityManager em;
	
	public void save(BoardContent boardContent) {
		em.persist(boardContent);
	}
	
//	public Board findOne(Long id) {
//		return em.find(Board.class, id);
//	}
//	
//	public List<Board> findAll(){
//		return em.createQuery("select b from board b", Board.class)
//				.getResultList();
//	}
}

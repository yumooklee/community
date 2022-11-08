package com.yumooklee.community.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.yumooklee.community.domain.Board;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

	private final EntityManager em;
	
	public void save(Board board) {
		em.persist(board);
	}
	
	public Board findOne(Long id) {
		return em.find(Board.class, id);
	}
}

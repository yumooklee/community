package com.yumooklee.community.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.yumooklee.community.domain.Category;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

	private final EntityManager em;
	
	public void save(Category category) {
		em.persist(category);
	}
	
	public Category findOne(Long id) {
		return em.find(Category.class, id);
	}
}

package com.yumooklee.community.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yumooklee.community.domain.Category;
import com.yumooklee.community.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;
	
	/*
	 * 회원가입
	 * */
	@Transactional
	public Long join(Category category) {
		
		categoryRepository.save(category);
		
		return category.getId();
	}
	
	//회원 전체 조회
//	public List<Category> findCategorys(){
//		return categoryRepository.findAll();
//	}
	
	@Transactional(readOnly = true)
	public Category findOne(Long categoryId) {
		return categoryRepository.findOne(categoryId);
	}
	
	@Transactional
	public void update(Long id, String name) {
		Category category = categoryRepository.findOne(id);
		category.setCategoryName(name);
	}
}

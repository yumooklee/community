package com.yumooklee.community.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yumooklee.community.domain.Category;
import com.yumooklee.community.service.CategoryService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CategoryApiController {

	private final CategoryService categoryService;
	
	@PostMapping("/api/registCategory")
	public RegistCategoryResponse registCategory(@RequestBody @Valid RegistCategoryRequest request) {
		Category category = new Category();
		category.setCategoryName(request.getCategoryName());
		
		Long id = categoryService.join(category);
		
		return new RegistCategoryResponse(id);
	}
	
	@PutMapping("/api/updateCategory/{id}")
	public UpdateCategoryResponse updateCategory(@PathVariable("id") Long id, @RequestBody @Valid UpdateCategoryRequest request) {
		categoryService.update(id, request.getCategoryName());
		Category findCategory = categoryService.findOne(id);
		
		return new UpdateCategoryResponse(findCategory.getId(), findCategory.getCategoryName());
	}
	
	@Data
	static class RegistCategoryRequest {
		private String categoryName;
	}
	
	@Data
	static class RegistCategoryResponse {
		private Long id;
		
		public RegistCategoryResponse(Long id) {
			this.id = id;
		}
	}
	
	@Data
	static class UpdateCategoryRequest {
		private String categoryName;
	}
	
	@Data
	@AllArgsConstructor
	static class UpdateCategoryResponse{
		private Long id;
		private String name;
	}
	
}

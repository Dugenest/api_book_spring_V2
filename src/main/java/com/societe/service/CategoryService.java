package com.societe.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.societe.data.Category;
import com.societe.data.CategoryRepository;


@Service

public class CategoryService {

	 private final CategoryRepository categoryRepository;

	    public CategoryService(CategoryRepository categoryRepository) {
	        this.categoryRepository = categoryRepository;
	    }

	    public List<Category> getAllCategorys() {
	        return (List<Category>) categoryRepository.findAll();
	    }

	    public Category getCategoryById(Long id) {
	        return categoryRepository.findById(id).orElseThrow(() ->
	                new RuntimeException("Book not found with id: " + id));
	    }

	    public Category addCategory(Category category) {
	        return categoryRepository.save(category);
	    }

	    public Category updateCategory(Long id, Category categoryDetails) {
	        Category category = getCategoryById(id);
	        category.setNameCategory(categoryDetails.getNameCategory());
	      
	        return categoryRepository.save(category);
	    }

	    public void deleteCategory(Long id) {
	        Category category = getCategoryById(id);
	        categoryRepository.delete(category);
	    }
	}

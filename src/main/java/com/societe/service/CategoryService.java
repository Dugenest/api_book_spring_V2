package com.societe.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.societe.data.Category;
import com.societe.data.CategoryRepository;
import com.societe.exception.CategoryNotFoundException;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Obtenir toutes les catégories
    public List<Category> getAllCategorys() {
        return (List<Category>) categoryRepository.findAll();
    }

    // Obtenir une catégorie par ID
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
    }

    // Ajouter une catégorie
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Mettre à jour une catégorie existante
    public Category updateCategory(Long id, Category categoryDetails) {
        Category category = getCategoryById(id); // Lève une exception si non trouvé
        category.setNameCategory(categoryDetails.getNameCategory());
        return categoryRepository.save(category);
    }

    // Supprimer une catégorie
    public void deleteCategory(Long id) {
        Category category = getCategoryById(id); // Lève une exception si non trouvé
        categoryRepository.delete(category);
    }
}

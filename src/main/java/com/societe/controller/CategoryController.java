package com.societe.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.societe.data.Category;
import com.societe.exception.CategoryNotFoundException;
import com.societe.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService cs;

    // Instanciation du service
    public CategoryController(CategoryService cs) {
        this.cs = cs;
    }

    // Liste de toutes les catégories
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = cs.getAllCategorys();

        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 si la liste est vide
        }

        return new ResponseEntity<>(categories, HttpStatus.OK); // 200 OK pour une liste réussie
    }

    // Obtenir une catégorie par ID
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        try {
            // Utilise le service pour récupérer la catégorie
            Category category = cs.getCategoryById(id);
            return ResponseEntity.ok(category); // Retourne la catégorie avec un statut 200
        } catch (CategoryNotFoundException ex) {
            // Si la catégorie n'est pas trouvée, retourne un statut 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Ajouter une catégorie
    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category c) {
        Category createdCategory = cs.addCategory(c);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED); // 201 pour la création
    }

    // Modifier une catégorie
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        try {
            Category updatedCategory = cs.updateCategory(id, category);
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK); // 200 OK pour la mise à jour
        } catch (CategoryNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // 404 si catégorie non trouvée
        }
    }

    // Supprimer une catégorie
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        try {
            cs.deleteCategory(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 si suppression réussie
        } catch (CategoryNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 si catégorie non trouvée
        }
    }
}

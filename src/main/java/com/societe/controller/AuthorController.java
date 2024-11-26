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

import com.societe.data.Author;
import com.societe.exception.AuthorNotFoundException;
import com.societe.service.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorController {

	private final AuthorService authorService;

	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}

	// Obtenir tous les auteurs
	@GetMapping
	public ResponseEntity<List<Author>> getAllAuthors() {
		List<Author> authors = authorService.getAllAuthors();
		return ResponseEntity.ok(authors);
	}

	// Obtenir un auteur par ID
	@GetMapping("/{id}")
	public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
		try {
			// Utilise le service pour récupérer l'auteur
			Author author = authorService.getAuthorById(id);
			return ResponseEntity.ok(author); // Retourne l'auteur avec un statut 200
		} catch (AuthorNotFoundException ex) {
			// Si l'auteur n'est pas trouvé, retourne un statut 404
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	// Créer un nouvel auteur
	@PostMapping
	public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
		Author createdAuthor = authorService.createAuthor(author);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthor);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author updatedAuthor) {
		try {
			// Appelle le service pour mettre à jour l'auteur
			Author updated = authorService.updateAuthor(id, updatedAuthor);
			return ResponseEntity.ok(updated); // Retourne l'auteur mis à jour avec un statut 200
		} catch (AuthorNotFoundException e) {
			// Si l'auteur n'est pas trouvé, retourne un statut 404
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	// Supprimer un auteur
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
		boolean deleted = authorService.deleteAuthor(id);
		if (deleted) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@Override
	public String toString() {
		return "AuthorController [authorService=" + authorService + "]";
	}

}

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

import com.societe.data.Book;
import com.societe.exception.BookNotFoundException;
import com.societe.exception.InvalidBookDataException;
import com.societe.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

	private final BookService bookService;

	// Instanciation du service
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	// Liste de tous les livres
	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> books = bookService.getAllBooks();

		if (books.isEmpty()) {
			return ResponseEntity.noContent().build(); // 204 si la liste est vide
		}

		return ResponseEntity.ok(books); // 200 OK pour une liste réussie
	}

	// Obtenir un livre par ID
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id) {
		try {
			// Utilise le service pour récupérer le livre
			Book book = bookService.getBookById(id);
			return ResponseEntity.ok(book); // Retourne le livre avec un statut 200
		} catch (BookNotFoundException ex) {
			// Si le livre n'est pas trouvé, retourne un statut 404
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	// Ajouter un livre
	@PostMapping
	public ResponseEntity<?> addBook(@RequestBody Book book) {
		try {
			// Vérification d'existence (ID déjà fourni)
			if (book.getIdBook() != null && bookService.existsById(book.getIdBook())) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body("Book with ID " + book.getIdBook() + " already exists.");
			}

			// Sauvegarde du livre
			Book savedBook = bookService.addBook(book);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedBook); // 201 Created
		} catch (InvalidBookDataException ex) {
			// Erreur de données invalides
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		} catch (Exception ex) {
			// Autres erreurs
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred: " + ex.getMessage());
		}
	}

	// Modifier un livre
	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
		try {
			Book updatedBook = bookService.updateBook(id, book);
			return ResponseEntity.ok(updatedBook); // 200 OK pour la mise à jour
		} catch (BookNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 si livre non trouvé
		}
	}

	// Supprimer un livre
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
		try {
			bookService.deleteBook(id);
			return ResponseEntity.noContent().build(); // 204 si suppression réussie
		} catch (BookNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 si livre non trouvé
		}
	}
}

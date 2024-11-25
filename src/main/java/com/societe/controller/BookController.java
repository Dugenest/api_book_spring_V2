package com.societe.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.societe.data.Book;
import com.societe.exception.BookNotFoundException;
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

    // Ajouter un livre
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        if (book.getIdBook() != null && bookService.existsById(book.getIdBook())) {
            // Livre déjà existant : retourner un statut 409 Conflict
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Book savedBook = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook); // 201 Created
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

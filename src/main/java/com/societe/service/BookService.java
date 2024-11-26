package com.societe.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.societe.data.Book;
import com.societe.data.BookRepository;
import com.societe.exception.BookNotFoundException;
import com.societe.exception.DuplicateBookException;
import com.societe.exception.InvalidBookDataException;

@Service
public class BookService {

	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	// Obtenir tous les livres
	public List<Book> getAllBooks() {
		return (List<Book>) bookRepository.findAll();
	}

	// Vérifie si un livre existe par ID
	public boolean existsById(Long id) {
		return bookRepository.existsById(id);
	}

	// Obtenir un livre par son ID
	public Book getBookById(Long id) {
		return bookRepository.findById(id)
				.orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
	}

	// Ajout d'un livre
	public Book addBook(Book book) {
		// Vérification de l'ID du livre
		if (book.getIdBook() != null && bookRepository.existsById(book.getIdBook())) {
			throw new DuplicateBookException("Book with ID " + book.getIdBook() + " already exists.");
		}

		// Vérification des données invalides
		validateBookData(book);

		// Sauvegarde du livre
		return bookRepository.save(book);
	}

	// Méthode de validation centralisée
	private void validateBookData(Book book) {
		if (book.getTitleBook() == null || book.getTitleBook().trim().isEmpty()) {
			throw new InvalidBookDataException("Book title cannot be null or empty.");
		}

		if (book.getAuthor() == null || book.getAuthor().getAuthorId() == null) {
			throw new InvalidBookDataException("Author ID is required.");
		}

		if (book.getCategories() == null || book.getCategories().isEmpty()) {
			throw new InvalidBookDataException("At least one category is required.");
		}
	}

	// Mettre à jour un livre existant
	public Book updateBook(Long id, Book bookDetails) {
		Book existingBook = getBookById(id);

		// Vérification des données invalides pour la mise à jour
		if (bookDetails.getTitleBook() == null || bookDetails.getTitleBook().isEmpty()) {
			throw new InvalidBookDataException("Book title cannot be null or empty.");
		}

		// Mettre à jour les champs
		existingBook.setTitleBook(bookDetails.getTitleBook());
		existingBook.setDateBook(bookDetails.getDateBook());

		return bookRepository.save(existingBook);
	}

	// Supprimer un livre
	public void deleteBook(Long id) {
		Book existingBook = getBookById(id);
		bookRepository.delete(existingBook);
	}
}

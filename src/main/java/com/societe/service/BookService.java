package com.societe.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.societe.data.Book;
import com.societe.data.BookRepository;
import com.societe.exception.BookNotFoundException;

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

    // Ajouter un livre
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    // Obtenir un livre par ID
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
    }

    // Mettre à jour un livre existant
    public Book updateBook(Long id, Book bookDetails) {
        Book existingBook = getBookById(id);

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

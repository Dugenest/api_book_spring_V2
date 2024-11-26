package com.societe.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.societe.data.Author;
import com.societe.data.AuthorRepository;
import com.societe.exception.AuthorNotFoundException;
import com.societe.exception.DuplicateAuthorException;
import com.societe.exception.InvalidAuthorDataException;

@Service
public class AuthorService {

	private AuthorRepository authorRepository;

	public AuthorService(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	// Obtenir un auteur par son ID
	public Author getAuthorById(Long id) {
		return authorRepository.findById(id)
				.orElseThrow(() -> new AuthorNotFoundException("Author not found with id: " + id));
	}
	
	 // Vérifie si un auteur existe par ID
    public boolean existsById(Long id) {
        return authorRepository.existsById(id);
    }

	// Obtenir tous les auteurs
	public List<Author> getAllAuthors() {
		return (List<Author>) authorRepository.findAll();
	}

	// Créer un auteur (vérifie avant s'il existe déjà par ID)
	public Author createAuthor(Author author) {
		// Vérifier si un auteur avec le même ID existe déjà
		if (author.getAuthorId() != null && authorRepository.findById(author.getAuthorId()).isPresent()) {
			throw new DuplicateAuthorException("Author with ID " + author.getAuthorId() + " already exists.");
		}

		// Vérification des données invalides
		if (author.getAuthorName() == null || author.getAuthorName().isEmpty() ||
			author.getAuthorFirstname() == null || author.getAuthorFirstname().isEmpty()) {
			throw new InvalidAuthorDataException("Author name and firstname cannot be null or empty.");
		}

		// Si aucun auteur avec cet ID n'existe, créer l'auteur
		return authorRepository.save(author);
	}

	// Update Author
	public Author updateAuthor(Long id, Author updatedAuthor) {
		// Rechercher l'auteur par ID
		Author existingAuthor = getAuthorById(id); // Cette méthode lève une exception si l'auteur n'existe pas

		// Vérification des données invalides pour la mise à jour
		if (updatedAuthor.getAuthorName() == null || updatedAuthor.getAuthorName().isEmpty() ||
			updatedAuthor.getAuthorFirstname() == null || updatedAuthor.getAuthorFirstname().isEmpty()) {
			throw new InvalidAuthorDataException("Author name and firstname cannot be null or empty.");
		}

		// Mettre à jour les données de l'auteur
		existingAuthor.setAuthorName(updatedAuthor.getAuthorName());
		existingAuthor.setAuthorFirstname(updatedAuthor.getAuthorFirstname());
		existingAuthor.setAuthorNationality(updatedAuthor.getAuthorNationality());

		// Sauvegarder et retourner l'auteur mis à jour
		return authorRepository.save(existingAuthor);
	}

	// Supprimer un auteur
	public boolean deleteAuthor(Long id) {
		if (authorRepository.existsById(id)) {
			authorRepository.deleteById(id);
			return true; // Suppression réussie
		} else {
			throw new AuthorNotFoundException("Author not found with id: " + id);
		}
	}
}

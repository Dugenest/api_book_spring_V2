package com.societe.data;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
public class Author implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAuthor;

	@Column(nullable = false)
	private String nameAuthor;

	@Column(nullable = false)
	private String nationalityAuthor;

	public Author(Long idAuthor, String nameAuthor, String nationalityAuthor) {
		super();
		this.idAuthor = idAuthor;
		this.nameAuthor = nameAuthor;
		this.nationalityAuthor = nationalityAuthor;
	}

	public Long getIdAuthor() {
		return idAuthor;
	}

	public void setIdAuthor(Long idAuthor) {
		this.idAuthor = idAuthor;
	}

	public String getNameAuthor() {
		return nameAuthor;
	}

	public void setNameAuthor(String nameAuthor) {
		this.nameAuthor = nameAuthor;
	}

	public String getNationalityAuthor() {
		return nationalityAuthor;
	}

	public void setNationalityAuthor(String nationalityAuthor) {
		this.nationalityAuthor = nationalityAuthor;
	}

	@Override
	public String toString() {
		return "Author [idAuthor=" + idAuthor + ", nameAuthor=" + nameAuthor + ", nationalityAuthor="
				+ nationalityAuthor + "]";
	}

}

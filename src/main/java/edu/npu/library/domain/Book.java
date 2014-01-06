package edu.npu.library.domain;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * domain object that represents bookcatalog table in database
 * @author Neeta Vekariya
 */
@Entity
@Table(name="bookcatalog")
@Access(AccessType.PROPERTY)
public class Book implements Serializable{
	private static final long serialVersionUID = -4807800321633473342L;

	private int bookId;
	
	@NotEmpty
	private String title;
	private int edition;
	private String publisher;
	private String author;
	private int quantity;
	private int currentstock;
	
	@NotNull
	@Min(value=1000000000)
	private long isbn;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getEdition() {
		return edition;
	}
	public void setEdition(int edition) {
		this.edition = edition;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public long getIsbn() {
		return isbn;
	}
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}
	
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getCurrentstock() {
		return currentstock;
	}
	public void setCurrentstock(int currentstock) {
		this.currentstock = currentstock;
	}
	@Override
	public String toString() {
		String str = "Book Id: "+bookId+", Title: "+title+", Edition: "+edition+", Author: "+author+", Publisher: "+publisher+", ISBN: "+isbn;
		return str;
	}
	@Override
	public boolean equals(Object arg0) {
		if(arg0 == null)return false;
		Book book = (Book)arg0;
		if(book.getBookId() == bookId){
			return true;
		}
		return false;
	}
	
	
	
	
}

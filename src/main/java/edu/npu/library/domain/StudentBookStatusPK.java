package edu.npu.library.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Primary key (userid , bookid) for Studentbookstatus table
 * @author Neeta Vekariya
 *
 */
@Embeddable
@Access(AccessType.PROPERTY)
public class StudentBookStatusPK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6020846318620303599L;

	private Date loanDate;
	
	private Book book;
	
	private User user;

	@Temporal(TemporalType.TIMESTAMP)
	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	@ManyToOne
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@ManyToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}

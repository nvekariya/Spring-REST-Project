package edu.npu.library.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Join table that provides Student Book Status detail
 * It will be modified when Student loans or returns a book
 * @author Neeta Vekariya
 *
 */
@Entity
@Table(name="studentbookstatus")
@Access(AccessType.PROPERTY)
@AssociationOverrides({
	@AssociationOverride(name = "pk.user", 
		joinColumns = @JoinColumn(name = "userId")),
	@AssociationOverride(name = "pk.book", 
		joinColumns = @JoinColumn(name = "bookId")) })
public class StudentBookStatus implements Serializable {
	
	
	
	private static final long serialVersionUID = -2953997123892878865L;

	private Date returnDate;

	private StudentBookStatusPK pk;

	@Temporal(TemporalType.TIMESTAMP)
	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	@EmbeddedId
	public StudentBookStatusPK getPk() {
		return pk;
	}

	public void setPk(StudentBookStatusPK pk) {
		this.pk = pk;
	}
	
	@Transient
	public Date getLoanDate() {
		return getPk().getLoanDate();
	}

	public void setLoanDate(Date loanDate) {
		getPk().setLoanDate(loanDate);
	}

	@Transient
	public Book getBook() {
		return getPk().getBook();
	}

	public void setBook(Book book) {
		getPk().setBook(book);
	}

	@Transient
	public User getUser() {
		return getPk().getUser();
	}

	public void setUser(User user) {
		getPk().getUser();
	}
	
}

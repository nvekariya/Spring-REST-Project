package edu.npu.library.dao.hibernate;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.npu.library.dao.StudentDao;
import edu.npu.library.domain.Book;
import edu.npu.library.domain.StudentBookStatus;
import edu.npu.library.domain.StudentBookStatusPK;
import edu.npu.library.domain.User;

/**
 * @author Neeta Vekariya
 * Hibernate impelementation of StudentDao
 */
@Repository("studentDao")
@Transactional(propagation=Propagation.REQUIRED)
public class StudentDaoHibernateImpl implements StudentDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * retuendate will be set for given loaned book
	 * that stats that student has returned that book
	 */
	@Override
	public void returnBook(int userId, int bookId) {
		
		Calendar cal = Calendar.getInstance();
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from StudentBookStatus where userId=:userId and bookId=:bookId and returnDate is null");
		q.setInteger("userId", userId);
		q.setInteger("bookId", bookId);
		StudentBookStatus status = (StudentBookStatus)q.uniqueResult();
		status.setReturnDate(cal.getTime());
		
	}

	/**
	 * New row will be created in StudentBookStatus (Join table) table with returndate as null
	 */
	@Override
	public void loanBook(int userId, int bookId) {
		Session session = sessionFactory.getCurrentSession();
		Calendar cal = Calendar.getInstance();
		
		StudentBookStatus status = new StudentBookStatus();
		User user = (User)session.get(User.class, userId);
		Book book = (Book)session.get(Book.class, bookId);
		StudentBookStatusPK pk = new StudentBookStatusPK();
		pk.setBook(book);
		pk.setUser(user);
		pk.setLoanDate(cal.getTime());
		status.setPk(pk);
		
		session.save(status);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Book> getLoanBooksList(int userId) {
		Query q = sessionFactory.getCurrentSession().createQuery("select b from StudentBookStatus s inner join s.pk.book b where s.pk.user.userId=:userId and s.returnDate is null");
		q.setInteger("userId", userId);
		@SuppressWarnings("unchecked")
		List<Book> bookList = (List<Book>)q.list();
		return bookList;
	}

	/**
	 * increase currentstock of given book by 1
	 */
	@Override
	public void incrementBookStock(int bookId) {
		Session session = sessionFactory.getCurrentSession();
		Book book = (Book)session.get(Book.class, bookId);
		book.setCurrentstock(book.getCurrentstock()+1);
	}

	/**
	 * decrease currentstock of given book by 1
	 */
	@Override
	public void decrementBookStock(int bookId) {
		Session session = sessionFactory.getCurrentSession();
		Book book = (Book)session.get(Book.class, bookId);
		book.setCurrentstock(book.getCurrentstock()-1);
		
	}
	
	

}

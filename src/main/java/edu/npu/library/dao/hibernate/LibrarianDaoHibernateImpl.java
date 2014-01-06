package edu.npu.library.dao.hibernate;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.npu.library.dao.LibrarianDao;
import edu.npu.library.domain.Book;

/**
 * @author Neeta Vekariya
 * Hibernate impelementation of LibrarianDao
 */
@Repository("librarianDao")
@Transactional(propagation=Propagation.REQUIRED)
public class LibrarianDaoHibernateImpl implements LibrarianDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public void addBookDetail(Book book) {
		sessionFactory.getCurrentSession().save(book);
	}
	
	@Override
	public void updateBookDetail(Book book) {
		sessionFactory.getCurrentSession().saveOrUpdate(book);
	}
	
	@Override
	public void deleteBookDetail(Book book) {
		sessionFactory.getCurrentSession().delete(book);
	}

}

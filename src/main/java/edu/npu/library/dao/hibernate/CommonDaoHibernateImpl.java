package edu.npu.library.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.npu.library.dao.CommonDao;
import edu.npu.library.domain.Book;
import edu.npu.library.domain.User;
import edu.npu.library.domain.UserLoginInfo;

/**
 * @author Neeta Vekariya
 * Hibernate impelementation of CommonDao
 */
@Repository("commonDao")
@Transactional(propagation=Propagation.REQUIRED)
public class CommonDaoHibernateImpl implements CommonDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public User loginUser(UserLoginInfo userLoginInfo) {
		Query q = sessionFactory.getCurrentSession().createQuery("from User where userName=:userName and password=:password");
		q.setString("userName", userLoginInfo.getUserName());
		q.setString("password", userLoginInfo.getPassword());
		User user = (User)q.uniqueResult();
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> findBooks() {
		Query q = sessionFactory.getCurrentSession().createQuery("from Book");
	    return (List<Book>) q.list();
	}
	@Override
	public void updateUserProfile(User user) {
		if(user.getPassword() == null){
			// retrieve password from db and set it into current user object
			User dbUser =findUserById(user.getUserId());
			user.setPassword(dbUser.getPassword());
		}
			sessionFactory.getCurrentSession().merge(user);
		
	}
	
	@Override
	public User findUserById(int userId) {
		User user = (User) sessionFactory.getCurrentSession().get(User.class, userId);
		return user;
	}

	@Override
	public User findUserByName(String userName) {
		Query q = sessionFactory.getCurrentSession().createQuery("from User where userName=:userName");
		q.setString("userName", userName);
		User user = (User)q.uniqueResult();
		return user;
	}

	@Override
	public Book findBookById(int bookId) {
		return (Book)sessionFactory.getCurrentSession().get(Book.class, bookId);
	}

	@Override
	public Book findBookByTitle(String title) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Book where title=:title");
		q.setString("title", title);
		Book book = (Book)q.uniqueResult();
		return book;
	}
	
	
	
	

}

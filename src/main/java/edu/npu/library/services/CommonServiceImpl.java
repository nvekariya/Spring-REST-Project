package edu.npu.library.services;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.npu.library.controllers.CommonController;
import edu.npu.library.dao.CommonDao;
import edu.npu.library.domain.Book;
import edu.npu.library.domain.User;
import edu.npu.library.domain.UserLoginInfo;
import edu.npu.library.exception.DomainException;

/**
 * @author Neeta Vekariya
 * Service to access database API of CommonDao
 */
@Service
@Transactional
public class CommonServiceImpl implements CommonService {

	@Autowired
	@Qualifier("commonDao")
	private CommonDao commonDao;
	
	@Autowired
	private MessageSource messageSource;

	private static final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);
	
	@Override
	public User loginUser(UserLoginInfo user) {
		return commonDao.loginUser(user);
	}

	@Override
	public List<Book> findBooks() {
		return commonDao.findBooks();
	}


	@Override
	public void updateUserProfile(User user)  throws DomainException{
		//validate user name
		validateUserName(user);
		commonDao.updateUserProfile(user);
	}

	@Override
	public User findUserByName(String userName) {
		return commonDao.findUserByName(userName);
	}

	@Override
	public Book findBookById(int bookId) {
		return commonDao.findBookById(bookId);
	}

	/**
	 * validates username if it already exists in database
	 * @param user
	 * @throws DomainException
	 */
	public void validateUserName(User user) throws DomainException{
		User dbUser = commonDao.findUserByName(user.getUserName());
		if(dbUser != null && dbUser.getUserId() != user.getUserId()){
			Locale currentLocale =  LocaleContextHolder.getLocale();
	    	String msg = messageSource.getMessage("username.exists", null, currentLocale);
			logger.error(msg);
			
			throw new DomainException(msg);
		}
	}
}

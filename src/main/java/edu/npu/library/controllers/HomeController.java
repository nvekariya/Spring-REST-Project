package edu.npu.library.controllers;

import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import edu.npu.library.domain.User;
import edu.npu.library.domain.UserLoginInfo;
import edu.npu.library.exception.DomainException;
import edu.npu.library.helper.LibraryConstants;
import edu.npu.library.services.CommonService;

/**
 * Controller to process login/logout requests
 * @author Neeta Vekariya
 */
@Controller
@RequestMapping("/*")
public class HomeController {
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	private MessageSource messageSource;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * @param userLoginInfo
	 * @param session
	 * @return User info except password
	 * @throws DomainException
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = LibraryConstants.MEDIA_TYPE_JSON, produces = LibraryConstants.MEDIA_TYPE_JSON)
	@ResponseBody
	public User loginUser(@Valid @RequestBody UserLoginInfo userLoginInfo, HttpSession session) throws DomainException {
		logger.info("Login user");
		User user = commonService.loginUser(userLoginInfo);
		if(user != null){
			user.setPassword(null);
			session.setAttribute("user", user);
		}else{
			Locale currentLocale =  LocaleContextHolder.getLocale();
	    	String msg = messageSource.getMessage("invalid.login", null, currentLocale);
			logger.error(msg);
			
			throw new DomainException(msg);
		}
		return user;
	}
	
	/**
	 * invalidates user session when logout
	 * @param session
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.PUT)
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void logout(HttpSession session) {
		logger.info("Logout");
		session.invalidate();
	}
	
}

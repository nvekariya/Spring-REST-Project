package edu.npu.library.domain;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Used for User login
 * @author Neeta
 *
 */
public class UserLoginInfo {

	@NotEmpty
	private String userName;
	
	@NotEmpty
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

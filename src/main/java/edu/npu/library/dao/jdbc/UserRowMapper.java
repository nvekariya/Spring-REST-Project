package edu.npu.library.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.npu.library.domain.User;

/**
 * Mapper to map resultset data with Bser object
 * @author Neeta Vekariya
 *
 */
public class UserRowMapper implements RowMapper<User> {

	public User mapRow(ResultSet results, int row) throws SQLException {
		User user = new User();
		
		user = new User();
				
		user.setUserRole(results.getString("userRole"));
		user.setUserId(results.getInt("userId"));
		user.setUserName(results.getString("userName"));
		user.setPassword(results.getString("password"));
		user.setFirstName(results.getString("firstName"));
		user.setLastName(results.getString("lastName"));
		user.setStreet(results.getString("street"));
		user.setCity(results.getString("city"));
		user.setZipcode(results.getInt("zipcode"));
		user.setState(results.getString("state"));
		user.setEmail(results.getString("email"));
		
		return user;
	}

}

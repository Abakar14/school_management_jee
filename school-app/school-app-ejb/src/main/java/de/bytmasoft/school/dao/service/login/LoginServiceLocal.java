package de.bytmasoft.school.dao.service.login;

import javax.ejb.Local;

import de.bytmasoft.school.dao.entities.User;

@Local
public interface LoginServiceLocal {


	public boolean checkLogin(User user);
	
	public boolean changePassword(User user);
	
	public boolean forgotPassword(User user);
	
	public boolean newRegisteration();
	
	public String successMessage();
	
	public String errorMessage();
}

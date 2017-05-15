package de.bytmasoft.school.dao.service.login;

import javax.ejb.Remote;

import de.bytmasoft.school.dao.entities.User;

@Remote
public interface LoginServiceRemote {


	public boolean checkLogin(User user);
	
	public boolean changePassword(User user);
	
	public boolean forgotPassword(User user);
	
	public boolean newRegisteration();
	
	public String successMessage();
	
	public String errorMessage();
}

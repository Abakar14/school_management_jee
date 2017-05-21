package de.bytmasoft.school.login;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import de.bytmasoft.school.dao.entities.User;
import de.bytmasoft.school.dao.service.login.LoginService;

@ManagedBean
@SessionScoped
public class LoginAction {

	@Inject
	private LoginService service;
	private User user;
	

	/**
	 * 
	 */
	public LoginAction() {

		
	}
	
	
	public void test(){
		
	}
	
	

	public String checkLogin(){
		
		if (service.checkLogin(user)) {
			// Open Module for user
			return "success";
		} else {
			// Error und block module for User
			return "error";
		}
	}
	
	public void ChangePassword() {
		if (service.changePassword(user)) {
			// send message to user if pwd changed
		} else {
			// send Message to user to tell him that pwd didn't changed
		}
	}

	public void ForgotPassword() {
		if (service.forgotPassword(user)) {
			// let user go to password forgotten page for renue send to him
		} else {
			// if the user did not have the right info then let display him a
			// message to let him know
		}
	}

	public void newRegisteration() {
		if (service.newRegisteration()) {
			// if the registeration is success send email to user und let him to
			// check his Email or
			// let him registeration by phone after complex questation for
			// security and send Message into his
			// phone to let him know that he is registered
		} else {
			// Display message if the registeration failed
		}
	}

	public String hallo() {
		return "Hallo Mr : " + user.getFirstName() + "" + user.getLastName();
	}
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

}

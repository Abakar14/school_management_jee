package de.bytmasoft.school.dao.service.login;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import de.bytmasoft.school.dao.entities.User;
import de.bytmasoft.school.dao.service.BytmasoftService;

/**
 * Session Bean implementation class LoginService
 */
@Stateless
public class LoginService implements LoginServiceRemote, LoginServiceLocal {

	
	private String successMsg;
	private String errorMsg;
	
	@Inject
	BytmasoftService<?, ?> service;
	
    /**
     * Default constructor. 
     */
    public LoginService() {
        // TODO Auto-generated constructor stub
    }

	/* (non-Javadoc)
	 * @see de.bytmasoft.school.dao.service.login.LoginServiceLocal#checkLogin()
	 */
	@Override
	public boolean checkLogin(User user) {
		//connect to DB select * from user where loginname = loginname;
		//check password for this user if 
		// if((user.getLoginName.equals(loginname))&&(user.getPassword().equals(password))){{
		//if login seccess set successMsg
	//else set errorMsg
		return false;
	}

	/* (non-Javadoc)
	 * @see de.bytmasoft.school.dao.service.login.LoginServiceLocal#changePassword()
	 */
	@Override
	public boolean changePassword(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see de.bytmasoft.school.dao.service.login.LoginServiceLocal#forgotPassword()
	 */
	@Override
	public boolean forgotPassword(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see de.bytmasoft.school.dao.service.login.LoginServiceLocal#newRegisteration()
	 */
	@Override
	public boolean newRegisteration() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see de.bytmasoft.school.dao.service.login.LoginServiceLocal#successMessage()
	 */
	@Override
	public String successMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see de.bytmasoft.school.dao.service.login.LoginServiceLocal#errorMessage()
	 */
	@Override
	public String errorMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}

package cn.edu.nju.software.dochub.service;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.nju.software.dochub.data.dao.UserDAO;

public class LoginService {
	
	UserDAO userDAO;
	
	public boolean login(String name){
		
		System.out.println(userDAO);
		if(userDAO.findByUsername(name).size()>0)
			return true;
		
		return false;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
		System.out.println(">>>>>>>>>>>>userDAO");
	}


	
}

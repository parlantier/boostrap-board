package org.board.service;

import java.util.Date;

import javax.inject.Inject;

import org.board.domain.LoginDTO;
import org.board.domain.UserVO;
import org.board.persistence.UserDAO;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserDAO dao;
	
	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return dao.login(dto);
	}

	@Override
	public void keepLogin(String uid, String sessionId, Date next) throws Exception {
		// TODO Auto-generated method stub
		
		dao.keepLogin(uid, sessionId, next);
	}

	@Override
	public UserVO checkLoginBefore(String value) {
		// TODO Auto-generated method stub
		return dao.checkUserWithSessionKey(value);
	}

}

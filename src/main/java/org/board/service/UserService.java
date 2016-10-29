package org.board.service;

import java.util.Date;

import org.board.domain.LoginDTO;
import org.board.domain.UserVO;

public interface UserService {

	public UserVO login(LoginDTO dto)throws Exception;
	
	public void keepLogin(String uid, String sessionId, Date next)throws Exception;
	
	public UserVO checkLoginBefore(String value);
}

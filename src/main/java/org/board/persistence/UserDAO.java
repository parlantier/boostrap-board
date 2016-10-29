package org.board.persistence;

import java.util.Date;

import org.board.domain.LoginDTO;
import org.board.domain.UserVO;

public interface UserDAO {

	public UserVO login(LoginDTO dto)throws Exception;
	
	public void keepLogin(String uid, String sessionId, Date next);
	
	public UserVO checkUserWithSessionKey(String value);
	
}

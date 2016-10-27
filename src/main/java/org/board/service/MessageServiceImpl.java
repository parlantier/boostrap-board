package org.board.service;

import javax.inject.Inject;

import org.board.domain.MessageVO;
import org.board.persistence.MessageDAO;
import org.board.persistence.PointDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageServiceImpl implements MessageService {

	@Inject
	private MessageDAO messageDAO;
	
	@Inject
	private PointDAO pointDAO;
	
	@Transactional
	@Override
	public void addMessage(MessageVO vo) throws Exception {
		// TODO Auto-generated method stub
		
		messageDAO.create(vo);
		pointDAO.updatePoint(vo.getSender(), 10);
	}

	@Override
	public MessageVO readMessage(String uid, Integer mid) throws Exception {
		// TODO Auto-generated method stub
		messageDAO.updateState(mid);
		pointDAO.updatePoint(uid, 5);
		
		return messageDAO.readMessage(mid);
	}

}

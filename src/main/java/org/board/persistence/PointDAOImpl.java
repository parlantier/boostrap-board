package org.board.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class PointDAOImpl implements PointDAO{

	@Inject
	private SqlSession session;
	
	private static String namespace = "org.board.mapper.PointMapper";

	@Override
	public void updatePoint(String uid, int point) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("uid", uid);
		paraMap.put("point", point);
		
		session.update(namespace+".updatePoint", paraMap);
	}
	
	
	
}

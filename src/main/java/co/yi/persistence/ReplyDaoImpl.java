package co.yi.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.yi.domain.Criteria;
import co.yi.domain.ReplyVO;



@Repository
public class ReplyDaoImpl implements ReplyDAO {

	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "com.yi.mapper.ReplyMapper";
	
	@Override
	public List<ReplyVO> list(int bno) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".list",bno);
	}

	@Override
	public void create(ReplyVO vo) {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace + ".create",vo);
	}

	@Override
	public void update(ReplyVO vo) {
		// TODO Auto-generated method stub
		sqlSession.update(namespace + ".update",vo);
	}

	@Override
	public void delete(int rno) {
		// TODO Auto-generated method stub
		sqlSession.delete(namespace + ".delete",rno);
	}

	@Override
	public List<ReplyVO> listPage(Criteria cri, int bno) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("bno", bno);
		map.put("cri", cri);
		return sqlSession.selectList(namespace+".listPage",map);
	}

	@Override
	public int totalCount(int bno) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".totalCount",bno);
	}

	@Override
	public ReplyVO selectByRno(int rno) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".selectByRno",rno);
	}

}

package co.yi.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.yi.domain.MemberVO;





@Repository
public class MemberDaoImpl implements MemberDAO {

	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "co.yi.mapper.MemberMapper";
	@Override
	public String getTime() {
		return sqlSession.selectOne(namespace + ".getTime");
	}

	@Override
	public void insertMember(MemberVO vo) {
		sqlSession.insert(namespace + ".insertMember",vo);
	}

	@Override
	public MemberVO readMember(String userid) {
		return sqlSession.selectOne(namespace + ".readMember",userid);
	}

	@Override
	public List<MemberVO> selectAll() {
		return sqlSession.selectList(namespace + ".selectAll");
	}

	@Override
	public int updateMember(MemberVO vo) {
		return sqlSession.update(namespace + ".updateMember",vo);
	}

	@Override
	public int deletMember(String userid) {
		return sqlSession.delete(namespace + ".deletMember",userid);
	}

	@Override
	public MemberVO read(String userid, String userpw) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<>();
		map.put("userid", userid);
		map.put("userpw", userpw);
		return sqlSession.selectOne(namespace + ".read",map);
	}

}

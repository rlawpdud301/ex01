package co.yi.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.yi.domain.Criteria;
import co.yi.domain.SearchCriteria;
import co.yi.domain.TblBoardVO;

@Repository
public class BoardDaoImpl implements BoardDAO {

	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "co.yi.mapper.boardMapper";
	
	@Override
	public void insert(TblBoardVO vo) {
		sqlSession.insert(namespace + ".insert",vo);
	}

	@Override
	public TblBoardVO read(int bno) {
		return sqlSession.selectOne(namespace + ".read",bno);
	}

	@Override
	public void update(TblBoardVO vo) {
		sqlSession.update(namespace + ".update",vo);
	}

	@Override
	public void delete(int bno) {
		sqlSession.delete(namespace + ".delete",bno);
	}

	@Override
	public List<TblBoardVO> listAll() {
		return sqlSession.selectList(namespace + ".listAll");
	}

	@Override
	public void increaseCnt(int bno) {
		sqlSession.update(namespace + ".increaseCnt",bno);
		
	}
	
	@Override
	public List<TblBoardVO> listPage(int page) {
		if (page <0) {
			page = 1;
		}
		page = (page-1)*10;
		return sqlSession.selectList(namespace + ".listPage",page);
	}

	@Override
	public List<TblBoardVO> listCriteria(Criteria cri) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".listCriteria",cri);
	}

	@Override
	public int totalCount() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".totalCount");
	}

	@Override
	public List<TblBoardVO> listSearch(SearchCriteria cri) {
		// TODO Auto-generated method stub
		
		return sqlSession.selectList(namespace + ".listSearch",cri);
	}

	@Override
	public int searchTotalCount() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".searchTotalCount");
	}

	@Override
	public void updateReplyCnt(int bno, int amount) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("bno", bno);
		map.put("amount", amount);
		sqlSession.update(namespace + ".updateReplyCnt",map);
	}

	@Override
	public void addAttach(String fullName) {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace + ".addAttach",fullName);
	}

	@Override
	public List<String> getAttach(int bno) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".getAttach",bno);
	}

	@Override
	public void deleteAttach(int bno) {
		// TODO Auto-generated method stub
		sqlSession.delete(namespace + ".deleteAttach",bno);
	}

	@Override
	public void deleteAttachByName(Map<String, String> map) {
		// TODO Auto-generated method stub
		sqlSession.delete(namespace + ".deleteAttachByName",map);
	}

	@Override
	public void addAttachByBno(String fullName, int bno) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("fullName", fullName);
		map.put("bno", bno); 
		sqlSession.insert(namespace + ".addAttachByBno",map);
	}

	

}

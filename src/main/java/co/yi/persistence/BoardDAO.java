package co.yi.persistence;

import java.util.List;
import java.util.Map;

import co.yi.domain.Criteria;
import co.yi.domain.SearchCriteria;
import co.yi.domain.TblBoardVO;

public interface BoardDAO {
	public void insert(TblBoardVO vo);
	public TblBoardVO read(int bno);
	public void update(TblBoardVO vo);
	public void delete(int bno);
	public List<TblBoardVO> listAll();
	public void increaseCnt(int bno);
	public int totalCount();
	
	
	/*-------------------------------------*/
	public List<TblBoardVO> listPage(int page);
	
	public List<TblBoardVO> listCriteria(Criteria cri);
	
	
	/*---------------------------------*/
	public List<TblBoardVO> listSearch(SearchCriteria cri);
	public int searchTotalCount();
	
	public void updateReplyCnt(int bno, int amount);
	
	public void addAttach(String fullName);
	
	public List<String> getAttach(int bno);
	public void deleteAttach(int bno);
	public void deleteAttachByName(Map<String, String> map);
	public void addAttachByBno(String fullName, int bno);
}

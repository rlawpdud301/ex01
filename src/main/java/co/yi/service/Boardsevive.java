package co.yi.service;

import java.util.List;

import co.yi.domain.Criteria;
import co.yi.domain.SearchCriteria;
import co.yi.domain.TblBoardVO;

public interface Boardsevive {
	public void regist(TblBoardVO vo);
	public TblBoardVO read(int bno);
	public void modify(TblBoardVO vo);
	public void remove(int bno);
	public List<TblBoardVO> listAll();
	public void increaseCnt(int bno);
	public List<TblBoardVO> listCriteria(Criteria cri);
	public int totalCount();
	public List<TblBoardVO> listSearch(SearchCriteria cri);
	public int searchTotalCount();
	public void modify(TblBoardVO vo, TblBoardVO vo2);
}

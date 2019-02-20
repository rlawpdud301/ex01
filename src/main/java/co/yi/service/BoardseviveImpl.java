package co.yi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import co.yi.domain.Criteria;
import co.yi.domain.SearchCriteria;
import co.yi.domain.TblBoardVO;
import co.yi.persistence.BoardDAO;

@Service
public class BoardseviveImpl implements Boardsevive {

	@Autowired
	private BoardDAO dao;
	
	@Override
	@Transactional
	public void regist(TblBoardVO vo) {
		dao.insert(vo);
		
		List<String> files = vo.getFiles();
		if (files == null || files.size() == 0) {
			return;
		}
		for (String fullName :files) {
			dao.addAttach(fullName);
		}
		
		
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)//
	public TblBoardVO read(int bno) {
		TblBoardVO vo = dao.read(bno);;
		vo.setFiles(dao.getAttach(bno));
		return vo;
	}

	@Override
	public void modify(TblBoardVO vo) {
		dao.update(vo);
	}

	@Override
	@Transactional
	public void remove(int bno) {
		dao.deleteAttach(bno);
		dao.delete(bno);
	}

	@Override
	public List<TblBoardVO> listAll() {
		return dao.listAll();
	}

	@Override
	public void increaseCnt(int bno) {
		dao.increaseCnt(bno);	
	}

	@Override
	public List<TblBoardVO> listCriteria(Criteria cri) {
		return dao.listCriteria(cri);
	}

	@Override
	public int totalCount() {
		// TODO Auto-generated method stub
		return dao.totalCount();
	}

	@Override
	public List<TblBoardVO> listSearch(SearchCriteria cri) {
		// TODO Auto-generated method stub
		return dao.listSearch(cri);
	}

	@Override
	public int searchTotalCount() {
		// TODO Auto-generated method stub
		return dao.searchTotalCount();
	}

	@Override
	@Transactional
	public void modify(TblBoardVO vo, TblBoardVO vo2) {
		if (vo2.getFiles() != null) {
			System.out.println(vo2.getFiles());
			Map<String, String> map = new HashMap<>();
			for (String fullName : vo2.getFiles()) {
				map.put("bno", (vo2.getBno()+"").trim());
				map.put("fullName", fullName);
				dao.deleteAttachByName(map);
			}
		}
		
		// TODO Auto-generated method stub
		for (String fullName :vo.getFiles()) {
			dao.addAttachByBno(fullName,vo.getBno());
		}
		dao.update(vo);
		
		
		
		
	}

}

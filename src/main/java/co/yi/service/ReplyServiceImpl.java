package co.yi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.yi.domain.Criteria;
import co.yi.domain.ReplyVO;
import co.yi.persistence.BoardDAO;
import co.yi.persistence.ReplyDAO;




@Service
public class ReplyServiceImpl implements ReplyService {

	
	@Autowired
	private ReplyDAO dao;
	
	@Autowired
	private BoardDAO boardDao;
	
	@Override
	public List<ReplyVO> list(int bno) {
		// TODO Auto-generated method stub
		return dao.list(bno);
	}

	@Override
	@Transactional
	public void create(ReplyVO vo) {
		// TODO Auto-generated method stub
		dao.create(vo);
		boardDao.updateReplyCnt(vo.getBno(), 1);
	}

	@Override
	public void update(ReplyVO vo) {
		// TODO Auto-generated method stub
		dao.update(vo);
	}

	@Override
	@Transactional
	public void delete(int rno) {
		// TODO Auto-generated method stub
		
		ReplyVO vo = dao.selectByRno(rno);	
		System.out.println(vo.getBno());
		dao.delete(rno);
		
		boardDao.updateReplyCnt(vo.getBno(),-1);
	}

	@Override
	public List<ReplyVO> listPage(Criteria cri, int bno) {
		// TODO Auto-generated method stub
		return dao.listPage(cri, bno);
	}

	@Override
	public int totalCount(int bno) {
		// TODO Auto-generated method stub
		return dao.totalCount(bno);
	}

}

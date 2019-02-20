package co.yi.persistence;

import java.util.List;

import co.yi.domain.Criteria;
import co.yi.domain.ReplyVO;



public interface ReplyDAO {
	public List<ReplyVO> list(int bno);
	public void create(ReplyVO vo);
	public void update(ReplyVO vo);
	public void delete(int rno);
	public List<ReplyVO> listPage(Criteria cri ,int bno);
	public int totalCount(int bno);
	public ReplyVO selectByRno(int rno);
}

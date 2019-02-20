package co.yi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.yi.domain.MemberVO;
import co.yi.persistence.MemberDAO;



@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO dao;
	
	@Override
	public String getTime() {
		// TODO Auto-generated method stub
		return dao.getTime();
	}

	@Override
	public void insertMember(MemberVO vo) {
		// TODO Auto-generated method stub
		dao.insertMember(vo);
	}

	@Override
	public MemberVO readMember(String userid) {
		// TODO Auto-generated method stub
		return dao.readMember(userid);
	}

	@Override
	public List<MemberVO> selectAll() {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}

	@Override
	public int updateMember(MemberVO vo) {
		// TODO Auto-generated method stub
		return dao.updateMember(vo);
	}

	@Override
	public int deletMember(String userid) {
		// TODO Auto-generated method stub
		return dao.deletMember(userid);
	}

	@Override
	public MemberVO read(String userid, String userpw) {
		// TODO Auto-generated method stub
		return dao.read(userid, userpw);
	}

}

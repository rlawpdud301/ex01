package co.yi.persistence;

import java.util.List;

import co.yi.domain.MemberVO;





public interface MemberDAO {
	public String getTime();
	public void insertMember(MemberVO vo);
	public MemberVO readMember(String userid);
	public List<MemberVO> selectAll();
	public int updateMember(MemberVO vo);
	public int deletMember(String userid);
	
	public MemberVO read(String userid, String userpw);
}

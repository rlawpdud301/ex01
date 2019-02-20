package co.yi.ex01;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.yi.domain.Criteria;
import co.yi.domain.TblBoardVO;
import co.yi.persistence.BoardDAO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardDaoTest {
	
	@Autowired
	private BoardDAO dao;
	
	
	/*@Test
	public void testinsert() {
		TblBoardVO vo= new TblBoardVO();
		vo.setTitle("aaa");
		vo.setContent("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		vo.setWriter("llllllllll");
		dao.insert(vo);
	}
	
	@Test
	public void testRead() {
		System.out.println(dao.read(1));
	}
	
	@Test
	public void testselectAll() {
		System.out.println(dao.listAll());
	}
	
	@Test
	public void testupdate() {
		TblBoardVO vo= new TblBoardVO();
		vo.setBno(1);
		vo.setTitle("kkkkkkkkkkkkk");
		vo.setContent("cccccccccccccccccccccccccccccccccccccccccccccccccc");
		dao.update(vo);
	}
	@Test
	public void testdelet() {
		dao.delete(3);
	}
	
	@Test
	public void testincreaseCnt() {
		dao.increaseCnt(10);
	}*/
	
	/*@Test
	public void testlistPage() {
		dao.listPage(2);
	}*/
	
	@Test
	public void testlistCriteria() {
		dao.listCriteria(new Criteria(2,20) );
	}
	
	@Test
	public void testtotalCount() {
		dao.totalCount();
	}
}

package co.yi.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.yi.domain.LoginDTO;
import co.yi.domain.MemberVO;
import co.yi.service.MemberService;

@Controller
@RequestMapping("/user/*")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private MemberService service;
	
	@RequestMapping(value ="login", method = RequestMethod.GET)
	public void loginGet() {
		logger.info("login get------------");
		
		
	}
	
	@RequestMapping(value ="loginPost", method = RequestMethod.POST)
	public void loginPost(String userid,String userpw, Model model) {
		logger.info("loginPost Post------------");
		MemberVO vo = service.read(userid, userpw);
		logger.info("loginPost Post------------"+vo);
		if (vo == null) {
			logger.info("loginPost return------------");
			return;
		}
		LoginDTO dto = new LoginDTO();
		dto.setUserid(vo.getUserid());
		dto.setUesrname(vo.getUsername());
		model.addAttribute("memberVO",dto);
		
	}
	
	@RequestMapping(value ="logout", method = RequestMethod.GET)
	public String loginOutGet(HttpSession session) {
		logger.info("logOut get------------");
		session.invalidate(); 
		return "redirect:/sboard/list";
	}
	
}

package co.yi.controller;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yi.util.MediaUtils;

import co.yi.domain.Criteria;
import co.yi.domain.PageMaker;
import co.yi.domain.TblBoardVO;
import co.yi.service.Boardsevive;

@Controller
@RequestMapping("/board/*") /*boardcontorller 안의 모든 url command 앞에 /board/ 가붙음*/
public class BoardController {

	@Autowired
	private Boardsevive service;
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@RequestMapping(value ="listAll", method = RequestMethod.GET)
	public void listAll(Model model) {
		model.addAttribute("list",service.listAll());	
	}
	
	@RequestMapping(value ="listCri", method = RequestMethod.GET)
	public void listCri(Criteria cri ,Model model) {
		model.addAttribute("list",service.listCriteria(cri));	
	}
	
	@RequestMapping(value ="listPage", method = RequestMethod.GET)
	public void listPage(Criteria cri ,Model model) {
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTottalCount(service.totalCount());
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("list",service.listCriteria(cri));	
	}
	
	@RequestMapping(value ="register", method = RequestMethod.GET)
	public void registerGet() {
		logger.info("register-Get");
	}
	
	@RequestMapping(value ="register", method = RequestMethod.POST)
	public String registerPost(TblBoardVO vo, Model model) {
		logger.info("register-Post");		
		service.regist(vo);
		model.addAttribute("result","success");				
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value ="read", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model) {
		logger.info("read-Get");
		service.increaseCnt(bno); 
		TblBoardVO vo = service.read(bno);
		model.addAttribute("vo",vo);
	}
	
	@RequestMapping(value ="readPage", method = RequestMethod.GET)
	public void readPage(@RequestParam("bno") int bno,Criteria cri,String  modify, Model model) {
		logger.info("read-Get");
		if (modify==null) {
			service.increaseCnt(bno); 
		}
		TblBoardVO vo = service.read(bno);
		model.addAttribute("vo",vo);
		model.addAttribute("cri", cri);
	}
	
	@RequestMapping(value ="remove", method = RequestMethod.POST)
	public String remove(int bno, Model model) {
		logger.info("remove-Post");
		service.remove(bno);
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value ="removePage", method = RequestMethod.POST)
	public String removePage(int bno,Criteria cri, Model model) {
		logger.info("remove-Post");
		service.remove(bno);
		return "redirect:/board/listPage?page="+cri.getPage();
	}
	 
	@RequestMapping(value ="modify", method = RequestMethod.GET)
	public void modifyGet(@RequestParam("bno") int bno, Model model) {
		logger.info("modify-Get");
		
		TblBoardVO vo = service.read(bno);
		vo.setBno(bno); 
		model.addAttribute("vo",vo);
	}
	
	@RequestMapping(value ="modify", method = RequestMethod.POST)
	public String modifyPost(TblBoardVO vo, Model model) {
		logger.info("modify-Post");
		service.modify(vo); 
		model.addAttribute("vo",vo);
		return "redirect:/board/read?bno="+vo.getBno()+"&modify=true";
	}
	
	
	@RequestMapping(value ="modifyPage", method = RequestMethod.GET)
	public void modifyPageGet(@RequestParam("bno") int bno,Criteria cri, Model model) {
		logger.info("modify-Get");
		
		TblBoardVO vo = service.read(bno);
		vo.setBno(bno); 
		model.addAttribute("vo",vo);
		model.addAttribute("cri",cri);
	}
	
	@RequestMapping(value ="modifyPage", method = RequestMethod.POST)
	public String modifyPagePost(TblBoardVO vo,Criteria cri, Model model) {
		logger.info("modify-Post");
		service.modify(vo); 
		model.addAttribute("vo",vo);
		return "redirect:/board/readPage?bno="+vo.getBno()+"&page="+cri.getPage()+"&modify=true";
	}
	
	@ResponseBody
	@RequestMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String filename) {
		ResponseEntity<byte[]> entity = null;
		logger.info("displayFile : " + filename);
		try {
			String format = filename.substring(filename.lastIndexOf(".") + 1);
			MediaType mType = MediaUtils.getMediaType(format);

			HttpHeaders headers = new HttpHeaders();
			InputStream in = null;
			in = new FileInputStream(uploadPath + "/" + filename);
			headers.setContentType(mType);

			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
}

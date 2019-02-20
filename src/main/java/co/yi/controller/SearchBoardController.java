package co.yi.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;

import com.yi.util.MediaUtils;
import com.yi.util.UploadFileUtils;

import co.yi.domain.Criteria;
import co.yi.domain.PageMaker;
import co.yi.domain.SearchCriteria;
import co.yi.domain.TblBoardVO;
import co.yi.service.Boardsevive;

@Controller
@RequestMapping("/sboard/*") /* boardcontorller 안의 모든 url command 앞에 /board/ 가붙음 */
public class SearchBoardController {

	/* @Inject */
	@Autowired
	private Boardsevive service;

	@Resource(name = "uploadPath")
	private String uploadPath;

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	// sboard/list?page=10
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void list(SearchCriteria cri, Model model) {

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTottalCount(service.searchTotalCount());
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("list", service.listSearch(cri));
		model.addAttribute("cri", cri);
		logger.info("aaaaaaa" + service.listSearch(cri));
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public void registerGet() {
		logger.info("register-Get");
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String registerPost(TblBoardVO vo, List<MultipartFile> imageFiles, Model model) throws IOException {
		logger.info("register-Post");

		List<String> files = new ArrayList<>();
		for (MultipartFile imageFile : imageFiles) {
			logger.info("file-name :" + imageFile.getOriginalFilename());
			logger.info("file-size :" + imageFile.getSize());

			String thumPath = UploadFileUtils.uploadFile(uploadPath, imageFile.getOriginalFilename(),
					imageFile.getBytes());

			files.add(thumPath);
		}
		vo.setFiles(files);

		service.regist(vo);
		model.addAttribute("result", "success");
		return "redirect:/sboard/list";
	}

	@RequestMapping(value = "read", method = RequestMethod.GET)
	public void readPage(@RequestParam("bno") int bno, SearchCriteria cri, String modify, Model model) {
		logger.info("read-Get");
		if (modify == null) {
			service.increaseCnt(bno);
		}
		TblBoardVO vo = service.read(bno);
		model.addAttribute("vo", vo);
		model.addAttribute("cri", cri);
	}

	@RequestMapping(value = "remove", method = RequestMethod.POST)
	public String remove(int bno, SearchCriteria cri, Model model) {
		logger.info("remove-Post");
		TblBoardVO vo = service.read(bno);
		List<String> files = vo.getFiles();
		for (String filename : files) {
			String st = filename.substring(0, 12);
			String end = filename.substring(14);
			File file = new File(uploadPath + filename);
			File filebig = new File(uploadPath + st + end);
			file.delete();
			filebig.delete();
		}
		service.remove(bno);
		return "redirect:/sboard/list?page=" + cri.getPage() + "&searchType=" + cri.getSearchType() + "&keyword="
				+ cri.getKeyword();
	}

	@RequestMapping(value = "modify", method = RequestMethod.GET)
	public void modifyGet(@RequestParam("bno") int bno, SearchCriteria cri, Model model) {
		logger.info("modify-Get");

		TblBoardVO vo = service.read(bno);
		vo.setBno(bno);
		model.addAttribute("vo", vo);
		model.addAttribute("cri", cri);
	}

	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public String modifyPost(TblBoardVO vo, SearchCriteria cri, Model model, List<MultipartFile> imageFiles,
			String[] removeimgs) throws IOException {
		logger.info("modify-Post");

		TblBoardVO vo2 = new TblBoardVO();
		List<String> list = new ArrayList<>();
		if (removeimgs != null) {
			for (String removefilename : removeimgs) {
				list.add(removefilename);
				logger.info("removefilename :" + removefilename);
				String st = removefilename.substring(0, 12);
				String end = removefilename.substring(14);
				File file = new File(uploadPath + removefilename);
				File filebig = new File(uploadPath + st + end);
				file.delete();
				filebig.delete();
			}
		}

		List<String> files = new ArrayList<>();

		for (MultipartFile imageFile : imageFiles) {
			logger.info("file-name :" + imageFile.getOriginalFilename());
			logger.info("file-size :" + imageFile.getSize());
			if (imageFile.getSize() > 0) {
				String thumPath = UploadFileUtils.uploadFile(uploadPath, imageFile.getOriginalFilename(),
						imageFile.getBytes());
				files.add(thumPath);
			}

		}

		vo.setFiles(files);

		vo2.setBno(vo.getBno());

		vo2.setFiles(list);

		service.modify(vo, vo2);
		model.addAttribute("vo", vo);
		model.addAttribute("keyword", cri.getKeyword());
		return "redirect:/sboard/read?bno=" + vo.getBno() + "&page=" + cri.getPage() + "&modify=true&searchType="
				+ cri.getSearchType();
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

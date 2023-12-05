package com.multi.campus;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.multi.common.CommonUtil;
import com.multi.model.CategoryVO;
import com.multi.model.ProductVO;
import com.multi.service.AdminService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/admin")
@Log4j
public class AdminController {

	@Inject // byType으로 주입
	private AdminService adminService;

	@Inject
	private CommonUtil util;

	@GetMapping("/prodForm")
	public String productsForm(Model m) {
		log.info("adminService: " + adminService);
		// 상위 카테고리 목록
		List<CategoryVO> upCgList = adminService.getUpcategory();

		m.addAttribute("upCgList", upCgList);

		return "admin/prodInput";
		// "/WEB-INF/views/admin/prodInput.jsp"
	}// ------------------------

	@PostMapping("/prodEditForm")
	public String productsEditForm(Model m, @RequestParam(defaultValue="0") int pnum) {
		log.info("pnum: "+pnum);
		if(pnum==0) {
			return "redirect:prodList";
		}//---------
		
		// 상위 카테고리 목록
		List<CategoryVO> upCgList = adminService.getUpcategory();
		
		//상품번호에 해당하는 상품정보 가져오기
		ProductVO item=adminService.getProduct(pnum);

		m.addAttribute("upCgList", upCgList);
		m.addAttribute("item",item);
		return "admin/prodEdit";
	}// ------------------------------------

	//파라미터 mode값에 따라 로직을 달리 처리함
	//mode값이 insert면 insert문 실행
	//mode값 edit이면 update문을 실행한다
	@PostMapping("/prodInsert")
	public String productsInsert(Model m, @ModelAttribute ProductVO item,
			@RequestParam(defaultValue="insert") String mode,
			@RequestParam("pimage") List<MultipartFile> pimage, HttpServletRequest req) {
		
		log.info("mode: "+mode);
		
		// 1. 업로드 디렉토리의 절대경로 얻기
		ServletContext app = req.getServletContext();
		String upDir = app.getRealPath("/resources/product_images");
		log.info("upDir: " + upDir);

		// 2. 업로드 처리=> MultipartFile의 transferTo() 이용
		if (pimage != null) {
			for (int i = 0; i < pimage.size(); i++) {
				MultipartFile mf = pimage.get(i);
				if (!mf.isEmpty()) {// 파일을 첨부했다면
					// 첨부파일명 알아내기
					String fileName = mf.getOriginalFilename();
					try {

						mf.transferTo(new File(upDir, fileName));// 업로드 처리함
						if (i == 0) {
							item.setPimage1(fileName);
						} else if (i == 1) {
							item.setPimage2(fileName);
						} else if (i == 2) {
							item.setPimage3(fileName);
						}
					} catch (IOException e) {
						log.error("파일 업로드 실패: " + e);
					}

				} // if--------
			} // for-----
		} // if---------------

		log.info("item: " + item);

		int n =0;
		if(mode.equals("insert")) {
			n=adminService.productInsert(item);//등록 처리
		}else if(mode.equals("edit")) {
			n=adminService.productUpdate(item);//수정 처리
		}

		String msg = (n > 0) ? "성공" : "실패";
		String loc = (n > 0) ? "prodList" : "javascript:history.back()";
		/*
		 * m.addAttribute("msg",msg); m.addAttribute("loc",loc);
		 */

		return util.addMsgLoc(m, msg, loc);
	}// ----------------------------

	@GetMapping("/prodList")
	public String productsList(Model m) {

		List<ProductVO> prodArr = adminService.productList();

		m.addAttribute("prodArr", prodArr);

		return "admin/prodList";
	}

}// ----------------------------------------------

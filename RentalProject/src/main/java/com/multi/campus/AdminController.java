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

	@Inject // byType���� ����
	private AdminService adminService;

	@Inject
	private CommonUtil util;

	@GetMapping("/prodForm")
	public String productsForm(Model m) {
		log.info("adminService: " + adminService);
		// ���� ī�װ� ���
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
		
		// ���� ī�װ� ���
		List<CategoryVO> upCgList = adminService.getUpcategory();
		
		//��ǰ��ȣ�� �ش��ϴ� ��ǰ���� ��������
		ProductVO item=adminService.getProduct(pnum);

		m.addAttribute("upCgList", upCgList);
		m.addAttribute("item",item);
		return "admin/prodEdit";
	}// ------------------------------------

	//�Ķ���� mode���� ���� ������ �޸� ó����
	//mode���� insert�� insert�� ����
	//mode�� edit�̸� update���� �����Ѵ�
	@PostMapping("/prodInsert")
	public String productsInsert(Model m, @ModelAttribute ProductVO item,
			@RequestParam(defaultValue="insert") String mode,
			@RequestParam("pimage") List<MultipartFile> pimage, HttpServletRequest req) {
		
		log.info("mode: "+mode);
		
		// 1. ���ε� ���丮�� ������ ���
		ServletContext app = req.getServletContext();
		String upDir = app.getRealPath("/resources/product_images");
		log.info("upDir: " + upDir);

		// 2. ���ε� ó��=> MultipartFile�� transferTo() �̿�
		if (pimage != null) {
			for (int i = 0; i < pimage.size(); i++) {
				MultipartFile mf = pimage.get(i);
				if (!mf.isEmpty()) {// ������ ÷���ߴٸ�
					// ÷�����ϸ� �˾Ƴ���
					String fileName = mf.getOriginalFilename();
					try {

						mf.transferTo(new File(upDir, fileName));// ���ε� ó����
						if (i == 0) {
							item.setPimage1(fileName);
						} else if (i == 1) {
							item.setPimage2(fileName);
						} else if (i == 2) {
							item.setPimage3(fileName);
						}
					} catch (IOException e) {
						log.error("���� ���ε� ����: " + e);
					}

				} // if--------
			} // for-----
		} // if---------------

		log.info("item: " + item);

		int n =0;
		if(mode.equals("insert")) {
			n=adminService.productInsert(item);//��� ó��
		}else if(mode.equals("edit")) {
			n=adminService.productUpdate(item);//���� ó��
		}

		String msg = (n > 0) ? "����" : "����";
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

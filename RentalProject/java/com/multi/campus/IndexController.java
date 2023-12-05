package com.multi.campus;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.multi.model.ProductVO;
import com.multi.service.ShopService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class IndexController {
	
	@Inject
	private ShopService shopService;
	
	//������� ��ȯ���� ������(void)
	//@RequestMapping("/index") �� ����� "/index"  ==> ������� �ȴ�
	@RequestMapping("/index")
	public void showIndex(Model m) {
		
		List<ProductVO> hitItems=shopService.selectByPspec("HIT");
		
		List<ProductVO> newItems=shopService.selectByPspec("NEW");
		
		m.addAttribute("hitItems", hitItems);
		m.addAttribute("newItems", newItems);
		
		//"/WEB-INF/views/index.jsp" �� ã�ư���
	}//-------------------------
	
	@RequestMapping("/prodDetail")
	public String productDetail(Model m,@RequestParam(defaultValue="0") int pnum) {
		log.info("pnum: "+pnum);
		//ShopService�� ��ǰ��ȣ�� ��ǰ���� �������� �޼��� ȣ��
		//==> ��ȯ�ϴ� ProductVO�� Model�� ����
		//key:"item"
		if(pnum==0) {
			return "redirect:index";
		}
		
		
		ProductVO item=shopService.selectByPnum(pnum);
		m.addAttribute("item",item);
		
		return "shop/prodDetail";
	}
	
	
	

}///////////////////////////////////////

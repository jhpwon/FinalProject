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
	
	//뷰네임을 반환하지 않으면(void)
	//@RequestMapping("/index") 로 등록한 "/index"  ==> 뷰네임이 된다
	@RequestMapping("/index")
	public void showIndex(Model m) {
		
		List<ProductVO> hitItems=shopService.selectByPspec("HIT");
		
		List<ProductVO> newItems=shopService.selectByPspec("NEW");
		
		m.addAttribute("hitItems", hitItems);
		m.addAttribute("newItems", newItems);
		
		//"/WEB-INF/views/index.jsp" 를 찾아간다
	}//-------------------------
	
	@RequestMapping("/prodDetail")
	public String productDetail(Model m,@RequestParam(defaultValue="0") int pnum) {
		log.info("pnum: "+pnum);
		//ShopService의 상품번호로 상품정보 가져오는 메서드 호출
		//==> 반환하는 ProductVO를 Model에 저장
		//key:"item"
		if(pnum==0) {
			return "redirect:index";
		}
		
		
		ProductVO item=shopService.selectByPnum(pnum);
		m.addAttribute("item",item);
		
		return "shop/prodDetail";
	}
	
	
	

}///////////////////////////////////////

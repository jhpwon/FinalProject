package com.multi.campus;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.multi.common.CommonUtil;
import com.multi.model.CartVO;
import com.multi.model.MemberVO;
import com.multi.service.ShopService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/user")
@Log4j
public class CartController {
	
	@Inject
	private ShopService shopService;
	
	@Inject
	private CommonUtil util;
	
	
	@PostMapping("/cartAdd")
	public String addCart(Model m, 
			HttpSession session,
			@RequestParam(defaultValue="0") int pnum,
			@RequestParam(defaultValue="0") int pqty) {
		log.info("pnum: "+pnum+", pqty: "+pqty);
		//로그인한 회원의 아이디=>세션에서 꺼내기
		
		MemberVO loginUser=(MemberVO)session.getAttribute("loginUser");
		String userid=loginUser.getUserid();
		
		CartVO cvo=new CartVO();
		cvo.setUserid_fk(userid);
		cvo.setPnum_fk(pnum);
		cvo.setPqty(pqty);
		//장바구니에 상품 추가-----
		int n=shopService.addCart(cvo);
		
		//특정회원의 장바구니 목록 가져오기
		//List<CartVO> cartArr=shopService.selectCartView(userid);
		//m.addAttribute("cartArr", cartArr);
		//return "shop/cartList";
		
		/*==> 여기서 foward방식으로 이동하면 브라우저 새로고침시 계속 상품수량을 추가하는 현상이 발생된다
		 * ==> 장바구니 총액 증가
		 * ==> redirect방식으로 이동해야 한다
		 * */
		return "redirect:cartList";
		
	}//----------------------------------
	
	@GetMapping("/cartList")
	public String cartList(Model m, HttpSession session) {
		
		MemberVO loginUser=(MemberVO)session.getAttribute("loginUser");
		String userid=loginUser.getUserid();
		
		//특정회원의 장바구니 목록 가져오기
		List<CartVO> cartArr=shopService.selectCartView(userid);
		
		//특정회원의 장바구니 총액, 총포인트 가져오기
		CartVO cartTotal=shopService.getCartTotal(userid);
		
		m.addAttribute("cartArr", cartArr);
		m.addAttribute("cartSum", cartTotal);
		
		return "shop/cartList";
	}//---------------------------------------
	
	@PostMapping("/cartDel")
	public String cartDelete(@RequestParam(defaultValue="0") int cartNum) {
		if(cartNum==0) {
			return "redirect:cartList";
		}
		int n=shopService.delCart(cartNum);
		
		return "redirect:cartList";
	}//---------------------------------------
	
	@GetMapping("/cartDelAll")
	public String cartDeleteAll(HttpSession session) {
		MemberVO loginUser=(MemberVO)session.getAttribute("loginUser");
		String userid=loginUser.getUserid();
		CartVO cvo=new CartVO();
		cvo.setUserid_fk(userid);
		shopService.delCartAll(cvo);
		
		return "redirect:cartList";
	}//---------------------------------------
	
	@PostMapping("/cartEdit")
	public String cartEdit(@ModelAttribute CartVO vo) {
		log.info("vo: "+vo);//cartNum, pqty
		
		shopService.editCart(vo);
		
		return "redirect:cartList";
	}//---------------------------------------
	/*스프링의 예외 처리 방법
	 * [1] @ExceptionHandler를 이용하는 방법
	 * [2] @ControllerAdvice를 이용하는 방법
	 * [3] @ResponseStatus 를 이용해서 HTTP상태코드 처리하는 방법
	 * */
	/*
	 * @ExceptionHandler(NumberFormatException.class) public String
	 * exceptionHandler(Exception ex, Model m) { String msg=ex.getMessage();//예외 메시지
	 * log.info("msg: "+msg); return util.addMsgBack(m, msg);
	 * }//---------------------------------------
	 */	
	

}////////////////////////////////////












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
		//�α����� ȸ���� ���̵�=>���ǿ��� ������
		
		MemberVO loginUser=(MemberVO)session.getAttribute("loginUser");
		String userid=loginUser.getUserid();
		
		CartVO cvo=new CartVO();
		cvo.setUserid_fk(userid);
		cvo.setPnum_fk(pnum);
		cvo.setPqty(pqty);
		//��ٱ��Ͽ� ��ǰ �߰�-----
		int n=shopService.addCart(cvo);
		
		//Ư��ȸ���� ��ٱ��� ��� ��������
		//List<CartVO> cartArr=shopService.selectCartView(userid);
		//m.addAttribute("cartArr", cartArr);
		//return "shop/cartList";
		
		/*==> ���⼭ foward������� �̵��ϸ� ������ ���ΰ�ħ�� ��� ��ǰ������ �߰��ϴ� ������ �߻��ȴ�
		 * ==> ��ٱ��� �Ѿ� ����
		 * ==> redirect������� �̵��ؾ� �Ѵ�
		 * */
		return "redirect:cartList";
		
	}//----------------------------------
	
	@GetMapping("/cartList")
	public String cartList(Model m, HttpSession session) {
		
		MemberVO loginUser=(MemberVO)session.getAttribute("loginUser");
		String userid=loginUser.getUserid();
		
		//Ư��ȸ���� ��ٱ��� ��� ��������
		List<CartVO> cartArr=shopService.selectCartView(userid);
		
		//Ư��ȸ���� ��ٱ��� �Ѿ�, ������Ʈ ��������
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
	/*�������� ���� ó�� ���
	 * [1] @ExceptionHandler�� �̿��ϴ� ���
	 * [2] @ControllerAdvice�� �̿��ϴ� ���
	 * [3] @ResponseStatus �� �̿��ؼ� HTTP�����ڵ� ó���ϴ� ���
	 * */
	/*
	 * @ExceptionHandler(NumberFormatException.class) public String
	 * exceptionHandler(Exception ex, Model m) { String msg=ex.getMessage();//���� �޽���
	 * log.info("msg: "+msg); return util.addMsgBack(m, msg);
	 * }//---------------------------------------
	 */	
	

}////////////////////////////////////












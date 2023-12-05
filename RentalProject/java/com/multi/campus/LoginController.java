package com.multi.campus;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.multi.common.NotUserException;
import com.multi.model.MemberVO;
import com.multi.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class LoginController {
	
	@Inject
	private MemberService mService;
	
	@GetMapping("/login")
	public String loginForm() {
		
		return "member/login";
	}
	@PostMapping("/login")
	public String loginProcess(HttpSession session, HttpServletResponse response,
			@ModelAttribute MemberVO user ,
			@RequestParam(defaultValue="off") String saveId) throws NotUserException  {
		log.info("user: "+user+", saveId: "+saveId);
		if(user.getUserid()==null||user.getUserpwd()==null) {
			return "redirect:login";
		}//if-----
		
		MemberVO loginUser=mService.loginCheck(user); 
		//아이디, 비번이 일치하지 않으면 NotUserException이 발생된다
		if(loginUser!=null) {//일치한다면
			////////////////////////////////////////
			session.setAttribute("loginUser", loginUser);
			///////////////////////////////////////////
			Cookie ck=new Cookie("uid", loginUser.getUserid());
			if(saveId.equals("on")) {
				ck.setMaxAge(7*24*60*60);//7일간 유효
			}else {
				ck.setMaxAge(0);//쿠키 삭제
			}
			ck.setPath("/");
			response.addCookie(ck);
		}
		return "redirect:index";
	}//-----------------------------
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:index";
	}//----------------------------

}/////////////////////



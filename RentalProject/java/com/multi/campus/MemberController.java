package com.multi.campus;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.multi.common.CommonUtil;
import com.multi.model.MemberVO;
import com.multi.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class MemberController {
	
	@Inject
	private CommonUtil util;
	
	@Inject
	private MemberService mService;
	
	@GetMapping("/signup")
	public String joinForm() {
		
		return "member/signup";
	}//--------------------------
	
	@PostMapping("/signup")
	public String joinProcess(Model m, @ModelAttribute MemberVO user) {
		log.info("user: "+user);
		if(user.getName()==null||user.getUserid()==null||user.getUserpwd()==null) {
			
			return "redirect:signup";
		}
		int n=mService.insert(user);		
		
		String msg=(n>0)?"회원가입 완료-로그인하세요":"가입 실패";
		String loc=(n>0)?"index":"javascript:history.back()";
		return util.addMsgLoc(m, msg, loc);
	}//--------------------------
	
	@GetMapping("/idCheck")
	public String idCheckForm() {
		
		return "member/idCheck";
	}//--------------------------
	
	@PostMapping("/idCheck")
	public String idCheckProcess(Model m, @RequestParam(defaultValue="") String userid) {
		
		boolean isUse=mService.idCheck(userid);
		String result=(isUse)?"success":"fail";
		String msg=(isUse)? userid+"를 사용할 수 있어요": userid+"는 이미 사용중 입니다";
		
		m.addAttribute("result",result);
		m.addAttribute("msg",msg);
		m.addAttribute("userid", userid);
		
		return "member/idCheckResult";
	}//-------------------------------

}////////////////////////////////////




package com.memo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.memo.model.MemoDAO;
import com.memo.model.MemoVO;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class MemoController { //POJO

	@Autowired
	private MemoDAO memoDao;
	
	
	@RequestMapping(value="/memo", method=RequestMethod.GET)
	public String memoForm() {
	
		return "memo/input";//뷰 네임
		//"/WEB-INF/views/memo/input.jsp"
	}//--------------------------
	
	@RequestMapping(value="/memo", method=RequestMethod.POST)
	public String memoInsert(Model model, @ModelAttribute("vo") MemoVO vo) {
		//@ModelAttribute를 붙이면 html의 input name하고 VO객체의 property명이 같을 경우 자동으로 입력값을 VO에
		//setting해준다
		//System.out.println(memo.getName()+"/"+memo.getMsg());
		log.info("memoDao: "+memoDao);
		log.info("vo: "+vo);
		if(vo.getName()==null|| vo.getName().trim().isEmpty()
				||vo.getMsg()==null||vo.getMsg().trim().isEmpty()) {
			
			return "redirect:memo";
			//뷰네임 앞에 "redirect:"을 붙이면 redirect방식으로 이동한다
		}
		
		
		int n=0;
		//for(int i=0;i<30;i++)
		n=memoDao.insertMemo(vo);
		
		String msg=(n>0)?"등록 성공":"등록 실패";
		String loc=(n>0)?"memoList":"memo";
		
		model.addAttribute("msg",msg);
		model.addAttribute("loc",loc);
		
		return "message";
		//접두어(prefix): /WEB-INF/views/
		//접미사(suffix): .jsp
		///WEB-INF/views/message.jsp
	}//----------------------------------
	
	//매핑하고 뷰네임 반환하기
	@RequestMapping(value="/memoList")//default GET방식
	public String memoList(Model model, @RequestParam(name="cpage", defaultValue="1") int cpage) {
		log.info("cpage: "+cpage);
		if(cpage<=0) {
			cpage=1;
		}
		
		int totalCount=memoDao.getTotalCount();
		int pageSize=5;
		int pageCount=(totalCount-1)/pageSize+1;
		
		if(cpage>pageCount) {
			cpage=pageCount;
		}
		
		int end=cpage*pageSize;
		int start=end-(pageSize-1);
		
		List<MemoVO> arr=memoDao.listMemo(start, end);
		
		int pagingBlock=5;
		int prevBlock=(cpage-1)/pagingBlock*pagingBlock;
		int nextBlock=prevBlock + (pagingBlock+1);
		
		//[1] memoDao의 listMemo(1,5)호출
		//List<MemoVO>객체를 model에 저장
		//키: memoArr
		model.addAttribute("memoArr", arr);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("prevBlock", prevBlock);
		model.addAttribute("nextBlock", nextBlock);
		model.addAttribute("cpage", cpage);
		
		//[2] MemoDAOMyBatis의 listMemo(1,5) 완성
		
		return "memo/list";
	}//----------------------------------
	
	@RequestMapping(value="/memoDelete", method=RequestMethod.POST)
	public String memoDelete(Model model,@RequestParam(name = "idx", defaultValue = "0") int idx) {
		log.info("idx: "+idx);
		//memoDao의 deleteMemo(idx)호출
		//유효성 체크
		if(idx==0) {
			return "redirect:memoList"; 
		}
		int n=memoDao.deleteMemo(idx);
		//그 결과 msg, loc
		String msg=(n>0)? "삭제 성공":"삭제 실패";
		String loc="memoList";
		
		model.addAttribute("msg",msg);
		model.addAttribute("loc",loc);
		
		return "message";
	}
	
	
	
}///////////////////////////////





















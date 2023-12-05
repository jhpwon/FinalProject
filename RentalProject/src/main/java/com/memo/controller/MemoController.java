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
	
		return "memo/input";//�� ����
		//"/WEB-INF/views/memo/input.jsp"
	}//--------------------------
	
	@RequestMapping(value="/memo", method=RequestMethod.POST)
	public String memoInsert(Model model, @ModelAttribute("vo") MemoVO vo) {
		//@ModelAttribute�� ���̸� html�� input name�ϰ� VO��ü�� property���� ���� ��� �ڵ����� �Է°��� VO��
		//setting���ش�
		//System.out.println(memo.getName()+"/"+memo.getMsg());
		log.info("memoDao: "+memoDao);
		log.info("vo: "+vo);
		if(vo.getName()==null|| vo.getName().trim().isEmpty()
				||vo.getMsg()==null||vo.getMsg().trim().isEmpty()) {
			
			return "redirect:memo";
			//����� �տ� "redirect:"�� ���̸� redirect������� �̵��Ѵ�
		}
		
		
		int n=0;
		//for(int i=0;i<30;i++)
		n=memoDao.insertMemo(vo);
		
		String msg=(n>0)?"��� ����":"��� ����";
		String loc=(n>0)?"memoList":"memo";
		
		model.addAttribute("msg",msg);
		model.addAttribute("loc",loc);
		
		return "message";
		//���ξ�(prefix): /WEB-INF/views/
		//���̻�(suffix): .jsp
		///WEB-INF/views/message.jsp
	}//----------------------------------
	
	//�����ϰ� ����� ��ȯ�ϱ�
	@RequestMapping(value="/memoList")//default GET���
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
		
		//[1] memoDao�� listMemo(1,5)ȣ��
		//List<MemoVO>��ü�� model�� ����
		//Ű: memoArr
		model.addAttribute("memoArr", arr);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("prevBlock", prevBlock);
		model.addAttribute("nextBlock", nextBlock);
		model.addAttribute("cpage", cpage);
		
		//[2] MemoDAOMyBatis�� listMemo(1,5) �ϼ�
		
		return "memo/list";
	}//----------------------------------
	
	@RequestMapping(value="/memoDelete", method=RequestMethod.POST)
	public String memoDelete(Model model,@RequestParam(name = "idx", defaultValue = "0") int idx) {
		log.info("idx: "+idx);
		//memoDao�� deleteMemo(idx)ȣ��
		//��ȿ�� üũ
		if(idx==0) {
			return "redirect:memoList"; 
		}
		int n=memoDao.deleteMemo(idx);
		//�� ��� msg, loc
		String msg=(n>0)? "���� ����":"���� ����";
		String loc="memoList";
		
		model.addAttribute("msg",msg);
		model.addAttribute("loc",loc);
		
		return "message";
	}
	
	
	
}///////////////////////////////





















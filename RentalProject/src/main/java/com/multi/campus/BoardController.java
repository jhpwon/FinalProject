package com.multi.campus;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.multi.common.CommonUtil;
import com.multi.model.BoardVO;
import com.multi.model.PagingVO;
import com.multi.service.BoardService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class BoardController {
	
	@Autowired
	private CommonUtil util;
	
	@Resource(name="boardService")
	private BoardService bService;
	
	
	@GetMapping("/board/write")
	public String boardForm() {
		
		return "board/boardWrite";
	}//-------------------
	
	@PostMapping("/user/write") // "/user/**" �� ������ �α����ؾ� �̿� ����
	public String boardInsert(Model m, BoardVO vo, 
			@RequestParam("mfilename") MultipartFile mf, HttpSession ses) {
		log.info("vo: "+vo);
		//1. ���� ���ε� ó��
		//[1] ���ε��� ������ ������
		ServletContext app=ses.getServletContext();
		String upDir=app.getRealPath("/resources/board_upload");
		log.info("upDir: "+upDir);
		
		File dir=new File(upDir);
		if(!dir.exists()) {
			dir.mkdirs();//���ε� ���丮 ����
		}
		
		//[2] ���ε��� ���ϸ�,����ũ�� �˾Ƴ���
		if(!mf.isEmpty()) {//÷���ߴٸ�
			String fname=mf.getOriginalFilename();//�������ϸ�
			long fsize=mf.getSize();//����ũ��
			//��������ƮŸ��
			//String ctype=mf.getContentType();
			UUID uuid=UUID.randomUUID();		
			String filename=uuid.toString()+"_"+fname;//������ ���ϸ�
			log.info("fname: "+fname+", filename: "+filename);
			
			vo.setFilename(filename);
			vo.setOriginFilename(fname);
			vo.setFilesize(fsize);
		
			//[3] ���ε� ó��--transferTo()
			try {
				mf.transferTo(new File(upDir, filename));
				
			} catch (Exception e) {
				log.error("���� ���ε� ����: "+e);
			}
		}//if-------------------
		
		//2. ��ȿ�� üũ
		if(vo.getUserid()==null||vo.getPasswd()==null||vo.getSubject()==null
				||vo.getUserid().trim().isEmpty()
				||vo.getPasswd().trim().isEmpty()) {
			return "redirect:write";
		}//if----------------
		//3. boardService�� insertBoard()ȣ��
		int n=0;
		String msg="�׽�Ʈ";
		String loc="list";
		if("write".equals(vo.getMode())) {
			//���۾���
			//for(int i=0;i<30;i++)
			n=bService.insertBoard(vo);
			msg="�۾��� ";
		}else if("edit".equals(vo.getMode())) {
			//�ۼ����ϱ�
			n=bService.updateBoard(vo);
			msg="�ۼ��� ";
		}else if("rewrite".equals(vo.getMode())) {
			//��۾���
			n=bService.rewriteBoard(vo);
			msg="��۾��� ";
		}
		
		msg+=(n>0)?"����":"����";
		loc=(n>0)?"../board/list":"javascript:history.back()";
		
		return util.addMsgLoc(m, msg, loc);
	}//--------------------
	
	
	
	@GetMapping("/board/list")
	public String boardListPaging(Model m, HttpServletRequest req, @ModelAttribute PagingVO page) {
		HttpSession session=req.getSession();
		//1. �� �Խñ� �� �������� or �˻��� �Խñ� �� ��������
		int totalCount=bService.getTotalCount(page);
		page.setTotalCount(totalCount);
		page.setPageSize(10);//�� ������ �� ������ ��� ����
		page.setPagingBlock(5);//����¡ �� ���� �� ����
		/////////////////////
		page.init(session);// ����¡ ���� ������ �����ϴ� �޼��� ȣ��
		/////////////////////////
		log.info("page: "+page);
		
		//2. �Խñ� ��� �������� or �˻��� �Խñ� ��� ��������
		List<BoardVO> boardArr=bService.getBoardAllPaging(page);
		
		//3. ������ �׺���̼� ���ڿ� �޾ƿ���
		String myctx=req.getContextPath();
		String loc="board/list";
		String pageNavi=page.getPageNavi(myctx, loc);
		
		m.addAttribute("boardArr", boardArr);
		m.addAttribute("paging", page);
		m.addAttribute("pageNavi", pageNavi);
		
		return "board/boardList3";
	}//-----------------------------------
	
	@GetMapping("/board/list_old")
	public String boardList(Model m, @RequestParam(defaultValue="1") int cpage) {
		if(cpage<1) {
			cpage=1;
		}
		
		//1. �� �Խñ� �� �������� or �˻��� �Խñ� �� ��������
		int totalCount=bService.getTotalCount();
		
		int pageSize=5;
		
		int pageCount=(totalCount-1)/pageSize+1;
		
		if(cpage>pageCount) {
			cpage=pageCount;
		}
		
		int start=(cpage-1)*pageSize;
		int end=start + (pageSize+1);
		
		//2. �Խñ� ��� �������� or �˻��� �Խñ� ��� ��������
		Map<String, Integer> map=new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		
		List<BoardVO> boardArr=bService.getBoardAll(map);
		
		
		m.addAttribute("totalCount", totalCount);
		m.addAttribute("boardArr", boardArr);
		m.addAttribute("pageCount", pageCount);
		m.addAttribute("cpage", cpage);
		
		return "board/boardList2";
	}//-------------------
	
	//Path���ٹ������ �����͸� �Ѱ��� ���
	@GetMapping("/board/view/{num}")
	public String boardView(Model m, @PathVariable("num") int num) {
		log.info("num: "+num);
		//1. ��ȸ�� ����
		bService.updateReadnum(num);
		
		//2 �۹�ȣ�� �۳��� ��������
		BoardVO board=bService.selectBoardByNum(num);
		
		m.addAttribute("board", board);
		
		return "board/boardView";
	}//-------------------
	
	@PostMapping("/user/edit")
	public String boardEditForm(Model m,@ModelAttribute BoardVO vo) {
		log.info("vo: "+vo);
		if(vo.getNum()==0||vo.getPasswd()==null) {
			return "redirect:/campus/board/list";
		}
		BoardVO dbVo=this.bService.selectBoardByNum(vo.getNum());
		if(dbVo==null) {
			return util.addMsgBack(m, "�ش� ���� �����");
		}
		//��� üũ
		if(!dbVo.getPasswd().equals(vo.getPasswd())) {
			return util.addMsgBack(m, "��й�ȣ�� ��ġ���� �ʾƿ�");
		}
		m.addAttribute("vo",dbVo);
		return "board/boardEdit";
	}//--------------------------------------
	
	@PostMapping("/user/delete")
	public String boardDelete(Model m,@RequestParam(defaultValue="0") int num,
			@RequestParam(defaultValue = "") String passwd, HttpSession session) {
		log.info("num: "+num+", passwd: "+passwd);
		if(num==0||passwd.isEmpty()) {
			return "redirect:../board/list";
		}
		//�ش� ���� DB���� ��������
		BoardVO vo=this.bService.selectBoardByNum(num);
		//��й�ȣ üũ
		String dbPasswd=vo.getPasswd();
		if(!dbPasswd.equals(passwd)) {
			return util.addMsgBack(m, "��й�ȣ�� ��ġ���� �ʾƿ�");
		}
		//db���� �� ����ó��
		int n=bService.deleteBoard(num);
		
		String upDir=session.getServletContext().getRealPath("/resources/board_upload");
		
		//������ ÷���� ������ �ִٸ� �������� ���� ó��
		if(n>0 && vo.getFilename()!=null) {
			File f=new File(upDir, vo.getFilename());
			if(f.exists()) {
				boolean b=f.delete();
				log.info("���� ���� ����: "+ b);
			}
		}
		
		String str=(n>0)? "���� ����":"���� ����";
		String loc=(n>0)?"../board/list":"javascript:history.back()";
		return util.addMsgLoc(m, str, loc);
	}//-----------------------------------
	
	@PostMapping("/user/rewrite")
	public String boardRewriteForm(Model m, BoardVO vo) {
		log.info("vo: "+vo);//num, subject
		
		m.addAttribute("vo", vo );
		
		return "board/boardRewrite";
	}
	

}///////////////////////////////////////









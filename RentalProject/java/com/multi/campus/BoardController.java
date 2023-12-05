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
	
	@PostMapping("/user/write") // "/user/**" 가 붙으면 로그인해야 이용 가능
	public String boardInsert(Model m, BoardVO vo, 
			@RequestParam("mfilename") MultipartFile mf, HttpSession ses) {
		log.info("vo: "+vo);
		//1. 파일 업로드 처리
		//[1] 업로드할 절대경로 얻어오기
		ServletContext app=ses.getServletContext();
		String upDir=app.getRealPath("/resources/board_upload");
		log.info("upDir: "+upDir);
		
		File dir=new File(upDir);
		if(!dir.exists()) {
			dir.mkdirs();//업로드 디렉토리 생성
		}
		
		//[2] 업로드한 파일명,파일크기 알아내기
		if(!mf.isEmpty()) {//첨부했다면
			String fname=mf.getOriginalFilename();//원본파일명
			long fsize=mf.getSize();//파일크기
			//파일컨텐트타입
			//String ctype=mf.getContentType();
			UUID uuid=UUID.randomUUID();		
			String filename=uuid.toString()+"_"+fname;//물리적 파일명
			log.info("fname: "+fname+", filename: "+filename);
			
			vo.setFilename(filename);
			vo.setOriginFilename(fname);
			vo.setFilesize(fsize);
		
			//[3] 업로드 처리--transferTo()
			try {
				mf.transferTo(new File(upDir, filename));
				
			} catch (Exception e) {
				log.error("파일 업로드 실패: "+e);
			}
		}//if-------------------
		
		//2. 유효성 체크
		if(vo.getUserid()==null||vo.getPasswd()==null||vo.getSubject()==null
				||vo.getUserid().trim().isEmpty()
				||vo.getPasswd().trim().isEmpty()) {
			return "redirect:write";
		}//if----------------
		//3. boardService의 insertBoard()호출
		int n=0;
		String msg="테스트";
		String loc="list";
		if("write".equals(vo.getMode())) {
			//원글쓰기
			//for(int i=0;i<30;i++)
			n=bService.insertBoard(vo);
			msg="글쓰기 ";
		}else if("edit".equals(vo.getMode())) {
			//글수정하기
			n=bService.updateBoard(vo);
			msg="글수정 ";
		}else if("rewrite".equals(vo.getMode())) {
			//답글쓰기
			n=bService.rewriteBoard(vo);
			msg="답글쓰기 ";
		}
		
		msg+=(n>0)?"성공":"실패";
		loc=(n>0)?"../board/list":"javascript:history.back()";
		
		return util.addMsgLoc(m, msg, loc);
	}//--------------------
	
	
	
	@GetMapping("/board/list")
	public String boardListPaging(Model m, HttpServletRequest req, @ModelAttribute PagingVO page) {
		HttpSession session=req.getSession();
		//1. 총 게시글 수 가져오기 or 검색한 게시글 수 가져오기
		int totalCount=bService.getTotalCount(page);
		page.setTotalCount(totalCount);
		page.setPageSize(10);//한 페이지 당 보여줄 목록 개수
		page.setPagingBlock(5);//페이징 블럭 단위 값 설정
		/////////////////////
		page.init(session);// 페이징 관련 연산을 수행하는 메서드 호출
		/////////////////////////
		log.info("page: "+page);
		
		//2. 게시글 목록 가져오기 or 검색한 게시글 목록 가져오기
		List<BoardVO> boardArr=bService.getBoardAllPaging(page);
		
		//3. 페이지 네비게이션 문자열 받아오기
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
		
		//1. 총 게시글 수 가져오기 or 검색한 게시글 수 가져오기
		int totalCount=bService.getTotalCount();
		
		int pageSize=5;
		
		int pageCount=(totalCount-1)/pageSize+1;
		
		if(cpage>pageCount) {
			cpage=pageCount;
		}
		
		int start=(cpage-1)*pageSize;
		int end=start + (pageSize+1);
		
		//2. 게시글 목록 가져오기 or 검색한 게시글 목록 가져오기
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
	
	//Path접근방식으로 데이터를 넘겼을 경우
	@GetMapping("/board/view/{num}")
	public String boardView(Model m, @PathVariable("num") int num) {
		log.info("num: "+num);
		//1. 조회수 증가
		bService.updateReadnum(num);
		
		//2 글번호로 글내용 가져오기
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
			return util.addMsgBack(m, "해당 글은 없어요");
		}
		//비번 체크
		if(!dbVo.getPasswd().equals(vo.getPasswd())) {
			return util.addMsgBack(m, "비밀번호가 일치하지 않아요");
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
		//해당 글을 DB에서 가져오기
		BoardVO vo=this.bService.selectBoardByNum(num);
		//비밀번호 체크
		String dbPasswd=vo.getPasswd();
		if(!dbPasswd.equals(passwd)) {
			return util.addMsgBack(m, "비밀번호가 일치하지 않아요");
		}
		//db에서 글 삭제처리
		int n=bService.deleteBoard(num);
		
		String upDir=session.getServletContext().getRealPath("/resources/board_upload");
		
		//서버에 첨부한 파일이 있다면 서버에서 삭제 처리
		if(n>0 && vo.getFilename()!=null) {
			File f=new File(upDir, vo.getFilename());
			if(f.exists()) {
				boolean b=f.delete();
				log.info("파일 삭제 여부: "+ b);
			}
		}
		
		String str=(n>0)? "삭제 성공":"삭제 실패";
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









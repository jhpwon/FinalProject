package com.multi.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.multi.mapper.BoardMapper;
import com.multi.model.BoardVO;
import com.multi.model.PagingVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardMapper bMapper;

	@Override
	public int insertBoard(BoardVO board) {
		
		return bMapper.insertBoard(board);
	}

	@Override
	public List<BoardVO> getBoardAll(Map<String, Integer> map) {
	
		return bMapper.getBoardAll(map);
	}

	@Override
	public List<BoardVO> getBoardAllPaging(PagingVO paging) {

		return bMapper.getBoardAllPaging(paging);
	}

	@Override
	public int getTotalCount() {	
		return bMapper.getTotalCount();
	}

	@Override
	public int getTotalCount(PagingVO paging) {
		return bMapper.getTotalCount(paging);
	}

	@Override
	public BoardVO selectBoardByNum(int num) {
		return bMapper.selectBoardByNum(num);
	}

	@Override
	public int updateReadnum(int num) {
		return bMapper.updateReadnum(num);
	}

	@Override
	public int deleteBoard(int num) {
		return bMapper.deleteBoard(num);
	}

	@Override
	public int updateBoard(BoardVO board) {
		return bMapper.updateBoard(board);
	}

	@Override
	public int rewriteBoard(BoardVO board) {
		//[1] 부모글(원글)의 글번호(num)로 부모글의 refer(글그룹번호), lev(답변레벨), sunbun(순번) 가져오기
		//==> select문
		BoardVO parent=this.selectRefLevSunbun(board.getNum());
		System.out.println("parent: "+parent);
		//[2] 기존에 달린 답변글 들이 있다면 내 답변글을 insert하기 전에 기존의 답변글들의 sunbun을 하나씩 증가시키자.
		//==> update문
		int n=this.updateSunbun(parent);
		
		//[3] 내가 쓴 답변 글을 insert 한다===> insert문
		//내가 쓴 답변글==>board
		//부모글 ==>parent (부모글의 refer,lev,sunbun)
		board.setRefer(parent.getRefer());//부모와 동일한 글그룹번호
		board.setLev(parent.getLev()+1);//답변레벨=부모lev+1
		board.setSunbun(parent.getSunbun()+1);//순번=부모sunbun+1
		
		return bMapper.rewriteBoard(board);
		
	}//-----------------------------------

	@Override
	public BoardVO selectRefLevSunbun(int num) {
		return bMapper.selectRefLevSunbun(num);
	}

	@Override
	public int updateSunbun(BoardVO parent) {

		return bMapper.updateSunbun(parent);
	}

}

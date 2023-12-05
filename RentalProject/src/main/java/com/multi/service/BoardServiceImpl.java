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
		//[1] �θ��(����)�� �۹�ȣ(num)�� �θ���� refer(�۱׷��ȣ), lev(�亯����), sunbun(����) ��������
		//==> select��
		BoardVO parent=this.selectRefLevSunbun(board.getNum());
		System.out.println("parent: "+parent);
		//[2] ������ �޸� �亯�� ���� �ִٸ� �� �亯���� insert�ϱ� ���� ������ �亯�۵��� sunbun�� �ϳ��� ������Ű��.
		//==> update��
		int n=this.updateSunbun(parent);
		
		//[3] ���� �� �亯 ���� insert �Ѵ�===> insert��
		//���� �� �亯��==>board
		//�θ�� ==>parent (�θ���� refer,lev,sunbun)
		board.setRefer(parent.getRefer());//�θ�� ������ �۱׷��ȣ
		board.setLev(parent.getLev()+1);//�亯����=�θ�lev+1
		board.setSunbun(parent.getSunbun()+1);//����=�θ�sunbun+1
		
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

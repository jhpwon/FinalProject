package com.multi.service;

import java.util.List;
import java.util.Map;

import com.multi.model.BoardVO;
import com.multi.model.PagingVO;

public interface BoardService {
	
	int insertBoard(BoardVO board);
	// �Խø�� ��������
	List<BoardVO> getBoardAll(Map<String,Integer> map);
	List<BoardVO> getBoardAllPaging(PagingVO paging);
	   
	
	   
	int getTotalCount();//�� �Խñ� �� ��������
	int getTotalCount(PagingVO paging);//�˻��� �� �Խñ� �� ��������

	// �۹�ȣ�� �ش��ϴ� �� ��������
	BoardVO selectBoardByNum(int num);   
	// ��ȸ�� �����ϱ�
	int updateReadnum(int num);
	   
	
	int deleteBoard(int num);
	int updateBoard(BoardVO board);

	// �亯��(������) �Խ��ǿ��� �亯�� �ޱ�
	int rewriteBoard(BoardVO board); //[�亯��]
	BoardVO selectRefLevSunbun(int num);//[�亯��]
	int updateSunbun(BoardVO parent);//[�亯��]

}//////////////////////////

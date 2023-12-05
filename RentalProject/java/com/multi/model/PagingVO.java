package com.multi.model;

import javax.servlet.http.HttpSession;

import lombok.Data;

/*����¡ ó�� �� �˻� ����� ���ȭ�Ͽ�
 * ������ �� �ֵ��� ����
 * */
@Data
public class PagingVO {
	
	//����¡ ó�� ���� ������Ƽ
	private int cpage;//���� ������ ������ ��ȣ
	private int pageSize=5;//�� ������ �� ������ ��� ����
	private int totalCount;//�� �Խñ� ��
	private int pageCount;//������ ��
	
	//DB���� ���ڵ带 ������� ���� ������Ƽ
	private int start;
	private int end;
	
	//����¡ ��ó�� ���� ������Ƽ
	private int pagingBlock=5;//�� �� �� ������ ������ ��
	private int prevBlock;//���� 5�� (���� ��)
	private int nextBlock;//���� 5�� (���� ��)
	
	//�˻� ���� ������Ƽ
	private String findType;//�˻� ����
	private String findKeyword;//�˻���
	
	
	/**����¡ ó�� ���� ������ �����ϴ� �޼���*/
	public void init(HttpSession session) {
		if(findType==null) {
			findType="";
		}
		
		if(findKeyword==null) {
			findKeyword="";
		}
		
		
		pageCount=(totalCount-1)/pageSize+1;
		if(cpage<1) {
			cpage=1;//1�������� ����Ʈ��
		}
		if(cpage>pageCount) {
			cpage=pageCount;//������ �������� ����
		}
		/*
		 * where rn > start and rn < end
		 * cpage	pageSize		start		end
		 * 1			5			 0			6
		 * 2						 5			11
		 * 
		 * */
		start=(cpage-1)*pageSize;
		end = start + (pageSize+1);
		
		//����¡ �� ����
		/*cpage
		 * [1][2][3][4][5] | [6][7][8][9][10] | [11][12][13][14][15] | [16][17][18][19][20]
		 * 
		 * cpage		pagingBlock			prevBlock(���� 5��)		nextBlock(���� 5��)
		 * 1~5				5					0						6
		 * 6~10									5						11
		 * 11~15								10						16
		 * ----------------------------------------------------------------------
		 * prevBlock=(cpage-1)/pagingBlock * pagingBlock
		 * nextBlock=prevBlock +(pagingBlock+1);
		 * */
		
		prevBlock=(cpage-1)/pagingBlock * pagingBlock;
		nextBlock=prevBlock +(pagingBlock+1);
	}//init()---------------------------------
	
	/**������ �׺���̼� ���ڿ��� ��ȯ�ϴ� �޼���
	 * myctx: ���ؽ�Ʈ��(/campus)
	 * loc : board/list
	 * */
	public String getPageNavi(String myctx, String loc) {
		String link=myctx+"/"+loc; // /campus/board/list
		String qStr="?findType="+findType+"&findKeyword="+findKeyword;
		link+=qStr;
		
		StringBuilder buf=new StringBuilder();
		buf.append("<ul class='pagination justify-content-center'>");
		
		if(prevBlock>0) {
			buf.append("<li class='page-item'>")
				.append("<a class='page-link' href='"+link+"&cpage="+prevBlock+"'>")
				.append("Prev")
				.append("</a>")
				.append("</li>");
		}
		for(int i=prevBlock+1;i<=nextBlock-1 && i<=pageCount  ;i++) {
			String css=(i==cpage)?"active":"";
			buf.append("<li class='page-item "+css+"'>")
			.append("<a class='page-link' href='"+link+"&cpage="+i+"'>")
			.append(i)
			.append("</a>")
			.append("</li>");
			
		}//for------
		if(nextBlock <= pageCount) {
			buf.append("<li class='page-item'>")
				.append("<a class='page-link' href='"+link+"&cpage="+nextBlock+"'>")
				.append("Next")
				.append("</a>")
				.append("</li>");
		}
		buf.append("</ul>");
		return buf.toString();
	}//----------------------------------------------

}/////////////////////




















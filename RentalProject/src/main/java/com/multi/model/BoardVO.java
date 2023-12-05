package com.multi.model;

import lombok.Data;

@Data
public class BoardVO {
	
	private String mode;//write,edit,rewrite
	
	private int num;
	private String userid;
	private String passwd;
	
	private String subject;
	private String content;
	
	private java.sql.Date wdate;
	private int readnum;
	
	private String filename;//물리적파일명(uuid_a.txt)
	private String originFilename;//원본파일명(a.txt)
	private long filesize;//첨부파일크기
	
	private String old_filename;//예전에 첨부한 파일명-> 글수정시 필요
	
	//답변형 게시판에서 필요한 프로퍼티
	private int refer;//글 그룹 번호
	private int lev;	//답변 레벨
	private int sunbun;//같은 글 그룹 내의 순서
	

}///////////////////////

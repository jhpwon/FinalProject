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
	
	private String filename;//���������ϸ�(uuid_a.txt)
	private String originFilename;//�������ϸ�(a.txt)
	private long filesize;//÷������ũ��
	
	private String old_filename;//������ ÷���� ���ϸ�-> �ۼ����� �ʿ�
	
	//�亯�� �Խ��ǿ��� �ʿ��� ������Ƽ
	private int refer;//�� �׷� ��ȣ
	private int lev;	//�亯 ����
	private int sunbun;//���� �� �׷� ���� ����
	

}///////////////////////

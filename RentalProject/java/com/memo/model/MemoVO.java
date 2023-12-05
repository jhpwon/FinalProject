package com.memo.model;

import java.sql.Date;

import lombok.Data;

@Data
public class MemoVO {
	
	private int idx;
	private String name;
	private String msg;
	private Date wdate;

}

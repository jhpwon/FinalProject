package com.multi.common;

import javax.inject.Inject;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.log4j.Log4j;

/*�������� ���� ó�� ���
 * [1] @ExceptionHandler�� �̿��ϴ� ���
 * [2] @ControllerAdvice�� �̿��ϴ� ���
 * [3] @ResponseStatus �� �̿��ؼ� HTTP�����ڵ� ó���ϴ� ���
 * 
 * servlet-context.xml�� component-scan�� com.multi.common ��Ű�� ����ؾ� ��
 * */
@ControllerAdvice
@Log4j
public class CommonExceptionAdvice {
	
	@Inject
	private CommonUtil util;
	
	@ExceptionHandler(NumberFormatException.class)
	public String exceptionHandler(Exception ex, Model m) {
		String msg=ex.getMessage();//���� �޽���
		log.info("msg: "+msg);
		return util.addMsgBack(m, msg);
	}//---------------------------------------
	
	@ExceptionHandler(NotUserException.class)
	public String loginExceptionHandler(Exception ex, Model m) {
		String msg=ex.getMessage();//���� �޽���
		log.info("msg: "+msg);
		return util.addMsgBack(m, msg);
	}//---------------------------------------

}///////////////////////////////////////

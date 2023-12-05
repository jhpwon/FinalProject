package com.multi.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.multi.model.MemberVO;

import lombok.extern.log4j.Log4j;
/*servlet-context.xml�� �� ����ϰ� �����Ѵ�
 * 
 *===================================================
 * <interceptors>
 * ...�߷�...
 * <interceptor>
			<mapping path="/admin/**"/>
			<beans:bean class="com.multi.interceptor.AdminCheckInterceptor"/>
	</interceptor>
	
 * </interceptors>
======================================================== 
 * */
@Log4j
public class AdminCheckInterceptor extends HandlerInterceptorAdapter  {
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) 
	throws Exception
	{
		log.info("preHandle()...");
		HttpSession ses=req.getSession();
		MemberVO user=(MemberVO)ses.getAttribute("loginUser");
		if(user!=null) {
			int mstate=user.getMstate();
			if(mstate!=9) {//�����ڰ� �ƴ϶��
				
				req.setAttribute("msg", "�����ڸ� �̿��� �� �ֽ��ϴ�");
				String loc=req.getContextPath()+"/index";
				req.setAttribute("loc", loc);
				
				RequestDispatcher disp=req.getRequestDispatcher("/WEB-INF/views/message.jsp");
				disp.forward(req, res);
				
				return false;
			}
		}
		return true;
	}//--------------------------------------
	

}///////////////////////////////

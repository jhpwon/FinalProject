package com.multi.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.multi.model.MemberVO;

import lombok.extern.log4j.Log4j;

/*Interceptor
 *  - ��Ʈ�ѷ��� ����Ǳ� ���� ���� ó���� ���� ������ 
 *    ������������ ���ͼ��Ϳ��� �����Ѵ�.
 *  - ���� ���
 *  1. ���ͼ��� ����
 *     [1] HandlerInterceptor�������̽��� ��ӹ޴� ���
 *     [2] HandlerInterceptorAdapter �߻�Ŭ������ ��ӹ޴� ���
 *      
 *  2. ���ͼ��� ��� => servlet-context.xml���� ����ϰ� ���� ������ ����
 * <!-- Interceptor ���� ===================================== -->
	<interceptors>
		<interceptor>
			<mapping path="/user/**"/>
			<mapping path="/admin/**"/>
			<beans:bean class="com.multi.interceptor.LoginCheckInterceptor"/>	
		</interceptor>	
	</interceptors>
 * */
@Log4j
public class LoginCheckInterceptor implements HandlerInterceptor{

	//[1] Controller�� �����ϱ� ���� ȣ��Ǵ� �޼���. true�� ��ȯ�ϸ� Controller�� �Ѿ��,
	//    false�� ��ȯ�ϸ� Controller�� ������� �ʴ´�.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("preHandle()...");
		
		HttpSession ses=request.getSession();
		MemberVO user=(MemberVO)ses.getAttribute("loginUser");
		if(user==null) {
			//�α������� �ʾҴٸ�
			request.setAttribute("msg", "�α����ؾ� �̿��� �� �ֽ��ϴ�");
			String loc=request.getContextPath()+"/login";
			request.setAttribute("loc", loc);
			
			RequestDispatcher disp=request.getRequestDispatcher("/WEB-INF/views/message.jsp");
			disp.forward(request, response);			
			return false;
		}		
		return true;
	}//-----------------------------------

	//[2] Controller�� ������ ��, ���� �並 �����ϱ� ���� ȣ��Ǵ� �޼���
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("postHandle()...");
	}

	//[3] Controller�����ϰ� �並 ������ �Ŀ� ȣ��Ǵ� �޼���
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("afterCompletion()...");
	}

}/////////////////////////////////

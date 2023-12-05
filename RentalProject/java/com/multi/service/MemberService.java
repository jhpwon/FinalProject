package com.multi.service;

import java.util.List;

import com.multi.common.NotUserException;
import com.multi.model.MemberVO;

public interface MemberService {
	
	public int insert(MemberVO vo);
	public boolean idCheck(String userid) ;
	
	public List<MemberVO> selectAll();
	
	public MemberVO selectByUserid(String userid) ;

	public MemberVO loginCheck(MemberVO user) throws NotUserException ;

}//////////////////////////////

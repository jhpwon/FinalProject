package com.multi.mapper;

import java.util.List;

import com.multi.common.NotUserException;
import com.multi.model.MemberVO;

public interface MemberMapper {
	
	public int insert(MemberVO vo);
	public int idCheck(String userid) ;
	
	public List<MemberVO> selectAll();
	
	public MemberVO selectByUserid(String userid) ;

}////////////////////////////////

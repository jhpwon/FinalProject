package com.multi.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.multi.common.NotUserException;
import com.multi.mapper.MemberMapper;
import com.multi.model.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	//servlet-context.xml에 BCryptPasswordEncoder빈 등록해야 함
	@Inject
	private BCryptPasswordEncoder passwordEncoder;
	
	@Inject
	private MemberMapper memberMapper;

	@Override
	public int insert(MemberVO vo) {
		//비밀번호 암호화 처리//////////////////////////////////////
		vo.setUserpwd(passwordEncoder.encode(vo.getUserpwd()));
		////////////////////////////////////////////////////////
		return this.memberMapper.insert(vo);
	}

	@Override
	public boolean idCheck(String userid) {
		boolean b=false;
		
		int cnt=this.memberMapper.idCheck(userid);
		
		b=(cnt==0)? true:false;
		
		return b;
	}

	@Override
	public List<MemberVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO selectByUserid(String userid) {		
		return this.memberMapper.selectByUserid(userid);
	}

	@Override
	public MemberVO loginCheck(MemberVO user) throws NotUserException {
		MemberVO dbuser=this.selectByUserid(user.getUserid());
		if(dbuser==null) {//아이디가 존재하지 않는 경우
			throw new NotUserException("아이디 또는 비밀번호가 일치하지 않아요");
		}
		//비밀번호 일치여부 체크
		boolean isMatch=passwordEncoder.matches(user.getUserpwd(), dbuser.getUserpwd());
			//사용자가 입력한 비번과 암호화된 비번이 일치하는지 여부를 체크해준다.
		System.out.println("isMatch: "+isMatch);
		if(isMatch) return dbuser;
		else throw new NotUserException("아이디 또는 비밀번호가 일치하지 않아요");
	}

}

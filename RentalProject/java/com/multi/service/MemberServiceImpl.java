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
	
	//servlet-context.xml�� BCryptPasswordEncoder�� ����ؾ� ��
	@Inject
	private BCryptPasswordEncoder passwordEncoder;
	
	@Inject
	private MemberMapper memberMapper;

	@Override
	public int insert(MemberVO vo) {
		//��й�ȣ ��ȣȭ ó��//////////////////////////////////////
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
		if(dbuser==null) {//���̵� �������� �ʴ� ���
			throw new NotUserException("���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʾƿ�");
		}
		//��й�ȣ ��ġ���� üũ
		boolean isMatch=passwordEncoder.matches(user.getUserpwd(), dbuser.getUserpwd());
			//����ڰ� �Է��� ����� ��ȣȭ�� ����� ��ġ�ϴ��� ���θ� üũ���ش�.
		System.out.println("isMatch: "+isMatch);
		if(isMatch) return dbuser;
		else throw new NotUserException("���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʾƿ�");
	}

}

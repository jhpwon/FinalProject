package com.memo.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
//��Ʈ�ѷ� ����: @Controller, 
//���� ���� : @Service,
//���Ӽ� ����(Persistence Layer): @Repository
//[����] servlet-context.xml�� component-scan������� ��Ű�� ����ؾ� ��

@Repository(value = "memoDAOMyBatis")
public class MemoDAOMyBatis implements MemoDAO {
	
	private final String NS="com.memo.model.MemoMapper";
	
	@Resource(name="sqlSession")
	private SqlSessionTemplate session;

	@Override
	public int insertMemo(MemoVO memo) {
		return session.insert(NS+".insertMemo", memo);
	}

	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return session.selectOne(NS+".getTotalCount");
	}

	@Override
	public List<MemoVO> listMemo(int start, int end) {
		Map<String, Integer> map=new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		
		return session.selectList(NS+".listMemo", map);
	}

	@Override
	public int deleteMemo(int idx) {
		return session.delete(NS+".deleteMemo", idx);
	}

	@Override
	public int updateMemo(MemoVO memo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemoVO getMemo(int idx) {
		// TODO Auto-generated method stub
		return null;
	}

}

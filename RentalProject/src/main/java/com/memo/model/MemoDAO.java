package com.memo.model;

import java.util.List;

public interface MemoDAO {

	int insertMemo(MemoVO memo);

	int getTotalCount();

	List<MemoVO> listMemo(int start, int end);

	int deleteMemo(int idx);

	int updateMemo(MemoVO memo);

	MemoVO getMemo(int idx);

}

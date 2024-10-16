package com.sist.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Update;

import com.sist.vo.FaqVO;
import com.sist.vo.QnaVO;

public interface CommunityService {
	public List<FaqVO> faqListData(String cate);
	public List<QnaVO> qnaListData(int start,int end);
	public int qnaRowCount();
    public String nicknameNullCheck(String id);
	public void qnaInsert(QnaVO vo);
    public QnaVO qnaDetailData(int qno);
    public List<QnaVO> qnaAnswerDetail(int groupId);
    public void qnaDelete(int groupId);
    public QnaVO qnaUpdateData(int qno);
    public void qnaUpdate(QnaVO vo);
}

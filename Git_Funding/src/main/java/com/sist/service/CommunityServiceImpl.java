package com.sist.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.CommunityDAO;
import com.sist.vo.FaqVO;
import com.sist.vo.QnaVO;

@Service
public class CommunityServiceImpl implements CommunityService{
	@Autowired
	private CommunityDAO cDao;

	@Override
	public List<FaqVO> faqListData(String cate) {
		return cDao.faqListData(cate);
	}

	@Override
	public List<QnaVO> qnaListData(int start,int end) {
		return cDao.qnaListData(start, end);
	}

	@Override
	public int qnaRowCount() {
		return cDao.qnaRowCount();
	}

	@Override
	public String nicknameNullCheck(String id) {
		return cDao.nicknameNullCheck(id);
	}

	@Override
	public void qnaInsert(QnaVO vo) {
	    String nickname = nicknameNullCheck(vo.getId());
	    vo.setNickname(nickname); 
	    cDao.qnaInsert(vo); 
	}

	@Override
	public QnaVO qnaDetailData(int qno) {
		return cDao.qnaDetailData(qno);
	}

	@Override
	public List<QnaVO> qnaAnswerDetail(int groupId) {
		return cDao.qnaAnswerDetail(groupId);
	}

	@Override
	public void qnaDelete(int groupId) {
		cDao.qnaDelete(groupId);
		
	}

	@Override
	public QnaVO qnaUpdateData(int qno) {
		return cDao.qnaUpdateData(qno);
	}

	@Override
	public void qnaUpdate(QnaVO vo) {
		cDao.qnaUpdate(vo);
	}
	
	
}

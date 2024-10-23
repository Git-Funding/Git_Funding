package com.sist.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;

public interface MypageService {
	public MemberVO mypageInfoData(String userId);
	public void SetNickname(Map map);
	public void mypageInfoUpdate(MemberVO vo);
	public List<WishVO> fundingWishList(String userId);
	public int fundingWishCount(String userId);
	public List<AlertVO> fundingAlertList(String userId);
	public int fundingAlertCount(String userId);
	public List<RewardBuyVO> getRewardBuyList(Map map);
    public int getTotalRewardBuyCount(String userId);
    public RewardBuyVO getPurchaseDetail(int rbno);
    public int goodsBuyTotalCount(String userId);
    
    // 프로젝트 관리자 페이지 펀딩 목록
    public List<FundingVO> myFundingListData(Map map);
	public int myFundingTotalCount(String userId);
	public int myFundingTotalPage(String userId);
}

package com.sist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
@Service
public class RewardServiceImpl implements RewardService{
	@Autowired
	private RewardDAO rDao;
	
	@Override
	public FundingRewardVO rewardBuyData(int rno) {
		// TODO Auto-generated method stub
		return rDao.rewardBuyData(rno);
	}

	@Override
	public void rewardBuyInsert(RewardBuyVO vo) {
		// TODO Auto-generated method stub
		rDao.rewardBuyInsert(vo);
	}

	@Override
	public void rewardBuyMinusAmount(RewardBuyVO vo) {
		// TODO Auto-generated method stub
		rDao.rewardBuyMinusAmount(vo);
	}

}

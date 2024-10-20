package com.sist.mapper;
import com.sist.vo.*; 
import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
public interface MypageMapper {
	@Select("SELECT userId,userName,userPwd,gender,email,post,addr1,addr2,phone,profile,nickname "
			+"FROM funding_member "
			+"WHERE userId=#{userId}")
	public MemberVO mypageInfoData(String userId);
	 
	@Update("UPDATE funding_member SET nickname=#{nickname} WHERE userId=#{userId}")
	public void SetNickname(Map map);  
	
	@Update("UPDATE funding_member SET email=#{email}, gender=#{gender}, addr1=#{addr1}, addr2=#{addr2}, post=#{post}, phone=#{phone} "
			+"WHERE userId=#{userId}")   
	public void mypageInfoUpdate(MemberVO vo);    
	   
	// 찜 목록  
	@Select("SELECT f.fno, f.title, f.type, f.thumb, w.wno, w.userId, w.cate, to_char(w.regdate, 'YYYY-MM-DD') as dbday " +
	        "FROM funding f " + 
	        "JOIN funding_wish w ON f.fno = w.pno " +
	        "WHERE w.userId = #{userId}")
	public List<WishVO> fundingWishList(String userId);
 
	@Select("SELECT COUNT(*) FROM funding_wish WHERE userId = #{userId}")
	public int fundingWishCount(String userId);
	
	// 알림 목록
	@Select("SELECT f.fno, f.title, f.type, f.thumb, a.ano, a.userId, to_char(a.regdate, 'YYYY-MM-DD') as dbday, " +
	        "to_char(f.startdate, 'YYYY-MM-DD') as startdate " +
	        "FROM funding f " +
	        "JOIN funding_alert a ON f.fno = a.fno " +
	        "WHERE a.userId = #{userId}")
	public List<AlertVO> fundingAlertList(String userId);
	
	@Select("SELECT COUNT(*) FROM funding_alert WHERE userId = #{userId}")
	public int fundingAlertCount(String userId);
  
	
	// 펀딩 구매 내역
	@Select("SELECT rb.rbno, rb.rno, rb.account, rb.price, rb.delivery, rb.totalprice, rb.userId, rb.name, rb.phone, rb.post, rb.addr1, rb.addr2, rb.requestMsg, rb.regdate,"
	        + "f.thumb, f.title, rb.num "  
	        + "FROM (SELECT rb.rbno, rb.rno, rb.account, rb.price, rb.delivery, rb.totalprice, rb.userId, rb.name, rb.phone, rb.post, rb.addr1, rb.addr2, rb.requestMsg, rb.regdate, rownum as num "
	        + "      FROM (SELECT * FROM reward_buy WHERE userId = #{userId} ORDER BY regdate DESC) "
	        + "      WHERE rownum <= #{end}) rb "
	        + "JOIN funding f ON rb.rno = f.fno "
	        + "WHERE rb.num BETWEEN #{start} AND #{end}")
	public List<RewardBuyVO> getRewardBuyList(Map map);


    @Select("SELECT COUNT(*) FROM reward_buy WHERE userId = #{userId}")
    public int getTotalRewardBuyCount(String userId);
 
    @Select("SELECT rb.rbno, f.thumb, f.title, rb.price, rb.delivery, rb.totalprice, rb.name, rb.phone, rb.post, rb.addr1, rb.addr2, rb.requestmsg, to_char(rb.regdate, 'YYYY-MM-DD') as regdate " +
            "FROM reward_buy rb " +
            "JOIN funding f ON rb.rno = f.fno " +
            "WHERE rb.rbno = #{rbno}") // rbno(구매 번호)를 통해 상세 정보 조회
    public RewardBuyVO getPurchaseDetail(int rbno);
}
        
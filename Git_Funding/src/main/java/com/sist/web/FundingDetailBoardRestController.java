package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.*;
import com.sist.vo.*;
@RestController
public class FundingDetailBoardRestController {
	@Autowired
	private FundingDetailBoardService bService;
	
	@GetMapping(value="funding/notice_list_vue.do", produces="text/plain;charset=UTF-8")
	public String funding_notice_list(int fno, int page) throws Exception{
		int rowSize=10;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		Map map=new HashMap();
		map.put("fno", fno);
		map.put("start", start);
		map.put("end", end);
		List<FundingDetailNoticeVO> list=bService.fundingNoticeListData(map);
		int totalpage=bService.fundingNoticeTotalPage();
		
		final int BLOCK=5;
		int startpage=((page-1)/BLOCK*BLOCK)+1;
		int endpage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage) {
			endpage=totalpage;
		}
		
		map=new HashMap();
		map.put("list", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startpage", startpage);
		map.put("endpage", endpage);
		 
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		return json;
	}
}

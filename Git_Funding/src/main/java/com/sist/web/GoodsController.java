package com.sist.web;

import java.text.DecimalFormat;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.GoodsService;
import com.sist.vo.CartVO;
import com.sist.vo.GoodsVO;

@Controller
public class GoodsController {
	private GoodsService gService;
	private DecimalFormat df=new DecimalFormat("###,###,###");
	@Autowired
	public GoodsController(GoodsService service) {
		this.gService=service;
	}
	
	@GetMapping("goods/cart.do")
	public String goods_cart() {
		return "goods/cart";
	}
	@GetMapping("goods/list.do")
	public String goods_list(String cate,String cateInfo,Model model) {
		
		model.addAttribute("cate",cate);
		model.addAttribute("cateInfo",cateInfo);
		
		return "goods/list";
	}
	@PostMapping("goods/order.do")
	public String goods_order(HttpServletRequest request,Model model) throws Exception {
		
		String[] fgcno=request.getParameterValues("fgcno");
		String[] account=request.getParameterValues("account");
		List<Integer> fg=new ArrayList<Integer>();
		for(int i=0;i<fgcno.length;i++) {
			fg.add(Integer.parseInt(fgcno[i]));
		}
		int totalgoods=0;
		int delivery=0;
		List<CartVO> cList=gService.orderListData(fg);
		for(int i=0;i<cList.size();i++) {
			cList.get(i).setAccount(Integer.parseInt(account[i]));
			cList.get(i).setTotalprice(Integer.parseInt(account[i])*Integer.parseInt(cList.get(i).getPrice().replaceAll("[^0-9]", "")));
			totalgoods+=Integer.parseInt(account[i])*Integer.parseInt(cList.get(i).getPrice().replaceAll("[^0-9]", ""));
			cList.get(i).setTpay(df.format(Integer.parseInt(account[i])*Integer.parseInt(cList.get(i).getPrice().replaceAll("[^0-9]", ""))));
			String deli=cList.get(i).getGvo().getDelivery();
			if(deli.equals("텐텐배송")) {
				delivery+=3000;
			}else if(deli.equals("업체조건배송")) {
				delivery+=5000;
			}else if(deli.equals("텐바이텐배송")){
        		delivery+=4000;
        	}else if(deli.equals("해외직구 배송")){
        		delivery+=10000;
        	}
		}
		
		String total=df.format(totalgoods);	
		String delTotal=df.format(delivery);
		String totalpay=df.format(delivery+totalgoods);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(cList);
		
		
		model.addAttribute("cList",json);
		model.addAttribute("totalgoods",total);
		model.addAttribute("totaldeli",delTotal);
		model.addAttribute("totalpay",totalpay);
		model.addAttribute("vo","''");
		return "goods/order";
	}
	@PostMapping("goods/orderDic.do")
	public String goods_orderDic(HttpServletRequest request,Model model) throws Exception{
		String account=request.getParameter("account");
		String optionSelect=request.getParameter("optionSelect");
		String fg_no=request.getParameter("fg_no");
		
		GoodsVO vo=gService.goodsOrderData(Integer.parseInt(fg_no));
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		

		int goodsprice=Integer.parseInt(vo.getPrice().replaceAll("[^0-9]", ""));
		int ea=Integer.parseInt(account);
		int totalprice=goodsprice*ea;
		
		String deli=vo.getDelivery();
		int delivery=0;
		if(deli.equals("텐텐배송")) {
			delivery+=3000;
		}else if(deli.equals("업체조건배송")) {
			delivery+=5000;
		}else if(deli.equals("텐바이텐배송")){
    		delivery+=4000;
    	}else if(deli.equals("해외직구 배송")){
    		delivery+=10000;
    	}
		
		String total=df.format(totalprice);
		String delTotal=df.format(delivery);
		String totalpay=df.format(totalprice+delivery);
		model.addAttribute("vo",json);
		model.addAttribute("account",account);
		model.addAttribute("option",optionSelect);
		model.addAttribute("fg_no",fg_no);
		model.addAttribute("totalgoods",total);
		model.addAttribute("totaldeli",delTotal);
		model.addAttribute("totalpay",totalpay);
		model.addAttribute("cList","''");
		return "goods/order";
	}
	
	@GetMapping("goods/order_ok.do")
	public String goods_order_ok() {
		return "goods/order_ok";
	}
	@GetMapping("goods/detail_before.do")
	   public String goods_detail_before(int fgno,HttpServletResponse response,RedirectAttributes ra)
	   {
		   // forward => Model를 이용해서 데이터 전송 
		   // redirect => RedirectAttributes이용해서 데이터 전송 
		   Cookie cookie=new Cookie("food_"+fgno, String.valueOf(fgno));
		   cookie.setMaxAge(60*60*24);
		   cookie.setPath("/");
		   response.addCookie(cookie);
		   ra.addAttribute("fgno", fgno);// ?fno=1
		   return "redirect:../goods/detail.do";
	   }
	@GetMapping("goods/detail.do")
	public String goods_detail(int fgno,Model model) {
		
		model.addAttribute("fg_no",fgno);
		
		return "goods/detail";
	}
}

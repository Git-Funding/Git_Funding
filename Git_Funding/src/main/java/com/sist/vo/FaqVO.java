package com.sist.vo;

import lombok.Data;

/*
 * �̸�       ��?       ����            
-------- -------- ------------- 
NO       NOT NULL NUMBER(38)    
CATE     NOT NULL VARCHAR2(50)  
QUESTION NOT NULL VARCHAR2(255) 
ANSWER   NOT NULL CLOB          

 */
@Data
public class FaqVO {
	private int no;
	private String cate, question, answer;
}

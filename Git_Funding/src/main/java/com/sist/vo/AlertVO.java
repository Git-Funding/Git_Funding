package com.sist.vo;
/*
ANO     NOT NULL NUMBER       
USERID           VARCHAR2(20) 
FNO              NUMBER       
REGDATE          DATE  
 */
import java.util.*;
import lombok.Data;
@Data
public class AlertVO {
	private int ano, fno;
	private String userId;
	private Date regdate;
}

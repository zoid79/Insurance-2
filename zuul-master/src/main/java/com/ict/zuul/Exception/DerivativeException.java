package com.ict.zuul.Exception;

public class DerivativeException extends Exception{
	
	private final int ERR_CODE;// 생성자를 통해 초기화 한다.
	
	public DerivativeException(String msg, int errcode){ //생성자		
		super(msg);		
		this.ERR_CODE=errcode;	
		}	
	
	public DerivativeException(String msg){// 생성자		
		this(msg, 403);// ERR_CODE를 100(기본값)으로 초기화한다.	
	}	
		
	public int getErrCode(){// 에러 코드를 얻을 수 있는 메서드도 추가한다.		
		return ERR_CODE;// 이 메서드는 주로 getMessage()와 함께 사용될 것이다.	
	}	
}

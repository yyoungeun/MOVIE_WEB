package com.sunday.cmn;

/**
 * @author sist
 *
 */
public class DTO {
	private int    total		;	// 총글수
	private int    num 			;	// 글번호
	
	public DTO(){}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "DTO [total=" + total + ", num=" + num + "]";
	}
}

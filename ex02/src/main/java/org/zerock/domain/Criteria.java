package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
//	1)페이지 번호(pageNum)
	private int pageNum;
//	2)한 페이지당 몇 개의 데이터를 보여줄 것인지?
	private int amount;
//	3)검색종류 
	private String type;
//	4)keyword
	private String Keyword;
	
	//생성 
	/*
		기본생성자에 이미 매개변수의 값에 1,10을 설정해뒀기 때문에 
		http://localhost:8080/board/list에서도 페이징이 된 채로 보여지는 것이다.
	*/
	public Criteria() {
		this(1,10);
	}
	
	//기본생성자에서 두개의 매개변수의 값을 가지기 위해 2개의 매개변수를 가지는 Criteria메서드를 만든 것.
	public Criteria(int pageNum, int amount) {
		this.pageNum=pageNum;
		this.amount=amount;
	}

}

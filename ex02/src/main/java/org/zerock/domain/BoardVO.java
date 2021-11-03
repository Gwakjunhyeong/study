package org.zerock.domain;

import java.util.Date;

import lombok.Data;

//영속영역이란? 테이블과 테이블의 데이터와 연결할 클래스VO를 연결한 것을 의미 
@Data
public class BoardVO {
	//게시판번호
	private int bno;
	//제목
	private String title;
	//내용
	private String content;
	//작성자
	private String writer;
	//작성일자
	private Date regDate;
	//수정일
	private Date updateDate;
	
	private int replycnt;
}

package org.zerock.domain;

import java.util.Date;
import lombok.Data;

//영속영역이란? 테이블과 테이블의 데이터와 연결할 클래스VO를 연결한 것을 의미 
@Data
public class ReplyVO {
	// 댓글번호
	private int rno;
	// 게시판번호
	private int bno;
	// 댓글내용
	private String reply;
	// 댓글작성자
	private String replyer;
	// 댓글작성일 
	private Date replydate;
	// 댓글수정
	private Date updatedate;
}

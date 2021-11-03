package org.zerock.service;

import java.util.List;

import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.domain.ReplyVO;

public interface ReplyService {
	//댓글쓰기 (insert) - Create
	public int register(ReplyVO vo);
	//댓글 상세페이지 (select) - Read
	public ReplyVO get(int rno);
	//댓글삭제 (delete) - Delete
	public int remove(int rno);
	//댓글수정 (update) - Update
	public int modify(ReplyVO vo);
	//댓글리스트목록

	//댓글 페이징 만든것을 포함하지 않은 댓글리스트목
	//public List<ReplyVO> getList(Criteria cri, int bno);

	// 댓글목록리스트(select된 결과가 여러 건이니까 ArrayList 타입)
	public ReplyPageDTO getList(Criteria cri, int bno);
	
}

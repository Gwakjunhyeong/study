package org.zerock.service;

import java.util.List;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardService {
	//명백하게 반환해야될 데이터가 있는 메서드는 리턴타입을 지정할 수 있다. ex) 추상메서드 get, 추상메서드 getList
	//게시판 글쓰기 
	public void register(BoardVO board);
	//게시판 상세페이지 
	public BoardVO get(Long bno);
	//게시판 글수정   
	public boolean modify(BoardVO board);
	//게시판 글삭제 
	public boolean remove(Long bno);
	//게시판 목록리스트  
	public List<BoardVO> getList(Criteria cri);
	
	public int getTotalCount(Criteria cri);
	
}

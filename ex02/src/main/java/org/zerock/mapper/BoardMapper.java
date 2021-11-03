package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardMapper {// 인터페이스
	// List-arraylist를 나타냄(움직임이 있는 배열)
	//추상메서드 (게시판 목록 리스트)
	//게시판 목록리스트(paging처리 안된거)
	public List<BoardVO> getList(); 
	//게시판 목록리스트(paging처리 된)
	public List<BoardVO> getListWithPaging(Criteria cri); 
	//tbl_board테이블의 전체 갯수
	public int getTotalCount(Criteria cri);
	
	// 추상 메서드 (게시판 글쓰기)
	// 만약 사용자에게 글등록이 완료 되었습니다. 라고 표기하고 싶을 경우 리턴값을 int로 가져가면 된다.
	public void insert(BoardVO board);
	// 추상 메서드 (게시판 글쓰기) insert문이 실행되고 생성된 pk값을 
	public void insertSelectKey(BoardVO board);
	// 추상 메서드( 게시판 목록 리스트에서 제목을 클릭한 후 나오는 상세페이지 조회)
	public BoardVO read(Long bno);
	// 추상 메서드 (게시판 글 삭제)  리턴타입을 int로 주는 이유는 글이 삭제 되었다면 리턴값 1을 보여주며 삭제되었음을 알려주기위해 
	public int delete(long bno);
	// 추상 메서드 (게시판 글 수정)  리턴타입을 int로 주는 이유는 글이 업데이트 되었다면 리턴값 1을 보여주며 업데이트 되었음을 알려주기위해 
	public int update(BoardVO board);
	
	
	//tble_board테이블 replycnt컬럼에 +1
	//두개 이상의 매개변수를 받을때에는 @Param으로 매개변수를 받는다.
	public void updateReplyCnt(@Param("bno") int bno,@Param("amount") int amount);
	

}

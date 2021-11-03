package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.domain.ReplyVO;

public interface ReplyMapper {
	//댓글쓰기 (insert) - Create
	public int insert(ReplyVO vo);
	//댓글 상세페이지 (select) - Read
	public ReplyVO read(int rno); //select태그에 #{bno}에 값을 보내기 위해
	//댓글삭제 (delete) - Delete
	public int delete(int rno);
	//댓글수정 (update) - Update
	public int update(ReplyVO vo);
	//댓글리스트목록
	//(MYBatis)Mapper는 매개변수로 무조건 하나밖에 못보낸다. 그래서 @Param 어노테이션을 사용하여 보내는 것이다.
	//단. service나 controller는 두개의 매개변수를 보낼수 있다  
	//ReplyVO가 들어간 이유는 mapper.xml에서 ReplyVO안에 들어있는 값들을 필요로 하고 그것을 사용해야 하기 때문에
	public List<ReplyVO> getListWithPaging(@Param("cri") Criteria cri,
										   @Param("bno") int bno);
	
	public int getCountByBno(int bno);
}

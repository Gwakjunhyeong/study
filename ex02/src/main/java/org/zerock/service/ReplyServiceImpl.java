package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.BoardMapper;
import org.zerock.mapper.ReplyMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

//Serviml은 무조건 Business구조임을 나타내기 위해 무조건 @Service어노테이션을 적용해준다.
@Service
@Log4j
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService {
//	@Setter(onMethod_=@Autowired) -> sptringFramework 버전이 4.3이상이면 @AllArgsConstructor을 사용하여 생략가능 
	private ReplyMapper mapper;
	private BoardMapper boardmapper;
	//댓글생성
	@Transactional
	@Override
	public int register(ReplyVO vo) {
		log.info("register......."+vo);
		boardmapper.updateReplyCnt(vo.getBno(), 1);
		return mapper.insert(vo);
	}
	//댓글 상세 페이지
	@Override
	public ReplyVO get(int rno) {
		log.info("get......."+rno);
		return mapper.read(rno);
	}
	@Transactional
	//댓글삭제
	@Override
	public int remove(int rno) {
		log.info("remove......"+rno);
		ReplyVO vo =mapper.read(rno);
		boardmapper.updateReplyCnt(vo.getBno(), -1);
		return mapper.delete(rno);
	}
	//댓글수정 
	@Override
	public int modify(ReplyVO vo) {
		log.info(vo);
		return mapper.update(vo);
	}
	//댓글목록리스트(select된 결과가 여러건이니까 ArrayList타입)
//	@Override
//	public List<ReplyVO> getList(Criteria cri, int bno) {
//		log.info("get Refplye");
//		return mapper.getListWithPaging(cri, bno);
//	}
	
	@Override
	public ReplyPageDTO getList(Criteria cri, int bno) {
		log.info("Get Reply List of a Board: "+ bno);
		
		return new ReplyPageDTO(
				mapper.getCountByBno(bno),
				mapper.getListWithPaging(cri, bno));
								
	}
	
	
	
	}

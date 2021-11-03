package org.zerock.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

//import시 빨간줄이 그여져서 안될 경우 pom.xml을 확인하기. 
//@RunWith는 @RunWith는 JUnit 프레임워크의 테스트 실행 방법을 확장할 때 사용하는 어노테이션이다.
//Junit 의 기본 테스트 러너인 BlockJunit4ClassRunner 대신
//@RunWith 이용해 지정한 클래스를 이용해 테스트 메소드를 수행하도록 지정해주는 어노테이션
@RunWith(SpringJUnit4ClassRunner.class)
//자동으로 딱 한 번만 만들어줄 애플리케이션 컨텍스트의 설정파일위치를 지정한 것이다.
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")

@Log4j
public class ReplyMapperTests {
	@Setter(onMethod_=@Autowired)
	private ReplyMapper mapper;
	//mapper가 제대로 작동하는지 확인하는지 테스
	@Test
	public void testMapper() {
		log.info(mapper);
	}
	
	@Test
	public void testCreate() {
		ReplyVO vo = new ReplyVO();
		
		vo.setBno(7);
		vo.setReply("댓글 테스트");
		vo.setReplyer("replyer");
		
		mapper.insert(vo);
	}
	
	@Test
	public void testRead() {
		int targetRno = 1;
		ReplyVO vo = mapper.read(targetRno);
		
		log.info(vo);
	}
	
	@Test
	public void testDelete() {
		int targetRno=1;
		mapper.delete(targetRno);
				
	}
	
	public void testUpdate() {
		ReplyVO vo = new ReplyVO();
		//replyVO Model에 rno값 저장
		vo.setRno(2);
		//replyVO Model에 reply값 저장
		vo.setReply("Update Reply");
		
		int count = mapper.update(vo);
		log.info("UPDATE COUNT" + count);
	
	}
	
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		List<ReplyVO> replies= mapper.getListWithPaging(cri, 6);
		replies.forEach(reply -> log.info(reply));
	}
}

package org.zerock.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


@RunWith(SpringJUnit4ClassRunner.class) //
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") //
@Log4j
public class BoardServiceTests {
	@Setter(onMethod_=@Autowired)
	//인스턴스 변수 (= service) 에 알맞은 타입의 객체 (= BoardService ) 를 자동으로 주입해달라는 어노테이션
	private BoardService service;
	
	//BoardService 객체가 제대로 주입이 가능한지 확인하는 작업 
	@Test
	public void testExist() {
		
		log.info(service);
		assertNotNull(service);
	}
	
	@Test
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newbie");
		
		service.register(board);
		
		log.info("생성된 게시물의 번호: "+board.getBno());
		
	}
	
	@Test
	public void testGetList() {
		Criteria cri = new Criteria();
		service.getList(cri).forEach(board -> log.info(board));
	}
	
	@Test	
	public void testGet() {
		log.info(service.get(1L));
	}
	
	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		board.setTitle("제목을 수정합니다.");
		board.setContent("내용을 수정합니다.");
		board.setWriter("곽준");
		board.setBno(1);
		log.info("MODIFY RESULT : " + service.modify(board));	
	}
	
	@Test
	public void testDelete() {
		log.info("REMOVE RESULT : "+ service.remove(2L));
	}
}
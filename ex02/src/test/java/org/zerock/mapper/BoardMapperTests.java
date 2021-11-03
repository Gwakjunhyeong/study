package org.zerock.mapper;

import org.junit.Test; 
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

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
public class BoardMapperTests {
	@Setter(onMethod_ = @Autowired)
	//인스턴스 변수 (= mapper) 에 알맞은 타입의 객체 (= BoardMapper ) 를 자동으로 주입해달라는 어노테이션
	private BoardMapper mapper;
	
	@Test
	public void testGetList() {
		mapper.getList().forEach(board -> log.info(board));
	}
	
	@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newbid");
		
		mapper.insert(board);
		
		log.info(board);
	}
	
	@Test
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글 select key");
		board.setContent("새로 작성하는 내용 select key");
		board.setWriter("newbie");
		
		mapper.insertSelectKey(board);
		
		log.info(board);
	}
	
	@Test
	public void testRead() {
		
		//존재하는 게시물 번호로 테스트
		BoardVO board = mapper.read(5L);
		
		log.info(board);
	}
	
	@Test
	public void testDelete() {
		log.info("DELETE COUNT: " + mapper.delete(3L));
	}
	
	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		board.setTitle("수정된 제목");
		board.setContent("수정된` 내용");
		board.setWriter("user00");
		board.setBno(5);
		
		int count = mapper.update(board);
		log.info("UPDATE COUNT: " + count);
	}


}

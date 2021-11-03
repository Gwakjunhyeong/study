package org.zerock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class) //
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") //
@Log4j
public class SampleServiceTests {
	@Setter(onMethod_=@Autowired)
	//인스턴스 변수 (= service) 에 알맞은 타입의 객체 (= BoardService ) 를 자동으로 주입해달라는 어노테이션
	private SampleService service;

	@Test
	public void testAdd() throws Exception {
		log.info(service);
		log.info(service.getClass().getName());
	}
}

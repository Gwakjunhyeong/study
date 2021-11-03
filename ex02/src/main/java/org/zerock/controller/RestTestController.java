package org.zerock.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;
import org.zerock.domain.Ticket;

import lombok.extern.log4j.Log4j;


//컨트롤러가 오류 시 servlet-context.xml에 이 로직이 있는지 확인하기 -> <context:component-scan base-package="org.zerock.controller" /> 
//rest방식은 .jsp를 이용하여 view를 보여주는 것이 아닌 웹브라우저로 순수한 데이터를 바로 보여주는 것 
@RestController //Rest컨트롤러는 어노테이션을 @Controller가 아닌 @RestController이다. 
@RequestMapping("rest")
@Log4j
public class RestTestController {
 //jsp파일은 기본적으로 content type을 만들어 주지만 rest방식은 직접 produces로 지정해줘야한다.
	@GetMapping(value="getText",produces="text/plain; charset=UTF-8")
	public String setText() {
		log.info("MIME TYPE:" + MediaType.TEXT_PLAIN_VALUE);	
		return "안녕하세요";
	}
	
	@GetMapping(value="getSample",produces= {MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
											 MediaType.APPLICATION_XML_VALUE})
	public SampleVO getSample() {
		//XML의 태그이름은 리런타입의 SampleVO의 이름으로 태그이름이 정해진다. 
		return new SampleVO(112, "스타","로드"); //return Sample newSampleVO(112,"스타","로드");
	}
	
	//ArrayList를 반환
	@GetMapping(value="getList",produces= {MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			 MediaType.APPLICATION_XML_VALUE})
	public List<SampleVO> getList() {
		
		return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i,i+"First",i+"Last")).collect(Collectors.toList());
				
	}
	//ResponseEntity는 HttpHeader와 HttpBody 그리고 HttpStatus 를 포함하는 클래스이다.
	//RestponseEntit 반환 -> jsp파일을 브라우저로 띄울 경우 오류에 관한 것을 jsp에서 알아서 보여주지만
	//						rest방식을 사용할 경우에는 알아서 오류를 보여주는 것이 없기 때문에 ResponseEntity가 그 오류를 보여준다.
	
	@GetMapping(value="/check",params= {"height","weight"})
	public ResponseEntity<SampleVO> check(double height, double weight) {
		SampleVO vo= new SampleVO(0,""+height,""+weight);
		
		ResponseEntity<SampleVO> result = null;
		
		if(height<150) {
			//HttpStaus를 통해 임의의 파라미터를 지정한 height와 weight의 값이 올바르다면 통신이 잘된건지 개발자도구 network에 있 status code에 값 보여준다.
			result=ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		}else {
			result=ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		return result;
	}
	//일반적인 데이터를 웹에 보낼때는 url에 read? bno = 3처럼 ? 을 쓰지만
	//									 변수   
	//rest방식은 url에 ?을 쓰지않고 getPath를 통해서 파라미터로 데이터를 바로 보낼수 있다 product/ bags / 1234
	//																 	 Mapping  파라미터 파라미
	@GetMapping("/product/{cat}/{pid})")

	public String[] getPath(@PathVariable("cat")String cat,@PathVariable("pid")String pid) {
		return new String[] {"category:" +cat,"productid:"+pid};
	}
	
	
	@PostMapping("/ticket")
	// Rest방식에서는 내가 일반적으로 알고있는 자바의 객체를 파라미터로 받고 싶다면 @RequestBody를 쓰면된다.
	
	//rest방식은 테스트를 하고자할때에는 두가지방식이 있다.
	//첫번째는 자바스크립트를 만들어 테스트를 할 수 있고,따로 프로그램을 이용하여 테스트할 수 있다.
	public Ticket convert(@RequestBody Ticket ticket) {
		log.info("convert..........ticket"+ticket);
		return ticket;
	}
	
	/*
	 Rest방식을 쓸 때 전송방식 
  	 *Create -> Post방식
	 *Read   -> get방식 
	 *Update -> Put방식
	 *Delete -> Delete방
	*/

//	
//	@PostMapping("/ticket") //Create -> Post방식 (insert)
//	public Ticket convert1(@RequestBody Ticket ticket) {
//		log.info("convert..........ticket"+ticket);
//		return ticket;
//	} 
	
	@GetMapping("/ticket") //Read   -> get방식 (select)
	public Ticket convert2(@RequestBody Ticket ticket) {
		log.info("convert..........ticket"+ticket);
		return ticket;
	} 
	
	@PutMapping("/ticket") //Update -> Put방식 (update)
	public Ticket convert3(@RequestBody Ticket ticket) {
		log.info("convert..........ticket"+ticket);
		return ticket;
	} 
	
	@DeleteMapping("/ticket") //Delete -> Delete방식 (delete)
	public Ticket convert4(@RequestBody Ticket ticket) {
		log.info("convert..........ticket"+ticket);
		return ticket;
	} 
}
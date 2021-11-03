package org.zerock.controller;

import java.util.List;

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
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@AllArgsConstructor //생성자를 만들어 자동으로 ReplyService타입의 객체에 연동하여 의존성을 주입. 
@RequestMapping("/replies")
public class ReplyController {
	
	ReplyService service; 
	//테스트를 해보기 위해선 자바스크립트를 만들거나 따로 프로그램으로 실행히켜봐야한다. 프로그램은 설치해놨다.
	//자바스크립트 타입으로 전송을 해야한다.  
	
	/*
 	@RestController - Controller가 Rest방식을 처리하기 위한 것임을 명시하기 위함.
 	@ResponseBody - 일반적인 JS와 같은 뷰로 전달되는 게 아니라 데이터 자체를 전달하기 위한 용도
 					JSON 데이터를원하는 타입의 객체로 변환해야 하는 경우에 주로 사용
 	@PathVariable - URL 경로에 있는 값을 파라미터로 추출하려고 할 때 사용
 	@CrossOrigin - Ajex의 크로스 도메인 문제를 해결해주는 어노테이션
 	@RequestBody - JSOn 데이터를 원하는 타입으로 바인딩 처리 (해당 파라미터의 타입으로 변환을 요구)
 	 				Request - 전달된 요청
 	 				Body - 내
	 */

	// REST방식으로 호출하는 경우 화면자체가 아니라 데이터 자체를 전송하는 방식이기 때문에 데이터를 
	// 요청한 쪽에서는 정사적인 데이터인지 비정사적인 데이ㅓ인지 구분할 수 있는 확실한 방법을 제공해야한다.
	// 그렇기에 ResponseEntity는 데이터와 함께 Http헤더의 상태메시 등을 같이 전달하는 용도로 사용한다.
	
	
// consumes -> input(프로그램에서 Custom Headers 부분에 입력되어야 하는 내용) 	produces -> output (댓글은 Text타입이기에 이렇게 쓴다.-> produces= {MediaType.TEXT_PLAIN_VALUE})
// consumes는 js에서 받는 데이터가 json타입이기떄문에 이렇게 쓰는거고
// produces는 서버에서 받은 데이터를 json타입이기때문에 이걸 string타입으로 보내주기 위해 TEXT_PLAIN_VALUE 를 쓰는것이다.
	@PostMapping(value="new",consumes="application/json",produces= {MediaType.TEXT_PLAIN_VALUE})
	//ResponseEntity는 HttpHeader와 HttpBody 를 포함하는 클래스이다.	
	public ResponseEntity<String> create(@RequestBody ReplyVO vo){
		log.info("ReplyVO:" + vo);
		
		int insertcount = service.register(vo);

		log.info("Reply INSERT COUNT:" + insertcount);
	//													HttpStatus는 성공했으면 200의 값을 나타내주며 실패했을경우 실패원인의 번호를 보여준다.								
		return insertcount==1? new ResponseEntity<>("success",HttpStatus.OK)
							 : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}// 댓글쓰기 끝 
	
//	@GetMapping(value="pages/{bno}/{page}",produces= {MediaType.APPLICATION_ATOM_XML_VALUE,
//												MediaType.APPLICATION_JSON_UTF8_VALUE})
//	//											@PathVariable -> 파라미터에 값을 보낼떄 사용하는 어노테이
//	public ResponseEntity<List<ReplyVO>> getList(@PathVariable("bno")int bno,@PathVariable("page")int page){
//		Criteria cri = new Criteria(page,bno);
//	//									select된 결과 		통신이 정상적으로 처리됐음 값으로 보내주는 					
//		return new ResponseEntity<>(service.getList(cri,bno),HttpStatus.OK);
//	}// 댓글 목록 리스트 끝
	
	@GetMapping(value="pages/{bno}/{page}",produces= {MediaType.APPLICATION_ATOM_XML_VALUE,
			MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ReplyPageDTO> getList(@PathVariable("bno")int bno,@PathVariable("page")int page) {
		
		Criteria cri = new Criteria(page, 10);
		
		log.info("get Reply List bno: "+bno);
		
		log.info("cri: "+cri);
		
		return new ResponseEntity<>(service.getList(cri, bno),HttpStatus.OK); 
	}
	
	@GetMapping(value="/{rno}", produces= {MediaType.APPLICATION_ATOM_XML_VALUE,
										   MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") int rno) {
		return new ResponseEntity<>(service.get(rno),HttpStatus.OK);
	}// 댓글 상세페이지 끝
	
	@DeleteMapping(value="/{rno}",produces= {MediaType.TEXT_PLAIN_VALUE})
		public ResponseEntity<String> remove(@PathVariable("rno") int rno){
		// 변수에 service.remove(rno)값을 넣어서 삼항문을 해도 	
		// int removecount = service.remove(rno);
			
			
		return  service.remove(rno)==1? new ResponseEntity<>("success",HttpStatus.OK)
									  : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}// 댓글 삭제 끝 
	
	@PutMapping(value="/{rno}", consumes="application/json", produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@PathVariable("rno") int rno,
										 @RequestBody ReplyVO vo){
		vo.setRno(rno);
		//service.modify에서는 매개변수로 (vo)만 받겠다고 만들어놨기 때문에 임의로 구현부에 적어놓은 
		service.modify(vo);
		
		
		
	return  service.modify(vo)==1? new ResponseEntity<>("success",HttpStatus.OK)
								  : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}// 댓글 수정 끝   
	
}

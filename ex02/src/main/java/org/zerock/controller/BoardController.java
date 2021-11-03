package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller //컨트롤러임을 무조건 표기해야한다.
@Log4j //롬복을통해 콘솔에 정보를 제공
@AllArgsConstructor //생성자를 만들어 자동으로 BoardService타입의 객체에 연동하여 의존성을 주입. 
@RequestMapping("/board")
public class BoardController {
	
	private BoardService service; // BoardService와 연동
	
	// 전체목록
	//@RequestMapping(value="/board/list", method = RequestMethod.GET) 
	@GetMapping("list") //@RequestMapping을 줄여서 쓴 것.
						//model이 서버에서 전송받은 데이터 (service.getList)를 view에 보내준다.	
	public void list(Model model, Criteria cri) {
		log.info("list");
		//				   배열이름, select된 결과물
		model.addAttribute("list", service.getList(cri));
		
		int total=service.getTotalCount(cri);
		
		model.addAttribute("pageMaker", new PageDTO(cri,total));
	}
	
	@GetMapping("read") 
	public void read(long bno, Model model) {
		log.info("read=" + bno);
		service.get(bno);
		model.addAttribute("read", service.get(bno));
	}
	
	//글쓰기 화면으로 이동하기 위해
	@GetMapping("register")
	public void register() {
		log.info("register");
	}
	
	//글쓰기 화면에서 글쓰기 버튼을 클릭했을 때 제목, 내용, 작성자를 처리하기 위해.
	@PostMapping("register")
	/* 
	   첫번째 방법  
	   public void registerPost(String title, String content, String writer) {}
	   매개변수로 하나씩 다 받게 될 경우 하나의 매개변수 당 16byte를 소비한다. 지금 예제로는 총 46byte를 소비한다.
	   하지만 두번째 방법인 BoardVO (Model)를 활용하여 매개변수로 받을 경우 하나의 매개변수를 사용하기 때문에 16byte를 소비한다.
	   46byte를 소비하던 것을 Model을 활용하여 16byte만 사용할 수 있게 된다.
	   이러한 데이터를 단축시키기위하여 Mode을 활용한다. 	
	*/
	
	//매개변수 RedirectAttributes는 추가적으로 새롭게 등록된 게시물의 번호를 같이 전달하기 위해 사용함.
	public String registerPost(BoardVO board, RedirectAttributes rttr) {
		log.info("insert 하기 전 = "+board);
		
		service.register(board);// 글쓰기 한 후 
		rttr.addAttribute("bno",board.getBno());
		log.info("insert 한 후  = "+board);
		
		return "redirect:/board/read"; // read.jsp 화면 이동. ->선생님한테 다시 한번 여쭤보기 
	}
	// 글 수정 화면(modify.jsp)으로 이동하기 위
	@GetMapping("modify")
	public void modifyGet(long bno, Model model) {
		log.info("modify");
		model.addAttribute("modify", service.get(bno));
	}
	
	@PostMapping("modify")
					//redirect는 애초에 GET방식이다. 그러나 Post방식에 사용 할경우 GET방식의 특징을 이용한 RedirectAttribute를 사용한다.
					//redirect시 데이터를 전송해야되는 rediret일 경우, RedirectAttributes 클래스를 사용하여 데이터를 전송한다.
	public String modifyPost(BoardVO board, RedirectAttributes rttr, Model model) {
		log.info("modifypost=" + board);
		service.modify(board);
//		model.addAttribute("result", service.modify(board)); 글이 수정됐다고 알림창 띄울 때 결과값 true를 이용해 jsp에 if문을 활용하여 팝업창 띄울수 있음
		
//redirect시에 modify페이지에서 read로 갈 때 read페이지가 받아야되는 bno의 값이 있어야 페이지를 보여주기 때문에 addAttribute를 통해 read에 bno값을 보내주는 것
	//lombok을 이용한 @Data를 통해 생성자를 만들었기 때문에 bno가 getBno로 만들어져 있다/ BoardVO에는 안보일 뿐이지 getbno의 생성자는 getBno로 만들어짐.
		rttr.addAttribute("bno", board.getBno());
		
		return "redirect:/board/read";
	}
	
	
/*
 	p220에 보면 remove는 무조건 PostMapping으로 처리하라고 나와있는데 여기서 GetMapping을 쓴 이유는?
  	삭제하는 페이지가 있을 경우에는 PostMapping으로 처리하는 것이 맞으나
  	수업시간에는 버튼을 이용하여 삭제하는 서비스를 만들었기 때문에 GetMapping으로 처리한 
*/
	@GetMapping("remove") 
	
	public String remove(long bno) {
		log.info("remove="+bno);
		service.remove(bno);
	
		//삭제하고나면 다시 목록페이지로 이동해야 하기 때문에 redirect를 사용한다.
		return "redirect:/board/list";
	}
}

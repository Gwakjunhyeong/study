/**
 * rest방식으로 댓글처리하기
 */
//$(선택자).click(function(){

//})

// ▼웹문서    가   ▼준비     됐을때 아래 함수 불러와서 실행해줘
$(document).ready(function(){ //언제 이벤트를 시작할건지~!~!~!~!
   
    var bnoValue=$("#bno").val();   // 게터로서 value값을 가지고오고 (게시물 번호)
	var replyUL=$(".chat")
	
	
	console.log(bnoValue);
	showList(1)
	
	//p440
	var pageNum=10;
	var replyPageFooter=$(".panel-footer");
	
	function showReplyPage(replyCnt) {
		
		
		var endNum=Math.ceil(pageNum/10.0)*10
		var startNum = endNum-9;
		
		var prev=startNum!=1;
		var next=false;
		
		if(endNum * 10 >= replyCnt){
			endNum = Math.ceil(replyCnt/10.0);	
		}
		if(endNum = 10 < replyCnt){
			next = true;
		}
		var str = "<ul class='pagination pull-right'>";
		
		if(prev){
			str+="<li class='page-item'><a class='page-link' href='"+(startNum-1)+"'>Previous</a></li>"
		}
		
		
		
		for(var i=startNum;i<=endNum;i++){
			var active = pageNum==i?"active":"";
			str+="<li class='page-item "+active+" '><a class='page-link' href='"+i+"'>"+i+"</a></li>"
		}
		
		
		
		if(next){
			str+="<li class='page-item'><a class='page-link' href ='"+(endNum + 1)+"'>Next</a></li>";
		}
		
		str += "<ul></div>";
		
		console.log(str);
		
		replyPageFooter.html(str);
		
	}
	
	
	
	
	
	
	
	
	function showList(page){ // showList 함수 선언 시작  
										//page:page||1는 댓글상세페이지를 볼 때 첫번째페이지가 기본세팅으로 될 수 있게 설정한 것 
										//page의 값 (showList를 호출하는 곳의 ()값이 없다면 1페이지를 나타내라는 것 )
		replyService.getList({bno:bnoValue,page:page||1},function(replyCnt,list){ // getList 함수 호출 시작
		
		var str="";	      
		
		//list가 null이거나 list의 길이가 0이면(select된 결과가 없으면)	
			if(list==null || list.length==0){
				replyUL.html("");
				return
			}

//         초기화                           조건식          증감식

				console.log(list);
				
				for(var i=0, len=list.length||0; i<len ; i++){
	     			str+="<li class='left clearfix' data-rno='"+list[i].rno+"'>"
					str+="<div>"
					str+="<div class='header'>"
					str+="<strong class='primary-font'>"+list[i].replyer+"</strong>"
					str+="<small class='full-right text-muted'>"+replyService.displayTime(list[i].replydate)+"</small>"
					str+="</div>"
					str+="<p>"+list[i].reply+"</p>"
					str+"</div></li>" 
					
					// list안에는 rno, bno, reply, replyer, replydate,updatedate의 값들이 있는데 list[i]를 이용해 배열번호 i번째의 배열을 불러낸다

     			}
			console.log(str)
			replyUL.html(str); 
			showReplyPage(replyCnt);
  		 })// getList 함수 호출 끝
	}// showList 함수 선언 끝  
	
	  var modal=$(".modal")
      var modalInputReply=modal.find("input[name='reply']")
      //<input class="form-control" name="reply" value="New Reply!!!!">
      var modalInputReplyer=modal.find("input[name='replyer']")
      //<input class="form-control" name="replyer" value="replyer">
      var modalInputReplyDate=modal.find("input[name='replyDate']")
      //<input class="form-control" name="replyDate" value="">
      var modalModBtn=$("#modalModBtn")
      //<button id="modalModBtn" type="button" class="btn btn-warning">Modify</button>
      var modalRemoveBtn=$("#modalRemoveBtn")
      //<button id="modalRemoveBtn" type="button" class="btn btn-danger">Remove</button>
      var modalRegisterBtn=$("#modalRegisterBtn")
      //<button id="modalRegisterBtn" type="button" class="btn btn-danger">Register</button>
      // var modalCloseBtn=$("#modalCloseBtn")
      //<button id="modalCloseBtn" type="button" class="btn btn-default">Close</button>
	
	
$("#addReplyBtn").on("click",function(){
	//alert("댓글쓰기")
	
	//내장함수임 
	modal.modal("show")
	//모달창의 close를 누르면 모달창이 사라짐
    modalCloseBtn.click(function(){modal.modal("hide")})//modal.modal은 내장함수를 불러 메서드를 실행한 것
	// modal창의 input태그의 값을 빈 문자열로 세팅 
	modal.find("input").val("")
	// modal창의 작성일자는 댓글쓰기할 때 사용자가 입력하는 것이 아니므로 숨김.
	modalInputReplyDate.closest("div").hide();
	// modal창에서 close버튼을 제외하고 나머지 버튼은 숨김.
	modal.find("button[id!='modalCloseBtn']").hide();
	
	
	// modal창에 댓글 쓰기버튼을 추가
	modalRegisterBtn.show();
	$(".modal").modal("show")
})//addReplyBtn 클릭 이벤트 함수 끝
 
// 모달창 안에 있는 Register버튼을 클릭하면
	modalRegisterBtn.on("click",function(){ 
		
		var reply={// reply인풋창의 값을 받아서 controller로 보냄 
					reply:modalInputReply.val(),
					replyer:modalInputReplyer.val(),
					bno:bnoValue
				}
		// replyService함수 호출
		replyService.add(reply,	//reply란 매개변수로 들어감 controller의 insert를 실행 
						 function(result){
								alert("Result : "+result);
								showList(1);
						 }
		)// add함수 호출 끝
		modal.find("input").val(""); //모달창안의 인풋값을 공백으로 하기위해
		modal.modal("hide");
		
	})// modalRegisterBtn 클릭이벤트 함수 	

		//특정 댓글 내용을 클릭 이벤트로 처리 
		$(".chat").on("click","li",function(e){
		// data-rno => data("rno")
		var rno=$(this).data("rno");
		
		console.log(rno);
		
		replyService.get(rno,function(reply){
			modalInputReply.val(reply.reply);
			modalInputReplyer.val(reply.replyer);
			modalInputReplyDate.val(replyService.displayTime(reply.replydate)).attr("readonly","readonly");
			modal.data("rno",reply.rno);
			
			modal.find("button[id != 'modalCloseBtn']").hide();
			modalModBtn.show();
			modalRemoveBtn.show();
			
			$(".modal").modal("show");
		})
})// 특정 댓글 내용을 클릭 이벤트로 처리 끝
	
	
	// 댓글 수정 처리
	modalModBtn.on("click",function(e){
		var reply={rno:modal.data("rno"),reply:modalInputReply.val()};
		
		replyService.update(
				reply,
				function(result){	// 수정이 성공했을 때 함수 시작
					alert("수정 완료");
					modal.modal("hide");
					showList(1);
				}					// 수정이 성공했을 때 함수 끝
			)// update함수 호출 끝
	})
	
	
	
	// 댓글 삭제 처리
	modalRemoveBtn.on("click",function(e){
		var rno=modal.data("rno");
		
		replyService.remove(// remove함수 호출 시작
				rno,					// rno
				function(count){	// callback 함수 시작
				console.log(count);
				
					if(count === "success"){
						alert("removed");
						modal.modal("hide");
						showList(1);
					}
				},					// callback 함수 끝
				function(error){	// 에러처리 함수 시작
					alert("error.....");
				}					// 에러처리 함수 끝
			)// remove함수 호출 끝
	})
	
	//댓글 페이징 버튼을 클릭 시 
	replyPageFooter.on("click",function(e){
		e.preventDefault();
		console.log("page click");
		var targetPageNum=$(this).attr("href");
		
		console.log("targetPageNum: "+targetPageNum);
		pageNum=targetPageNum;
		showList(pageNum);
		
	})
	
	
	

})// ready

/*
document에 먼저 다 테스트 해보고 getList만 넣었음..

var bnoValue=$("#bno").val();   // 게터로서 value값을 가지고오고 (게시물 번호)
   // $("#bno").val();    세터로 1이라는 변수를 value에 넣는다
   console.log(bnoValue);
   //   replyService함수 호출
   replyService.add({reply:"JS Test", replyer:"tester", bno:bnoValue},
         			function(result){alert("Result : " + result);
   })// add함수 호출 끝
   // getList 함수 호출 시작
   replyService.getList({bno:bnoValue,page:1},function(list){ // getList 함수 호출 시작
      //         초기화                                         조건식       증감식
      for(var i=0, len=list.length||0; i<len ; i++){
         console.log(list[i]);
         
      }
   })//getList 함수 호출 끝


	// remove 함수 선언부분 
	//		           rno  callback함수  에러처리함수							 
	// function remove(rno,   callback,    error){}
	
	replyService.remove(// remove함수 시작 
		2,// (rno 매개변수) 
		function(count){// (callback 매개변수)
		console.log(count);
		if(count==="success"){
			alert("removed");	
		}	
	},					   									 
		function(error) {// 에러처리 함수 시작 
			alert("error.........")
		}				 									  
	)// 에러처리 함수 끝  
	replyService.update({// update함수 호출 시
			rno:5,
			reply:"modefied Reply......"
	},
	function(result){
			alert("수정완료");
	}
	)// update함수 호출 끝 
	replyService.get(10,function(data){
		console.log(data)
	})// get함수 호출 끝
})// ready
	
	
console.log("Reply Module.........");

*/
	
console.log("Reply Module.........");




//   replyService함수 선언

var replyService = (function(){
   function add(reply, callback, error){   // 댓글을 쓰기 위한 함수 선언
      console.log("reply.......")
      
	// .ajax 자바스크립트에서 비동기식으로 처리할 때 사용한다. type, url, data, contentType, success, error는 계속 사용하기 때문에 외워두자.
      $.ajax({
         type:"post", //jsp의 form태그 안에 있는 method라 생각하면 된다. 				//type과 url은 메서드를 만들 때 필수적이
         url:"/replies/new", //jsp의 form태그 안에 있는 action 이라 생각하면 된다!!!!!!!!! 
         
	     //map을 string으로 변환시켜서 보내준다. 
 		 data:JSON.stringify(reply), //controller에 데이터를 보내주는 inpt태그와 같다 생각하면된다. 중요한 건 데이터를 보내준다는 것 !
         contentType:"application/json; charset=UTF-8",
         success:function(result,status,xhr){
            if(callback){
               callback(result);
            }
         },
         error:function(xhr,status,er){
            if(error){
               error(er);
            }            
         }
      })//ajax 끝
   }//add함수 끝


   function getList(param, callback,error){
      var bno = param.bno; //게시판 번호
      var page = param.page || 1; //페이지 번호 

      console.log("getLIst")
	  //type이 get인 경우에는 $.ajax를 줄여서 표현할 수 있다. 단, get방식만 가능하다.
		
		/* 
			Rest방식으로 url주소를 보낼 때 get타입일 경우, 매개변수가 일반변수라면 $.ajax를 $.get으로 줄여줄 수 있으며,   
		 									      매개변수가 json타입이라 $.getJSON으로 줄여줄 수 있다. ex) get메서드를 확인 (213줄)
		*/		
		
	  
 	  // getJSON은 매개변수가 2개 이상을 받을 때 사용하고
	  // get은 매개변수 하나일때 사용한다.

	
      $.getJSON("/replies/pages/"+bno+"/"+page+".json",   //getJSON 시작(댓글 목록리스트를 처리하기 위한 함수 선언)`
            function(data){
	
               if(callback){
				//callback(data);
				//data=service.getlistWithPaging(cri, bno) 
	
                  callback(data.replyCnt,data.list);
					
               }
         })//getJSON 끝
         .fail(function(xhr,status,err){
            if(error){
               error();
	            }
	         });//fail 끝
	   }//getList함수 끝
	   
	function remove(rno,callback,error) {// remove 함수 시작
		$.ajax({//ajax시작
			/*
			 Rest방식을 쓸 때 전송방식 
		  	 *Create -> Post방식
			 *Read   -> get방식 
			 *Update -> Put방식
			 *Delete -> Delete방
			*/		
			type:"delete",     
			url:"/replies/"+rno, 
			success:function(result,status,xhr){// 삭제 성공했을 때 시
            if(callback){	
               callback(result);
            }
         },// 삭제 성공했을 때 
	         error:function(xhr,status,er){
	            if(error){
	               error(er);
	            }            
	         }// 삭제에 실패했을 때 
		})	//ajax 끝 
		
	}
   //remove 함수 
   function update(reply, callback, error) {
		console.log("RNO=" + reply.rno);
			 $.ajax({
		     type:"put",				//type과 url은 메서드를 만들 때 필수적이다 
		     url:"/replies/"+reply.rno,
		     data:JSON.stringify(reply),
		     contentType:"application/json; charset=UTF-8",
		     success:function(result,status,xhr){// 수정 성공했을 때 시작 
		        if(callback){
		           callback(result);
		        }
		     },// 수정 성공했을 때 
		     error:function(xhr,status,er){// 수정 실패했을 때 시작 
		        if(error){
		           error(er);
		        }            
		     }// 수정 실패했을 때 
		  })// ajax 끝
		}// update함수 선언 끝 
	function get(rno,callback,error){//댓글 상세페이지 처리하는 함수(get함수 시작)
		
		//type이 get인 경우에는 $.ajax를 줄여서 표현할 수 있다. 단, get방식만 가능하다.
		
		// Rest방식으로 url주소를 보낼 때 get타입일 경우, 매개변수가 일반변수라면 $.ajax를 $.get으로 줄여줄 수 있으며,   
		// 									      매개변수가 json타입이라 $.getJSON으로 줄여줄 수 있다. ex) getList메서드를 확인 (138줄)
		//	
		$.get("/replies/"+rno+".json",function(result){	//매개변수 rno가 &.get에 있는 +rno+를 의미
			console.log("aaaaa")
			if(callback){	//이 줄의 if가 callback자리의 매개변수 내용 
			
				callback(result);
			}
		}).fail(function(xhr,status,err){ // .fail이 error의매개변수의 내용 
			if(error){
				error()
			}
		})
		
	}// 댓글 상세페이지 처리하는 함 get함수 선언 끝  
	
	function displayTime(timeValue) {
		
		var today= new Date(); // 오늘 날짜
				// 현재시간(today.getTime) - 화면에서 넘어온 시간 (timeValue)
		var gap = today.getTime() - timeValue;
		
		var dateObj = new Date(timeValue);
		
		var str = "";
		
		if(gap<(1000*60*60*24)) {
			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();
			
			return [(hh > 9 ? '':'0')+hh, ':',(mi>9?'':'0')+mi,
				':',(ss > 9 ? '':'0')+ss].join('');
		} else {
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth()+1; // 자바의 getMonth()은 0,1,2,3,4,5,6,7,8,9,10,11이 되니  +1을 해줘야한다.
			var dd = dateObj.getDate();
		
				return [yy,'/',(mm > 9? '' : '0' )+mm,'/',
				(dd > 9 ? '' : '0')+dd].join('');
		}		
	};	

   return {
		add:add,
		getList:getList,
		remove:remove,
		update:update,
		get:get,
		displayTime : displayTime
	};
})();
//검색버튼 선택
$(document).ready(function(){
   var searchForm=$("#searchForm");
   
   $("#searchForm button").on("click",function(e){
      //사용자가 검색을 하기 위해 검색종류 선택하지 않았을 경우
      if(!searchForm.find("option:selected").val()){//선택된 option이  value값이 없으면!
         alert("검색종류를 선택하세요.")
         return false;
      }
      //사용자가 검색을 하기 위해 키워드를 입력하지 않았을 경우
      if(!searchForm.find("input[name='keyword']").val()){//input안 키워드에  value값이 없으면!
         alert("키워드를 입력하세요.")
         return false;
      }
      //
      searchForm.find("input[name='pageNum']").val("1")
      //인풋창에 값을 입력하고 검색버튼을 눌렀을때 첫번째 페이지가 나오게하기 위해
//      e.preventDefault();

	  /*
	  	preventDefault는 기존에 있는 이벤트를 삭제할때 사용
	  
		브라우저에서 검색버튼을 클릭하면 <form> 태그의 전송은 막고 페이지의 번호는 1이 되도록 처리한다. 
		또한 화면에서 키워드가 없다면 검색을 하지 않도록 제어
	  */
      searchForm.submit();
      
      return;
      
   })
})

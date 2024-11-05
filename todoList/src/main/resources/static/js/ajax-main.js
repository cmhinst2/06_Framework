console.log("ajax-main.js loaded.");

/* 요소 얻어와서 변수에 저장 */

// 할 일 개수 관련 요소
const totalCount = document.querySelector("#totalCount");
const completeCount = document.querySelector("#completeCount");
const reloadBtn = document.querySelector("#reloadBtn");

// 할 일 추가 관련 요소
const todoTitle = document.querySelector("#todoTitle");
const todoContent = document.querySelector("#todoContent");
const addBtn = document.querySelector("#addBtn");

// 할 일 목록 조회 관련 요소
const tbody = document.querySelector("#tbody");

// 할 일 상세 조회 관련 요소
const popupLayer = document.querySelector("#popupLayer");
const popupTodoNo = document.querySelector("#popupTodoNo");
const popupTodoTitle = document.querySelector("#popupTodoTitle");
const popupComplete = document.querySelector("#popupComplete");
const popupRegDate = document.querySelector("#popupRegDate");
const popupTodoContent = document.querySelector("#popupTodoContent");
const popupClose = document.querySelector("#popupClose");

// 상세 조회 팝업레이어 관련 버튼 요소
const changeComplete = document.querySelector("#changeComplete");
const updateView = document.querySelector("#updateView");
const deleteBtn = document.querySelector("#deleteBtn");

// 수정 레이어 관련 요소
const updateLayer = document.querySelector("#updateLayer");
const updateTitle = document.querySelector("#updateTitle");
const updateContent = document.querySelector("#updateContent");
const updateBtn = document.querySelector("#updateBtn");
const updateCancel = document.querySelector("#updateCancel");



// -------------------------------------------------

/**
 * fetch() API
 * 비동기 요청을 수행하는 최신 Javascript API 중 하나임.
 * 
 * - Promise 는 비동기 작업의 결과를 처리하는 방법 중 하나.
 * -> 어떤 결과가 올지는 모르지만 반드시 결과를 보내주겠다라는 약속.
 * -> 비동기 작업이 맞이할 완료 또는 실패와 그 결과 값을 나타냄
 * -> 비동기 작업이 완료되었을 때 실행할 콜백 함수를 지정하고,
 *    해당 작업의 성공 또는 실패 여부를 처리할 수 있도록 함.
 * 
 * Promise 객체는 세 가지 상태를 가짐
 * - Pending (대기 중) : 비동기 작업이 완료되지 않은 상태
 * - Fulfilled (이행됨) : 비동기 작업이 성공적으로 완료된 상태
 * - Rejected (거부됨)  : 비동기 작업이 실패한 상태
 * 
 * - 콜백 함수(callback function)는 특정 작업이나 이벤트가 완료된 후 호출되는 함수
 *   주로 비동기 작업이 끝난 시점에 실행됨.
 *   비동기 작업이 완료될 때 까지 기다렸다가, 해당 작업의 결과를 이용하여
 *   추가적인 작업을 수행하는 방식으로 사용됨. 
 *
 */

// 전체 Todo 개수 조회 및 출력하는 함수 
function getTotalCount() { // 함수 정의

  // 비동기로 서버에서 전체 Todo 개수 조회하는
  // fetch() API 코드 작성

  fetch("/ajax/totalCount") // 서버로 비동기 요청 (GET 요청)
  // 첫 번째 then (응답을 처리하는 역할)
  .then( response => { // fetch가 서버에서 응답을 받으면, 
                      // 이 응답(response)을 텍스트 형식으로 변환하는 콜백 함수
    // 매개변수 response : 비동기 요청에 대한 응답이 담긴 객체
    // console.log(response);

    // response.text() : 응답 데이터를 문자열/숫자 형태로 변환한 결과를 가지는
    //                    Promise 객체 반환
    // console.log(response.text());

    return response.text();
  })
  // 두 번째 then (변환된 데이터를 활용하는 역할)
  .then(result => { // 첫 번째 콜백함수가 완료된 후 호출.
                // 변환된 텍스트 데이터(result)를 받아서
                // 콘솔에 단순 출력
    // 두번째 콜백함수의 매개변수 result
    // == 첫 번째 콜백함수에서 반환된 Promise 객체의 PromiseResult 값
    // == 변환된 텍스트 데이터를 result 변수로 받아서 처리
    
    // console.log("result" , result);

    // #totalCount 요소의 내용으로 result 를 대입
    totalCount.innerText = result;


  })

};


// completeCount 값 비동기 통신으로 얻어와 화면 출력 함수
function getCompleteCount() { // 함수 정의

  // fetch() : 비동기로 요청해서 결과 데이터 가져오기

  // 첫 번째 then()의 response : 
  // - 응답 결과, 요청 주소, 응답 데이터 등이 담겨있음

  // 두 번째 then() 의 result  : 
  // - 첫 번째 then 에서 알맞게 데이터(text(), json())가 변환된 응답 데이터

  // response.text() : 응답 데이터를 텍스트 형태로 변환

  fetch("/ajax/completeCount")
  .then( response => response.text() )
  // == then( (response) => { return response.text() } )
  .then( result => {
    // #completeCount 요소에 내용으로 result 값 출력
    completeCount.innerText = result;
  });

}

// 새로고침 버튼이 클릭되었을 때 
reloadBtn.addEventListener("click", () => {
  getTotalCount();
  getCompleteCount();
  selectTodoList();
});

// -----------------------------------------------

// 할 일 추가 버튼 클릭 시 동작
addBtn.addEventListener("click", () => {

  if(todoTitle.value.trim().length === 0 || todoContent.value.trim().length === 0) {
    alert("제목이나 내용은 비어있을 수 없습니다!");
    return;
  }

  // 비동기로 할 일 추가하는 fetch() 코드 작성
  // - 요청 주소 : "/ajax/add"
  // - 데이터 전달 방식 (method) : "POST" 방식
  // - 전달 데이터 : todoTitle.value, todoContent.value  
  // JSON 
  // { "name" : "홍길동",
  //   "age"  : 20,
  //   "skills" : ["javascript", "java", "oracle",...]
  // }

  // todoTitle과 todoContent를 저장한 JS 객체 
  const param = {
    // key   :   value
    "todoTitle" : todoTitle.value,
    "todoContent" : todoContent.value
  }

  //console.log(param);

  fetch("/ajax/add" , {
    // key : value
    method : "POST",  // POST 방식 요청
    headers : {"Content-Type" : "application/json"}, // 요청 데이터의 형식을 JSON 으로 지정
    body : JSON.stringify(param)  // param이라는 JS 객체를 JSON(string)으로 변환    
  })
  .then( resp => resp.text() ) // 반환된 값을 text로 변환
  .then( result => {
    // 첫번째 then에서 반환된 값 중 변환된 text를 result 에 저장

    if(result > 0) { // 성공
      alert("추가 성공!!");

      // 추가 성공한 제목, 내용 지우기
      todoTitle.value = "";
      todoContent.value = "";

      // 할 일이 새롭게 추가되었음
      // -> 전체 Todo 개수 다시 조회
      getTotalCount();

      // -> 전체 Todo 목록 다시 조회
      selectTodoList();
      
    } else { // 실패
      alert("추가 실패...");
    }
  });

});

//---------------------------------------------------

// 비동기로 할 일 목록을 조회하는 함수
const selectTodoList = () => {

  fetch("/ajax/selectList")
  .then(resp => resp.json()) // 응답 결과를 json으로 파싱
  .then(todoList => {
    // 매개변수 todoList :
    // 첫번째 then 에서 text() / json() 했냐에 따라 
    // 단순 텍스트이거나 JS Object 일수도 있음

    // 만약 text() 사용했다면 문자열
    // -> JSON.parse() 이용하여 JS Object 타입으로 변환 가능.

    // JSON.parse(JSON데이터) : string -> object 
    // - string 형태의 JSON 데이터를 JS Object 타입으로 변환
    
    // JSON.stringify(JS Object) : object -> string
    // - JS Object 타입을 string 형태의 JSON 데이터로 변환
  
    console.log( todoList );// JS 객체 배열 형태로 출력

    // ------------------------------------------------

    // 기존에 출력되어 있던 할 일 목록을 모두 비우기
    tbody.innerHTML = "";

    // #tbody에 tr/td 요소를 생성해서 내용 추가
    for(let todo of todoList) { // 향상된 for문

      // tr 태그 생성
      const tr = document.createElement("tr");

      // JS 객체에 존재하는 key 모음 배열
      const arr = ['todoNo', 'todoTitle', 'complete', 'regDate'];

      for(let key of arr) {
        const td = document.createElement("td");

        // 제목인 경우
        if(key === 'todoTitle') {
          const a = document.createElement("a"); // a태그 생성
          a.innerText = todo[key]; // todo["todoTitle"]
          a.href = "/ajax/detail?todoNo=" + todo.todoNo;
          td.append(a);
          tr.append(td);

          // a태그 클릭 시 기본 이벤트(페이지 이동) 막기
          a.addEventListener("click", e => {
            e.preventDefault(); // 기본 이벤트 방지

            // 할 일 상세 조회 비동기 요청
            // e.target.href : 클릭된 a태그의 href 속성값
            selectTodo(e.target.href);
          });


          // 추가해야하는 분기문
          continue;
        }

        // 제목이 아닌 경우
        td.innerText = todo[key];
        tr.append(td);
      }

      // tbody 의 자식으로 tr 추가
      tbody.append(tr);

    }


  });

}


// ----------------------------------------------------------------

// 비동기로 할 일 상세 조회하는 함수
const selectTodo = (url) => {
  // 매개변수 url == "/ajax/detail?todoNo=10"   형태의 문자열

  fetch(url)
  .then(resp => resp.json())
  .then(todo => {
    // console.log(todo);

    // popup layer 에 조회된 값을 출력
    popupTodoNo.innerText      = todo.todoNo;
    popupTodoTitle.innerText   = todo.todoTitle;
    popupComplete.innerText    = todo.complete;
    popupRegDate.innerText     = todo.regDate;
    popupTodoContent.innerText = todo.todoContent;

    // popup layer 보이게하기
    popupLayer.classList.remove("popup-hidden");

    // update layer 가 혹시라도 열려있다면 숨기기
    updateLayer.classList.add("popup-hidden");

    /**
     * 요소.classList.add("클래스명")
     * - 요소에 해당 클래스 추가
     * 
     * 요소.classList.remove("클래스명")
     * - 요소에서 해당 클래스 삭제
     * 
     * 요소.classList.toggle("클래스명")
     * - 요소에 해당 클래스 있으면 제거
     * - 요소에 해당 클래스 없으면 추가
     * 
     *  */


  });
}


// popup layer의 X 버튼 클릭 시 닫기
popupClose.addEventListener("click", () => {
  // 숨겨주는 class 추가
  popupLayer.classList.add("popup-hidden");
});

// ----------------------------------------------------------------------------

// 삭제 버튼 클릭 시
deleteBtn.addEventListener("click" , () => {

  // 취소 클릭 시 아무것도 안함 (해당 이벤트 함수 종료)
  if( !confirm("정말 삭제하시겠습니까?") ) {
    return;
  }

  // 삭제할 할 일 번호 얻어오기
  const todoNo = popupTodoNo.innerText; 

  // 삭제 비동기 요청 (DELETE 방식)
  fetch("/ajax/delete", {
    method : "DELETE", // DELETE 방식 요청 -> @DeleteMapping 처리
    headers : {"Content-Type" : "application/json"},
    body : todoNo // todoNo 단일 값 하나는 JSON 형태로 자동 변환되어 전달됨
    // body : JSON.stringify(todoNo) 
    // -> 원래는 이렇게 명시하는게 옳음(엄격한 환경에서는 이렇게 작성해야함)
  })
  .then( resp => resp.text() )
  .then( result => {

    //console.log(result);
    if(result > 0) { // 삭제 성공
      alert("삭제 되었습니다!");

      // 상세 조회 팝업 레이어 닫기
      popupLayer.classList.add("popup-hidden");

      // 전체, 완료된 할 일 개수 다시 조회
      // + 할 일 목록 다시 조회
      getTotalCount();
      getCompleteCount();
      selectTodoList();

    } else { // 삭제 실패
      alert("삭제 실패...");
    }


  })

});


// -------------------------------------------------------

// 완료 여부 변경 버튼 클릭 시
changeComplete.addEventListener("click", () => {

  // 변경할 할 일 번호, 완료 여부 (Y <-> N)
  const todoNo = popupTodoNo.innerText;
  const complete = popupComplete.innerText === 'Y' ? 'N' : 'Y';

  // SQL 수행에 필요한 두 값을 JS 객체로 묶음
  const obj = {"todoNo" : todoNo, "complete" : complete};

  // 비동기로 완료 여부 변경 요청
  fetch("/ajax/changeComplete", {
    method: "PUT", // @PutMapping
    headers : {"Content-Type" : "application/json"},
    body : JSON.stringify(obj) // js Object 형태인 obj를 JSON 형식으로 변경
  })
  .then( resp => resp.text() )
  .then( result => {
    
    //console.log(result);

    if(result > 0) { // 성공

      // update된 DB 데이터를 다시 조회해서 화면에 출력
      // -> 서버 부하가 큼

      //selectTodo();
      // 서버 부하를 줄이기 위해 상세 조회에서 Y/N 만 바꾸기
      popupComplete.innerText = complete;

      //getCompleteCount();
      // 서버 부하를 줄이기 위해 완료된 Todo 개수 +-1

      const count = Number(completeCount.innerText);

      if(complete === 'Y') completeCount.innerText = count + 1;
      else                  completeCount.innerText = count - 1;


      selectTodoList();
      // 서버 부하 줄이기 가능! -> 코드가 좀 복잡해서 그냥 사용

    } else { // 실패
      alert("완료 여부 변경 실패!!");
    }



  });

});

// ---------------------------------------------------

// 상세조회에서 수정 버튼(#updateView) 클릭 시
updateView.addEventListener("click", () => {

  // 기존 상세 조회 팝업 레어이는 숨기고
  popupLayer.classList.add("popup-hidden");

  // 수정 팝업 레이어 보이게 
  updateLayer.classList.remove("popup-hidden");

  // 수정 레이어 보일 때
  // 상세 조회 팝업 레이어에 작성된 제목, 내용 얻어와 세팅
  updateTitle.value = popupTodoTitle.innerText;

  updateContent.value 
    = popupTodoContent.innerHTML.replaceAll("<br>" , "\n");
  // HTML 화면에서 줄 바꿈이 <br>로 인식되고 있는데
  // textarea에서는 \n으로 바꿔줘야 줄 바꿈으로 인식된다!

  // 수정 레이어 -> 수정 버튼에 data-todo-no 속성 추가
  updateBtn.setAttribute("data-todo-no", popupTodoNo.innerText);
  //<button id="updateBtn" data-todo-no=${todoNo}>수정</button> 
});

// -----------------------------------------------------

// 수정 레이어에서 취소버튼(#updateCancel)이 클릭되었을 때
updateCancel.addEventListener("click", () => {

  // 수정 레이어 숨기기
  updateLayer.classList.add("popup-hidden");

  // 상세 조회 팝업 레이어를 보이기
  popupLayer.classList.remove("popup-hidden");

});

// ------------------------------------------------------

// 수정 레이어 -> 수정 버튼(#updateBtn) 클릭 시
updateBtn.addEventListener("click", e => {

  // 서버로 전달해야되는 값을 객체로 묶어둠
  const obj = {
    "todoNo"      : e.target.dataset.todoNo,
    "todoTitle"   : updateTitle.value,
    "todoContent" : updateContent.value
  };

  //console.log(obj);

  // 비동기 요청 (PUT)
  fetch("/ajax/update", {
    method : "PUT",
    headers : {"Content-Type" : "application/json"},
    body : JSON.stringify(obj)
  })
  .then( resp => resp.text() )
  .then( result => {

    if(result > 0) {
      alert("수정 성공!");

      // 수정 레이어 숨기기
      updateLayer.classList.add("popup-hidden");

      // 상세 조회 레이어는 보이게
      // -> 수정한 내용이 출력되도록
      // selectTodo();
      // -> 성능 개선
      popupTodoTitle.innerText = updateTitle.value;

      popupTodoContent.innerHTML 
        = updateContent.value.replaceAll("\n", "<br>");

      popupLayer.classList.remove("popup-hidden");

      selectTodoList(); // 목록 다시 조회

      updateTitle.value = "";   // 제목 input 남은 흔적 제거
      updateContent.value = ""; // 내용 textarea 남은 흔적 제거
      updateBtn.removeAttribute("data-todo-no"); // 속성제거



    } else {
      alert("수정 실패..ㅜㅜ");
    }

  });
  
});






// js 파일에 함수 호출 코드 바로 작성 -> 페이지 로딩 시 바로 실행하여 화면에 출력
getTotalCount(); // 함수 호출
getCompleteCount(); // 함수 호출
selectTodoList(); // 함수 호출
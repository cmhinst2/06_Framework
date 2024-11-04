console.log("ajax-main.js loaded.");

/* 요소 얻어와서 변수에 저장 */

// 할 일 개수 관련 요소
const totalCount = document.querySelector("#totalCount");
const completeCount = document.querySelector("#completeCount");
const reloadBtn = document.querySelector("#reloadBtn");

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

getTotalCount(); // 함수 호출
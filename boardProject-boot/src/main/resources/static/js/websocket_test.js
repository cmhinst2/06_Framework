// websocket_test.js
// 웹소켓 테스트

// 1. SockJS 라이브러리 추가
// -> common.html 에 작성

// 2. SockJS 객체를 생성
const testSock = new SockJS("/testSock");
// - 객체 생성 시 자동으로
// ws://localhost(또는 ip)/testSock 으로 연결요청을 보냄

// 3. 생성된 SockJS 객체를 이용해서 메시지 전달
const sendMessageFn = (name, str) => {

  // JSON을 이용해서 데이터를 TEXT 형태로 전달
  const obj = {
    "name" : name,
    "str"  : str
  };

  testSock.send(JSON.stringify(obj));
}

// 4. 서버로부터 현재 클라이언트에게 
// 웹소켓을 이용한 메시지가 전달된 경우
testSock.addEventListener("message", e => {

  // e.data : 서버로 부터 전달받은 message
  const msg = JSON.parse(e.data); // JSON -> JS Object 
  console.log(`${msg.name} : ${msg.str}`);
  //            홍길동 : Hi 
});

// console.log 메시지 출력


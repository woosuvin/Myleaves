/**
 * 정지언
 * chatRoom.js
 */

 // 클라이언트에서 stomp 연결
function connect() {
	const socket = new SockJS('/ws-stomp'); // WebSocketConfig SockJs 연결 주소
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		setConnected(true);
		console.log('Connected: ' + frame);
		loadChat(chatList)  //저장된 채팅 불러오기

		// room/{roomId}를 구독
		stompClient.subscribe('/room/' + roomId, function(chatMessage) {
			showChat(JSON.parse(chatMessage.body));
		});
	});
}

//html 에서 입력값, roomId 를 받아서 Controller 로 전달
function sendChat() {
	stompClient.send("/send/" + roomId, {},
		JSON.stringify({
			'sender': $("#sender").val(),
			'message': $("#message").val()
		}));
}

// send
function sendChat() {
	stompClient.send("/send/" + roomId, {},
		JSON.stringify({
			'sender': $("#sender").val(),
			'message': $("#message").val()
		}));
}

// 채팅 보기
function showChat(chat) {
	$("#chatting").append(
		"<tr><td>" + "[" + chat.userId + "]" + chat.message + "</td></tr>"
	);
}
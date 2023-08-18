/**
 * 
 */

let stompClient = null;


const messageListElement = (data) => {
	const chatting = document.querySelector('div#chatting');
	const userId = document.querySelector('input#userId').value;
	
	let htmlStr = '';
	for(let chat of data) {
  		const date = new Date(chat.createdDate).toLocaleString('ko-KR', { month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit' });
		if(chat.userId != userId) { // 로그인 유저랑 글쓴이랑 다르면 상대방
			htmlStr += `
				<div>
					<div class="card mt-2 card-other">
						<div class="card-body p-2 chat-other">
							<div>${chat.userId}: ${chat.message}</div>
						</div>
					</div>
					<div class="card mb-2 other-date">
						<small class="chat-time">${date}</small>
					</div>
				</div>
			`;
		} else {  // 로그인 유저가 글쓴이이면
			htmlStr += `
				<div class="writer">
					<div class="card mt-2 card-writer">
						<div class="card-body p-2 chat-writer">
							<div>${chat.message}</div>
						</div>
					</div>
					<div class="card mb-2 writer-date">
						<small class="chat-time chat-writer">${date}</small>
					</div>
				</div>
			`;
		}
	}
	chatting.innerHTML = htmlStr;
};


function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#chatting").html("");
}

function connect() {
    const sellId = document.querySelector('input#sellId').value;
    const myId = document.querySelector('input#myId').value;
    const otherId = document.querySelector('input#otherId').value;
    const data = { sellId, myId, otherId };

    console.log('소켓');
    console.log(data);

    // const socket = new SockJS('/gs-guide-websocket');
    const socket = new SockJS(location.host + '/gs-guide-websocket');
    console.log(socket);
    stompClient = Stomp.over(socket);
    stompClient.connect({ data }, function (frame) {
    	showMessageList(); // 리스트 새로고침
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/connect/chatting', function (showChat) { 
			const userId = JSON.parse(showChat.body).id;
			const message = JSON.parse(showChat.body).content; 
            showGreeting(userId, message); //send 클릭하고 메세지 채팅방에 보임
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

// 페이지 로딩 시 자동으로 연결 시도
$(document).ready(function () {
    connect();
});

// 페이지가 언로드될 때 자동으로 연결 닫기
$(window).on('beforeunload', function () {
    disconnect();
});



// 메세지 보냄
function sendMessage() {
	data= {};
	data.userId = $("#userId").val();
	data.message = $("#message").val();
		
    stompClient.send("/app/sendMessage", {}, JSON.stringify(data));
    
}

// 메세지 저장
function saveMessage() {
	const userId = document.querySelector('input#userId').value;
	const message = document.querySelector('input#message').value;
	const roomId = document.querySelector('input#roomId').value;
	
	const reqUrl = '/chat/create';
	const data = {userId, roomId, message};
	console.log(userId, message, roomId);
	
	axios
	.post(reqUrl, data)
	.then((response) => {
		console.log(response);
		//showMessageList();
	})
	.catch((error) => console.log(error));
}



function showMessageList() {
	const roomId = document.querySelector('input#roomId').value;
	const reqUrl = `/chat/${roomId}`;
	const chatting = document.getElementById('chatting');
	
	axios.get(reqUrl)
	.then((response) => {
		console.log(response);
		messageListElement(response.data);
		chatting.scrollTop = chatting.scrollHeight;
	})
	.catch((error) => console.log(error));
}

function showGreeting(userId, message) {
	
	const logInId = document.querySelector('input#userId').value;
	const otherId = document.querySelector('input#otherId').value;
	const myId = document.querySelector('input#myId').value;
	const chatting = document.getElementById('chatting');
	const date = new Date().toLocaleString('ko-KR', { month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit' });
	
	if(logInId === userId) { // 판매자
		$("#chatting").append(`<div class="writer"><div class="card mt-2 card-writer"><div class="card-body p-2 chat-writer"><div>`
					    + message + `</div></div></div><div class="card mb-2 writer-date"><small class="chat-time chat-writer">`
					     + date + `</small></div></div>`);
	} else if (logInId !== userId) { // 말 건 놈
		$("#chatting").append(`
							<div>
								<div class="card mt-2 card-other">
									<div class="card-body p-2 chat-other">
										<div>`+ userId +`: `+message+`</div>
									</div>
								</div>
								<div class="card mb-2 other-date">
									<small class="chat-time">` + date + `</small>
								</div>
							</div>
					`);
	}
	
	chatting.scrollTop = chatting.scrollHeight;
	
	
						    
}



$(function () { 
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#send").click(function() { 
		sendMessage();
		saveMessage();
		document.querySelector('input#message').value = '';
	 });
});
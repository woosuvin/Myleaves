/**
 * 
 */

let stompClient = null;


const messageListElement = (data) => {
	const chatting = document.querySelector('div#chatting');
	const userId = document.querySelector('input#userId').value;
	
	let htmlStr = '';
	for(let chat of data) {
		if(chat.userId != userId) { // 로그인 유저랑 글쓴이랑 다르면 상대방
			htmlStr += `
				<div class="other">
					<span>${chat.userId}</span>: <span>${chat.message}</span>
					<small>${chat.createdDate}</small>
				</div>
			`;
		} else {  // 로그인 유저가 글쓴이이면
			htmlStr += `
				<div class="writer">
					<div>${chat.message}</div>
					<small>${chat.createdDate}</small>
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
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/connect/chatting', function (showChat) {  
            showGreeting(JSON.parse(showChat.body).content); //send 클릭하고 메세지 채팅방에 보임
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
    showMessageList();
});

// 페이지가 언로드될 때 자동으로 연결 닫기
$(window).on('beforeunload', function () {
    disconnect();
});




// 메세지 보냄
function sendMessage() {
    stompClient.send("/app/sendMessage", {}, JSON.stringify({'message': $("#message").val()}));
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
		showMessageList();
	})
	.catch((error) => console.log(error));
}



function showMessageList() {
	const roomId = document.querySelector('input#roomId').value;
	const reqUrl = `/chat/${roomId}`;
	
	axios.get(reqUrl)
	.then((response) => {
		console.log(response);
		messageListElement(response.data);
	})
	.catch((error) => console.log(error));
}

// 메세지 채팅방에 보임 -> 안씀
function showGreeting(message) {
    const userId = document.querySelector('input#userId').value;
    $("#chatting").append("<div><strong>" + userId + "</strong>: " + message + "</div>");
}

$(function () { 
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#send").click(function() { saveMessage(); });
});
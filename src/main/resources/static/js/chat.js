/** 
 * 정지언 채팅
 * chat.js
 * 
 */

/*function openChatList() {
	const myId = document.querySelector('input#myId').value;
	const sellId = document.querySelector('input#sellId').value;

	console.log(myId);
	let data = {
		myId : myId,
		sellId : sellId		
	}
	
		axios.get(`/chat/chatList`, myId, 600, 400)
			.then(response => {
			const chatrooms = response.data;
		
			openCenteredWindow(chatrooms);  
		  })
		  .catch(error => {
			  console.error("Error", error);
		  });
}*/

function openChatPopup() {
	const userId = document.querySelector('input#userId').value;
	const width = 600;
	const height = 400;
	const left = (window.innerWidth - width) / 2;
	const top = (window.innerHeight - height) / 2;
	const options = "width=" + width + ",height=" + height + ",top=" + top + ",left=" + left;

	// 새 창으로 이동
	window.open("/chat/room?myId=" + encodeURIComponent(userId), "_blank", options);
}

function openChatRoom() {
	openCenteredWindow('/chat/room/enter/{roomId}', 600, 400);
}

document.addEventListener('DOMContentLoaded', () => {
	
    const chatBtn = document.querySelector('#chatButton');
    const sellId = document.querySelector('input#sellId').value;
    const myId = document.querySelector('input#sellerId').value;
    const otherId = document.querySelector('input#userId').value;

    const openChatRoom = () => {
		console.log(myId, sellId, otherId);
        const data = { myId, sellId, otherId };
        const reqUrl = '/chat/startChat';
        
        axios.post(reqUrl, data)
            .then(response => {
                if (response.data === 'Chat room already exists.') {
                    window.location.href = '/chat/roomDetail?sellId=' + sellId + '&otherId=' + otherId;
                } else {
                    // New chat room created. 경우의 동작
                }
            })
            .catch(error => {
                console.log(error);
            });
    };

    if (chatBtn != null) {
        chatBtn.addEventListener('click', openChatRoom);
    }
});

// 판매자와 대화하기 버튼
/*document.getElementById('chatButton').addEventListener('click', function() {
	// 클라이언트에서 필요한 데이터 설정
	const sellId = document.querySelector('input#sellId').value;
	const myId = document.querySelector('input#sellerId').value;
	const otherId = document.querySelector('input#userId').value;
	
	// HTTP POST 요청 보내기
	fetch('/startChat', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/x-www-form-urlencoded',
		},
		body: `sellId=${sellId}&myId=${myId}&otherId=${otherId}`,
	})
		.then(response => response.text())
		.then(data => {
			const newChatWindow = window.open('', '_blank', 'width=600,height=400');
			if (data.includes('New chat room created.')) {
				newChatWindow.location.href = '/chat/roomDetail?sellId=' + sellId + '&otherId=' + otherId;
			} else {
				// 이미 존재하는 채팅방인 경우, 위에서 이미 새 창을 열었으므로 추가 작업 필요 없음
			}
		})
		.catch(error => {
			console.error('Error:', error);
		});
});*/

// 클라이언트에서 stomp 연결
/*function connect() {
	const socket = new SockJS('/ws-stomp'); // WebSocketConfig SockJs 연결 주소
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function (frame) {
		setConnected(true);
		console.log('Connected: ' + frame);
		loadChat(chatList) // 채팅 불러오기
		
		// room/{roomId}를 구독
		stompClient.subscribe('/room/' + roomId, function (chatMessage) {
			showChat(JSON.parse(chatMessage.body));
		})
	})
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
}*/

/*function createRoom() {
	if("" === this.room)
	
	
}*/

/*const vm = new Vue({
	el: '#app',
	data: {
		room_name: '',
		chatrooms: []
	},
	created() {
		this.findAllRoom();
	},
	methods: {
		findAllRoom() {
			axios.get('/chat/rooms').then(response => {
				this.chatrooms = response.data;
			});
		},
		createRoom() {
			if (this.room_name === "") {
				alert("방 제목을 입력해 주십시요.");
				return;
			} else {
				const params = new URLSearchParams();
				params.append("name", this.room_name);
				axios.post('/chat/room', params)
					.then(response => {
						alert(response.data.roomName + "방 개설에 성공하였습니다.");
						this.room_name = '';
						this.findAllRoom();
					})
					.catch(response => {
						alert("채팅방 개설에 실패하였습니다.");
					});
			}
		},
		enterRoom(roomId) {
			const sender = prompt('대화명을 입력해 주세요.');
			if (sender !== "") {
				localStorage.setItem('wschat.sender', sender);
				localStorage.setItem('wschat.roomId', roomId);
				location.href = "/chat/room/enter/" + roomId;
			}
		}
	}
});*/
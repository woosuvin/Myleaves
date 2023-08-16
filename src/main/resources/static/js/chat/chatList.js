/** 
 * 정지언 채팅
 * chat.js
 * 
 */

// 채팅목록 열기
function openChatList() {
	const sellId = document.querySelector('input#sellId').value;
	const url = '/chat/room?sellId=' + sellId;
	const width = 400;
	const height = 600;
	const left = (window.innerWidth - width) / 2;
	const top = (window.innerHeight - height) / 2;
	const options = 'toolbar=no, location=no, directories=no, status=no, menubar=no, ' +
		'scrollbars=yes, resizable=yes, width=' + width + ', height=' + height +
		', top=' + top + ', left=' + left;

	window.open(url, 'ChatRoomPopup', options);
}

// 상단바 채팅목록 열기
function openNavChatList() {
	const myId = document.querySelector('span#userId').innerText;
	//const otherId = document.querySelector('span#userId').innerText;
	const url = `/chat/list?myId=${myId}`;
	const width = 400;
	const height = 600;
	const left = (window.innerWidth - width) / 2;
	const top = (window.innerHeight - height) / 2;
	const options = 'toolbar=no, location=no, directories=no, status=no, menubar=no, ' +
		'scrollbars=yes, resizable=yes, width=' + width + ', height=' + height +
		', top=' + top + ', left=' + left;

	window.open(url, 'ChatRoomPopup', options);
}



document.addEventListener('DOMContentLoaded', () => {

	const chatBtn = document.querySelector('button#chatButton');
	const sellId = document.querySelector('input#sellId').value;
	const myId = document.querySelector('input#sellerId').value;
	const otherId = document.querySelector('input#userId').value;
	
	// sellbuy detail에서 채팅방 생성, 채팅방 열기
	const openChatRoom = () => {
		console.log(myId, sellId, otherId);
		const data = { sellId, myId, otherId };
		const reqUrl = '/chat/startChat';

		const screenWidth = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
		const screenHeight = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;

		const popupWidth = 400;
		const popupHeight = 600;

		const leftPosition = (screenWidth - popupWidth) / 2;
		const topPosition = (screenHeight - popupHeight) / 2;

		const popupFeatures = `width=${popupWidth},height=${popupHeight},left=${leftPosition},top=${topPosition},modal=yes,dependent=yes`;

		axios.post(reqUrl, data)
			.then(response => {
				console.log(response.data);
				window.open('/chat/roomDetail?roomId='+response.data, '_blank', popupFeatures); // db에서 찾아
			})
			.catch(error => {
				console.log(error);
			});
	};

	if (chatBtn != null) {
		chatBtn.addEventListener('click', openChatRoom);
	}
	
	// 리스트에서 채팅방 열기
	const openExistChatRoom = (e) => {
		/*const chatBtn = e.currentTarget;*/
		/*const sellId = document.querySelector('input#sellId').value;*/
		const myId = document.querySelector('input#sellerId').value;
		const otherId = document.querySelector('input#userId').value;
		console.log(myId, otherId);
		const sellId = e.target.getAttribute('data-id');
		console.log(e.target);
		console.log(sellId);
		
		const roomId = document.querySelector('input#roomId').value;
		
		const screenWidth = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
		const screenHeight = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;

		const popupWidth = 400;
		const popupHeight = 600;

		const leftPosition = (screenWidth - popupWidth) / 2;
		const topPosition = (screenHeight - popupHeight) / 2;

		const popupFeatures = `width=${popupWidth},height=${popupHeight},left=${leftPosition},top=${topPosition},modal=yes,dependent=yes`;

		window.open(`/chat/roomDetail?roomId=${roomId}`, '_blank', popupFeatures);
	};
	const chatBtns = document.querySelectorAll('div#chatButton');
	for(let btn of chatBtns) {
		btn.addEventListener('click', openExistChatRoom);
	}
	
});

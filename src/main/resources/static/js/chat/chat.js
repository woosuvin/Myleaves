/** 
 * 정지언 채팅
 * chat.js
 * 
 */

function openChatRoom() {
	openCenteredWindow('/chat/room/enter/{roomId}', 600, 400);
}

// 채팅방 생성, 채팅방 열기
document.addEventListener('DOMContentLoaded', () => {

	const chatBtn = document.querySelector('#chatButton');
	const sellId = document.querySelector('input#sellId').value;
	const myId = document.querySelector('input#sellerId').value;
	const otherId = document.querySelector('input#userId').value;

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
				if (response.data === 'Chat room already exists.') {
					window.open(`/chat/roomDetail?sellId=${sellId}&otherId=${otherId}`, '_blank', popupFeatures);
				} else {
					window.open(`/chat/roomDetail?sellId=${sellId}&otherId=${otherId}`, '_blank', popupFeatures);
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


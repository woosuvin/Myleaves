/** 
 * 정지언 채팅
 * chat.js
 * 
 */

// 채팅목록 열기
function openChatList() {
	const otherId = document.querySelector('input#userId').value;
	const url = '/chat/room?otherId=' + otherId;
	const width = 400;
	const height = 600;
	const left = (window.innerWidth - width) / 2;
	const top = (window.innerHeight - height) / 2;
	const options = 'toolbar=no, location=no, directories=no, status=no, menubar=no, ' +
		'scrollbars=yes, resizable=yes, width=' + width + ', height=' + height +
		', top=' + top + ', left=' + left;

	window.open(url, 'ChatRoomPopup', options);
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

// 채팅방 리스트에서 채팅 열기
document.addEventListener('DOMContentLoaded', () => {
	const chatButtons = document.querySelectorAll('.list');

	const openChatRoom = (e) => {
		const chatBtn = e.currentTarget;
		const sellId = document.querySelector('input#sellId').value;
		const myId = document.querySelector('input#sellerId').value;
		const otherId = document.querySelector('input#userId').value;
/*		const data = { sellId, myId, otherId };
		const reqUrl = '/chat/startChat';*/

		const screenWidth = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
		const screenHeight = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;

		const popupWidth = 400;
		const popupHeight = 600;

		const leftPosition = (screenWidth - popupWidth) / 2;
		const topPosition = (screenHeight - popupHeight) / 2;

		const popupFeatures = `width=${popupWidth},height=${popupHeight},left=${leftPosition},top=${topPosition},modal=yes,dependent=yes`;

		window.open(`/chat/roomDetail?sellId=${sellId}&otherId=${otherId}`, '_blank', popupFeatures);
/*		axios.post(reqUrl, data)
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
			});*/
	};

    chatButtons.forEach(button => {
        button.addEventListener('click', openChatRoom);
    });
});

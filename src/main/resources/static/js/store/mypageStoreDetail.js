/**
 * 주문 취소 버튼
 * 지현
 */

document.addEventListener('DOMContentLoaded', () => {

	const btnCancel = document.querySelector('#btnCancel');
	btnCancel.addEventListener('click', writeReason);
	
});

const writeReason = (e) => {
	const cancelArea = document.querySelector('div#cancel');
	cancelArea.innerHTML = '';
	let htmlStr = '';
		htmlStr += `
			 	<div id="cancel">
			 		<div class="mt-3 text-center">
						<button id="btnBack" onclick="back()" class="btn btn-outline-dark">돌아가기</button>
					</div>
			 		<div class="mt-3 input-group">
						<input id="reason" type="text" class="form-control" placeholder="취소 사유를 입력해주세요.">
						<button id="updateReason" class="btnUpdate btn btn-outline-dark">주문 취소</button>
					</div>
			 	</div>
			 `;
	cancelArea.innerHTML = htmlStr;
	
	const btnUpdate = document.querySelector('#updateReason');
	btnUpdate.addEventListener('click', updateReason);
	
};

const updateReason = (e) => {
//	const form = document.querySelector('#updateReasonForm');
	
	const orderId = document.querySelector('#orderId').innerText;
	const reason = document.querySelector('input#reason').value;
	const status = '취소 처리중';
	console.log(reason, status, orderId);
	if (reason === '') {
		alert('취소 사유를 입력해주세요.');
		return;
	}
	
	const reqUrl = `/orderDetail/update/${orderId}`; // 요청 주소
    const data = { replyText, status }; // {replyText: replyText}, 요청 데이터(수정할 댓글 내용)
    axios
        .put(reqUrl, data) // PUT 방식의 Ajax 요청을 보냄.
        .then((response) => {
            console.log(response);
            // TODO:
        }) // 성공 응답일 때 동작할 콜백을 등록.
        .catch((error) => console.log(error)); // 에러 응답일 때 동작할 콜백을 등록.
    
	
};

const back = (e) => {
	const orderId = document.querySelector('#orderId').innerText;
	
	const cancelArea = document.querySelector('div#cancel');
	cancelArea.innerHTML = '';
	let htmlStr = '';
	htmlStr += `
	<div id="cancel" class="m-3 text-center">
		<button id="btnCancel" class="btn btn-outline-dark">주문 취소하기</button>
	</div>
	`;
	cancelArea.innerHTML = htmlStr;
	
	const btnCancel = document.querySelector('#btnCancel');
	btnCancel.addEventListener('click', writeReason);

};


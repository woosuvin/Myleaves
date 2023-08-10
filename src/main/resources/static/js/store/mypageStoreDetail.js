/**
 * 주문 취소 버튼
 * 지현
 */

document.addEventListener('DOMContentLoaded', () => {
	
	const orderId = document.querySelector('input#orderId').value;
	console.log(orderId);
	
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
							<button id="updateReason" class="btnUpdate btn btn-outline-dark" data-id="${orderId}">주문 취소</button>
						</div>
				 	</div>
				 `;
		cancelArea.innerHTML = htmlStr;
		
		
		// 수빈: 주문 취소 버튼 이벤트(취소 사유 등록, 주문 상태 변경)
		const updateReason = (e) => {
			
			const reason = document.querySelector('input#reason').value;
			const status = '취소 처리중';
			console.log(orderId, reason, status);
			
			if (reason === '') {
				alert('취소 사유를 입력해주세요.');
				return;
			}
			
			const reqUrl = `/api/mypage/store/orderDetail/${orderId}`; // 요청 주소
		    const data = {orderId, reason, status}; // {replyText: replyText}, 요청 데이터(수정할 댓글 내용)
		    
		    const check = confirm(`주문을 취소하시겠습니까?
취소 접수 후 변경이 불가합니다.
이후 문의사항은 고객센터에 문의 바랍니다.`);
		    
		    if(check) {
				axios
		        .put(reqUrl, data) // PUT 방식의 Ajax 요청을 보냄.
		        .then((response) => {
		            console.log(response);
		            alert('취소 접수 완.');
		            location.reload();
		        })
		        .catch((error) => console.log(error));
			}
		    
		};
		const btnUpdate = document.querySelector('#updateReason');
		btnUpdate.addEventListener('click', updateReason);
	
	};
	const btnCancel = document.querySelector('#btnCancel');
	btnCancel.addEventListener('click', writeReason);


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
	
});




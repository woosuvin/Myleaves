/**
 * 수빈
 * 관리자 페이지 주문 상태 변경
 */

document.addEventListener('DOMContentLoaded', () => {
	
	// 주문 상태 변경 함수
	const modifyStatus = (e) => {
		
		const orderId = e.target.getAttribute('data-id'); // 주문 번호
		const status = document.querySelector(`#status_${orderId}`).value; // 주문 상태
		console.log(orderId, status);
		
		const reqUrl = `/api/mngr/order/${orderId}`;
		const data = {orderId, status};
		
		const check = confirm(`변경 사항을 저장하시겠습니까?
주문 번호: ${orderId}
주문 상태: ${status}`);
		if(check) {
			
			axios.put(reqUrl, data)
			.then((response) => {
					alert('저장되었습니다.');
			})
			.catch((error) => console.log(error));
		}
	};
	
	// 저장 버튼들
	const statudBtn = document.querySelectorAll('button#statudBtn');
	for(let btn of statudBtn) {
		btn.addEventListener('click', modifyStatus);
	}
	
	
	
});
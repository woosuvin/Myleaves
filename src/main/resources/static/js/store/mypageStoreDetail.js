/**
 * 주문 취소 버튼
 * 지현
 */

 document.addEventListener('DOMContentLoaded', () => {
	 
	 const orderId = document.querySelector('#orderId').value;
	 const userId = document.querySelector('#userId').value;
	 
	 const orderStatus = document.querySelector('#orderStatus').value;
	 
	 const btnCancel = document.querySelector('#btnCancel');
	 
	 btnCancel.addEventListener('click', () => {
		 const cancelArea = document.querySelector('div#cancel');
		 cancelArea.innerHTML = '';
		 
		 let htmlStr = '';
		 htmlStr += `
		 	<div id="cancel">
		 		<div class="input-group">
					<input id="reason" type="text" class="form-control" placeholder="취소 사유를 입력해주세요.">
					<button id="submitCancel" class="btn btn-outline-dark" type="button">주문 취소</button>
				</div>
				<div class="mt-3 text-center">
					<button id="btnReturn" onclick="return()" class="btn btn-outline-dark">돌아가기</button>
				</div>
		 	</div>
		 `
		 cancelArea.innerHTML = htmlStr;
		 
	 });
	 
	 const btnReturn = document.querySelector('#btnReturn');
	 btnReturn.addEventListener('click', () => {
		const cancelArea = document.querySelector('div#cancel');
		let htmlStr = '';
		htmlStr += `
		<div id="cancel" class="m-3 text-center">
			<th:block th:case="'입금 확인'">
				<button id="btnCancel" class="btn btn-outline-dark">주문 취소하기</button>
			</th:block>
		</div>
		`
		cancelArea.innerHTML = htmlStr;
	 });
	 
 });
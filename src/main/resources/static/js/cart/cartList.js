/**
 * 수빈
 * 장바구니 페이지
 * 수량 버튼, 삭제 버튼, 결제하기 버튼, 금액 계산
 */

document.addEventListener('DOMContentLoaded', () => {
	
	const deleteItem = (e) => {
		const check = confirm('장바구니에서 삭제하겠습니까?');
		if(!check) {
			return;
		}
		
		const itemId = e.target.getAttribute('data-id');
		const userId = document.querySelector('span#userId').innerText;
		const reqUrl = `/api/cart/${userId}/${itemId}`;
		const data = {itemId, userId};
		axios
		.delete(reqUrl, data)
		.then((response) => {
			console.log(response);	
			location.reload(); // 화면 새로고침
		})
		.catch((error) => console.log(error));

	};
	
	const btnDeletes = document.querySelectorAll('button#cartDeleteBtn');
	for(let btn of btnDeletes) {
		btn.addEventListener('click', deleteItem);
	}
	
	
	/*const btnMinuss = document.querySelectorAll('button#btnMinus');
	for(let btn of btnMinuss) {
		btn.addEventListener('click', (e) => {
			const itemId = e.target.getAttribute('data-id');
			const cntId = `#cnt_${itemId}`; // 해당 아이템의 수량의 아이디
			const cnt = document.queryselector('#cntId');
			console.log(cnt);
			cnt.innerText = '';
			cnt.value++;
		});
	}
	const btnPluss = document.querySelectorAll('button#btnPlus');
	*/
	
});

/** 수빈
 * store detail 수량 변경 버튼
 */

document.addEventListener('DOMContentLoaded', () => {
	
	const btnMinus = document.querySelector('button#btnMinus');
	const btnPlus = document.querySelector('button#btnPlus');
	const productCnt = document.querySelector('input#productCnt'); 
	const price = document.querySelector('#price').innerText;
	const totalPrice = document.querySelector('#totalPrice');
	const inven = document.querySelector('div#inven').value;
	totalPrice.innerHTML = price; // 맨 처음 총 상품금액(price * 1)
	
	const cntPlus = () => {
		if(productCnt.value === inven) { // 선택 수량이 남은 재고와 같을 때 +를 누르면
			alert('최대 수량 입니다.');
			return;
		}
		
		productCnt.innerText = ''; // 상품 개수 input 비우고
		productCnt.value++; // 더한 상품 개수 값 채워주기
		
		totalPrice.innerHTML = price * productCnt.value; // 총 상품 금액에 금액*수량 채워주기
	};
	btnPlus.addEventListener('click', cntPlus);
	
	const cntMinus = () => {
		if(productCnt.value === '1') { // 수량 1일 때 -버튼을 누르면
			alert('최소 수량 입니다.');
			return;
		}
		
		productCnt.innerText = ''; // 상품개수 input 비우고
		productCnt.value--; // 더한 상품 개수 값 채워주기
		
		totalPrice.innerHTML = price * productCnt.value; // 총 상품 금액에 금액*수량 채워주기
	};
	btnMinus.addEventListener('click', cntMinus);
});
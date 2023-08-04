/** 수빈
 * store detail 수량 변경 버튼
 */

document.addEventListener('DOMContentLoaded', () => {
	
	const form = document.querySelector('form#storeForm');
	const btnMinus = document.querySelector('button#btnMinus');
	const btnPlus = document.querySelector('button#btnPlus');
	const itemPrice = document.querySelector('#itemPrice').innerText;
	const totalPrice = document.querySelector('#totalPrice');
	const inven = document.querySelector('input#inven').value;
	const addWishBtn = document.querySelector('button#addWishBtn');
	const addCartBtn = document.querySelector('button#addCartBtn');
	totalPrice.innerHTML = itemPrice; // 맨 처음 총 상품금액(price * 1)
	const userId = document.querySelector('span#userId').innerText; // 현재 로그인 아이디
	const itemId = document.querySelector('span#itemId').innerText; // 상품 아이디
	
	const cntPlus = () => {
		const cnt = document.querySelector('input#cnt');
		if(cnt.value === inven) { // 선택 수량이 남은 재고와 같을 때 +를 누르면
			alert('최대 수량 입니다.');
			return;
		}
		
		cnt.innerText = ''; // 상품 개수 input 비우고
		cnt.value++; // 더한 상품 개수 값 채워주기
		totalPrice.innerHTML = itemPrice * cnt.value; // 총 상품 금액에 금액*수량 채워주기
	};
	btnPlus.addEventListener('click', cntPlus);
	
	const cntMinus = () => {
		const cnt = document.querySelector('input#cnt');
		if(cnt.value === '1') { // 수량 1일 때 -버튼을 누르면
			alert('최소 수량 입니다.');
			return;
		}
		
		cnt.innerText = ''; // 상품개수 input 비우고
		cnt.value--; // 더한 상품 개수 값 채워주기
		totalPrice.innerHTML = itemPrice * cnt.value; // 총 상품 금액에 금액*수량 채워주기
	};
	btnMinus.addEventListener('click', cntMinus);
	
	const addWish = () => {
		console.log('위시 리스트 추가')
		/*const confirm = confirm('관심 상품에 저장하시겠습니까?');
		if(confirm) {
			form.action = 'update'; // action 이름 정해지면 바꾸기
			form.method = 'post';
			form.submit();
		}*/
		// TODO: 리스트 테이블에 있는지 검사해야됨
	}
	addWishBtn.addEventListener('click', addWish);
	
	const addCart = () => {
		
		const cnt = document.querySelector('input#cnt').value;
		console.log(userId, itemId, cnt);
		const check = confirm('장바구니에 추가하시겠습니까?');
		if(check) {
			const data = {userId, itemId, cnt};
			const reqUrl = '/api/cart';
			axios.post(reqUrl, data)
			.then((response) => {
				console.log(response);
				alert('장바구니에 추가되었습니다.');
			})
			.catch((error) => {
				console.log(error);
			});
		}
		// TODO: 카트 테이블에 있는지 검사해야됨
	};
	addCartBtn.addEventListener('click', addCart);
	
	
	
	
	
});
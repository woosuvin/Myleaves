/**
 * 수빈 분양 상태 변경
 */
document.addEventListener('DOMContentLoaded', () => {
	
	let select = document.getElementById('select'); // select box
	
	select.addEventListener('change', function() {
		const sellId = document.querySelector('input#sellId').value; // 분양글 번호
		const sellerId = document.querySelector('#sellerId').innerText; // 분양 글 올린 사람
		const buyerId = document.querySelector('#buyerId').innerText; // 사는 사람
		/*const title = document.querySelector('#title').innerText;
		const price = document.querySelector('#price').innerText;*/
		
		let sold;
		
		if(select.value === '판매중') {
			sold = 1;
		} else if(select.value === '거래중') {
			sold = 2;
		} else {
			sold = 3;
		}
		
		console.log(sellId, sellerId, buyerId, sold);
		
		// 1 2 분양중 거래중일 경우
		if(sold === 1 || sold === 2) {
			reqUrl = `/sellbuy/update/${sellId}/${sold}`;
			data = { sellId, sold };
			const check = confirm('변경하시겠습니까?');
			if (check) {
				axios.put(reqUrl, data)
				.then((response) => {
					console.log(response);	
				})
				.catch((error) => console.log(error));
			}
		}
		
		// 3 분양료일 경우 
		
		if(sold === 3) {
			reqUrl = '/sellbuy/sold';
			data = { sellId, sellerId, buyerId, sold };
			const check = confirm('변경하시겠습니까?');
			if (check) {
				axios.post(reqUrl, data)
					.then((response) => {
						console.log(response);
					})
					.catch((error) => console.log(error));
			}
		}
		
		
	}); 
	
		
		
	
	
});
/**
 * 무통장입금 입력사항 div 처리
 */

document.addEventListener('DOMContentLoaded', () => {
	
	const payments = document.querySelectorAll("input[name='payment']");
	const withoutBankBtn = document.querySelector('input#withoutBank');
	const withoutBankDiv = document.querySelector('div#withoutBankDiv');
	const cardDiv = document.querySelector('div#cardDiv');
	
	payments.forEach((radio) => {
		radio.addEventListener('change', (e) => {
			const current = e.currentTarget; // 현재 클릭된 버튼
			if(current === withoutBankBtn) { // current가 무통장이면
				withoutBankDiv.style.display = 'block';
				cardDiv.style.display = 'none';
			} else { // current가 카드결제면
				withoutBankDiv.style.display = 'none';
				cardDiv.style.display = 'block';
			}
		});
	});
	
	
});
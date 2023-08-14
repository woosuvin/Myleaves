/** 수빈
 * 관리자 스토어 수정/삭제
 */

document.addEventListener('DOMContentLoaded', () => {
	
	const form = document.querySelector('form#storeModifyForm');
	const itemId = document.querySelector('input#itemId').value;
	
	const checkbox = document.getElementById('checkbox'); // 판매 중단 체크박스
	const sold = document.querySelector('#soldValue').value; 
	const inven = document.querySelector('input#inven').value; // 재고
	
	if (sold === '1' || inven === '0') { // sold=1 이거나 수량=0 이면 체크박스 checked=true
		checkbox.checked = true;
	} else {
		checkbox.checked = false;
	}
	
	const btnUpdate = document.querySelector('button#btnUpdate');
	btnUpdate.addEventListener('click', (e) => {
		
		const itemName = document.querySelector('input#itemName').value; // 상품명
		const price = document.querySelector('input#price').value; // 가격
		const content = document.querySelector('textarea#summernote').value; // 상세 설명
		
		
		if (itemName === '' || price === '' || content === '' || inven === '') {
			alert('내용을 입력하세요');
			return;
		}
		
		const result = confirm('변경 사항을 저장하시겠습니까?');
		if (result) {
			form.action = 'update';
			form.method = 'post';
			form.submit();
		}
	});
	
		
	/*const btnDelete = document.querySelector('button#btnDelete');
	btnDelete.addEventListener('click', (e) => {
		const result = confirm('상품을 삭제하시겠습니까?');
		if (result) {
			form.action = 'delete';
			form.method = 'post';
			form.submit();
		}
	});*/
	
});
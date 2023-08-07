/**
 * mypage 수정 삭제
 */

document.addEventListener('DOMContentLoaded', function() {
	const updateForm = document.querySelector('#updateForm')

	const btnDelete = document.querySelector('#btnDelete');
	const btnCancel = document.querySelector('#btnCancel');
	const btnUpdate = document.querySelector('#btnUpdate')
	
	// 수정 취소 버튼
	btnCancel.addEventListener('click', () => {
		const check = confirm('작성하신 내용은 저장되지 않습니다. 취소하시겠습니까?');
		if (check) {
			history.go(-1);
		}
	})
	
	// 삭제 버튼
	btnDelete.addEventListener('click', () => {
		const result = confirm('정말 삭제할까요?')
		console.log(`삭제 결과 = ${result}`)
		const userId = document.querySelector('#userId').value;
		if(result) {
			updateForm.action = 'delete'
			updateForm.method = 'post'
			updateForm.submit();
		}
		
	})
	
	
	// 수정 버튼
	btnUpdate.addEventListener('click', () => {
		const planteriorId = document.querySelector('#planteriorId').value;
		const plantName = document.querySelector('#plantName').value; // 한글만 가능하게 설정
		const plantNameEnglish = document.querySelector('#plantNameEnglish').value; // 영어만 가능하게 설정
		const userId = document.querySelector('#userId').value;
		
		if (plantName === '' || plantNameEnglish === '') {
			alert('비어있는 부분을 입력해주세요')
			return;
		}
		
		const result = confirm('변경 사항을 저장하시겠습까?')
		if(result) {
			updateForm.action = 'update'
			updateForm.method = 'post'
			updateForm.submit();
		}
	})
	
	
})
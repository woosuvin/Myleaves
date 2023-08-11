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
		if (result) {
			updateForm.action = 'delete'
			updateForm.method = 'post'
			updateForm.submit();
		}

	})


	// 수정 처리
	const inputStateContent = document.querySelector('input#stateContent');
	const inputConditionContent = document.querySelector('input#conditionContent');

	const filterBtns = document.querySelectorAll('.filterBtn');

	for (const filterBtn of filterBtns) {
		filterBtn.addEventListener('click', (e) => {

			console.log('ghkrdls');
			e.preventDefault();

			// li 삽입할 ul 요소를 찾음
			const secondFilter = document.querySelector('#secondFilter');
			secondFilter.classList.remove('d-none');

			console.log(e.target.value);

			inputStateContent.value = e.target.value;
			inputConditionContent.value = '';
		});
	}
	
	const filterSecondBtns = document.querySelectorAll('input.filterSecondBtn');
	for (let btn of filterSecondBtns) {
		btn.addEventListener('click', (e) => {
			console.log(e.target.value);
			inputConditionContent.value +=  e.target.value + ',';
			
		})
	}

	btnUpdate.addEventListener('click', (e) => {
		
		e.preventDefault();
		
		const stateContent = document.querySelector('input#stateContent').value;
		const conditionContent = document.querySelector('input#conditionContent').value;
		
		const planteriorId = document.querySelector('#planteriorId').value;
		const plantName = document.querySelector('#plantName').value; // 한글만 가능하게 설정
		const plantNameEnglish = document.querySelector('#plantNameEnglish').value; // 영어만 가능하게 설정
		const userId = document.querySelector('#userId').value;

		if (plantName === '' || plantNameEnglish === '' || 
			stateContent === '' || conditionContent === '') {
			alert('비어있는 부분을 선택해주세요')
			return;
		}

		const result = confirm('변경 사항을 저장하시겠습까?')
		if (result) {
			updateForm.action = 'update'
			updateForm.method = 'post'
			updateForm.submit();
		}
	})


})
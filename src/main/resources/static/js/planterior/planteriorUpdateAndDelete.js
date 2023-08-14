/**
 * mypage 수정 삭제
 */

document.addEventListener('DOMContentLoaded', function() {
	const updateForm = document.querySelector('#updateForm')

	const btnDelete = document.querySelector('#btnDelete');
	const btnCancel = document.querySelector('#btnCancel');
	const btnUpdate = document.querySelector('#btnUpdate');


	// 한글만 입력가능
	const plantName = document.querySelector("#plantName");

	plantName.addEventListener("keypress", function(event) {
		onlyKorean(event);
	});

	function onlyKorean(event) {
		var keyCode = event.which || event.keyCode;
		var inputValue = event.target.value;

		// 허용되는 키 코드: 한글 자모음 및 스페이스, 백스페이스, 딜리트
		if ((keyCode >= 12592 && keyCode <= 12687) || keyCode === 32 || keyCode === 8 || keyCode === 46) {
			return true;
		} else {
			event.preventDefault();
			alert('한글만 입력가능합니다.')
			return false;
		}
	}
	
	// 영어만 입력가능

	const plantNameEnglish = document.querySelector('#plantNameEnglish');

	plantNameEnglish.addEventListener("input", function(event) {
		var cleanedValue = this.value.replace(/[^a-zA-Z\s]/g, '');
		if (cleanedValue !== this.value) {
			alert("한글과 숫자는 입력할 수 없습니다.");
		}
		this.value = cleanedValue;
	});
	

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
    const fileInput = document.getElementById('file-input');
    const file = fileInput.files[0];

	const filterBtns = document.querySelectorAll('.filterBtn');

	for (const filterBtn of filterBtns) {
		filterBtn.addEventListener('click', (e) => {

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
			inputConditionContent.value += e.target.value + ',';

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
			stateContent === '' || conditionContent === '' || !file) {
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
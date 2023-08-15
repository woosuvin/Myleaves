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
	const file = document.getElementById('file');
	let textContent = '';
	const preview = document.querySelector('.preview');
	file.addEventListener('change', () => {
		const selectedFiles = file.files

		for (const files of selectedFiles) {
			textContent = `파일명 : ${files.name}`;
			preview.innerHTML = textContent;
		}
	})


	const filterBtns = document.querySelectorAll('.filterBtn');
	let lastClickedBtn = null; // 마지막으로 클릭한 버튼을 추적
	let lastClickedValue = null; // 마지막으로 클릭한 버튼의 값 추적

	for (const filterBtn of filterBtns) {
		filterBtn.addEventListener('click', (e) => {

			// li 삽입할 ul 요소를 찾음
			const secondFilter = document.querySelector('#secondFilter');
			// 이미 'clicked' 클래스가 추가된 버튼을 다시 클릭한 경우
			if (filterBtn === lastClickedBtn) {
				filterBtn.classList.remove('clicked');
				secondFilter.classList.add('d-none');
				lastClickedBtn = null; // 버튼 추적 상태 초기화
				lastClickedValue = null; // 버튼 값 추적 상태 초기화
			} else {
				// 다른 버튼의 'clicked' 클래스 제거
				for (const otherBtn of filterBtns) {
					if (otherBtn !== filterBtn) {
						otherBtn.classList.remove('clicked');
					}
				}

				// 클릭한 버튼에 'clicked' 클래스 추가
				filterBtn.classList.add('clicked');
				secondFilter.classList.remove('d-none');

				// 마지막으로 클릭한 버튼을 업데이트
				lastClickedBtn = filterBtn;
				lastClickedValue = e.target.value;

			}



			// 클릭한 버튼의 값을 가져와 input 요소에 설정
			inputStateContent.value = lastClickedValue || ''; // 값이 없는 경우는 빈 문자열로 설정
			console.log(inputStateContent.value)
			inputConditionContent.value = '';
		});
	}

	let lastClickedBtnFilterSecond = null; // 마지막으로 클릭한 버튼을 추적
	let lastClickedValueFilterSecond = null;
	const filterSecondBtns = document.querySelectorAll('input.filterSecondBtn');
	for (let btn of filterSecondBtns) {
		btn.addEventListener('click', (e) => {
			// 이미 'clicked' 클래스가 추가된 버튼을 다시 클릭한 경우
			if (btn === lastClickedBtnFilterSecond) {
				btn.classList.remove('clicked');
				lastClickedBtnFilterSecond = null; // 버튼 추적 상태 초기화
				lastClickedValueFilterSecond = null; // 버튼 값 추적 상태 초기화
			} else {

				// 클릭한 버튼에 'clicked' 클래스 추가
				btn.classList.add('clicked');
				lastClickedBtn = btn;
				lastClickedValue = e.target.value;
			}

			console.log(e.target.value);
			inputConditionContent.value += lastClickedValue + ',';

		})
	}

	// 리셋 버튼 선택
	const filter_reset_btn = document.querySelector('#filter_reset_btn');
	filter_reset_btn.addEventListener('click', () => {
		
		// 버튼의 선택 해제
		for (const filterBtn of filterBtns) {
			filterBtn.classList.remove('clicked');
		}

		// 'secondFilter'의 'd-none' 클래스 추가하여 숨김
		secondFilter.classList.add('d-none');

		// 값 초기화
		inputStateContent.value = '';
		inputConditionContent.value = '';
	});




	btnUpdate.addEventListener('click', (e) => {

		e.preventDefault();

		const stateContent = document.querySelector('input#stateContent').value;
		const conditionContent = document.querySelector('input#conditionContent').value;

		const planteriorId = document.querySelector('#planteriorId').value;
		const plantName = document.querySelector('#plantName').value; // 한글만 가능하게 설정
		const plantNameEnglish = document.querySelector('#plantNameEnglish').value; // 영어만 가능하게 설정
		const userId = document.querySelector('#userId').value;
		const pcid = document.querySelector('#pcid').value;

		if (plantName === '' || plantNameEnglish === '' ||
			stateContent === '' || conditionContent === '' || textContent === '') {
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
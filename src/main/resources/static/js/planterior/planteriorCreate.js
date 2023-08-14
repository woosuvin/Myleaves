/**
 * planterior create page
 */
document.addEventListener('DOMContentLoaded', () => {

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




	// 플랜테리어 작성 취소 버튼
	const createForm = document.querySelector('#createForm');

	const inputStateContent = document.querySelector('input#stateContent');
	const inputConditionContent = document.querySelector('input#conditionContent');

	const btnCancel = document.querySelector('#btnCancel');
	btnCancel.addEventListener('click', () => {
		const check = confirm('작성하신 내용은 저장되지 않습니다. 취소하시겠습니까?');
		if (check) {
			history.go(-1);
		}
	})

	// 플랜테리어 작성: 영문/한문
	const btnCreate = document.querySelector('#btnCreate');
	const filterBtns = document.querySelectorAll('.filterBtn');
	let stateContent = '';
	let conditionContent = '';
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

			// 추가
			/*
			let htmlStr = '';
			htmlStr += `
				<li>
					<input type="button" class="filterSecondBtn" id="filterSecondBtn" name="conditionContent" value="초보자용"  />
					<input type="button" class="filterSecondBtn" id="filterSecondBtn" name="conditionContent" value="선물하기 좋은"  />
					<input type="button" class="filterSecondBtn" id="filterSecondBtn" name="conditionContent" value="공기정화"  />
					<input type="button" class="filterSecondBtn" id="filterSecondBtn" name="conditionContent" value="빛이 적어도 되는"  />
					<input type="button" class="filterSecondBtn" id="filterSecondBtn" name="conditionContent" value="향기나는"  />
					<input type="button" class="filterSecondBtn" id="filterSecondBtn" name="conditionContent" value="반려동물 안전한"  />
					<input type="button" class="filterSecondBtn" id="filterSecondBtn" name="conditionContent" value="목대있는"  />
					<input type="button" class="filterSecondBtn" id="filterSecondBtn" name="conditionContent" value="흙이 필요없는"  />
					<input type="button" class="filterSecondBtn" id="filterSecondBtn" name="conditionContent" value="덩굴로 자라는"  />
				</li>
				`;
			secondFilter.innerHTML = htmlStr;
			
			const filterSecondBtns = document.querySelectorAll('input.filterSecondBtn');
			for (let btn of filterSecondBtns) {
				btn.addEventListener('click', () => {
					conditionContent += btn.value;
					console.log(conditionContent);
				})
			}
			*/

		});
	}

	const filterSecondBtns = document.querySelectorAll('input.filterSecondBtn');
	for (let btn of filterSecondBtns) {
		btn.addEventListener('click', (e) => {
			console.log(e.target.value);
			inputConditionContent.value += e.target.value + ',';

		})
	}


	btnCreate.addEventListener('click', (e) => {
		e.preventDefault();

		const plantName = document.querySelector('#plantName').value; // 한글만 가능하게 설정
		const plantNameEnglish = document.querySelector('#plantNameEnglish').value; // 영어만 가능하게 설정
		const userId = document.querySelector('#userId').value;

		const stateContent = document.querySelector('input#stateContent').value;
		const conditionContent = document.querySelector('input#conditionContent').value;
		const file = document.querySelector('#file').value;

		// 카테고리 삽입 가능성 존재
		// 이미지 성공시 !formFile 넣기
		if (plantName === '' || plantNameEnglish === '' ||
			stateContent === '' || conditionContent === '' ) {
			alert('비어있는 부분을 선택해주세요')
			return;
		}
		const check = confirm('수정할 경우, 마이페이지에서만 수정 가능합니다.')
		if (check) {
			createForm.action = ''
			createForm.method = 'post'
			createForm.submit();
		}
	});



	// 플랜테리어 이미지 
	function imageUpload(input) {
		const preview = input.parentElement.querySelector('.imagePreview');
		const reader = new FileReader();
		reader.onload = function(e) {
			preview.src = e.target.result;
		};
		if (input.files && input.files[0]) {
			reader.readAsDataURL(input.files[0]);
		} else {
			preview.src = "";
		}
	}



});
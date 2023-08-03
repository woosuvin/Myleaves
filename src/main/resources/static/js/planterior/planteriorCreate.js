/**
 * planterior create page
 */
document.addEventListener('DOMContentLoaded', () => {
	
	// 플랜테리어 작성 취소 버튼
	const createForm = document.querySelector('#createForm');
	const btnCancel = document.querySelector('#btnCancel');
	btnCancel.addEventListener('click', () => {
		const check = confirm('작성하신 내용은 저장되지 않습니다. 취소하시겠습니까?');
		if (check) {
			history.go(-1);
		}
	})

	// 플랜테리어 작성: 영문/한문
	let formFile = document.querySelector('#formFile').value;
	const btnCreate = document.querySelector('#btnCreate');
	btnCreate.addEventListener('click', () => {
		event.preventDefault();
		const plantName = document.querySelector('#plantName').value; // 한글만 가능하게 설정
		const plantNameEnglish = document.querySelector('#plantNameEnglish').value; // 영어만 가능하게 설정
		const userId = document.querySelector('#userId').value;
		
		// 카테고리 삽입 가능성 존재
		// 이미지 성공시 !formFile 넣기
		if (plantName === '' || plantNameEnglish === '') {
			alert('비어있는 부분을 입력해주세요')
			return;
		}
		const check = confirm('수정할 경우, 마이페이지에서만 수정 가능합니다.')
		if (check) {
			createForm.submit();
		}
	})

	// 플랜테리어 카테고리(보내기) -> second filter는 댓글 처럼 innerHtml 하기


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
	
	
	
})
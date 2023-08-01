/**
 * 플랜테리어 js
 */
document.addEventListener('DOMContentLoaded', () => {

	/**
	 *  planterior create page
	 */

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
	const formFile = document.querySelector('#formFile').value;
	const btnCreate = document.querySelector('#btnCreate');
	btnCreate.addEventListener('click', () => {
		event.preventDefault();
		const PlantkoreanName = document.querySelector('#PlantkoreanName').value;
		const PlantEnglishName = document.querySelector('#PlantEnglishName').value;
		// 카테고리 삽입 가능성 존재
		if (PlantkoreanName === '' || PlantEnglishName === '' || !formFile) {
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
	/* 
	const inputContainer = document.getElementById('inputContainer');
	deleteButton.addEventListener('click', () => {

		if (!formFile) {
			alert('파일을 첨부해 주세요')
			return false;
		} else {
			const fileInput = inputContainer.querySelector('.input-group');
			inputContainer.removeChild(fileInput);
		}

	})
	*/
	
	/**
	 *  planterior main
	 */


})








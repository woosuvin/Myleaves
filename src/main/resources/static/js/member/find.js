document.addEventListener('DOMContentLoaded', () => {
	let authCode;

	function showForm(formId) {
		const forms = document.querySelectorAll('div[id$="Form"]');
		forms.forEach(form => {
			if (form.id === formId) {
				form.style.display = 'block';
			} else {
				form.style.display = 'none';
			}
		});
	}

	const findIdButton = document.getElementById('findIdButton');
	const findPwdButton = document.getElementById('findPwdButton');

	findIdButton.addEventListener('click', () => {
		showForm('findIdForm');
	});

	findPwdButton.addEventListener('click', () => {
		showForm('findPwdForm');
	});

	const btnIdConfirm = document.querySelector('button#btnIdConfirm');
	btnIdConfirm.addEventListener('click', (e) => {

		const name = document.querySelector('input[placeholder="이름을 입력해주세요"]').value;
		const email = document.querySelector('input#idForm-inputEmail').value;
		
		
		
		let reqUrl = '/member/find/id';
		reqUrl += `?name=${encodeURIComponent(name)}&email=${encodeURIComponent(email)}`;

		axios.get(reqUrl)
			.then((response) => {
				alert(response.data);
			})
			.catch((error) => {
				console.log(error);
			})
	});

	const btnSubmit = document.querySelector('button#btnSubmit');
	btnSubmit.addEventListener('click', (e) => {

		const reqUrl = '/member/confirm';
		const email = document.querySelector('input#pwdForm-inputEmail').value;

		axios.get(reqUrl, { params: { email: email } })
			.then((response) => {
				console.log(response.data);
				authCode = response.data;
			})
			.catch((error) => {
				console.log(error);
			});
	});

	const btnPwdConfirm = document.querySelector('button#btnPwdConfirm');
	btnPwdConfirm.addEventListener('click', (e) => {
		const userId = document.querySelector('input#inputId').value;

		const inputCode = document.querySelector('input#inputCode').value;
		if (authCode == inputCode) {

			const reqUrl = '/member/modify/pwd';

			axios.get(reqUrl, { params: {userId: userId} })
				.then((response) => {
					alert('임시 비밀번호는 ' + response.data + '입니다.');
				})
				.catch((error) => {
					alert('존재하지 않은 사용자입니다.');
				})
		} else {
			alert('인증번호가 일치하지 않습니다.');
		}
	});
});
document.addEventListener('DOMContentLoaded', () => {
	const form = document.querySelector('form#signup-form');
	const btnSend = document.querySelector('button#btnSend');
	const btnConfirm = document.querySelector('button#btnConfirm');
	const btnJoin = document.querySelector('input#btnJoin');
	let authCode;
	let isMatched;
	
	btnSend.addEventListener('click', (e) => {
		e.preventDefault();
		
		const email = document.querySelector('input[name=email]').value;
		const reqUrl = '/member/confirm';

		axios.get(reqUrl, { params: { email: email } })
			.then((response) => {
				console.log(response.data);
				authCode = response.data;
			})
			.catch((error) => {
				console.log(error);
			});
	});
	
	btnConfirm.addEventListener('click', (e) => {
		e.preventDefault();
		
		const inputCode = document.querySelector('input[placeholder=인증번호]').value;
		if (authCode == inputCode) {
			isMatched = true;
			alert('확인되었습니다.');
		} else {
			isMatched = false;
			alert('인증번호가 일치하지 않습니다.');
		}
	});
	
	btnJoin.addEventListener('click', (e) => {
		e.preventDefault();
		
		if (isMatched) {
			form.submit();
		} else {
			alert('인증번호가 일치하지 않아 가입을 진행할 수 없습니다.');
		}
	});
});
document.addEventListener('DOMContentLoaded', () => {
	const form = document.querySelector('form#signup-form');
	const btnSend = document.querySelector('button#btnSend');
	const btnConfirm = document.querySelector('button#btnConfirm');
	const btnJoin = document.querySelector('input#btnJoin');
	const inputId = document.querySelector("input[name=userId]");
	const idErrorMessage = document.querySelector("#idErrorMessage");
	//const pwdErrorMessage = document.querySelector('#pwdErrorMessage');
	//const inputPwd = document.querySelector('input[name=pwd]');

	let isMatchedId;
	//let isValidPwd;

	const inputPhone = document.querySelector('input[name=phone]'); // 휴대폰 번호 입력 필드를 선택합니다.

	inputPhone.addEventListener('input', (event) => {
		event.target.value = event.target.value.replace(/[^0-9]/g, ''); // 숫자 이외의 문자를 모두 제거합니다.
	});

	inputId.addEventListener('blur', function() {
		const id = inputId.value;
		//const idPattern = /^(?=.{5,20}$)([a-z]+$|[a-z0-9]+$|[a-z0-9_\-]+$|[a-z_\-]+)$/;
		//
		//if (!idPattern.test(id)) {
		//	idErrorMessage.textContent = '5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.';
		//	isMatchedId = false;
		//} else {
		//	idErrorMessage.textContent = '';
		//	isMatchedId = true;
		//}
		//
		const reqUrl = '/member/check/id';

		axios.get(reqUrl, { params: { inputId: id } })
			.then((response) => {
				if (response.data !== 'fail') {
					idErrorMessage.textContent = '이미 존재하는 아이디입니다.';
					isMatchedId = false;

				} //else if (!idPattern.test(id)) {
				//idErrorMessage.textContent = '5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.';
				//isMatchedId = false;
				//} 
				else {
					idErrorMessage.textContent = '';
					isMatchedId = true;
				}
			})
			.catch((error) => {
				console.log(error);
			});
	});

	//inputPwd.addEventListener('blur', function() {
	//	const password = inputPwd.value;

	//	// 정규식을 사용하여 패스워드 유효성 검사
	//	const passwordPattern = /^(?=.*[A-Za-z\d@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,16}$/;

	//	if (!passwordPattern.test(password)) {
	//		pwdErrorMessage.textContent = '8~16자의 영문 대/소문자, 숫자, 특수문자(@$!%*#?&) 중 하나만 포함해야 합니다.';
	//		isValidPwd = false; // 패스워드 형식이 맞지 않으면 false로 설정
	//	} else {
	//		pwdErrorMessage.textContent = ''; // 텍스트를 초기화하여 에러 메시지를 없앰
	//		isValidPwd = true; // 패스워드 형식이 맞을 때만 true로 설정
	//	}
	//});


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

		const inputCode = document.querySelector('input#inputCode').value;
		console.log(inputCode);
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
			if (isMatchedId) {
				//if (isValidPwd) {
				form.submit();
				//} else {
				//	alert('비밀번호를 확인해주세요');
				//}
			} else {
				alert('아이디 정보를 확인해주세요');
			}


		} else {
			alert('인증번호가 일치하지 않아 가입을 진행할 수 없습니다.');
		}
	});
});
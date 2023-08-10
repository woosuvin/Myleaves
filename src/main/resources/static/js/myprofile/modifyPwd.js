document.addEventListener('DOMContentLoaded', () => {
	const btnSubmit = document.querySelector('button#btnSubmit');

	document.querySelector('input#currentPwd').addEventListener('blur', function() {
		const currentPwd = this.value;
		const reqUrl = '/mypage/profile/matches/pwd';


		axios.get(reqUrl, { params: { userId: userId,
									  currentPwd: currentPwd }})
			.then((response) => {
				if (response.data !== true) {
					alert('비밀번호가 일치하지 않습니다.');
				} 
			})
			.catch((error) => {
				alert(error);
			});
	});

	btnSubmit.addEventListener('click', (e) => {
		const newPwd = document.querySelector('input#newPwd').value;
		const reNewPwd = document.querySelector('input#re-newPwd').value;
	
		const reqUrl = '/mypage/profile/modify/pwd';
		
		if (newPwd === reNewPwd) {
			
			axios.put(reqUrl, { userId: userId,
							    reNewPwd: reNewPwd })
				 .then((response) => {
					 alert('비밀번호가 변경되었습니다.')
					 window.location.href = '/mypage';
				 })
				 .catch((error) => {
					 alert('비밀번호가 일치하지 않습니다.')
				 });
		}
	});
});
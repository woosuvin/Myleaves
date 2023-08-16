document.addEventListener('DOMContentLoaded', () => {
	const btnUpdate = document.querySelector('button#btnUpdate');

	btnUpdate.addEventListener('click', (e) => {

		function getSelectedGender() {
			var selectedGender = document.querySelector('input[name="gender"]:checked').value;
			return selectedGender;
		}
		
		const userId = document.querySelector('span#userId').textContent;
		const phone = document.querySelector('input#phone').value;
		const birth = document.querySelector('input#birth').value;
		
		data = { userId: userId,
			     gender: getSelectedGender(),
				 phone: phone,
				 birth: birth }
		
		const reqUrl = '/mypage/profile/update'
		 
		axios.put(reqUrl, data)
			 .then((response) => {
				 alert('회원정보가 수정되었습니다.');
			 })
			 .catch((error) => {
				 alert(error);
			 });
	});
});

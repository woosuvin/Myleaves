document.addEventListener('DOMContentLoaded', () => {
	const btnSubmit = document.querySelector('button#btnSubmit');
	//const userId = document.querySelector('div#userId').value;
	
	const userId = 'admin';
	console.log(userId);
	
	let userPwd;

	document.querySelector('input#currentPwd').addEventListener('blur', function() {
		const currentPwd = this.value;
		const reqUrl = '/member/;

		axios.get(requrl, userId)
			.then((response) => {
				userPwd = response.data;
			})
			.catch((error) => {
				alert(error);
			});

		if (userPwd !== currentPwd) {

		}
	});




	btnSubmit.addEventListener('click', (e) => {
		const newPwd = document.querySelector('input#newPwd').value;
		const reNewPwd = document.querySelector('input#re-newPwd').value;

		console.log(newPwd);
		console.log(reNewPwd);

	});
});
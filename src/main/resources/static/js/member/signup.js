document.addEventListener('DOMContentLoaded', () => {
	const form = document.querySelector('form#signup-form');
	const btnSend = document.querySelector('button#btn-send');

	btnSend.addEventListener('click', (e) => {
		e.preventDefault();
		
		const email = document.querySelector('input[name=email]').value;
		const reqUrl = '/member/confirm';

		axios.get(reqUrl, { params: { email: email } })
			.then((response) => {
				console.log(response);
			})
			.catch((error) => {
				console.log(error);
			});
	});
});
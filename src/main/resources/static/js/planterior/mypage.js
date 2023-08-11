document.addEventListener('DOMContentLoaded', () => {
	
	const userId = document.querySelector('#userId').value;
	
	// 화면 전환
	const goToHome = function() {
		window.location.href = `/mypage/planterior/bookmark?userId=${userId}`;
	}
	
	
	// 북마크 취소
	const imgButtonFills = document.querySelectorAll('.imgButtonFill');
	for (const imgButtonFill of imgButtonFills) {
		imgButtonFill.addEventListener('click', (e) => {
			e.preventDefault();

			const planteriorId = imgButtonFill.value;
			const userId = document.querySelector('#userId').value;

			console.log(planteriorId, userId);
			const data = { planteriorId, userId }
			const reqUrl = `/planterior/home/delete/${planteriorId}/${userId}`;

			axios.delete(reqUrl, data)
				.then((response) => {
					console.log(response);

					if (response.data) {
						goToHome();
					} else {
						console.log('')
					}
				})
				.catch((error) => {
					console.log(error)
				})
		})
	}

});
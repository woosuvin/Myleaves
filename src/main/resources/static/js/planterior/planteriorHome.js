/**
 * 플랜테리어 메인 페이지 js
 */
document.addEventListener('DOMContentLoaded', () => {

	// 로그인 안 한 글쓰기 버튼 작동
	const notCreate = document.querySelector('.notCreate')
	if (notCreate !== null) {
		notCreate.addEventListener('click', () => {
			alert('로그인 후 작성가능합니다.')
		})

	}

	// 로그인 안 한 유저의 북마크 클릭시
	const btnNones = document.querySelectorAll('.none')
	for (const btnNone of btnNones) {
		if (btnNone !== null) {
			btnNone.addEventListener('click', () => {
				alert('로그인 후 북마크 가능합니다.')
			})
		}
	}


	/**
	 *  planterior main
	 *  북마크
	 */

	// 화면 전환
	const goToHome = function() {
		window.location.href = `/planterior`;
	}

	// 북마트 insert
	const imgButtons = document.querySelectorAll('.imgButton');
	for (const imgButton of imgButtons) {
		imgButton.addEventListener('click', (e) => {

			console.log('되니?')

			const planteriorId = imgButton.value;
			const userId = document.querySelector('#userId').value;
			console.log(planteriorId, userId)

			const data = { planteriorId, userId }

			axios.post('/planterior/home/like', data)
				.then((response) => {

					if (response.data) {
						goToHome()

					} else {
						console.log('없다')
					}
				})
				.catch((error) => {
					console.log(error)
				})

		})

		// 북마크 취소
		const imgButtonFills = document.querySelectorAll('.imgButtonFill');
		for (const imgButtonFill of imgButtonFills) {
			imgButtonFill.addEventListener('click', (e) => {
				e.preventDefault();

				console.log("dlsms = ", e.target);

				const planteriorId = document.querySelector('#planteriorId').value;
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


	}


})








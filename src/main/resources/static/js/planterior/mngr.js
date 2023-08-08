/**
 * 관리자 북마크 삭제
 */
document.addEventListener('DOMContentLoaded', () => {
	
	// 북마크 취소
		const imgButtonFills = document.querySelectorAll('.imgButtonFill');
		for (const imgButtonFill of imgButtonFills) {
			imgButtonFill.addEventListener('click', (e) => {
				e.preventDefault();

				const planteriorId = imgButtonFill.value;
				const userId = "admin"

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



})
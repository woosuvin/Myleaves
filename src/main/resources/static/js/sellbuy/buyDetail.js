/** 
 * 정지언
 * buy_detail.js
 * 입양 상세페이지
 */

// 관심상품에 추가
document.addEventListener('DOMContentLoaded', () => {

	const addWishBtn = document.querySelector('#addWishBtn');
	const deleteWishBtn = document.querySelector('#deleteWishBtn');
	const userId = document.querySelector('input#userId').value; //로그인한 사용자 아이디
	const sellId = document.querySelector('input#sellId').value;

	const addWish = () => {
		console.log(userId, sellId);
			const data = {userId, sellId};
			const reqUrl = '/api/buyWish';
			axios.post(reqUrl, data)
			.then((response) => {
				console.log(response);
				alert('관심상품에 추가되었습니다.');
				location.reload(); // 화면 새로고침
			})
			.catch((error) => {
				console.log(error);
			});
	};
	if(addWishBtn != null) {
		addWishBtn.addEventListener('click', addWish);
	}
	
	// 관심상품 삭제
	const deleteWish = (e) => {
		const data = {sellId, userId};
		const reqUrl = `/api/buyWish/${userId}/${sellId}`;
		axios
		.delete(reqUrl, data)
		.then((response) => {
			console.log(response);
			location.reload();
		})
		.catch((error) => {
			console.log(error);
		});
	
	};
	if(deleteWishBtn != null) {
		deleteWishBtn.addEventListener('click', deleteWish);
	}
	
});




 
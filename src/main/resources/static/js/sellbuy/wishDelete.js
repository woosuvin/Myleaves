/**
 * 
 */

document.addEventListener('DOMContentLoaded', () => {

	const userId = document.querySelector('input#userId').value; //로그인한 사용자 아이디
	const sellId = document.querySelector('input#sellId').value;
	const deleteWishBtn = document.querySelector('#deleteWishBtn');

	// 관심상품 삭제
	const deleteWish = () => {
		console.log(userId, sellId);
		const data = { sellId, userId };
		const reqUrl = `/api/wish/delete`;
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

	deleteWishBtn.addEventListener('click', deleteWish);
});

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

	/**
	 * 검색 + home controller에 보내야 함. 그래야 찾지  => create.js에도 추가 필. => postmapping 참고하기
	 * 
	 */

	// 검색 초기화 구현


	// 검색 요소들
	const inputStateContent = document.querySelector('input#stateContent');
	const inputConditionContent = document.querySelector('input#conditionContent');
	const filterForm = document.querySelector('.filterForm')
	const filterBtns = document.querySelectorAll('.filterBtn')
	for (const filterBtn of filterBtns) {
		filterBtn.addEventListener('click', (e) => {

			e.preventDefault();

			console.log('ghkrdls');
			e.preventDefault();

			// li 삽입할 ul 요소를 찾음
			const secondFilter = document.querySelector('#secondFilter');
			secondFilter.classList.remove('d-none');

			console.log(e.target.value);

			inputStateContent.value = e.target.value;
			inputConditionContent.value = '';

			const stateContent = document.querySelector('input#stateContent').value;
			const conditionContent = document.querySelector('input#conditionContent').value;

			//console.log(stateContent)
			//filterForm.action = '/planterior/search'
			//filterForm.method = 'get'
			//filterForm.submit();

		})
	}

	const filterSecondBtns = document.querySelectorAll('input.filterSecondBtn');
	for (let btn of filterSecondBtns) {
		btn.addEventListener('click', (e) => {
			console.log(e.target.value);
			inputConditionContent.value += e.target.value + ',';
		})
	}


	const click = document.querySelector('#click');
	click.addEventListener('click', () => {
		
		const stateContent = document.querySelector('input#stateContent').value;
		const conditionContent = document.querySelector('input#conditionContent').value;
	
		filterForm.action = '/planterior/search'
		filterForm.method = 'get'
		filterForm.submit();
		
	});



	/**
	 *  planterior main
	 *  북마크
	 */

	// 로그인 안 한 유저의 북마크 클릭시
	const btnNones = document.querySelectorAll('.none')
	for (const btnNone of btnNones) {
		if (btnNone !== null) {
			btnNone.addEventListener('click', () => {
				alert('로그인 후 북마크 가능합니다.')
			})
		}
	}

	// 작성자가 북마크 클릭시
	const notBookmarks = document.querySelectorAll('.notBookmark')
	for (const notBookmark of notBookmarks) {
		if (notBookmark !== null) {
			notBookmark.addEventListener('click', () => {
				alert('작성자는 북마크할 수 없습니다.')
			})
		}
	}


	// 화면 전환
	const goToHome = function() {
		window.location.href = `/planterior`;
	}

	// 북마트 insert
	const imgButtons = document.querySelectorAll('.imgButton');
	for (const imgButton of imgButtons) {
		imgButton.addEventListener('click', (e) => {

			const planteriorId = imgButton.value;
			const userId = document.querySelector('#userId').value;

			//alert(`${planteriorId}`)			

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

	/**
	 * paging
	 
	
	
	// "더보기" 버튼 클릭 시 함수 호출
	const loadMoreButton = document.querySelector('#loadMoreButton')
	loadMoreButton.addEventListener('click', () => {
		console.log('확')
		loadMore();
	})

	let pageNumber = 0; // 초기 페이지 번호
	const pageSize = 20; // 페이지 크기

	function loadMore() {
		pageNumber++; // 다음 페이지로 이동

		axios.get("/api/planteriors", {
			params: {
				page: pageNumber,
				size: pageSize
			}
		})
			.then(function(response) {
				const data = response.data;
				console.log(data) //data.number
				const container = document.querySelector(".card-container");
				const authName = document.querySelector('.userId').value;

				if (data.number > 0) {
					data.content.forEach(function(item) {
						console.log("ghkr")
						let col = document.createElement("div");
						col.className = "col";

						let card = document.createElement("div");
						card.className = "card";

						let img = document.createElement("img");
						img.src = "@{/images/planterior/eucalyptus.png}";

						let cardBody = document.createElement("div");
						cardBody.className = "card-body";

						let plantName = document.createElement("p");
						plantName.className = "planteriorButtonEle";
						plantName.innerText = item.plantName + '(' + item.plantNameEnglish + ')';

						let buttonDiv = document.createElement("div");

						// 로그인 했을 경우
						if (authName === item.userId) {
							let hiddenUserId = document.createElement("input");
							hiddenUserId.type = "hidden";
							hiddenUserId.id = "userId";
							hiddenUserId.name = "userId";
							hiddenUserId.value = item.userId;

							let hiddenPlantName = document.createElement("input");
							hiddenPlantName.type = "hidden";
							hiddenPlantName.id = "plantName";
							hiddenPlantName.value = item.plantName;

							let hiddenPlanteriorId = document.createElement("input");
							hiddenPlanteriorId.type = "hidden";
							hiddenPlanteriorId.id = "planteriorId";
							hiddenPlanteriorId.value = item.planteriorId;

							buttonDiv.appendChild(hiddenUserId);
							buttonDiv.appendChild(hiddenPlantName);
							buttonDiv.appendChild(hiddenPlanteriorId);

							// 이미 북마크한 경우
							if (bookmark[item.planteriorId] != null) {
								let buttonFill = document.createElement("button");
								buttonFill.className = "imgButtonFill";
								buttonFill.setAttribute("th:value", item.planteriorId);
								let imgFillIcon = document.createElement("img");
								imgFillIcon.src = "@{/images/planterior/bookmarkFill.svg}";
								buttonFill.appendChild(imgFillIcon);
								buttonDiv.appendChild(buttonFill);
							}
							// 북마크하지 않은 경우
							else {
								let button = document.createElement("button");
								button.className = "imgButton";
								button.setAttribute("th:value", item.planteriorId);
								let imgIcon = document.createElement("img");
								imgIcon.src = "@{/images/planterior/bookmark.svg}";
								button.appendChild(imgIcon);
								buttonDiv.appendChild(button);
							}
						}
						// 로그인하지 않았을 경우
						else {
							let button = document.createElement("button");
							button.className = "notBookmark";
							let imgIcon = document.createElement("img");
							imgIcon.src = "@{/images/planterior/bookmark.svg}";
							button.appendChild(imgIcon);
							buttonDiv.appendChild(button);


						}

						cardBody.appendChild(plantName);
						cardBody.appendChild(buttonDiv);
						card.appendChild(img);
						card.appendChild(cardBody);
						col.appendChild(card);
						container.appendChild(col);
					});
				} else {
					// 더 이상 데이터가 없을 경우 버튼 숨기기 등의 처리
					const loadMoreButton = document.querySelector('#loadMoreButton')
					loadMoreButton.style.display = "none";
				}
			})
			.catch(function(error) {
				console.error("에러 발생:", error);
			});
	}
*/



})








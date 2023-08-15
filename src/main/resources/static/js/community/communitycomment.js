/**
 *  댓글 영역 보이기/숨기기 토글
 *  댓글 검색, 등록, 수정, 삭제
 */

document.addEventListener('DOMContentLoaded', () => {
	// 로그인한 사용자 이름 -> 댓글 등록, 수정, 삭제할 때 사용하기 위해서.
    const authName = document.querySelector('div#authName').innerText;
	
	const bsCollapse = new bootstrap.Collapse('div#communityCommentToggleDiv', { toggle: false });

	 // 버튼 아이콘 이미지
    const toggleBtnIcon = document.querySelector('img#toggleBtnIcon'); 

    
	const btnToggleCommunityComment = document.querySelector('#btnToggleCommunityComment');
	btnToggleCommunityComment.addEventListener('click', () => {
		bsCollapse.toggle();

	
         if (toggleBtnIcon.alt === 'toggle-off') {  // toggle === 'toggle-off'
             toggleBtnIcon.src = '/images/community/toggle2-on.svg';
             toggleBtnIcon.alt = 'toggle-on';

             
             // 댓글 전체 목록을 서버에 요청하고, 응답이 오면 화면 갱신(새로 그림).
				getCommentsWithCommunityId();
             
         } else {
                toggleBtnIcon.src = '/images/community/toggle2-off.svg';
                toggleBtnIcon.alt = 'toggle-off';
        }
     });
     
	
    // 댓글 삭제 버튼 클릭을 처리하는 이벤트 리스너 콜백
	const deleteCommunityComment = (e) => {
		const result = confirm('정말로 삭제할까요?');
		if (!result) {
			return;
		}

		// 삭제할 댓글 아이디
		const communityCommentId = e.target.getAttribute('data-communityCommentId');

		const reqUrl = `/api/communitycomment/${communityCommentId}`;

		axios
			.delete(reqUrl)
			.then((response) => {
				console.log(response);
				getCommentsWithCommunityId(); // 댓글 새로고침 
			})
			.catch((error) => console.log(error));
	};
	

 	 // 댓글 업데이트 버튼들의 클릭을 처리하는 이벤트 리스너 콜백:
     const updateCommunityComment = (e) => {

        // 수정할 댓글 아이디
        const communityCommentId = e.target.getAttribute('data-communityCommentId');
        const before = $('div#text' + communityCommentId).text().trim();

		$('div#text' + communityCommentId).html('<textarea name="updateText" class="form-control mt-2">' + before + '</textarea>');
		$('div#buttonDiv' + communityCommentId).html(`
			<div class="d-grid gap-2 d-md-flex justify-content-md-end">
				<button id="btnUpdateDiv" type="button" class="btn btn-outline-dark btn-sm" data-id="${communityCommentId}">
					등록
				</button>
			</div>
		`);
		
		$('#btnUpdateDiv').click(function(e) {

        // 댓글 입력 아이디
        // const textAreaId = `#content_${communityCommentId}`;
        // console.log(`${textAreaId}`);
       
        //  수정할 댓글 내용
        // const content = document.querySelector(textAreaId).value;
		const reqUrl = `/api/communitycomment/${communityCommentId}`;
		const content = $('textarea[name="updateText"]').val().trim();
        const data = {content};  // 수정한 댓글 내용

        if(content ===''){
            alert('수정할 댓글 내용을 입력하세요.')
            return;
        }
	  
        axios
            .put(reqUrl, data)
            .then((response) => {   
                console.log(response);
				getCommentsWithCommunityId(); // 댓글 새로고침 
            })
            .catch((error) => console.log(error)); 
       })
    };
    


	const makeCommentElements = (data) => {
		document.querySelector('span#communityCommentCount').innerText = data.length;
		const comments = document.querySelector('div#comments');
		comments.innerHTML = '';

		let htmlStr = '';
		for (let comment of data) {
			if (authName === comment.userId) {

			htmlStr += `
			<div class="card p-3 mb-2">
		        <div class="row">
		          <div class="col-10">
		            <span class="d-none">${comment.communityCommentId}</span>
		            <span class="fw-bold">${comment.userId}</span>
		          </div>
			      <div class="col-2 d-grid gap-2 d-md-flex justify-content-md-end" id="buttonDiv${comment.communityCommentId}">
				        <button class="btnDelete btn btn-secondary btn-sm" data-communityCommentId="${comment.communityCommentId}">삭제</button>
				        <button class="btnModify btn btn-outline-dark btn-sm" data-communityCommentId="${comment.communityCommentId}">수정</button>
			     </div>  
                 <div id="modifyContent${comment.communityCommentId}">
					    <div id="text${comment.communityCommentId}">
					        <pre class="pre-bootstrap" id="content_${comment.communityCommentId}">${comment.content}</pre>
					   </div>
				 </div>  
			  </div>
 	     `;	
		}else {
            htmlStr += `
            	<div class="card p-3 mb-2">
			          <div>
			          	  <span class="d-none">${comment.communityCommentId}</span>
			          	  <span class="fw-bold">${comment.userId}</span>
			          </div>
					  <div>
				       	 <pre class="pre-bootstrap" id="content_${comment.communityCommentId}">${comment.content}</pre>
					  </div>
				</div>
            `;
        }
        htmlStr += '</div>';
	}
	
		comments.innerHTML = htmlStr;
		
		const btnDeletes = document.querySelectorAll('button.btnDelete');
		for (let btn of btnDeletes) {
			btn.addEventListener('click', deleteCommunityComment);
		}

		const btnModifies = document.querySelectorAll('button.btnModify');
		for (let btn of btnModifies) {
			btn.addEventListener('click', updateCommunityComment);
		}
	};

	const getCommentsWithCommunityId = async () => {
		const id = document.querySelector('input#communityId').value;
		const reqUrl = `/api/communitycomment/all/${id}`;

		try {
			const response = await axios.get(reqUrl);
			makeCommentElements(response.data);
		} catch (error) {
			console.log(error);
		}
	};
	
	// 댓글 등록 버튼 클릭 시 이벤트 리스너 등록
	const btnCommunityCommentCreate = document.querySelector('button#btnCommunityCommentCreate');
	btnCommunityCommentCreate.addEventListener('click', () => {
		// 몇번 게시물의 댓글?
		const communityId = document.querySelector('input#communityId').value;
		const content = document.querySelector('textarea#content').value;
		const userId = authName;
		
		console.log(`${communityId}`);
		console.log(`${content}`);
		console.log(`${userId}`);

		if (content === '') {
			alert('댓글 내용을 입력하세요.');
			return;
		}

		const data = { communityId, content, userId };
		const reqUrl = '/api/communitycomment';

		axios
			.post(reqUrl, data)
			.then((response) => {
				alert('댓글 등록완료');
				console.log(response);
				getCommentsWithCommunityId();
				document.querySelector('textarea#content').value = '';
			})
			.catch((error) => console.log(error));
	});
	

	

});	
	











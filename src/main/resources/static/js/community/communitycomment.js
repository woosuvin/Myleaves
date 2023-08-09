/**
 *  댓글 영역 보이기/숨기기 토글
 *  댓글 검색, 등록, 수정, 삭제
 */

document.addEventListener('DOMContentLoaded', () => {
	// 로그인한 사용자 이름 -> 댓글 등록, 수정, 삭제할 때 사용하기 위해서.
    const authName = document.querySelector('div#authName').innerText;
	
	const bsCollapse = new bootstrap.Collapse('div#communityCommentToggleDiv', { toggle: false });

	const btnToggleCommunityComment = document.querySelector('#btnToggleCommunityComment');
	btnToggleCommunityComment.addEventListener('click', (e) => {
		bsCollapse.toggle();

		if (e.target.innerText === '보이기') {
			e.target.innerText = '숨기기';
			getCommentsWithCommunityId();
		} else {
			e.target.innerText = '보이기';
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
        // 댓글 입력 textarea 아이디
        const textAreaId = `textarea#content_${communityCommentId}`;
        
        //  수정할 댓글 내용
        const content = document.querySelector(textAreaId).value;
        if(content ===''){
            alert('수정할 댓글 내용을 입력하세요.')
            return;
        }
		const reqUrl = `/api/communitycomment/${communityCommentId}`;
        const data = {content};  // 수정한 댓글 내용

	  
        axios
            .put(reqUrl, data)
            .then((response) => {   
                console.log(response);
				getCommentsWithCommunityId(); // 댓글 새로고침 
            })
            .catch((error) => console.log(error)); 
    };
    


	const makeCommentElements = (data) => {
		document.querySelector('span#communityCommentCount').innerText = data.length;
		const comments = document.querySelector('div#comments');
		comments.innerHTML = '';

		let htmlStr = '';
		for (let comment of data) {
			htmlStr += `
		        <div class="card my-2">
		          <div>
		            <span class="d-none">${comment.communityCommentId}</span>
		            <span class="fw-bold">${comment.userId}</span>
		          </div>
		          `;
          
         // 로그인한 사용자와 댓글 작성자가 같을 때만 삭제, 수정 버튼을 보여줌.
         if (authName === comment.userId) {
			 htmlStr += `
            <textarea id="content_${comment.communityCommentId}">${comment.content}</textarea>
	        <div>
	            <button class="btnDelete btn btn-outline-danger" data-communityCommentId="${comment.communityCommentId}">삭제</button>
	            <button class="btnModify btn btn-outline-success" data-communityCommentId="${comment.communityCommentId}">수정</button>
	        </div>
 	     `;	
		}else {
            htmlStr += `
            <textarea id="content_${comment.communityCommentId}" readonly>${comment.content}</textarea>
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
		console.log(communityId);
		const content = document.querySelector('textarea#content').value;
		const userId = authName;

		if (content === '') {
			alert('댓글 내용을 입력하세요.');
			return;
		}

		const data = { communityId, content, userId };
		const reqUrl = '/api/communitycomment';

		axios
			.post(reqUrl, data)
			.then((response) => {
				console.log(response);
				getCommentsWithCommunityId();
				document.querySelector('textarea#content').value = '';
			})
			.catch((error) => console.log(error));
	});
});	
	











/**
 * 
 */
let afBundleArr = [];
let afAldArr = [];
let afAldNameArr = [];

document.addEventListener('DOMContentLoaded', () => {

	// 공지사항 저장
	let save = document.querySelector('button#SaveBtn');
	if(save != null) {
		save.addEventListener('click', saveNotice);
	}	
	
	// 공지사항 업데이트
	let update = document.querySelector('a#updateBtn');
	if(update != null) {
		update.addEventListener('click', updateNotice);
	}
	
	// 공지사항 삭제
	let deleted = document.querySelector('a#deleteBtn');
	if(deleted != null) {
		deleted.addEventListener('click', deleteNotice);
	}	
	
	// 첨부파일
	const afAddBtn = document.querySelector('input#af-add');
	const fileInput = document.querySelector('input#fileInput');
	afAddBtn.addEventListener('click', (e) => {
		e.preventDefault();
		
		fileInput.click();
	});
	fileInput.addEventListener('change', (e) => fileAdd(e));
	
	// 저장된 첨부파일이 있는지 확인
	const attachFiles = document.querySelector('#attachFiles');
	if(attachFiles.childNodes != null) {
		// 삭제 버튼 이벤트 등록
		deleteBtnAct();
		
		// 저장된 첨부파일 배열 만들기
		const btnFileDeletes = document.querySelectorAll('button.btnFileDelete');
		if(btnFileDeletes != null) {
			for(let btn of btnFileDeletes) {
				let idx = btn.getAttribute('data-id');
				let name = btn.getAttribute('data-value');
				afAldArr.push(idx);
				afAldNameArr.push(name);
			}
		}
		console.log(afAldArr);
		console.log(afAldNameArr);
	}
	
});

// 빈칸 검사
function is_Empty() {
	const title = document.querySelector('input#title');
	if(title.value.length <= 0) {
		alert('제목을 입력해주세요.');
		title.focus();
		return false;	
	}
	
	const content = document.querySelector('textarea#summernote');
	let contentValue = content.value.replaceAll(/<[^>]*>?/g,'').replaceAll(/\&nbsp;/g, '');
	// console.log(contentValue.length);
	if(contentValue.length <= 1) {
		alert('내용을 입력해주세요.')
		content.focus();
		return false;
	}
	
	return true;
	
}

// 공지사항 저장
function saveNotice() {
	const title = document.querySelector('input#title');
	const content = document.querySelector('textarea#summernote');
	// 고정여부
	const fix = document.querySelector('input#fix');
	const is_checked = fix.checked;
	let fixVal = 0;
	
	if(is_Empty()) {
		if(is_checked) {
			fixVal = -1;
		}
		
		data = {
			title : title.value,
			content : content.value,
			fix : fixVal
		}
		
		axios.post('write', data)
		.then((res) => {
			console.log(res.data);
			fileUpload(res.data.nid);
			
			// 공지사항 등록 완료
			alert('공지사항 등록 성공');
			$(location).attr('href', 'list');
		})
		.catch(() => {
			alert('공지사항 등록 오류');
		})
	}
}

// 공지사항 업데이트
function updateNotice() {
	const nid = document.querySelector('input#nid');
	const title = document.querySelector('input#title');
	const content = document.querySelector('textarea#summernote');
	// 고정여부
	const fix = document.querySelector('input#fix');
	const is_checked = fix.checked;
	let fixVal = 0;
	
	if(is_Empty()) {
		if(is_checked) {
			fixVal = -1;
		}
		
		data = {
			nid : nid.value,
			title : title.value,
			content : content.value,
			fix : fixVal
		}
		
		axios.post('modify', data)
		.then(() => {
			fileUpload(nid.value);
			
			alert('공지사항 수정 성공');
			$(location).attr('href', 'list');
		})
		.catch(() => {
			alert('공지사항 수정 오류');
		})
	}
}

// 공지사항 삭제
function deleteNotice() {
	const result = confirm('정말 삭제할까요?');
    if (!result) {
        return;
    }
    
    deleteForm.action = '/mngr/notice/delete';
    deleteForm.method = 'post';
    deleteForm.submit();
}

// 파일 추가
function fileAdd(e) {
	// console.log(e.target.files);
	let fileList = e.target.files;
	
	for(let i = 0; i < fileList.length; i++) {
		afBundleArr.push(fileList[i]);		
	}
	
	refreshFile();
	
}

// 파일 삭제
function deleteFile(e) {
	let idx = e.target.getAttribute('data-id');
	let name = e.target.getAttribute('data-value');
	
	console.log(idx);
	if(!afAldArr.includes(idx)) {
		// DB에 등록된 파일이 아닐 경우
		afBundleArr.splice(idx, 1);
		console.log("새로 추가한 파일: " + afBundleArr);
	} else {
		// DB에 등록된 파일일 경우
		afAldArr.splice(idx, 1);
		afAldNameArr.splice(name, 1);
		console.log("있던 파일: " + afAldArr);
		
		// DB에서 삭제
		deleteFileDb(idx);
	}
	
	refreshFile();
}

// DB에서 파일 삭제
function deleteFileDb(idx) {
	let ufid = idx;
	
	let data = {ufid: ufid};
	
	axios.post('deleteFile', data)
	.then((res) => {
		console.log(res);
	})
	.catch((err) => {
		console.log(err);
	})
	
	refreshFile();
}

// 파일 테이블 새로고침
function refreshFile() {	
	let fileTable = document.querySelector('#attachFiles');
	let html = '';
	
	// 가지고 있던 파일
	for(let i = 0; i < afAldArr.length; i++) {
		html += `
			<tr>
				<td>
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-paperclip" viewBox="0 0 16 16">
					  <path d="M4.5 3a2.5 2.5 0 0 1 5 0v9a1.5 1.5 0 0 1-3 0V5a.5.5 0 0 1 1 0v7a.5.5 0 0 0 1 0V3a1.5 1.5 0 1 0-3 0v9a2.5 2.5 0 0 0 5 0V5a.5.5 0 0 1 1 0v7a3.5 3.5 0 1 1-7 0V3z"/>
					</svg>
					<span>${afAldNameArr[i]}</span>
				</td>
				<td class="text-center">
					<button class="btn btn-danger btnFileDelete"
							data-id="${afAldArr[i]}"
							data-value="${afAldNameArr[i]}">
						삭제
					</button>
				</td>
			</tr>
		`;
	}
	
	// 새로 추가된 파일
	for(let i = 0; i < afBundleArr.length; i++) {		
		html += `
			<tr>
				<td>
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-paperclip" viewBox="0 0 16 16">
					  <path d="M4.5 3a2.5 2.5 0 0 1 5 0v9a1.5 1.5 0 0 1-3 0V5a.5.5 0 0 1 1 0v7a.5.5 0 0 0 1 0V3a1.5 1.5 0 1 0-3 0v9a2.5 2.5 0 0 0 5 0V5a.5.5 0 0 1 1 0v7a3.5 3.5 0 1 1-7 0V3z"/>
					</svg>
					<span>${afBundleArr[i].name}</span>
				</td>
				<td class="text-center">
					<button class="btn btn-danger btnFileDelete" data-id="${i}">
						삭제
					</button>
				</td>
			</tr>			
		`;
	}
	
	fileTable.innerHTML = html;
	deleteBtnAct();
	
}

// 파일 삭제 버튼 이벤트 등록
function deleteBtnAct() {
	// 첨부파일 삭제
	const btnFileDeletes = document.querySelectorAll('button.btnFileDelete');
	if(btnFileDeletes != null) {
		for(let btn of btnFileDeletes) {
			btn.addEventListener('click', deleteFile);
		}
	}
}

// 파일 업로드
function fileUpload(e) {
	let nid = e;
	
	if(afBundleArr.length > 0) {
		let formData = new FormData();		
		formData.append("nid", nid);
		for(let i = 0; i < afBundleArr.length; i++) {
			formData.append("uploadFile", afBundleArr[i]);
		}
		
		// 보내기
		axios.post('upload', formData, {
		  headers: {
		    'Content-Type': 'multipart/form-data'
		  }
		})
		.then((res) => {
			console.log(res.data);
			if(res.data > 0) {
				console.log('파일 업로드 완료');
			}
		})
		.catch((err) => {
			console.log(err);
			alert('파일 등록 오류');
		})
	}
}


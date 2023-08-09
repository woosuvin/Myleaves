/**
 * 게시글 업데이트 $ 삭제
 */

 document.addEventListener('DOMContentLoaded', () => { 
	
	const communityModifyForm = document.querySelector('#communityModifyForm');
	
	btnUpdate = document.querySelector('#btnUpdate');
	btnUpdate.addEventListener('click', (e) => {
	//	const communityId = document.querySelector('').value;
		const title = document.querySelector('#title').value;
		const content = document.querySelector('#content').value;
		
		if(title === '' || content === ''){
			alert('제목과 내용은 반드시 입력해야 합니다.');
			return;
		}
		
		const result = confirm('변경 내용을 업데이트 할까요?');
		if(!result){
			return;
		}
		communityModifyForm.action = '/community/update'
		communityModifyForm.method = 'post'
		communityModifyForm.submit();
	});
	
	
	
	const btnDelete = document.querySelector("#btnDelete");
	btnDelete.addEventListener('click', (e) => {
		
		const result = confirm('정말로 삭제할까요?');
		if (!result){
			return;
		}
		communityModifyForm.action = '/community/delete'
		communityModifyForm.method = 'post'
		communityModifyForm.submit();
	}); 
 });
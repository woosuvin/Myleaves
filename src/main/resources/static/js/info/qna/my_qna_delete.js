/**
 * QnA 마이페이지  삭제 
 */
document.addEventListener('DOMContentLoaded', () => {
	
	const modifyForm = document.querySelector('#qnaModifyForm')
	// 삭제
    const btnDelete = document.querySelector('#btnDelete');
    btnDelete.addEventListener('click', (e) => {
        const result = confirm('삭제?');
        if (!result) {
            return;           
        } 
          
            modifyForm.action = '/mypage/qna/delete' 
            modifyForm.method = 'post';  
			modifyForm.submit(); 
    });
	
});
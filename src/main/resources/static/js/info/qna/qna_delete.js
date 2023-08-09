/**
 * QnA modify 삭제
 */
document.addEventListener('DOMContentLoaded', () => {
	const modifyForm = document.querySelector('#qnaModifyForm')
	
	const btnDelete = document.querySelector('#btnDelete');
    btnDelete.addEventListener('click', (e) => {
        const result = confirm('삭제?');
        if (!result) {
            return;           
        } 
          
            modifyForm.action = '/info/qna/delete' 
            modifyForm.method = 'post';  
			modifyForm.submit(); 
    });
});
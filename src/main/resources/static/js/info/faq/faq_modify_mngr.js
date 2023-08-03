/**
 *  FAQ 관리자 페이지
 * 수정, 삭제 페이지
 */
document.addEventListener('DOMContentLoaded', () => {
    
    const modifyForm = document.querySelector('#faqDetailForm')
    
    
    // 수정 업데이트
    const btnUpdate = document.querySelector('#btnFaqUpdate')
    btnUpdate.addEventListener('click', (e) => {
        const question= document.querySelector('input#question').value;
        const answer = document.querySelector('textarea#answer').value;
        if (question === '' || answer === '') {
            alert('꼭 입력해주세요');
            return; 
        }
        
        const check = confirm('업데이트?');
        if(check) {
            
            modifyForm.action = '/mngr/faq/update'
            modifyForm.method = 'post';   
            modifyForm.submit(); 
        }
    });
    
    // 삭제
    const btnDelete = document.querySelector('#btnFaqDelete');
    btnDelete.addEventListener('click', (e) => {
        const result = confirm('삭제?');
        if (!result) {
            return;           
        } 
          
            modifyForm.action = '/mngr/faq/delete' 
            modifyForm.method = 'post';  
			modifyForm.submit(); 
    });
    
    
    
});
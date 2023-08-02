/**
 * 지현 수정 & 삭제
 */

 document.addEventListener('DOMContentLoaded', () => {
    
    // <form> 요소를 찾음.
    const sellModifyForm = document.querySelector('#sellModifyForm');
    
    const btnUpdate = document.querySelector('#btnUpdate');
    btnUpdate.addEventListener('click', (e) => {
        const title = document.querySelector('#title').value;
        // const thumbnail 
        const content = document.querySelector('#content').value;
        // const price
        // const sido
        // const gungu
        // const loc
        
        if (title === '' || content === '') {
            alert('cant insert blank');
            return;
        }
        
        const result = confirm('UPDATE?')
        if (!result) {
            return;
        }
        
        sellModifyForm.action = '/sell/update';
        sellModifyForm.method = 'post';
        sellModifyForm.submit();
        
    });
    
    const btnDelete = document.querySelector('#btnDelete');
    btnDelete.addEventListener('click', (e) => {
        const result = confirm('DELETE?');
        if (!result) {
            return;
        }
        
        sellModifyForm.action = '/sell/delete'; 
        sellModifyForm.method = 'post'; 
        sellModifyForm.submit(); 
    });
});
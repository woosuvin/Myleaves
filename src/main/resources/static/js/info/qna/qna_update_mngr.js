/**
 * QnA 답변 업데이트
 */
document.addEventListener('DOMContentLoaded', () => {
     const modifyForm = document.querySelector('#modifyQnAMngrForm');
     
     const btnUpdate = document.querySelector('#btnUpdate');
     btnUpdate.addEventListener('click', () =>{
         // 제목과 내용이 입력되어 있는 지 체크
         const qid = document.querySelector('input#qid').value;
         const an_title = document.querySelector('input#an_title').value; //input에 입력된 값
         const an_content = document.querySelector('textarea#an_content').value; //textarea에 입력된 값
         if(an_title === ''||an_content ===''){
             alert('제목과 내용은 반드시 입력하세요.')
             return;
         }
         const check = confirm('변경 내용을 저장할까요?')
        
         if(check){
             modifyForm.action = '/mngr/qna/update'
             modifyForm.method = 'post';
             modifyForm.submit();
         }
     })
 });
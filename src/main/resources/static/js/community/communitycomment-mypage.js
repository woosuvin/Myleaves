/**
 * my_comments.html에서 사용
 */
document.addEventListener('DOMContentLoaded', () => {
   let deleteButton = document.querySelector('.delete-button');
   const allCheckbox = document.querySelector('.all-checkbox');
   let rowCheckboxes = document.querySelectorAll('input[id="row-checkbox"]');
 //  const searchButton = document.querySelector('svg.bi.bi-search');
 //  console.log(searchButton);

   allCheckbox.addEventListener('change', () => {
      let isChecked = allCheckbox.checked;

      rowCheckboxes.forEach((checkbox) => {
         checkbox.checked = isChecked;
      });
   });

	deleteButton.addEventListener('click', () => {
      const selectedCommunityCommentIds = [];
      rowCheckboxes.forEach((checkbox) => {
         if (checkbox.checked) {
            const communityCommentId = checkbox.closest('tr').querySelector('#communityCommentId').innerText;
            console.log(communityCommentId);

            selectedCommunityCommentIds.push({ communityCommentId: communityCommentId });
            console.log(selectedCommunityCommentIds);
			window.location.reload();// 댓글 삭제 후 페이지 새로고침         
         }
      });

      // Ajax, RESTful API DELETE
      axios({
         method: 'delete',
         url: '/api/communitycomment/deletecomments',
         data: selectedCommunityCommentIds,
         headers: { 'Content-Type': 'application/json' }
      })
         .then((response) => {
            if (!response.data) {
               throw new Error('댓글 삭제에 실패 했습니다.');
            }

       //     const urlParams = new URLSearchParams(window.location.search);
       //     let pageNum = urlParams.get('pageNum');

       //     if (pageNum === null) {
        //       pageNum = 1;
        //    }

      //      console.log(pageNum);
            // 삭제 작업 완료 후 처리할 내용
//            updateTable(pageNum);

            deleteButton = document.querySelector('.delete-button');
            rowCheckboxes = document.querySelectorAll('input[id="row-checkbox"]');

            allCheckbox.addEventListener('change', () => {
               isChecked = allCheckbox.checked;

               rowCheckboxes.forEach((checkbox) => {
                  checkbox.checked = isChecked;
               });
            });

            allCheckbox.checked = false;
         })
         .catch((error) => {
            // 에러 발생 시 처리할 내용
            console.log('Error: ' + error);
         });
   });
	
	  
}); 
/**
 * QnA 마이페이지 리스트 부분 삭제
 */

document.addEventListener('DOMContentLoaded', () => {

    const modifyForm = document.querySelector('#qnamyalldeleteForm');
    
    // 체크박스 전체 선택
    const allCheckbox = document.querySelector('#selectAll');
    
    // 체크 박스 삭제
    const btnDelete = document.querySelector('#btnAllDelete');
    
    // 체크박스 전체 선택, 해제
    allCheckbox.addEventListener('change', (e) => {
        const checkboxes = document.querySelectorAll('input[name="selectedQnaIds"]');
        checkboxes.forEach(checkbox => {
            checkbox.checked = e.target.checked;
        });
    });
	
	 // 전체 삭제
    btnDelete.addEventListener('click', (e) => {
        const checkboxes = document.querySelectorAll('input[name="selectedQnaIds"]');
        const checkedCheckboxes = Array.from(checkboxes).filter(checkbox => checkbox.checked);

        if (checkedCheckboxes.length === 0) {
            e.preventDefault(); // 폼 제출 방지
            alert('삭제할 글을 선택해주세요.');
        } else {
            const result = confirm('선택한 글을 삭제하시겠습니까?');
            if (!result) {
                e.preventDefault(); // 폼 제출 방지
            } else {
                const userIdInput = document.querySelector('#hiddenuserId');
                const userId = userIdInput.value;

                modifyForm.method = "post";
                modifyForm.action = `/mypage/qna/alldelete?userId=${encodeURIComponent(userId)}`;
                modifyForm.submit();
            }
        }
    });
	
    // 체크 부분 박스 삭제
    btnDelete.addEventListener('click', (e) => {
        const result = confirm('선택한 글을 삭제하시겠습니까?');
        if (!result) {
            e.preventDefault(); // 폼 제출 방지
            return;
        }

        const userIdInput = document.querySelector('#hiddenuserId');
        const userId = userIdInput.value;
        const selectedQnaIds = Array.from(modifyForm.querySelectorAll('input[name="selectedQnaIds"]:checked'))
            .map(checkbox => checkbox.value);

        if (selectedQnaIds.length > 0) {
            modifyForm.method = "post";
            modifyForm.action = `/mypage/qna/alldelete?userId=${encodeURIComponent(userId)}`;
            modifyForm.submit();
        }
    });

});

document.addEventListener('DOMContentLoaded', () => {
    const deleteButton = document.querySelector('.delete-button');
    const allCheckbox = document.querySelector('.all-checkbox');
    let rowCheckboxes = document.querySelectorAll('input[id="row-checkbox"]');

    // 전체 체크박스 선택/해제 시 동작
    allCheckbox.addEventListener('change', () => {
        const isChecked = allCheckbox.checked;

        rowCheckboxes.forEach((checkbox) => {
            checkbox.checked = isChecked;
        });

        // 삭제 버튼 활성/비활성화
        deleteButton.disabled = !isChecked;
    });

    // 개별 체크박스 상태 변경 시 삭제 버튼 활성/비활성화
    rowCheckboxes.forEach((checkbox) => {
        checkbox.addEventListener('change', () => {
            const anyChecked = Array.from(rowCheckboxes).some(checkbox => checkbox.checked);
            deleteButton.disabled = !anyChecked;
        });
    });

	let planteriorId = '';
    deleteButton.addEventListener('click', () => {
        const data = [];

        rowCheckboxes.forEach((checkbox) => {
            if (checkbox.checked) {
                planteriorId = checkbox.closest('tr').querySelector('#planteriorId').innerText;
                data.push({ planteriorId: planteriorId, userId: "admin" });
            }
        });

        const userId = 'admin';

        // 삭제 요청
        axios({
            method: 'delete',
            url: `/planterior/home/delete`,
            data: data,
            headers: { 'Content-Type': 'application/json' }
        })
        .then((response) => {
            if (!response.data) {
                throw new Error('삭제에 실패');
            }
            window.location.reload();
        })
        .catch((error) => {
            console.log(error);
        });
    });
});

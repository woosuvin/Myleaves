/**
 * FAQ 만들기
 */

//JavaScript를 이용하여 질문을 클릭하면 해당 답변을 보여주는 기능

// 페이지가 로드되면 실행되는 함수
document.addEventListener("DOMContentLoaded", function () {
    // FAQ 아이템들을 가져옵니다.
    const faqItems = document.querySelectorAll(".faq-item");

    // FAQ 아이템들에 클릭 이벤트를 추가합니다.
    faqItems.forEach(item => {
        const question = item.querySelector(".faq-question");
        const answer = item.querySelector(".faq-answer");

        // 질문을 클릭하면 답변이 토글됩니다.
        question.addEventListener("click", () => {
            if (answer.style.display === "none") {
                answer.style.display = "block";
            } else {
                answer.style.display = "none";
            }
        });

        // 로드될 때 각 FAQ 아이템의 초기 상태를 설정합니다.
        // 저장된 상태가 있는지 확인하고, 없으면 답변을 숨깁니다.
        if (!localStorage.getItem(`faq_${item.querySelector(".faq-qid").textContent}`)) {
            answer.style.display = "none";
        }
    });
});

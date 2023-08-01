/**
 * FAQ 만들기
 */

//JavaScript를 이용하여 질문을 클릭하면 해당 답변을 보여주는 기능
const faqItems = document.querySelectorAll('.faq-item');

faqItems.forEach(item => {
	const question = item.querySelector('.faq-question');
	const answer = item.querySelector('.faq-answer');

	// 저장된 상태가 있는지 확인하고, 없으면 숨김
	if (!localStorage.getItem(question.textContent)) {
		answer.style.display = 'none';
	}

	question.addEventListener('click', () => {
		if (answer.style.display === 'none') {
			answer.style.display = 'block';
			localStorage.setItem(question.textContent, 'visible');
		} else {
			answer.style.display = 'none';
			localStorage.removeItem(question.textContent);
		}
	});
});
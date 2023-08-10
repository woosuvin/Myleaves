document.addEventListener('DOMContentLoaded', () => {
	const form = document.querySelector('form#actionForm');
	const pageLinks = document.querySelectorAll('a.page-link');

	pageLinks.forEach(pageLink => {
		pageLink.addEventListener('click', (e) => {
			e.preventDefault();

			const hrefValue = pageLink.getAttribute('href');
			const pageNumInput = form.querySelector('input[name="pageNum"]');
			pageNumInput.value = hrefValue;
			
			console.log(pageNumInput.value);
			form.submit();
		});
	});
});
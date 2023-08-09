/**
 * 
 */

document.addEventListener('DOMContentLoaded', () => {
	const pageList = document.querySelectorAll('a.page-link');
    for(let page of pageList) {
		page.addEventListener('click', formSend);
	}
    
});

function formSend(e) {
	e.preventDefault();
	
    const actionForm = document.querySelector('#actionForm');
    
    console.log(e.target);
	const targetPage = e.target.getAttribute('value');
	console.log(targetPage);
	
	document.getElementsByName("offset")[0].value = targetPage;
	actionForm.submit();
}
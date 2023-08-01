/**
 * 
 */

document.addEventListener('DOMContentLoaded', () => {
	
    const actionForm = document.querySelector('#actionForm');
    
    const page = document.querySelectorAll('a.page-link');
    page.addEventListener('click', (e) => {
		e.preventDefault();
		
		const targetPage = this.value;
		console.log(targetPage);
		
		actionForm.find("input[name='pageNum']").val(targetPage);
		actionForm.submit();
	})
    
});
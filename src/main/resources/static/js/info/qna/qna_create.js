/**
 * 
 */
document.addEventListener('DOMContentLoaded', () => {

	const checkbox = document.querySelector('#checkbox');
		const check = checkbox.checked;

		if (check) {
			document.getElementById('secret').innerText = 1;
		} else {
			document.getElementById('secret').innerText = 0;
		}

	document.getElementById('checkbox').onclick = function() {

		const checkbox = document.querySelector('#checkbox');
		const check = checkbox.checked;

		if (check) {
			document.getElementById('secret').innerText = 1;
		} else {
			document.getElementById('secret').innerText = 0;
		}

	}

});
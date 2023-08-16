document.addEventListener('DOMContentLoaded', () => {
	const form = document.querySelector('form#actionForm');
	const pageLinks = document.querySelectorAll('a.page-link');

	const jData1 = JSON.parse(json1);
	const jData2 = JSON.parse(json2);
	// console.log(jData2);

	var labelList1 = new Array();
	var valueList1 = new Array();
	var colorList1 = new Array();
	
	var labelList2 = new Array();
	var valueList2 = new Array();
	var colorList2 = new Array();

	for (var i = 0; i < jData1.length; i++) {
		var d = jData1[i];
		labelList1.push(d.gender);
		valueList1.push(d.cnt);
		colorList1.push(colorize());
	}
	
	for (var i = 0; i < jData2.length; i++) {
		var d = jData2[i];
		labelList2.push(d.month);
		valueList2.push(d.cnt);
		colorList2.push(colorize());
	}

	var data1 = {
		labels: labelList1,
		datasets: [{
			backgroundColor: colorList1,
			data: valueList1
		}]
	};
	
	var data2 = {
		labels: labelList2,
		datasets: [{
			backgroundColor: colorList2,
			data: valueList2
		}]
	};

	var options1 = {
	    responsive: false, // 반응형 비활성화
	    maintainAspectRatio: false, // 가로:세로 비율 유지 비활성화
	    title: {
	        display: true,
	        text: '성별 가입자 수'
	    }
	};
	
	var options2 = {
	    responsive: false, // 반응형 비활성화
	    maintainAspectRatio: false, // 가로:세로 비율 유지 비활성화
	    title: {
	        display: true,
	        text: '월별 가입자 수'
	    },
	    legend: {
        display: false // 범례 표시하지 않음
    	}
	};
	
	var ctx1 = document.getElementById('genderChart1').getContext('2d');
	var ctx2 = document.getElementById('genderChart2').getContext('2d');
	
	new Chart(ctx1, {
	    type: 'pie',
	    data: data1,
	    options: options1 // 위에서 설정한 옵션 적용
	});
	
	new Chart(ctx2, {
	    type: 'bar',
	    data: data2,
	    options: options2 // 위에서 설정한 옵션 적용
	});

	function colorize() {
		var r = Math.floor(Math.random() * 200);
		var g = Math.floor(Math.random() * 200);
		var b = Math.floor(Math.random() * 200);
		var color = 'rgba(' + r + ', ' + g + ', ' + b + ', 0.7)';
		return color;
	}

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
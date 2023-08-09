/**
 * 
 */

document.addEventListener('DOMContentLoaded', () => {
	
	// 첨부파일 다운로드
	const downloadFiles = document.querySelectorAll('a.downloadFile');
	if(downloadFiles != null) {
		for(let aLink of downloadFiles) {
			aLink.addEventListener('click', downloadFile);
		}
	}
});


// 파일 다운로드
function downloadFile(e) {
	e.preventDefault();

	let ufid = this.getAttribute('data-value');
	console.log(ufid);
	let fileName = this.firstElementChild.innerText;
	console.log(fileName);

	let data = { ufid: ufid };
	axios({
		url: 'download',
		data: data,
		method: 'POST',
		responseType: 'blob'
	})
	.then((response) => {
		console.log(response.data);
		const url = window.URL
			.createObjectURL(new Blob([response.data]));
		console.log(url);
		if (fileName) {
			let a = document.createElement("a");
			
			if (a.download === undefined) {
				window.location.href = ufid;
			} else {
				a.href = url;
				a.download = fileName;
			    document.body.appendChild(a)
			    a.click();
			}
		} else {
			window.location.href = downloadUrl;
		}
		
	})
	.catch((err)=> console.log(err))
	
	/*
	let ufid = this.getAttribute('data-value');
	console.log(ufid);
	let fileName = this.firstElementChild.innerText;
	console.log(fileName);
	
	data = {ufid : ufid};
	axios.post('download', data)
	.then((res) => {
		console.log(res);
		// let blob = new Blob([byte], {type: 'plain/text'});
		const byteCharacters = atob(res.data.atchdFile);
		const byteNumbers = new Array(byteCharacters.length);
		for (let i = 0; i < byteCharacters.length; i++) {
			byteNumbers[i] = byteCharacters.charCodeAt(i);
		}
		const byteArray = new Uint8Array(byteNumbers);
		const blob = new Blob([byteArray]);
		// const blobUrl = window.URL.createObjectURL(blob);
		
		if (window.navigator && window.navigator.msSaveOrOpenBlob) {
			window.navigator.msSaveOrOpenBlob(blob, fileName);
		} else {
			var URL = window.URL || window.webkitURL;
			var downloadUrl = URL.createObjectURL(blob);
			
			if (fileName) {
				var a = document.createElement("a");
				
				if (a.download === undefined) {
					window.location.href = downloadUrl;
				} else {
					a.href = downloadUrl;
					a.download = fileName;
				    document.body.appendChild(a)
				    a.click();
				}
			} else {
				window.location.href = downloadUrl;
			}
		}

		// ---------------------------------------------
		// let blob = new Blob([res.data.atchdFile], {type: 'plain/text'});
		let fileName = res.data.fileName;
		console.log(typeof(blob));
		
		const url = URL.createObjectURL(blob);
		if (fileName) {
			let a = document.createElement("a");
			
			if (a.download === undefined) {
				window.location.href = url;
			} else {
				a.href = url;
				a.download = fileName;
			    document.body.appendChild(a)
			    a.click();
			}
		} else {
			window.location.href = downloadUrl;
		}
		
		
	})
	.catch((err) => {
		console.log(err);
	})
	*/
	
}
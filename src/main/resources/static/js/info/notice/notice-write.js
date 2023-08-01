/**
 * 
 */
let afBundleArr = [];

document.addEventListener('DOMContentLoaded', () => {
		
	// 공지사항 저장
	let save = document.querySelector('button#SaveBtn')
	save.addEventListener('click', () => {
		const title = document.querySelector('input#title');
		const content = document.querySelector('textarea#summernote');
		// 고정여부
		const fix = document.querySelector('input#fix');
		const is_checked = fix.checked;
		let fixVal = 0;
		
		if(is_Empty()) {
			if(is_checked) {
				fixVal = -1;
			}
			
			data = {
				title : title.value,
				content : content.value,
				fix : fixVal
			}
			
			axios.post('write', data)
			.then(() => {
				alert('공지사항 등록 성공');
				$(location).attr('href', 'list');
			})
			.catch(() => {
				alert('공지사항 등록 오류');
			})
		}
	});
	
	// 첨부파일
	let afAddBtn = document.querySelector('input#af-add');
	let fileInput = document.querySelector('input#fileInput');
	
	afAddBtn.addEventListener('click', (e) => {
		e.preventDefault();
		
		fileInput.click();
	});
	
	fileInput.addEventListener('click', (e) => {
		if(fileInput.val() != '') {
			let data = new FormData($('#attachFileForm')[0]);
		}
	});
});

function is_Empty() {
	const title = document.querySelector('input#title');
	if(title.value.length <= 0) {
		alert('제목을 입력해주세요.');
		title.focus();
		return false;	
	}
	
	const content = document.querySelector('textarea#summernote');
	let contentValue = content.value.replaceAll(/<[^>]*>?/g,'').replaceAll(/\&nbsp;/g, '');
	// console.log(contentValue.length);
	if(contentValue.length <= 1) {
		alert('내용을 입력해주세요.')
		content.focus();
		return false;
	}
	
	return true;
	
}
		/* 
		$(document).ready(function() {
			
			// 첨부파일
			$('.af-btn-add').on('click',function(e){
				e.preventDefault();
				
				$('#fileInput').trigger('click');
			});
			
			$('input[name="fileInput"]').change(function() {
				if($('input[name="fileInput"]').val()!=''){
					var data = new FormData($('#attachFileForm')[0]);
					$.ajax({
						type:"POST",
						enctype: 'multipart/form-data',
						url: "/attach/file",
						data: data,
						processData: false,
						contentType: false,
				        cache: false,
				        timeout: 600000,
						success: function(res){
							$('.loading').hide();
							$('#fileInput').val('');
							let afRow = '';
							afRow += '<tr>';
							afRow += '<td class="w90"><a href="/download/attach/'+res.af.afId+'">'+res.af.fileNm+'</a></td>';
							afRow += '<td><input type="button" value="삭제" onClick="deleteFile(this)" class="btn btn-danger btn-sm"></td>';
							afRow += '</tr>';
							$('#attachFiles').append(afRow);
							afBundleArr.push(res.af);
						},
						error: function(xhr, status, error){
							$('.loading').hide();
							$('#fileInput').val('');
							alert('파일 업로드 오류: ' + xhr.responseJSON.message);
							console.log(xhr);
						}
					})
				}
			});
		} */
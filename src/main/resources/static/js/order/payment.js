/**
 * 
 */

function requestPay() {

	const form = document.querySelector('#orderForm');
	
	// 라디오버튼 체크 value, 1: 카드  3: 카카오페이
	const radio = document.querySelector('input[name="payment"]:checked').value;
	
	const userId = document.querySelector('#userId').innerText;
	const price = document.querySelector('#price').value;
	const name = document.querySelector('.name').value;
	const zipcode = document.querySelector('#sample6_postcode').value;
	const addr = document.querySelector('#sample6_address').value;
	const addrdetail = document.querySelector('#sample6_detailAddress').value;
	const tel = document.querySelector('#tel').value;
	const req = document.querySelector('#req').value;
	const cnt = document.querySelector('#cnt').value;
	const itemName = document.querySelector('#itemName').value;
	// const itemImg


	var IMP = window.IMP;
	IMP.init("imp03583343");
	
	
	IMP.request_pay({
		pg: getPgMethod(),
		pay_method: getPayMethod(),
		merchant_uid: "merchant_" + new Date().getTime(),
		name: itemName,
		amount: price,
		buyer_email: "",
		buyer_name: name,
		buyer_tel: tel,
		buyer_addr: addr,
		buyer_postcode: zipcode
		},
		function(rsp) { // callback
			/** 결제 검증 **/
			console.log(rsp);
			if (rsp.success) {

				form.action = '/order/orderDetail?userId=' + userId;
				form.method = 'post';
				form.submit();
			} else {
				alert("결제에 실패했습니다." + "에러코드 : " + rsp.error_code + " 에러 메시지 : " + rsp.error_message);
			}
	});
	
	function getPgMethod() {
		switch(radio) {
			case '1': // 카드
				return "html5_inicis";
				break;
			
			case '3': // 카페
				return "kakao";
				break;
		}
	}

	function getPayMethod() {
		switch(radio) {
			case '1': // 카드
				return "card";
				break;
			
			case '3': // 카페
				return "kakao";
				break;
		}
	}
	
}


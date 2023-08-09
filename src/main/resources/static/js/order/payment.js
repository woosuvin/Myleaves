/**
 * 
 */

function requestPay() {
	console.log("님 됨?");

	const form = document.querySelector('#orderForm');

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

		pg: "html5_inicis",
		pay_method: "card",
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
}


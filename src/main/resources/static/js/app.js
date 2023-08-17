/**
 * 
 */

let stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    const sellId = document.querySelector('input#sellId').value;
    const myId = document.querySelector('input#myId').value;
    const otherId = document.querySelector('input#otherId').value;
    const data = { sellId, myId, otherId };

    console.log('소켓');
    console.log(data);

    const socket = new SockJS('/gs-guide-websocket');
    console.log(socket);
    stompClient = Stomp.over(socket);
    stompClient.connect({ data }, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

// 페이지 로딩 시 자동으로 연결 시도
$(document).ready(function () {
    connect();
});

// 페이지가 언로드될 때 자동으로 연결 닫기
$(window).on('beforeunload', function () {
    disconnect();
});

/*let stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    const sellId = document.querySelector('input#sellId').value;
	const myId = document.querySelector('input#userId').value;
	const otherId = document.querySelector('input#otherId').value;
    const data = { sellId, myId, otherId };
    
    console.log('소켓');
    console.log(data);
    
    const socket = new SockJS('/gs-guide-websocket');
    console.log(socket);
    stompClient = Stomp.over(socket);
    stompClient.connect({data}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}*/

function sendMessage() {
    stompClient.send("/app/hello", {}, JSON.stringify({'message': $("#message").val()}));
}

function showGreeting(message) {
    const userId = document.querySelector('input#userId').value;
    $("#greetings").append("<tr><td><strong>" + userId + "</strong>: " + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMessage(); });
});
package com.itwill.myleaves.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

//@Configuration
//@RequiredArgsConstructor
//@EnableWebSocketMessageBroker
//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        registry.setApplicationDestinationPrefixes("/send");
//    
//        //해당 주소를 구독하고 있는 클라이언트들에게 메세지 전달
//        registry.enableSimpleBroker("/room");
//    }
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/ws-stomp")   //SockJS 연결 주소
//                .withSockJS(); //버전 낮은 브라우저에서도 적용 가능
//        // 주소 : ws://localhost:8080/ws-stomp
//    }
//    
//}

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		// "/topic"으로 시작하는 destination 헤더를 가진 메시지를 브로커로 라우팅 한다. connect
		config.enableSimpleBroker("/connect");
		// "/app" 경로로 시작하는 STOMP 메시지의 destination 헤더는 Controller 객체의 @MessageMapping 메서드로 라우팅된다. sendMessage
		config.setApplicationDestinationPrefixes("/app");
	}
                                                                                                                                            
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// WebSocket 또는 SockJs Client가 웹소켓 핸드셰이크 커넥션을 생성할 경로
		registry.addEndpoint("/gs-guide-websocket").withSockJS();
	}
	
}
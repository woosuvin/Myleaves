package com.itwill.myleaves.service.member;
import java.util.Random;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.itwill.myleaves.repository.member.EmailService;

import jakarta.mail.Message.RecipientType;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@Service
public class EmailServiceImpl implements EmailService{
 
    @Autowired
    JavaMailSender emailSender;
 
    public static final String AUTH_CODE = createKey();
 
    private MimeMessage createMessage(String to)throws Exception{
        System.out.println("보내는 대상 : " + to);
        System.out.println("인증 번호(ePw) : " + AUTH_CODE);
        
        MimeMessage message = emailSender.createMimeMessage();
 
        message.addRecipients(RecipientType.TO, to);//보내는 대상
        message.setSubject("이메일 인증");//제목
 
        String msgg="";
        msgg+= "<div style='margin:20px;'>";
        msgg+= "<h1> 네풀잎스 인증 메일</h1>";
        msgg+= "<br>";
        msgg+= "<p>아래 코드를 복사해 입력해주세요<p>";
        msgg+= "<br>";
        msgg+= "<p>감사합니다.<p>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg+= "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "CODE : <strong>";
        msgg+= AUTH_CODE + "</strong><div><br/> ";
        msgg+= "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress(to, "myleaves"));//보내는 사람
 
        return message;
    }
 
    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();
 
        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤
 
            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    //  A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return key.toString();
    }
    
    @Override
    public String sendSimpleMessage(String to) throws Exception {
    	log.info("sendSimpleMessage(to={})", to);
    	
        MimeMessage message = createMessage(to);
        
        try{
            emailSender.send(message);
            log.info("sendSimpleMessage(message={})", message);
            
        }catch(MailException es){
        	log.info("sendSimpleMessage(es={})", es);
        	
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return AUTH_CODE;
    }
}
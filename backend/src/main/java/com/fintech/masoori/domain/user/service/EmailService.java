package com.fintech.masoori.domain.user.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendEmail(String to, String code) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        String content = mailContent(code);

        helper.setTo(to);
        helper.setSubject("[마수리] 이메일 인증을 위한 인증코드 발송");
        helper.setText(content, true);

        mailSender.send(message);
    }

    public String mailContent(String code) {

        String content = "";
        content += "<div style='margin:5%;'>";
        content += "<h2> 안녕하세요 마수리입니다. </h2>";
        content += "<br>";
        content += "<p>아래 인증코드를 입력해주세요.<p>";
        content += "<p>감사합니다.<p>";
        content += "<br>";
        content += "<div align='center' style='border:1px solid #813E83; border-radius:10px; font-family:verdana';>";
        content += "<h3 style='color:#813E83;'>이메일 인증 코드</h3>";
        content += "<div style='font-size:110%'>";
        content += "CODE : <strong>";
        content += code+"</strong><div><br/> ";
        content += "</div>";

        return content;
    }

    public String createCode(int codeLength) {
        Random random = new Random();
        StringBuilder key = new StringBuilder();

        for (int i = 0; i < codeLength; i++) {
            int index = random.nextInt(4);

            switch (index) {
                case 0:
                case 1:
                    key.append((char) ((int) random.nextInt(26) + 65)); break;
                default: key.append(random.nextInt(10));
            }
        }
        return key.toString();
    }


}

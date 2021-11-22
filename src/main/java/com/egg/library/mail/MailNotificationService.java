package com.egg.library.mail;

import com.egg.library.domain.CustomerVO;
import com.egg.library.domain.LoanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class MailNotificationService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private Template template;

    @Async
    public void sendMail(String title, LoanVO loan, String mail) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            String htmlMsg = template.getTemplateMail(loan);
            helper.setText(htmlMsg, true);
            helper.setTo(mail);
            helper.setSubject(title);
            helper.setFrom(from);
            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    public void sendWelcomeMail(String title, CustomerVO customerVO, String mail) {
        new Thread(() -> {
            try {
                MimeMessage mimeMessage = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
                String htmlMsg = template.getWelcomeTemplate(customerVO);
                helper.setText(htmlMsg, true);
                helper.setTo(mail);
                helper.setSubject(title);
                helper.setFrom(from);
                mailSender.send(mimeMessage);
            } catch (MessagingException e) {
             e.printStackTrace();
            }
        }).start();
    }
}

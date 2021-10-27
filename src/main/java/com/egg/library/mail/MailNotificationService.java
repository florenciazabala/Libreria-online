package com.egg.library.mail;

import com.egg.library.domain.LoanVO;
import org.springframework.beans.factory.annotation.Autowired;
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
            helper.setFrom("noreply@booking.com");
            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

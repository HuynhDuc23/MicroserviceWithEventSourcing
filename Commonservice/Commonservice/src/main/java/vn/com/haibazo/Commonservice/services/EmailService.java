package vn.com.haibazo.Commonservice.services;

import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Slf4j
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    @Autowired
    private JavaMailSender javaMailSender ;

    /**
     * Send an email with optional HTML content and attachment
     * @param to   The email address
     * @param subject The email subject
     * @param content  Optional HTML content
     * @param isHtml Optional HTML content
     * @param attachment Optional HTML attachment
     */
    public void sendEmail(String to, String subject, String content , boolean isHtml , File attachment) {
        try {
            MimeMessage message = this.javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,isHtml);
            // provider file
            if(attachment!=null){
                FileSystemResource fileSystemResource = new FileSystemResource(attachment);
                helper.addAttachment(fileSystemResource.getFilename(), attachment);
            }
            javaMailSender.send(message);
            log.info("Email sent successfully to {}", to);
        }catch(MessagingException ex){
           log.error(ex.getMessage(), ex);
        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

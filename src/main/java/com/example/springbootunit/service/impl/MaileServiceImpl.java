package com.example.springbootunit.service.impl;

import com.example.springbootunit.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;


@Component
public class MaileServiceImpl implements MailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MailSender mailSender;

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${mail.fromMail.addr}")
    private String from;

    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage simpleMailMessage =new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);

        try {
            mailSender.send(simpleMailMessage);
            logger.info("简单邮件已发送");
        }catch (Exception e){
            logger.error("发送异常",e);
        }
    }

    /**
     * 发送html邮件
     * @param to
     * @param subject
     * @param content
     */
    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper mimeMessageHelper =new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);;
            mimeMessageHelper.setText(content);
            javaMailSender.send(mimeMessage);
            logger.info("html发送成功");
        }catch(MessagingException e){
            logger.error("发送异常",e);
        }

    }

    @Override
    public void sendInlineResourceMail(String to, String subject, String content,String rscPath,String rscId) {
        MimeMessage mimeMessage =javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content);
            FileSystemResource res =new FileSystemResource(new File(rscPath));
            mimeMessageHelper.addInline(rscId,res);
            javaMailSender.send(mimeMessage);
            logger.info(" 嵌入静态资源的邮件已经发送");
        } catch (MessagingException e) {
            logger.error("发送失败",e);
        }
    }


}

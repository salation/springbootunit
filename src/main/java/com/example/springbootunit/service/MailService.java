package com.example.springbootunit.service;

public interface MailService {
    //发送简单邮件
    public void sendSimpleMail (String to,String subject,String content);

    //发送html格式邮件
    public void sendHtmlMail (String to,String subject,String content);

    //发送带有附件的邮件
    public void sendInlineResourceMail(String to,String subject,String content,String rscPath,String rscId);
}

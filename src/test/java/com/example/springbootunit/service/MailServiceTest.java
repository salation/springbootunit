package com.example.springbootunit.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {
    @Autowired
    private MailService mailService;
    @Autowired
    private TemplateEngine templateEngine;
    @Test
    public void testSimpleMail() throws Exception{
        mailService.sendSimpleMail("1927848575@qq.com","nihao","moshengren");
    }
    @Test
    public void testHtmlMail()throws Exception{
        String content = "<html>\n" +
                "<body>\n" +
                "<h3>hellp</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendSimpleMail("1927848575@qq.com","nihao",content);
    }

    @Test
    public void sendInlineResourceMail() {
        String rscId = "neo006";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "E:\\111.jpg";

        mailService.sendInlineResourceMail("1927848575@qq.com", "主题：这是有图片的邮件", content, imgPath, rscId);
    }

    @Test
    public void sendTemplateMail() {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail("1927848575@qq.com","主题：这是模板邮件",emailContent);
    }
}

package com.example.springbootunit.controller;

import com.example.springbootunit.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MailController {
    @Autowired
    MailService mailService;

    //mailService.sendSimpleMail("luyiheng@inspur.com","nihao","dajiahao");
}

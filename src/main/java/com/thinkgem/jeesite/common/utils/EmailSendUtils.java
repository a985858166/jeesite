package com.thinkgem.jeesite.common.utils;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class EmailSendUtils {
//    @Autowired
    private static Email email;
    public static void send(String subject,String msg,String... to) throws EmailException {
        if (email == null){
            email = new SimpleEmail();
            email.setHostName("smtp.163.com");
            email.setSmtpPort(465);
            email.setAuthentication("15059682804@163.com","KSvgIT17vltn");
            email.setSSLOnConnect(true);
            email.setFrom("15059682804@163.com");
        }
        email.setSubject(subject);
        email.setMsg(msg);
        email.addTo(to);
        System.out.println(subject+"--"+msg+"----"+to[0]);
        email.send();
    }
}

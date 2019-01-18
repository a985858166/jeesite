package com.thinkgem.jeesite.modules.cxj.email;

import com.thinkgem.jeesite.common.utils.EmailSendUtils;
import org.apache.commons.mail.EmailException;

public class SendEmailTask implements Runnable {

    private String toemail;
    private String title;
    private String content;

    public SendEmailTask(String toemail, String title, String content){
        this.toemail = toemail;
        this.title = title;
        this.content = content;
    }

    @Override
    public void run() {
        try {
            EmailSendUtils.send(title, content, toemail);//send是自行封装的发邮件的方法
            System.out.println("邮件发送成功");
        } catch (EmailException e) {
            System.out.println("邮件发送失败");
            e.printStackTrace();
        }
    }

}
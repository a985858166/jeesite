package com.thinkgem.jeesite.test;

import com.thinkgem.jeesite.common.utils.EmailSendUtils;
import com.thinkgem.jeesite.common.utils.Threads;
import com.thinkgem.jeesite.modules.cxj.email.SendEmailThread;
import org.apache.commons.mail.EmailException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml", "classpath:spring-context-activiti.xml", "classpath:spring-context-email.xml","classpath:spring-context-jedis.xml","classpath:spring-context-shiro.xml"})
public class Test2 {
    @Autowired
    private SendEmailThread sendEmailThread;
    @Test
    public void test(){
        sendEmailThread.setEmail("a","b","985858166@qq.com");
        sendEmailThread.runTask();
        Threads.sleep(100000);
    }
    @Test
    public void test2() throws EmailException {
        EmailSendUtils.send("a","b","985858166@qq.com");
    }
}

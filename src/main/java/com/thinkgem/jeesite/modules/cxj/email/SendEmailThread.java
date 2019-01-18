package com.thinkgem.jeesite.modules.cxj.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
@Component
public class SendEmailThread {
    private static Logger log = LoggerFactory.getLogger(SendEmailThread.class);
    private String[] toEmails;//需要发送的邮箱
    private String title;//需要发送的标题
    private String content;//需要发送的内容
    @Qualifier("taskExecutor")
    @Autowired
    private ThreadPoolTaskExecutor poolTaskExecutor;

    public SendEmailThread() {

    }
    public void setEmail(String title, String content, String... toEmails){
        this.toEmails = toEmails;
        this.title = title;
        this.content = content;
    }

    /**
     * 使用多线程执行任务
     *
     * @author chenminchang
     * @create 2017年3月22日上午10:50:31
     */
    public void runTask() {
        if (poolTaskExecutor == null) {
            log.debug("the poolTaskExecutor is null");
        } else {
            if (toEmails != null && toEmails.length > 0) {
                if (toEmails.length < poolTaskExecutor.getCorePoolSize())//当任务数小于创建的线程
                    poolTaskExecutor.setCorePoolSize(toEmails.length);
                for (String email : toEmails) {
                    try {
                        poolTaskExecutor.execute(getNextTask(email, this.title , this.content));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 获取新任务
     * @param toemail
     * @param title
     * @param content
     * @return
     * @author chenminchang
     * @create 2017年3月20日下午5:15:07
     */
    private Runnable getNextTask(String toemail, String title, String content) {
        return new SendEmailTask(toemail, title, content);
    }


}

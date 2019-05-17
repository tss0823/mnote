package com.loong.mnote.test.service.component;

import com.loong.mnote.service.component.MailComponent;
import com.loong.mnote.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: sam
 * @date: 2018-12-28 17:09
 */
public class MailComponentTest extends BaseTest {


    @Autowired
    private MailComponent mailComponet;

    @Test
    public void testSendMail(){

        boolean reulst = mailComponet.sendText("583697470@qq.com", "测试", "测试loong内容");
        logger.info("result={}",reulst);

    }
}

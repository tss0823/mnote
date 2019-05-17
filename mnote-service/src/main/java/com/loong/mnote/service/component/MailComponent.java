package com.loong.mnote.service.component;

import com.loong.mnote.common.enums.SmsCodeTypeEnum;
import com.loong.mnote.common.exception.BizException;
import com.loong.mnote.service.AbstractService;
import com.loong.mnote.service.component.cache.StringCacheService;
import com.sun.mail.util.MailSSLSocketFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.*;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author: sam
 * @date: 2018-12-28 16:49
 */
@Component
public class MailComponent extends AbstractService {

    @Autowired
    private SystemConfigComponent systemConfigComponent;

    @Autowired
    private StringCacheService stringCacheService;

//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;

    public String getEmailCode(Integer type,Integer userType,final String email){
        if (StringUtils.indexOf(email,"@") <= 0 ) {
            throw new BizException("邮箱地址不合法");
        }
        final String emailCode = RandomStringUtils.random(4, false, true);
        //
        SmsCodeTypeEnum smsCodeTypeEnum = SmsCodeTypeEnum.getByCode(type);
        String smsCodeKey = StringUtils.join(new Object[]{smsCodeTypeEnum.getCacheKey(),userType, email}, "_");
        String cacheValue = stringCacheService.get(smsCodeKey);
        if (StringUtils.isNotEmpty(cacheValue)) {  //存在不用重复发送
            return cacheValue;
        }
        long period = smsCodeTypeEnum.getTimeUnit().toSeconds(smsCodeTypeEnum.getExpireTime());
        stringCacheService.set(smsCodeKey,emailCode, (int) period);
        if (systemConfigComponent.isProd()) {  //生产环境才发送验证码
            new Thread(() -> {
//                String value = "%23code%23%3d"+smsCode;
                //TODO
//                sendText(email,"LoongPay email code",emailCode);
            }).start();
        }
        return emailCode;
    }

    public boolean sendText(String receive, String subject, String content) {
        List<String> arrayList = new ArrayList<>();
        arrayList.add(receive);
        return sendmail(arrayList, subject, content, null);
    }

    public boolean sendMultiText(List<String> receiveList, String subject, String content) {
        return sendmail(receiveList, subject, content, null);
    }

    private boolean sendmail(List<String> receiveList, String subject, String content, List<Byte> attachmentList) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtphm.qiye.163.com");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.port", "465");

            props.put("mail.smtp.ssl.enable", "true");
            //开启安全协议
            MailSSLSocketFactory sf = null;
            try {
                sf = new MailSSLSocketFactory();
                sf.setTrustAllHosts(true);
            } catch (GeneralSecurityException e1) {
                logger.error("sf error", e1);
            }
            props.put("mail.smtp.ssl.socketFactory", sf);

            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("sam@mnote.com", "5Np4c6tFkxR735Rb");
                }
            });
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("sam@mnote.com", false));
            List<Address> internetAddressList = new ArrayList<>();
            for (String s : receiveList) {

                internetAddressList.add(new InternetAddress(s));
            }
            msg.setRecipients(Message.RecipientType.TO, internetAddressList.toArray(new Address[]{}));
            msg.setSubject(subject);

            MimeMultipart parts = new MimeMultipart();// 创建部件集对象
            MimeBodyPart part = new MimeBodyPart();// 创建一个部件
            part.setContent(content, "text/html;charset=utf-8");// 设置邮件文本内容
            parts.addBodyPart(part);// 把部件添加到部件集中

//            // 添加附件
//            List<AttachBean> attachBeanList = mail.getAttachs();// 获取所有附件
//            if (attachBeanList != null) {
//                for (AttachBean attach : attachBeanList) {
//                    MimeBodyPart attachPart = new MimeBodyPart();// 创建一个部件
//                    attachPart.attachFile(attach.getFile());// 设置附件文件
//                    attachPart.setFileName(MimeUtility.encodeText(attach
//                            .getFileName()));// 设置附件文件名
//                    String cid = attach.getCid();
//                    if(cid != null) {
//                        attachPart.setContentID(cid);
//                    }
//                    parts.addBodyPart(attachPart);
//                }
//            }


            msg.setContent(parts);
            msg.setSentDate(new Date());

//            MimeBodyPart messageBodyPart = new MimeBodyPart();
//            messageBodyPart.setContent("Tutorials point email", "text/html");
//
//            Multipart multipart = new MimeMultipart();
//            multipart.addBodyPart(messageBodyPart);
//            MimeBodyPart attachPart = new MimeBodyPart();
//
//
//            attachPart.attachFile("/var/tmp/image19.png");
//            multipart.addBodyPart(attachPart);
//            msg.setContent(multipart);
            Transport.send(msg);
            return true;

        } catch (Exception e) {
            logger.error("send mail failed! err={}", ExceptionUtils.getRootCauseMessage(e));
            return false;
        }
    }
}

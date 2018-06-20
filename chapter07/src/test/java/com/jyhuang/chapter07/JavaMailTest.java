package com.jyhuang.chapter07;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Chapter07Application.class)
public class JavaMailTest {

    @Autowired
    private JavaMailSenderImpl mailSender;

    /**
     * 纯文本简单邮件
     *
     * @throws Exception
     */
    @Test
    public void sendMail() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        mimeMessage
                .setFrom(new InternetAddress(String.format("黄健瑜<%s>", mailSender.getUsername())));
        mimeMessage
                .setRecipients(MimeMessage.RecipientType.TO, "996934716@qq.com, 2635957004@QQ.com");
        mimeMessage.setSubject("测试 Spring boot 使用 JavaMail 发送邮件");
        mimeMessage.setText("当你看到这封邮件时，说明测试成功！");

        mailSender.send(mimeMessage);
    }

    /**
     * 纯文本带附件邮件
     *
     * @throws MessagingException
     */
    @Test
    public void sendMailWithAttachment() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(new InternetAddress(String.format("黄健瑜<%s>", mailSender.getUsername())));
        helper.setTo("996934716@qq.com");
        helper.setCc(new InternetAddress("2635957004@qq.com"));
        helper.setSubject("测试 Spring boot 使用 JavaMail 发送邮件(带附件)");
        helper.setText("当你看到这封邮件时，说明测试成功！");

        FileSystemResource file = new FileSystemResource(
                new File("/Users/jyhuang/Pictures/private/timg.jpeg"));
        helper.addAttachment(file.getFilename(), file);

        mailSender.send(mimeMessage);
    }

    /**
     * 嵌入图片等静态资源的邮件
     * @throws MessagingException
     */
    @Test
    public void sendMailWithInline() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(String.format("黄健瑜<%s>", mailSender.getUsername()));
        helper.setTo("996934716@qq.com");
        helper.setSubject("测试 Spring boot 使用 JavaMail 发送邮件(嵌入静态资源)");
        helper.setText(
                "<html><body><h3>当你看到这封邮件时，说明测试成功！</h3><img src=\"cid:img01\" ></body></html>");

        FileSystemResource file = new FileSystemResource(
                new File("/Users/jyhuang/Pictures/private/timg.jpeg"));
        helper.addInline("img01", file); // 此处的参数 contentId 对应 text 中的 img01

        mailSender.send(mimeMessage);
    }
}

package com.meilele.utils;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class MailUtils {

    @Value("#{configProperties['mail.host']}")
    private String        mailHost;

    @Value("#{configProperties['mail.user']}")
    private String        mailUser;

    @Value("#{configProperties['mail.password']}")
    private String        mailPassword;

    private static String MAIL_HOST;

    private static String MAIL_USER;

    private static String MAIL_PASSWORD;

    @PostConstruct
    private void memcachedClientInit() {
        MAIL_HOST = mailHost;
        MAIL_USER = mailUser;
        MAIL_PASSWORD = mailPassword;
    }

    public static void sendmail(MailMsgInfo msg) {
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.sohu.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        //使用JavaMail发送邮件的5个步骤
        //1、创建session
        Session session = Session.getInstance(prop);
        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        try {
            //2、通过session得到transport对象
            Transport ts = session.getTransport();
            //3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
            ts.connect(MAIL_HOST, MAIL_USER, MAIL_PASSWORD);
            //4、创建邮件
            Message message = createSimpleMail(session, msg);
            //5、发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
        } catch (Exception e) {

        }
    }

    public static MimeMessage createSimpleMail(Session session, MailMsgInfo msg) throws Exception {
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress(MAIL_USER));
        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(msg.getSendAddress()));
        //邮件的标题
        message.setSubject(msg.getSubject());
        //邮件的文本内容
        message.setContent(msg.getContent(), "text/html;charset=UTF-8");
        //返回创建好的邮件对象
        return message;
    }

    public static void main(String[] args) {
        MailUtils.MAIL_HOST = "mail.meilele.com";
        MailUtils.MAIL_USER = "mllsys@meilele.com";
        MailUtils.MAIL_PASSWORD = "mll123!@#";
        MailMsgInfo msg = new MailMsgInfo();
        msg.setSendAddress("luoyongchun@meilele.com");
        msg.setSubject("aaa");

        String html = "<table border='6'>"
                      + "<caption>我的标题</caption>"
                      + "<tr>"
                      + "  <td style='width: 40%;'>需求</td>"
                      + "  <td style='width: 10%;'>开始时间</td>"
                      + "  <td style='width: 50%;'>参与人</td>"
                      + "</tr>"
                      + "<tr>"
                      + "  <td><a target='_blank' style='text-decoration:none;' href='http://pro.meilele.com/view.php?id=7376'>7376</a>门店生产系统中的商品推荐列表，无法搜索商品</td>"
                      + "  <td>500</td>"
                      + "  <td>赵志文&nbsp;&nbsp;刘霞1&nbsp;&nbsp;敖芳&nbsp;&nbsp;蒋勇&nbsp;&nbsp;张建龙&nbsp;&nbsp;梁大光&nbsp;&nbsp;曲京松&nbsp;&nbsp;孙志平&nbsp;&nbsp</td>"
                      + "</tr>" + "</table>";
        msg.setContent(html);
        MailUtils.sendmail(msg);
    }
}

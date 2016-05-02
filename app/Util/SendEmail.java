// File Name SendEmail.java

package Util;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.io.*;
import java.net.InetAddress;
import java.util.Properties;
import java.util.Date;
import com.sun.mail.smtp.*;
import models.User;

public class SendEmail
{

    public static void SendMail(String email, String title, String body) throws Exception {
        Properties props = System.getProperties();
        props.put("mail.smtps.host","smtp.mailgun.org");
        props.put("mail.smtps.auth","true");
        Session session = Session.getInstance(props, null);
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("postmaster@sandboxfee5fbdcc44b40e5812ba6dc72fed87b.mailgun.org"));
        msg.setRecipients(Message.RecipientType.TO,
        InternetAddress.parse(email, false));
        msg.setSubject(title);
        msg.setText(body);
        msg.setSentDate(new Date());
        SMTPTransport t =
            (SMTPTransport)session.getTransport("smtps");
        t.connect("smtp.mailgun.com", "postmaster@sandboxfee5fbdcc44b40e5812ba6dc72fed87b.mailgun.org", "b6bc75cb98181f8ef5aaef6231a75a20");
        t.sendMessage(msg, msg.getAllRecipients());
        System.out.println("Response: " + t.getLastServerResponse());
        t.close();
    }

}
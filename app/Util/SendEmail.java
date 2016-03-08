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


   public static void SendEmail(User mailUser, String title, String body)
   {    
      // Recipient's email ID needs to be mentioned.
      String to = mailUser.email;

      // Sender's email ID needs to be mentioned
      String from = "mailgun@swe-project.herokuapp.com";

      // Assuming you are sending email from localhost
      String host = "localhost";

      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      properties.setProperty("smtp.mailgun.org", host);

      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties);

      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

         // Set Subject: header field
         message.setSubject(title);

         // Now set the actual message
         message.setText(body);

         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
}
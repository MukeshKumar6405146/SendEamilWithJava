package org.example;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailHandler {

    void sendMail(){
        Properties sysProperties = System.getProperties();
        System.out.println(sysProperties);

        sysProperties.put("mail.smtp.host",MailMetaData.HostServer);
        sysProperties.put("mail.smtp.port",MailMetaData.Port);
        sysProperties.put(MailMetaData.sslProperty,"true");
        sysProperties.put(MailMetaData.authPerm,"true");

        //create a session using sender-email
        Authenticator mailAuthenticator = new CustomizedMailAuthentication();
        Session mailSession = Session.getInstance(sysProperties,mailAuthenticator);

        //mime message build

        MimeMessage mailMessage = new MimeMessage(mailSession);
         try {
             mailMessage.setFrom(MailMetaData.myUserMail);
             mailMessage.setSubject("This is my java code testing");
             mailMessage.setText("Hey this is mukesh,I am trying to send mail using java");

             //set the receiver
             Address receiverEmail = new InternetAddress(MailMetaData.receiverMail);
             mailMessage.setRecipient(Message.RecipientType.TO, receiverEmail);
         }catch (Exception mailException){
             System.out.println(mailException.toString());
         }
    }
}

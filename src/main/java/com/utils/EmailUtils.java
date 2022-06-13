package com.utils;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

public class EmailUtils {
    public static void sendMail(String host, String port, final String sender, String senderName, String password, String recipientEmail, String subject, String message) throws UnsupportedEncodingException, MessagingException {
        Properties properties = new Properties();
        properties.put("mail.imap.ssl.enable", "true"); // required for Gmail
        properties.put("mail.imap.auth.mechanisms", "XOAUTH2");
        Session session = Session.getInstance(properties);
        Store store = session.getStore("imap");
        store.connect("imap.gmail.com", "thisisthientesting@gmail.com", "ya29.a0ARrdaM9fM0lbThKXRrfbDoLEC9sI-v08lNACvTaHCP_EOYfs9Wcr-UP8Djo6clucdJWL6CVoM0tdH1WXEPJ8a6ok41dsKouz9fBW7yVdRkHsgHfu39OYNr80IQ90jDLhDspi8aHxJlYrlN7vY99SKbhbuTEm");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, password);
            }
        };

        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(sender, senderName));
//        InternetAddress[] toAddress  = {new InternetAddress(recipientEmail)};
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setText(message);

        Transport.send(msg);
    }


    public static void sendMail() throws UnsupportedEncodingException, MessagingException {
        Properties properties = new Properties();
        properties.put("mail.imap.ssl.enable", "true"); // required for Gmail
        properties.put("mail.imap.auth.mechanisms", "XOAUTH2");
        Session session = Session.getInstance(properties);
        Store store = session.getStore("imap");
        store.connect("imap.gmail.com", "thisisthientesting@gmail.com", "ya29.a0ARrdaM8JoSmYlR-kfW9wsAZm6FzWHDjUKYVo817kpsb_xhFlBKoN7xl8C04u15i_hhxfHgVN4IrfuzqrSC5dJlaISqCBb6iDcdYkagISArxhaLP3hyqD0_VfTK4V9B8HMRUaFEBvCc0YEAivndeofVdsgbrB");

        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress("thisisthientesting@gmail.com", "PetsParadise"));
//        InternetAddress[] toAddress  = {new InternetAddress(recipientEmail)};
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("nguyenducthien9@gmail.com"));
        msg.setSubject("HI");
        msg.setSentDate(new Date());
        msg.setText("Hi");

        Transport.send(msg);
    }
}

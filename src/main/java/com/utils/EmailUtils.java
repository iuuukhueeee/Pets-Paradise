package com.utils;

import com.checkout.Cart;
import com.checkout.Item;
import com.env.ENVIRONMENT;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtils {
//    public static void sendMail(String host, String port, final String sender, String senderName, String password, String recipientEmail, String subject, String message) throws UnsupportedEncodingException, MessagingException {
//        Properties properties = new Properties();
//        properties.put("mail.imap.ssl.enable", "true"); // required for Gmail
//        properties.put("mail.imap.auth.mechanisms", "XOAUTH2");
//        Session session = Session.getInstance(properties);
//        Store store = session.getStore("imap");
//        store.connect("imap.gmail.com", "thisisthientesting@gmail.com", "ya29.a0ARrdaM9fM0lbThKXRrfbDoLEC9sI-v08lNACvTaHCP_EOYfs9Wcr-UP8Djo6clucdJWL6CVoM0tdH1WXEPJ8a6ok41dsKouz9fBW7yVdRkHsgHfu39OYNr80IQ90jDLhDspi8aHxJlYrlN7vY99SKbhbuTEm");
//
//        Authenticator auth = new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(sender, password);
//            }
//        };
//
//        Message msg = new MimeMessage(session);
//
//        msg.setFrom(new InternetAddress(sender, senderName));
////        InternetAddress[] toAddress  = {new InternetAddress(recipientEmail)};
//        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
//        msg.setSubject(subject);
//        msg.setSentDate(new Date());
//        msg.setText(message);
//
//        Transport.send(msg);
//    }


    public static boolean sendResetPassword(String newPassword, String email) {
        final String username = ENVIRONMENT.usernameEmail;
        final String password = ENVIRONMENT.passwordEmail;

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("thisisthientesting@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject("Your new password");
            message.setText(newPassword);

            Transport.send(message);
            System.out.println("done");

        } catch (MessagingException e) {
            return false;
        }
        return true;
    }

    public static boolean sendConfirmOrder(String orderID, String email, Cart cart) {
        final String username = "thisisthientesting@gmail.com";
        final String password = "mdkyzhywmatgpelk";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("thisisthientesting@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject("Your Order");
            String order = "";
            for (Item item: cart.getCart().values()) {
                order += item.getProduct() + " ";
            }
            message.setText(orderID + "\n" + order);

            Transport.send(message);
            System.out.println("done");

        } catch (MessagingException e) {
            return false;
        }
        return true;
    }
}

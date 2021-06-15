/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExternalJavaFiles;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author farzeen
 */
public class JavaMailUtil {
    public static void sendMail(String userName,String recepient,String pass){
        
        try {
            String sysEmail,sysPass;
            Properties properties=new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            sysEmail="scmv2021@gmail.com";
            sysPass="Scmv12345";
            Session  session= Session.getInstance(properties, new Authenticator(){             
                @Override
                protected PasswordAuthentication  getPasswordAuthentication(){
                    return new PasswordAuthentication(sysEmail,sysPass);
                }
            }); 
            Message message =  prepareMessage(session,sysEmail,recepient,pass,userName);
            Transport.send(message);
            System.out.println("Email Sent Successfully!!!");
        } catch (MessagingException ex) {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    public static void sendOTP(String recepient,String OTP){
        
        try {
            String sysEmail,sysPass;
            Properties properties=new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            sysEmail="scmv2021@gmail.com";
            sysPass="Scmv12345";
            Session  session= Session.getInstance(properties, new Authenticator(){             
                @Override
                protected PasswordAuthentication  getPasswordAuthentication(){
                    return new PasswordAuthentication(sysEmail,sysPass);
                }
            }); 
            Message message =  prepareOTPMessage(session,sysEmail,recepient,OTP);
            Transport.send(message);
            System.out.println("Email Sent Successfully!!!");
        } catch (MessagingException ex) {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    private static Message prepareMessage(Session  session,String sysEmail,String recepient,String pass,String userName) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sysEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Password of Your Account");
            message.setText("Dear Valuable User!\nYour User Name is -->"+userName+"\nYour password is --> "+pass+"\nKindly change your password after Signing-in to your account to maintain security of your account!!!\nRegards,\nSCMV Team"); 
           return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    private static Message prepareOTPMessage(Session  session,String sysEmail,String recepient,String OTP) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sysEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("OTP for SCMV Tool Account Registration");
            message.setText("Dear Valuable User!\nThank You for registering on SCMV Tool.\nPlease enter the OTP on your requested system and get start using the Tool Freely.\nYour One-Time-Password is -->"+OTP+"\nRegards,\nSCMV Team"); 
           return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}

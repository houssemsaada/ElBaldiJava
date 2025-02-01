/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.services;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/**
 *
 * @author houss
 */
public class SmsServicee {
    public static final String ACCOUNT_SID = "ACf072049084ba239a0ee6b374a501568b";
    public static final String AUTH_TOKEN = "df5bca0ea09aef00c48ae33e187a595f";
    public static final String TWILIO_NUMBER = "+15735153840";
    public static final String MY_NUMBER = "+21692673854";

    public static void sendSms(String to, String messageBody) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber(MY_NUMBER), new PhoneNumber(TWILIO_NUMBER), messageBody).create();
        System.out.println(message.getSid());
    }
}

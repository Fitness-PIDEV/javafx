/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class Smsapi {
    public static final String ACCOUNT_SID = "AC9623caa6d339122c86641df33c839165";
    public static final String AUTH_TOKEN = "c450df92a2659bb7bbaa15f29d5fcd6a";

    public static void sendSMS(String num, String msg) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(num),new PhoneNumber("+19107764542"), msg).create();

        System.out.println(message.getSid());

    }
}

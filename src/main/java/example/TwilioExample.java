package example;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class TwilioExample {

	public void sendSms(String accSid,String token,String fromPhoneNumber,String toPhoneNumber,String msg) {
		Twilio.init(accSid, token);
		Message message = Message.creator(new com.twilio.type.PhoneNumber(toPhoneNumber),
				new com.twilio.type.PhoneNumber(fromPhoneNumber), msg).create();

		System.out.println(message.getSid());
	}
}

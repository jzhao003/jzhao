package rest.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import rest.service.HelloMessageService;

@Component
public class ScheduledTask {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	private HelloMessageService helloService;
	
	@Scheduled(cron = "0/5 * * * * ?")
	public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));
		if (helloService == null) {
			log.info("=============");
		} else {
			log.info(helloService.getAccountSid());
		}
//		new TwilioExample().sendSms("Hello Quartz!");
	}
}

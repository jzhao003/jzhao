package example;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rest.service.HelloMessageService;

@Component
public class ShuangseqiuJob implements Job {
	
	@Autowired
	private HelloMessageService helloService;
	
	public void execute(JobExecutionContext context) throws JobExecutionException {

		if (helloService == null) {
			System.out.println("=============");
		}
//		System.out.println(helloService.getAccountSid());
		
//		new TwilioExample().sendSms("Hello Quartz!");

	}
}

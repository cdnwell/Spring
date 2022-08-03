package main;

public class StartSendLog {

	public static void main(String[] args) {
		SendLogCronTrigger trigger = new SendLogCronTrigger("0/10 * * * * ?",SendLogJob.class);
		trigger.triggerJob();
	}

}

package main;

public class StartSendLog {
	// 3일에 한 번 씩 실행 : 0 0 13 */3 * ?
	public static void main(String[] args) {
		SendLogCronTrigger trigger = new SendLogCronTrigger("0 0 13 */3 * ?",SendLogJob.class);
		trigger.triggerJob();
	}

}

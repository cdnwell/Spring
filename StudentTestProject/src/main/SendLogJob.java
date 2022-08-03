package main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SendLogJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		File file = new File("error.txt");
		FileReader fr = null;
		BufferedReader br = null;
		String text = new String();
		
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			while(true) {
				String s = br.readLine();
				if( s == null)
					break;
				text += s;
			}
			System.out.println(text);
			
			String[] msg = text.split("\t");	
			
			String log_date = msg[0];
			String error_code = msg[3];
			String content = msg[4];
			
			String apiUrl = "http://localhost:9999/search.do?log_date="+log_date+"&error_code="+error_code+
					"&content="+content;
			URL url = new URL(apiUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			FileOutputStream fos = new FileOutputStream("error.txt",false);
			PrintWriter pw = new PrintWriter(fos);
			
			String str = "";
			pw.println(str);
			pw.flush();
			pw.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(br != null) br.close();
				if(fr != null) fr.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}

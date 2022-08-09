package main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SendLogJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		File file = new File("error.txt");
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			Charset cst = Charset.forName("utf-8");
			fr = new FileReader(file,cst);
			br = new BufferedReader(fr);
			
			String str;
			while((str = br.readLine()) != null) {
				String str0 = str.split("\t")[0];
				String str1 = str.split("\t")[1];
				sendLog(str0,str1);
				System.out.println(str);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(br != null) br.close();
				if(fr != null) fr.close();
				file.delete();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void sendLog(String str0,String str1) {
		
		try {
			String apiUrl = "http://localhost:9999/errorLog.do?log=%s&result=%s";
			apiUrl = String.format(apiUrl, URLEncoder.encode(str0.split("_")[0],"utf-8"), URLEncoder.encode(str1,"utf-8"));
			URL url = new URL(apiUrl);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String s = "";
			String tmp;
			
			while((tmp=br.readLine())!=null)
				s += tmp;
			
			br.close();
			conn.disconnect();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

}

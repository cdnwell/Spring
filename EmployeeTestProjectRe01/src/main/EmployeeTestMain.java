package main;

import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

class EmployeeTestMain {

	@Test
	void test() {
		String search = "이";
		String kind = "name";
		
		try {
			search = URLEncoder.encode(search, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String apiUrl = "http://localhost:9999/search.do?kind="+kind+"&search="+search;
		
		try {
			URL url = new URL(apiUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
			
			String result = "";
			
			while(true) {
				String str = br.readLine();
				if(str == null) break;
				result += str;
			}
			
			JSONObject json = new JSONObject(result);
			
			if(json.getInt("code") == 500) {
				throw new Exception(json.getInt("code")+"\t"+json.getString("message"));
			}
			
			JSONArray array = json.getJSONArray("result");
			for(int i = 0;i<array.length();i++) {
				JSONObject obj = array.getJSONObject(i);
				System.out.println(
						obj.getString("eno") + "\t"
						+ obj.getString("name") + "\t"
						+ obj.getString("department") + "\t"
						+ obj.getInt("position")
						);
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			try {
				FileOutputStream fos = new FileOutputStream("error.txt",true);
				Charset cset = Charset.forName("utf-8");
				PrintWriter pw = new PrintWriter(fos,true,cset);  
			    
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd_hh:mm:ss");
				
				String str = sdf.format(Calendar.getInstance().getTime()) + "\t" + e.getMessage();
				pw.println(str);
				pw.flush();
				pw.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		
	}

}

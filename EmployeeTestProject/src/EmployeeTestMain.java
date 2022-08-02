import static org.junit.jupiter.api.Assertions.*;

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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

class EmployeeTestMain {

	@Test
	void test() {
		String search = "김";
		String kind = "name";
		try {
			search = URLEncoder.encode(search, "utf-8");
			
			//결과가 나왔는지 않나왔는지로 exception 해서 파일처리 가능
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String apiUrl = "http://localhost:9999/search.do?kind="+kind+"&search="+search;
		try {
			URL url = new URL(apiUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String result = "";
			
			while(true) {
				String str = br.readLine();
				//str == null > 다 읽어왔다.
				if(str == null) break;
				result += str;
			}
			//result에 받아온 json이 다 들어있다.
//			System.out.println(result);
			//받아온 결과를 파싱하기
			JSONObject json = new JSONObject(result);
			
			if(json.getInt("code") == 500)
				fail("검색결과 없음");
			//오류 났는지 체크하기
			if(json.getInt("code") == 500) {
				throw new Exception(json.getInt("code") + "\t" + json.getString("message"));
			}
			
			JSONArray array = json.getJSONArray("result");
			for(int i = 0 ; i < array.length(); i++) {
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
			e.printStackTrace();
		} catch (Exception e) {
			//exception이 걸리면 파일 쓰기
			//서버쪽에서 결과 코드를 500으로 보냈을떄 로그로 처리하는 부분
			try {
				FileOutputStream fos = new FileOutputStream("error.txt",true);
				PrintWriter pw = new PrintWriter(fos);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd_hh:mm:ss");
				//calendar.getinstance 현재 날짜 가져옴
				//tab을 기준으로 순서대로 파싱가능
				String str = sdf.format(Calendar.getInstance().getTime()) + "\t";
				e.getMessage();
				pw.println(str);
				pw.flush();
				pw.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}

}

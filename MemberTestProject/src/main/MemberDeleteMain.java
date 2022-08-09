package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Scanner;

import org.json.JSONObject;

public class MemberDeleteMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("삭제할 아이디를 입력해주세요 >> ");
		String id = sc.nextLine();
		
		try {
			id = URLEncoder.encode(id,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String apiUrl = "http://localhost:9999/deleteMember.do?id="+id;
		
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
			
			if(json.getInt("code")==500) throw new Exception();
			
			System.out.println(json.get("message"));
			
		} catch (Exception e) {
			try {
				FileOutputStream fos = new FileOutputStream("error.txt",true);
				Charset cs = Charset.forName("utf-8");
				PrintWriter pw = new PrintWriter(fos,false,cs);
				
				//전달한 데이터, 삭제결과 기록
				String str = "전달한 데이터(아이디) : " + id + "\t삭제 결과 : 실패";
				pw.println(str);
				pw.flush();
				pw.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
		}
		
	}

}

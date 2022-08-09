package main;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

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
import org.junit.jupiter.api.Test;

class MemberTestMain {
	/*
	 * 한 건의 데이터를 전송하여 삭제하는 테스트를 수행하시오
	 */
	@Test
	void test() {
		Scanner sc = new Scanner(System.in);
		System.out.print("삭제할 아이디를 입력해주세요 >> ");
		String id = sc.nextLine();
		JSONObject json = null;
		
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
			
			json = new JSONObject(result);
			
			if(json.getInt("code") == 500)
				fail("삭제를 실패하였습니다.");
			
			System.out.println(json.getString("message"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

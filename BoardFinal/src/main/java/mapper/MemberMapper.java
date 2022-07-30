package mapper;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import config.DBManager;
import dto.MemberDTO;

public class MemberMapper {
	private static MemberMapper instance = new MemberMapper();
	
	private MemberMapper() {
		
	}
	
	public static MemberMapper getInstance() {
		if(instance == null)
			instance = new MemberMapper();
		
		return instance;
	}
	
	public MemberDTO login(String id, String passwd) {
		//일 할 때 마다 하나씩 session을 빌려옴
		//사용자가 여러명 접근했을 때 session 하나로 씀
		//사용자가 접근할 때 마다 빌려쓰게 만듦
		SqlSession session = DBManager.getInstance().getSession();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("passwd", passwd);
		MemberDTO dto = session.selectOne("login",map);
		session.close();
		return dto;
	}
	
}

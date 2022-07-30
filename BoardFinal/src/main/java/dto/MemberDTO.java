package dto;

//dto는 무조건 기본 생성자가 있어야 됨, mybatis에서 필요, 세터 게터도 필수

public class MemberDTO {
	private String id;
	private String passwd;
	private String name;
	private String nick;
	private int gradeNo;
	private String gradeName;
	
	public MemberDTO(String id, String passwd, String name, String nick, int gradeNo, String gradeName) {
		super();
		this.id = id;
		this.passwd = passwd;
		this.name = name;
		this.nick = nick;
		this.gradeNo = gradeNo;
		this.gradeName = gradeName;
	}
	public MemberDTO(String id, String passwd, String name, String nick, int gradeNo) {
		super();
		this.id = id;
		this.passwd = passwd;
		this.name = name;
		this.nick = nick;
		this.gradeNo = gradeNo;
	}
	public MemberDTO() {
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the pass
	 */
	public String getPasswd() {
		return passwd;
	}
	/**
	 * @param pass the pass to set
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the nick
	 */
	public String getNick() {
		return nick;
	}
	/**
	 * @param nick the nick to set
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}
	/**
	 * @return the gradeNo
	 */
	public int getGradeNo() {
		return gradeNo;
	}
	/**
	 * @param gradeNo the gradeNo to set
	 */
	public void setGradeNo(int gradeNo) {
		this.gradeNo = gradeNo;
	}
	/**
	 * @return the gradeName
	 */
	public String getGradeName() {
		return gradeName;
	}
	/**
	 * @param gradeName the gradeName to set
	 */
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
	
	
}

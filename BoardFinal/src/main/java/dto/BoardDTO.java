package dto;

public class BoardDTO {
	private int bno;
	private String title;
	private String writer;
	private String nick;
	private int bcount;
	private String bdate;
	private int blike;
	private int bhate;
	private String content;
	
	public BoardDTO() {	}

	public BoardDTO(int bno, String title, String writer, String nick, int bcount, String bdate, int like, int hate,
			String content) {
		super();
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.nick = nick;
		this.bcount = bcount;
		this.bdate = bdate;
		this.blike = like;
		this.bhate = hate;
		this.content = content;
	}
	
	public BoardDTO(int bno, String title, String writer, String nick, int bcount, String bdate, int like, int hate) {
		super();
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.nick = nick;
		this.bcount = bcount;
		this.bdate = bdate;
		this.blike = like;
		this.bhate = hate;
	}

	public BoardDTO(String title, String writer, String content) {
		super();
		this.title = title;
		this.writer = writer;
		this.content = content;
	}

	/**
	 * @return the bno
	 */
	public int getBno() {
		return bno;
	}

	/**
	 * @param bno the bno to set
	 */
	public void setBno(int bno) {
		this.bno = bno;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the writer
	 */
	public String getWriter() {
		return writer;
	}

	/**
	 * @param writer the writer to set
	 */
	public void setWriter(String writer) {
		this.writer = writer;
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
	 * @return the bcount
	 */
	public int getBcount() {
		return bcount;
	}

	/**
	 * @param bcount the bcount to set
	 */
	public void setBcount(int bcount) {
		this.bcount = bcount;
	}

	/**
	 * @return the bdate
	 */
	public String getBdate() {
		return bdate;
	}

	/**
	 * @param bdate the bdate to set
	 */
	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	/**
	 * @return the blike
	 */
	public int getBlike() {
		return blike;
	}

	/**
	 * @param blike the blike to set
	 */
	public void setBlike(int blike) {
		this.blike = blike;
	}

	/**
	 * @return the bhate
	 */
	public int getBhate() {
		return bhate;
	}

	/**
	 * @param bhate the bhate to set
	 */
	public void setBhate(int bhate) {
		this.bhate = bhate;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	
	
}

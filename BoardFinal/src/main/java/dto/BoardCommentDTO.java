package dto;

public class BoardCommentDTO {
	private int cno;
	private int bno;
	private String content;
	private String writer;
	private String date;
	private int like;
	private int hate;
	
	public BoardCommentDTO() {
		super();
	}

	public BoardCommentDTO(int cno, int bno, String content, String writer, String date, int like, int hate) {
		super();
		this.cno = cno;
		this.bno = bno;
		this.content = content;
		this.writer = writer;
		this.date = date;
		this.like = like;
		this.hate = hate;
	}
	
	public BoardCommentDTO(int bno, String content, String writer) {
		super();
		this.bno = bno;
		this.content = content;
		this.writer = writer;
	}

	/**
	 * @return the cno
	 */
	public int getCno() {
		return cno;
	}
	/**
	 * @param cno the cno to set
	 */
	public void setCno(int cno) {
		this.cno = cno;
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
	 * @return the cdate
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param cdate the cdate to set
	 */
	public void setDate(String cdate) {
		this.date = cdate;
	}
	/**
	 * @return the like
	 */
	public int getLike() {
		return like;
	}
	/**
	 * @param like the like to set
	 */
	public void setLike(int like) {
		this.like = like;
	}
	/**
	 * @return the hate
	 */
	public int getHate() {
		return hate;
	}
	/**
	 * @param hate the hate to set
	 */
	public void setHate(int hate) {
		this.hate = hate;
	}
	
	@Override
	public String toString() {
		return "BoardCommentDTO [cno=" + cno + ", bno=" + bno + ", content=" + content + ", writer=" + writer
				+ ", cdate=" + date + ", like=" + like + ", hate=" + hate + "]";
	}
	
}

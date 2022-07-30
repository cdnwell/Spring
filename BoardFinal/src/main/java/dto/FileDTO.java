package dto;

import java.io.File;

public class FileDTO {
	private String path;
	private String fileName;
	private String type;
	private int bno;
	private int order;
	
	public FileDTO() {
		super();
	}

	public FileDTO(File file, int bno, int order) {
		this.path = file.getAbsolutePath();	//현재 파일 경로가 나옴, 파일명만 제외하고
		this.fileName = file.getName();
		//파일 확장자를 뽑기 위한 구문
		switch(fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase()) {
		case "png":
		case "bmp":
		case "gif":
		case "jpg":
			this.type="image";
			break;
		default:
			this.type="normal";
		}
		this.bno = bno;
		this.order = order;
	}
	
	public FileDTO(String path, String fileName, String type, int bno, int order) {
		super();
		this.path = path;
		this.fileName = fileName;
		this.type = type;
		this.bno = bno;
		this.order = order;
	}
	
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		//dto는 set메서드로 저장하기 때문에 null 값이 안나오도록 이렇게 직접 작성해주어야 한다.
		File file = new File(path);
		this.fileName = file.getName();
		//파일 확장자를 뽑기 위한 구문
		switch(fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase()) {
		case "png":
		case "bmp":
		case "gif":
		case "jpg":
			this.type="image";
			break;
		default:
			this.type="normal";
		}
		this.path = path;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}
	
	@Override
	public String toString() {
		return "FileDTO [path=" + path + ", fileName=" + fileName + ", type=" + type + ", bno=" + bno + ", order="
				+ order + "]";
	}
	
}

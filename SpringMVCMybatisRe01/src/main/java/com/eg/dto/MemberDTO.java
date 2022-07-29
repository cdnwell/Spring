package com.eg.dto;

import org.apache.ibatis.type.Alias;

@Alias("member")
public class MemberDTO {
	private String id;
	private String passwd;
	private String name;
	private int age;
	private String gender;
	private String address;
	
	public MemberDTO() {
		super();
	}
	
	public MemberDTO(String id, String passwd, String name, int age, String gender, String address) {
		super();
		this.id = id;
		this.passwd = passwd;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.address = address;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", passwd=" + passwd + ", name=" + name + ", age=" + age + ", gender=" + gender
				+ ", address=" + address + "]";
	}
	
}

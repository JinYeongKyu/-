package model.bbs;

public class MemberDto {

	private String id;
	private String pwd;
	private String name;
	private String gender;
	private String inters;
	private String selfintroduce;
	private String education;
	
	public MemberDto() {}
	
	public MemberDto(String id, String pwd, String name, String gender, String inters, String education, String selfintroduce) {		
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.gender = gender;
		this.inters = inters;
		this.education = education;
		this.selfintroduce = selfintroduce;		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSelfintroduce() {
		return selfintroduce;
	}
	public void setSelfintroduce(String selfintroduce) {
		this.selfintroduce = selfintroduce;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getInters() {
		return inters;
	}
	public void setInters(String inters) {
		this.inters = inters;
	}
	
}


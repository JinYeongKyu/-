package model.bbs;

import java.sql.Date;

public class BBSDto {	
	
	private String no;
	private String id;
	private String title;
	private String content;
	private String hitCount;
	private Date postDate;
	private String name;
	
	public BBSDto() {}	
	
	public BBSDto(String no, String id, String title, String content, String hitCount, Date postDate) {
		this.no = no;
		this.id = id;
		this.title = title;
		this.content = content;
		this.hitCount = hitCount;
		this.postDate = postDate;
	}

	public String getNo() {
		return no;
	}
	
	public void setNo(String no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getHitCount() {
		return hitCount;
	}
	public void setHitCount(String hitCount) {
		this.hitCount = hitCount;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

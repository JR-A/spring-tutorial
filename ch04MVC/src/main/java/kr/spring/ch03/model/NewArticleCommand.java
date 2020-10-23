package kr.spring.ch03.model;

//자바빈
public class NewArticleCommand {
	private String title;
	private String content;
	private int parentId;
	
	//Getters and Setters
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public int getParentId() {
		return parentId;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	
	@Override
	public String toString() {
		return "NewArticleCommand [title=" + title + ", content=" + content + ", parentId=" + parentId + "]";
	}	
	
}

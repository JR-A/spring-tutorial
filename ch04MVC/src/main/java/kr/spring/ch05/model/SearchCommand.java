package kr.spring.ch05.model;

//VO
public class SearchCommand {
	//get방식으로 전송시 전송되는 name과같도록
	private String type;
	private String query;
	
	//Getters and Setters
	public String getType() {
		return type;
	}
	public String getQuery() {
		return query;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	
}

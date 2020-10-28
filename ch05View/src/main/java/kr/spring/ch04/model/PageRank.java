package kr.spring.ch04.model;

public class PageRank {
	//프로퍼티
	private int rank;
	private String page;
	
	public PageRank() {}
	
	public PageRank(int rank, String page) {
		this.rank = rank;
		this.page = page;
	}
	
	//Getters and Setters
	public int getRank() {
		return rank;
	}

	public String getPage() {
		return page;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public void setPage(String page) {
		this.page = page;
	}
	
	
}

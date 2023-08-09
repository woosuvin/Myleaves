package com.itwill.myleaves.repository.notice;

import lombok.Data;

@Data
public class NoticePaging {
	
	private int offset;
	private int rows;
	private int startPage;
	private int endPage;
	private int total;
	private int previous;
	private int next;
	private int totalPages;
	
	// 만약 검색을 넣는다면 여기에 넣으셈.
	
	public NoticePaging() {
		this.offset = 1;
		this.rows = 10;
	}
	
	public NoticePaging(int offset, int rows) {
		super();
		this.offset = offset;
		this.rows = rows;
	}
	
	public void pagingTotal(int total) {
		this.total = total;
		System.out.println(this.toString());
		
		this.totalPages = ((total - 1) / this.rows) + 1;
		this.endPage = this.offset > this.offset / 10 * 10 ? (this.offset / 10 + 1) * 10 : this.offset / 10 * 10;
		this.startPage = this.offset > 10 ? this.endPage - 9 : 1;
		
		this.endPage = this.endPage > this.totalPages ? this.totalPages : endPage;
		
		this.previous = this.startPage >= 10 ? this.startPage - 10 : 0;
		this.next = ((total - 1) / this.rows) + 1 >= 10 ? this.startPage + 10 : this.endPage;
	}
}

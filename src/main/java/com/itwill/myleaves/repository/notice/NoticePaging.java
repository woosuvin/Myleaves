package com.itwill.myleaves.repository.notice;

import lombok.Data;

@Data
public class NoticePaging {
	
	private int offset;
	private int rows;
	private int startPage;
	private int endPage;
	private int total;
	
	// 만약 검색을 넣는다면 여기에 넣으셈.
	
	public NoticePaging() {
		this.offset = 0;
		this.rows = 10;
	}
	
	public NoticePaging(int offset, int rows) {
		super();
		this.offset = offset;
		this.rows = rows;
	}
	
	public void pagingTotal(int total) {
		this.total = total;
		
		this.endPage = (int)(Math.ceil((this.offset + 1) / 10.0)) * 10;		
		this.startPage = endPage - 9;
		
		int realEnd = (int) (Math.ceil((total * 1.0) / rows));
		this.endPage = realEnd <= endPage ? realEnd : endPage;
	}
}

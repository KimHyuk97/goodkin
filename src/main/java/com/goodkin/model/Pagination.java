package com.goodkin.model;

import lombok.Data;

@Data
public class Pagination {
    private int page;
	private int maxPage;
	private int startPage;
	private int endPage;
	private int startRow;
	private int endRow;
	private int limitCount;

	private static final int PAGE_LIMIT = 10;

	public Pagination(int page, int listCount) {
		this.page = page;
		this.startRow = (page - 1) * PAGE_LIMIT;
		this.endRow = PAGE_LIMIT;

		int maxPage = (int) ((double) listCount / PAGE_LIMIT + 0.9);
		int startPage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		
		int endPage = startPage + 10 - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		this.maxPage = maxPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.limitCount = listCount;
	}
}

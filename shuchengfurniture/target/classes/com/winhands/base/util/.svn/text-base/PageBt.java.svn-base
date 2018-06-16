package com.winhands.base.util;


public class PageBt {
	private int pageNo = 1;
	private int pageSize = 10;
	private long count;
	private int first;
	private int last;
	private int prev;
	private int next;
	private boolean firstPage;
	private boolean lastPage;
	private int length = 8;
	private int slider = 1; 
	
//	private List<Map<String, Object>> mapList = new ArrayList();

	private String orderBy = "";

	private String funcName = "page";

	public PageBt() {
	}

	public PageBt(int pageNo, int pageSize) {
		this(pageNo, pageSize, 0L);
	} 

	public PageBt(int pageNo, int pageSize, long count) {
		setCount(count);
		setPageNo(pageNo);
		this.pageSize = pageSize;
//		setList(list);
	}

	public void initialize() {
		this.first = 1;

		this.last = (int) (this.count
				/ (this.pageSize < 1 ? 20 : this.pageSize) + this.first - 1L);

		if ((this.count % this.pageSize != 0L) || (this.last == 0)) {
			this.last += 1;
		}

		if (this.last < this.first) {
			this.last = this.first;
		}

		if (this.pageNo <= 1) {
			this.pageNo = this.first;
			this.firstPage = true;
		}

		if (this.pageNo >= this.last) {
			this.pageNo = this.last;
			this.lastPage = true;
		}

		if (this.pageNo < this.last - 1)
			this.next = (this.pageNo + 1);
		else {
			this.next = this.last;
		}

		if (this.pageNo > 1)
			this.prev = (this.pageNo - 1);
		else {
			this.prev = this.first;
		}

		if (this.pageNo < this.first) {
			this.pageNo = this.first;
		}

		if (this.pageNo > this.last)
			this.pageNo = this.last;
	}

	public String toString() {
		initialize();

		StringBuilder sb = new StringBuilder();

		if (this.pageNo == this.first)
			sb.append("<li class=\"disabled\"><a href=\"javascript:\">&#171; 上一页</a></li>\n");
		else {
			sb.append("<li><a href=\"javascript:" + this.funcName + "("
					+ this.prev + "," + this.pageSize
					+ ");\">&#171; 上一页</a></li>\n");
		}

		int begin = this.pageNo - this.length / 2;

		if (begin < this.first) {
			begin = this.first;
		}

		int end = begin + this.length - 1;

		if (end >= this.last) {
			end = this.last;
			begin = end - this.length + 1;
			if (begin < this.first) {
				begin = this.first;
			}
		}

		if (begin > this.first) {
			int i = 0;
			for (i = this.first; (i < this.first + this.slider) && (i < begin); i++) {
				sb.append("<li><a href=\"javascript:" + this.funcName + "(" + i
						+ "," + this.pageSize + ");\">" + (i + 1 - this.first)
						+ "</a></li>\n");
			}
			if (i < begin) {
				sb.append("<li class=\"disabled\"><a href=\"javascript:\">...</a></li>\n");
			}
		}

		for (int i = begin; i <= end; i++) {
			if (i == this.pageNo)
				sb.append("<li class=\"active\"><a href=\"javascript:\">"
						+ (i + 1 - this.first) + "</a></li>\n");
			else {
				sb.append("<li><a href=\"javascript:" + this.funcName + "(" + i
						+ "," + this.pageSize + ");\">" + (i + 1 - this.first)
						+ "</a></li>\n");
			}
		}

		if (this.last - end > this.slider) {
			sb.append("<li class=\"disabled\"><a href=\"javascript:\">...</a></li>\n");
			end = this.last - this.slider;
		}

		for (int i = end + 1; i <= this.last; i++) {
			sb.append("<li><a href=\"javascript:" + this.funcName + "(" + i
					+ "," + this.pageSize + ");\">" + (i + 1 - this.first)
					+ "</a></li>\n");
		}

		if (this.pageNo == this.last)
			sb.append("<li class=\"disabled\"><a href=\"javascript:\">下一页 &#187;</a></li>\n");
		else {
			sb.append("<li><a href=\"javascript:" + this.funcName + "("
					+ this.next + "," + this.pageSize + ");\">"
					+ "下一页 &#187;</a></li>\n");
		}

		sb.append("<li class=\"disabled controls\"><a href=\"javascript:\">共 <span id=\"pco\">"
				+ this.count + "</span>" + " 条记录</a><li>\n");

		sb.insert(0, "<ul class=\"pagination\">\n").append("</ul>\n");

		sb.append("<div style=\"clear:both;\"></div>");

		return sb.toString();
	}

	public long getCount() {
		return this.count;
	}

	public void setCount(long count) {
		this.count = count;
		if (this.pageSize >= count)
			this.pageNo = 1;
	}

	public int getPageNo() {
		return this.pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = (pageSize > 500 ? 500 : pageSize <= 0 ? 10 : pageSize);
	}

	public int getFirst() {
		return this.first;
	}

	public int getLast() {
		return this.last;
	}

	public int getTotalPage() {
		return getLast();
	}

	public boolean isFirstPage() {
		return this.firstPage;
	}

	public boolean isLastPage() {
		return this.lastPage;
	}

	public int getPrev() {
		if (isFirstPage()) {
			return this.pageNo;
		}
		return this.pageNo - 1;
	}

	public int getNext() {
		if (isLastPage()) {
			return this.pageNo;
		}
		return this.pageNo + 1;
	}

//	public List<T> getList() {
//		return this.list;
//	}
//
//	public void setList(List<T> list) {
//		this.list = list;
//	}

//	public List<Map<String, Object>> getMapList() {
//		return this.mapList;
//	}
//
//	public void setMapList(List<Map<String, Object>> mapList) {
//		this.mapList = mapList;
//	}

	public String getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getFuncName() {
		return this.funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public boolean isDisabled() {
		return this.pageSize == -1;
	}

	public boolean isNotCount() {
		return this.count == -1L;
	}
}
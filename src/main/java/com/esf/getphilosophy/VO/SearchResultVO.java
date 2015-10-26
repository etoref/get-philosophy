package com.esf.getphilosophy.VO;

import java.util.List;

public class SearchResultVO {
	
	List<PageVO> pageList;
	
	private SearchResultCode resultCode;
	
	private long timeElapsed;

	public SearchResultVO(List<PageVO> pageList, SearchResultCode resultCode, long timeElapsed) {
		super();
		this.pageList = pageList;
		this.resultCode = resultCode;
		this.timeElapsed = timeElapsed;
	}

	public List<PageVO> getPageList() {
		return pageList;
	}

	public SearchResultCode getResultCode() {
		return resultCode;
	}
	
	public String getDisplayMessage(){
		return resultCode.getDisplayMessage();
	}

	public long getTimeElapsed() {
		return timeElapsed;
	}
}

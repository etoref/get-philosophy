package com.esf.getphilosophy.VO;

import java.util.List;

public class SearchResultVO {
	
	List<PageVO> pageList;
	
	private SearchResultCode resultCode;

	public SearchResultVO(List<PageVO> pageList, SearchResultCode resultCode) {
		super();
		this.pageList = pageList;
		this.resultCode = resultCode;
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

}

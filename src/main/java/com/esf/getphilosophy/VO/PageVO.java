package com.esf.getphilosophy.VO;

import java.time.Instant;

import org.springframework.util.StringUtils;

public class PageVO {

	private String url;
	
	private String firstLink;

	private Instant lastUpdate;
	
	public PageVO(String url, String firstLink, Instant lastUpdate) {
		super();
		this.url = url;
		if(StringUtils.isEmpty(firstLink)){
			throw new IllegalArgumentException("firstLink, must be informed.");
		}
		this.firstLink = firstLink;
		this.lastUpdate = lastUpdate;
	}

	public String getUrl() {
		return url;
	}

	public String getFirstLink() {
		return firstLink;
	}

	public Instant getLastUpdate() {
		return lastUpdate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PageVO other = (PageVO) obj;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "PageVO [url=" + url + ", firstLink=" + firstLink + ", lastUpdate=" + lastUpdate + "]";
	}

	
}

package com.esf.getphilosophy.domain;

import java.time.Instant;

public class Page {
	
	private String url;
	
	private String firstLink;

	private Instant lastVisit;

	public Page(String url, String firstLink, Instant lastVisit) {
		super();
		this.url = url;
		this.firstLink = firstLink;
		this.lastVisit = lastVisit;
	}
	
	public String getUrl() {
		return url;
	}

	public String getFirstLink() {
		return firstLink;
	}

	public Instant getLastVisit() {
		return lastVisit;
	}

	
}

package com.esf.getphilosophy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Page {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(unique=true)
	private String url;
	
	private String firstLink;

	protected Page(){
		
	}
	
	public Page(String url, String firstLink) {
		super();
		this.url = url;
		this.firstLink = firstLink;
	}
	
	public String getUrl() {
		return url;
	}

	public String getFirstLink() {
		return firstLink;
	}

	@Override
	public String toString() {
		return "Page [id=" + id + ", url=" + url + ", firstLink=" + firstLink + "]";
	}

	
}

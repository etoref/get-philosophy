package com.esf.getphilosophy.repository.online;

import com.esf.getphilosophy.helper.RegExHelper;

public class WikipediaLineParser {


	private String LINK_REGEX = "href=\"(\\/wiki\\/\\S+)\"";
	
	private String BLACKLIST_REGEX = ".*(disambiguation).*|.*:.*";
	
	private String BASE_URL = "https://en.wikipedia.org";
	
	private String content;
	
		public WikipediaLineParser(String content) {
		super();
		this.content = content;
	}

	public boolean hasArticleTitle(){
		if(content.matches(".*<p>.*<b>.*<\\/b>.*")){
			return true;
		} else {
			return false;
		}
	}
		
	public String getFirstValidLink(){
		
		if(content.contains("rel=\"canonical\"")){
			return null;
		}
		
		return RegExHelper.findFirstValidMatch(content,LINK_REGEX,BLACKLIST_REGEX);		
				
	}

	public String buildUrl(String firstLink) {

		return BASE_URL + firstLink;
	}
	
	
	
}

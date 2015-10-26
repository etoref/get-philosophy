package com.esf.getphilosophy.repository.online;

import java.io.BufferedReader;
import java.io.IOException;

import org.springframework.util.StringUtils;

import com.esf.getphilosophy.VO.PageVO;
import com.esf.getphilosophy.VO.PageVO.SourceType;
import com.esf.getphilosophy.helper.HTTPConnectionHelper;

public class WikipediaPageParser {
	
	private String url;

	public WikipediaPageParser(String url) {
		super();
		this.url = url;
	}

	public PageVO retrievePageVO() {
		
		String linkToNextPage = getLinkToNextPage(); 
		
		if(StringUtils.hasText(linkToNextPage)){
			return new PageVO(url, linkToNextPage, SourceType.WEB);
		}

		return null;
	}

	private String getLinkToNextPage() {

		try {
			BufferedReader rd = HTTPConnectionHelper.createBufferedReader(url);
			
			String line;
			boolean articleReached = false;
			while ((line = rd.readLine()) != null) {
				
				WikipediaLineParser sourceLine = new WikipediaLineParser(line);
				
				if(!articleReached && sourceLine.hasArticleTitle()){
					articleReached = true;
				}
				
				if(articleReached){
					String firstLink = sourceLine.getFirstValidLink();
					if(StringUtils.hasText(firstLink)){
						return sourceLine.buildUrl(firstLink);
					}
				}
			}
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}


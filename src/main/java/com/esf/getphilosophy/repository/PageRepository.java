package com.esf.getphilosophy.repository;

import org.springframework.stereotype.Repository;

import com.esf.getphilosophy.VO.PageVO;
import com.esf.getphilosophy.repository.online.WikipediaPageParser;

@Repository
public class PageRepository {
	
	public PageVO retriveOnline(String url){
		WikipediaPageParser pageParser = new WikipediaPageParser(url);
		return pageParser.retrievePageVO();
	}

}

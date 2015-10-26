package com.esf.getphilosophy.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.esf.getphilosophy.VO.PageVO;
import com.esf.getphilosophy.VO.PageVO.SourceType;
import com.esf.getphilosophy.domain.Page;
import com.esf.getphilosophy.repository.db.PageDatabaseRepository;
import com.esf.getphilosophy.repository.online.WikipediaPageParser;

@Repository
public class PageRepository {
	
	@Autowired
	private PageDatabaseRepository dbRepository;
	
	public PageVO retrivePage(String url){
		
		Page page = dbRepository.findByUrl(url);
		
		if(page != null){
			return new PageVO(page.getUrl(), page.getFirstLink(), SourceType.DataBase);
		} else {

			WikipediaPageParser pageParser = new WikipediaPageParser(url);
			PageVO pageVO = pageParser.retrievePageVO();
			dbRepository.save(new Page(pageVO.getUrl(),pageVO.getFirstLink()));
			return pageVO;
			
		}		
		
	}

}

package com.esf.getphilosophy.service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esf.getphilosophy.VO.PageVO;
import com.esf.getphilosophy.VO.SearchResultCode;
import com.esf.getphilosophy.VO.SearchResultVO;
import com.esf.getphilosophy.config.WikipediaSearchConfig;
import com.esf.getphilosophy.repository.PageRepository;

@Service
public class WikipediaSearchService {

	private static final Logger log = LoggerFactory.getLogger(WikipediaSearchService.class);
	
	@Autowired
	private PageRepository pageRepository;
	
	@Autowired
	private WikipediaSearchConfig searchConfig;
	
	public SearchResultVO processURL(String url) {

		Instant start = Instant.now(); 
		
		int interactionsCount = 0; 
		PageVO actualPage = pageRepository.retrivePage(url);
		
		List<PageVO> pageList = new ArrayList<PageVO>();
		
		if(actualPage == null){
			return new SearchResultVO(pageList, SearchResultCode.STUCK,getDuration(start).toMillis());
		}
		
		pageList.add(actualPage);
		while (searchConfig.getMaxHops() > interactionsCount){

			log.info("Count: "+interactionsCount);
			log.info("Pagina: "+actualPage);
			
			actualPage = pageRepository.retrivePage(actualPage.getFirstLink());
			
			if(actualPage == null){
				return new SearchResultVO(pageList, SearchResultCode.STUCK,getDuration(start).toMillis());
			}

			if(pageList.contains(actualPage)){
				pageList.add(actualPage);
				return new SearchResultVO(pageList,SearchResultCode.LOOP,getDuration(start).toMillis());
			}			
			
			pageList.add(actualPage);
			
			if(actualPage.getUrl().equals(searchConfig.getTargetPageURL())){
				return new SearchResultVO(pageList,SearchResultCode.SUCCESS,getDuration(start).toMillis());
			}
			interactionsCount++;
				
		} 

		return new SearchResultVO(pageList, SearchResultCode.STEP_OUT, getDuration(start).toMillis());
	}

	private Duration getDuration(Instant start) {
		Instant end = Instant.now();
		Duration duration = Duration.between(start, end);
		return duration;
	}

	
}

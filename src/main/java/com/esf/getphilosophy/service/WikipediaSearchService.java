package com.esf.getphilosophy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esf.getphilosophy.VO.PageVO;
import com.esf.getphilosophy.VO.SearchResultCode;
import com.esf.getphilosophy.VO.SearchResultVO;
import com.esf.getphilosophy.config.WikipediaSearchConfig;
import com.esf.getphilosophy.repository.PageRepository;

@Service
public class WikipediaSearchService {

	@Autowired
	private PageRepository pageRepository;
	
	@Autowired
	private WikipediaSearchConfig searchConfig;
	
	public SearchResultVO processURL(String url) {

		int interactionsCount = 0; 
		PageVO actualPage = pageRepository.retriveOnline(url);
		
		List<PageVO> pageList = new ArrayList<PageVO>();
		
		if(actualPage == null){
			return new SearchResultVO(pageList, SearchResultCode.STUCK);
		}
		
		pageList.add(actualPage);
		while (searchConfig.getMaxHops() > interactionsCount){

			System.out.println("Count: "+interactionsCount+" Pagina: "+actualPage);
			
			actualPage = pageRepository.retriveOnline(actualPage.getFirstLink());
			
			if(actualPage == null){
				return new SearchResultVO(pageList, SearchResultCode.STUCK);
			}

			pageList.add(actualPage);
			
			if(pageList.contains(actualPage)){
				return new SearchResultVO(pageList,SearchResultCode.LOOP);
			}			
			
			if(actualPage.getUrl().equals(searchConfig.getTargetPageURL())){
				return new SearchResultVO(pageList,SearchResultCode.SUCCESS);
			}
			interactionsCount++;
				
		} 

		return new SearchResultVO(pageList, SearchResultCode.STEP_OUT);
	}

	
}

package com.esf.getphilosophy.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.esf.getphilosophy.VO.SearchResultVO;
import com.esf.getphilosophy.service.WikipediaSearchService;

@Controller
public class SearchController {

	@Autowired
	private WikipediaSearchService searchService;
	
    @RequestMapping(value={"/","/search"}, method=RequestMethod.GET)
    public String search(Model model) {

    	   	
    	model.addAttribute("searchForm",new SearchForm());
        return "search";
    }
    
    @RequestMapping(value="/search", method=RequestMethod.POST)
    public String search(@ModelAttribute SearchForm searchForm, Model model) {
    
    	SearchResultVO searchResult = searchService.processURL(searchForm.getUrl());
    	
    	model.addAttribute("searchForm",searchForm);
    	model.addAttribute("searchResult",searchResult);
    	
    	return "search";
    }
	
}

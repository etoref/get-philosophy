package com.esf.getphilosophy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
public class WikipediaSearchConfig {
	
	@Value("${target.url}")
	private String targetPage;
	
	@Value("${hops.max}")
	private Integer maxHops;
	
	public String getTargetPageURL(){
		return targetPage;
	}

	public Integer getMaxHops() {
		return maxHops;
	}	
}

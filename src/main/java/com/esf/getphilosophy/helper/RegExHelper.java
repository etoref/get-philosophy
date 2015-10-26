package com.esf.getphilosophy.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExHelper {

	public static String findFirstValidMatch(String content, String regexToAccept, String regexToReject) {
		Pattern pattern = Pattern.compile(regexToAccept);
		Matcher matcher = pattern.matcher(content);
		while(matcher.find()){	
			
			String link = matcher.group(1);
			
			if(!link.matches(regexToReject)){
				return link;
			}
		}
		
		return null;
	}
	
}

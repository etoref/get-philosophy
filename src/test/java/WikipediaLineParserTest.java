import static org.junit.Assert.*;

import org.junit.Test;

import com.esf.getphilosophy.repository.online.WikipediaLineParser;

public class WikipediaLineParserTest {

	@Test
	public void testSuccessfullExtractFirstLinkFromLine() {
		
		String line = "<td class=\"category\"><a href=\"/wiki/Greeks\" title=\"Greeks\">Greek</a></td>";
		
		WikipediaLineParser helper = new WikipediaLineParser(line);
		
		assertEquals("/wiki/Greeks", helper.getFirstValidLink());
	}
	
	@Test
	public void testSuccessfullExtractLinkFromLineAfterRejectFirst() {

		String line = "title=\"Harpalus\">Harpalus</a>, <a href=\"/wiki/Hephaestion_(disambiguation)\" title=\"Hephaestion\">Hephaestion</a>,<td class=\"category\"><a href=\"/wiki/Greeks\" title=\"Greeks\">Greek</a></td>";
		
		WikipediaLineParser helper = new WikipediaLineParser(line);	
			
		assertEquals("/wiki/Greeks", helper.getFirstValidLink());
	}	
	
	@Test
	public void testRejectHelpLink() {

		String line = "<p><b>Aristotle</b> (<span class=\"nowrap\"><span class=\"IPA nopopups\"><a href=\"/wiki/Help:IPA_for_English\" title=\"Help:IPA for English\">/<span ";
		
		WikipediaLineParser helper = new WikipediaLineParser(line);
		
		assertNull(helper.getFirstValidLink());
	}
	
	@Test
	public void testRejectDisambiguationLink() {

		String line = "title=\"Harpalus\">Harpalus</a>, <a href=\"/wiki/Hephaestion_(disambiguation)\" title=\"Hephaestion\">Hephaestion</a>, <a href=\"/wiki/Meno_(disambiguation)\" title=\"";
		
		WikipediaLineParser helper = new WikipediaLineParser(line);
		
		assertNull(helper.getFirstValidLink());
	}
	
	@Test
	public void testRejectProtectionPolicyLink() {

		String line = "<div id=\"mw-indicator-pp-default\" class=\"mw-indicator\"><a href=\"/wiki/Wikipedia:Protection_policy#semi\" title=\"This article is semi-protected.\"><img alt=\"Page semi-protected\" src=\"//upload.wikimedia.org/wikipedia/commons/thumb/f/fc/Padlock-silver.svg/20px-Padlock-silver.svg.png\" width=\"20\" height=\"20\" srcset=\"//upload.wikimedia.org/wikipedia/commons/thumb/f/fc/Padlock-silver.svg/30px-Padlock-silver.svg.png 1.5x, //upload.wikimedia.org/wikipedia/commons/thumb/f/fc/Padlock-silver.svg/40px-Padlock-silver.svg.png 2x\" data-file-width=\"128\" data-file-height=\"128\" /></a></div>";
		
		WikipediaLineParser helper = new WikipediaLineParser(line);
		
		assertNull(helper.getFirstValidLink());
	}
	
	@Test
	public void testRejectParenthesizedLink() {

		String line = "<div id=\"mw-indicator-pp-default\" class=\"mw-indicator\"><a href=\"/wiki/Quality_(philosophy)\" title=\"This article is semi-protected.\"><img alt=\"Page semi-protected\" src=\"//upload.wikimedia.org/wikipedia/commons/thumb/f/fc/Padlock-silver.svg/20px-Padlock-silver.svg.png\" width=\"20\" height=\"20\" srcset=\"//upload.wikimedia.org/wikipedia/commons/thumb/f/fc/Padlock-silver.svg/30px-Padlock-silver.svg.png 1.5x, //upload.wikimedia.org/wikipedia/commons/thumb/f/fc/Padlock-silver.svg/40px-Padlock-silver.svg.png 2x\" data-file-width=\"128\" data-file-height=\"128\" /></a></div>";
		
		WikipediaLineParser helper = new WikipediaLineParser(line);
		
		assertNull(helper.getFirstValidLink());
	}	
	
}

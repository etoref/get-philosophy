package com.esf.getphilosophy.VO;

public enum SearchResultCode {

	SUCCESS("We've found Philosphy's Page!"),
	LOOP("We've got a Loop..."),
	STUCK("There's no more place to go :("),
	STEP_OUT("There're no more steps.");
	
	private String displayMessage;

	private SearchResultCode(String displayMessage) {
		this.displayMessage = displayMessage;
	}

	public String getDisplayMessage() {
		return displayMessage;
	}

}

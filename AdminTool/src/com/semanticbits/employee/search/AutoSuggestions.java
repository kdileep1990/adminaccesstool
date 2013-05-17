package com.semanticbits.employee.search;

import org.springframework.stereotype.Component;

@Component("autoSuggestions")
public class AutoSuggestions {
	private String[] firstNames;
	private String[] lastNames;
	public String[] getFirstNames() {
		return firstNames;
	}
	public void setFirstNames(String[] firstNames) {
		this.firstNames = firstNames;
	}
	public String[] getLastNames() {
		return lastNames;
	}
	public void setLastNames(String[] lastNames) {
		this.lastNames = lastNames;
	}
}

package com.bidjisoft.medium.interfaces;

import java.util.HashMap;
import java.util.Map;

public class Processor implements Parametrable {

	public void process() {
		System.out.println("process with parameter 'custom_parameter=\"" + getParameter("custom_parameter") + "\"'");
	}
	
	Map<String, String> parameters = new HashMap<>();
	@Override
	public Map<String, String> getParameters() {
		return parameters;
	}
	
}

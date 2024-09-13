package com.bidjisoft.medium.interfaces;

import java.util.HashMap;
import java.util.Map;

public class MyCustomAntTask implements Parametrable {

	public void execute() {
		Processor processor = new Processor();
		processor.setParameters(parameters);
		processor.process();
	}
	
	public static void main(String[] args) {
		MyCustomAntTask task = new MyCustomAntTask();
		task.addParameter("custom_parameter", "value of custom_parameter");
		task.execute(); 
	}

	Map<String, String> parameters = new HashMap<>();
	@Override
	public Map<String, String> getParameters() {
		return parameters;
	}
	
}

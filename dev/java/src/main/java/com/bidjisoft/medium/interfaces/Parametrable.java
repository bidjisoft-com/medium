package com.bidjisoft.medium.interfaces;

import java.util.Map;

public interface Parametrable {

	Map<String, String> getParameters();
	
	default void addParameter(String name, String value) {
		getParameters().put(name, value);
	}

	default boolean hasParameter(String name) {
		return getParameters().containsKey(name);
	}
	
	default String getParameter(String name) {
		return getParameters().getOrDefault(name, null);
	}
	
	default void setParameters(Map<String, String> params) {
		for (String key : params.keySet()) {
			getParameters().put(key, params.get(key));
		}
	}
}

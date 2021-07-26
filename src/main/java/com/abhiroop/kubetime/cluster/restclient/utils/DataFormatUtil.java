package com.abhiroop.kubetime.cluster.restclient.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang.StringUtils;

public class DataFormatUtil {

	
	 public static String toCamelCase(String name, String delimiter) {
	        String[] parts = name.split("-");
	        List<String> capitalized = Stream.of(parts)
	                .map(StringUtils::capitalize)
	                .collect(Collectors.toList());
	        return StringUtils.uncapitalize(StringUtils.join(capitalized, ""));
	    }
}

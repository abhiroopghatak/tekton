package com.abhiroop.kubetime.cluster.restclient.utils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

public class DataFormatUtil {

	public static String toCamelCase(String name, String delimiter) {
		String[] parts = name.split("-");
		List<String> capitalized = Stream.of(parts).map(StringUtils::capitalize).collect(Collectors.toList());
		return StringUtils.uncapitalize(StringUtils.join(capitalized, ""));
	}

	public static Date trimDateWithNoTime(Date d) throws ParseException {

		d= DateUtils.truncate(d, Calendar.DATE);

		return d;
	}
}

package com.abhiroop.kubetime.cluster.restclient.utils;

import java.nio.charset.Charset;
import java.util.Base64;

import org.apache.commons.lang.ArrayUtils;

public class Base64Helper {

	public static String encode(byte[] unencoded) {
		return encode(unencoded, Charset.defaultCharset());
	}

	public static String encode(byte[] unencoded, Charset charset) {
		if (unencoded == null) {
			return null;
		} else if (unencoded.length == 0) {
			return "";
		}
		return new String(Base64.getEncoder().encode(unencoded), charset);
	}

	public static String encode(String unencoded) {
		if (unencoded == null) {
			return null;
		}
		return encode(unencoded.getBytes(), Charset.defaultCharset());
	}

	public static String encode(String unencoded, Charset charset) {
		if (unencoded == null) {
			return null;
		}
		return encode(unencoded.getBytes(), charset);
	}

	public static String decode(byte[] encoded, Charset charset) {
		if (ArrayUtils.isEmpty(encoded)) {
			return "";
		}
		return new String(Base64.getDecoder().decode(encoded), charset);
	}

	public static String decode(String encoded) {
		if (encoded == null) {
			return null;
		}
		return decode(encoded.getBytes(Charset.defaultCharset()), Charset.defaultCharset());
	}

	public static byte[] decodeBinary(String encoded) {
		Charset charset = Charset.defaultCharset();
		return decode(encoded.getBytes(charset), charset).getBytes(charset);
	}
}

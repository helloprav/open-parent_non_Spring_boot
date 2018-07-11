/**
 * 
 */
package org.openframework.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Java Developer
 *
 */
public class StringUtil {

	private static Logger logger = LoggerFactory.getLogger(StringUtil.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

	/**
	 * Replaces token with the value in string. 
	 * 
	 * @param updatedString String from which token is to be replaced.
	 * @param token token in the string.
	 * @param value with which token is to be replaced.
	 * @return String
	 */
	public static String replaceToken(String inputString, String token, String value) {

		logger.debug("replaceToken start ::::" + Thread.currentThread().getId());
		logger.debug("Input String [{}], Token [{}], Value [{}]", inputString, token, value);
		String updatedString = inputString;
		while (updatedString.indexOf(token) != -1) {
			StringBuilder sb = new StringBuilder(updatedString);
			sb.replace(updatedString.indexOf(token),
					updatedString.indexOf(token) + token.length(), value);
			updatedString = sb.toString();
		}
		return updatedString;
	}

}

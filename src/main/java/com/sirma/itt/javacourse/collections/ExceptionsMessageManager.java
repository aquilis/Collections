package com.sirma.itt.javacourse.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Joins certain predefined string messages into a single one.
 */
public class ExceptionsMessageManager {
	private static final char SEPARATOR = '|';
	private final Map<String, String> exceptions;
	private String message;

	/**
	 * constrcuts the message manager with an initial map of exceptions.
	 * 
	 * @param exceptions
	 *            is the hash map containig the exception messages.
	 */
	public ExceptionsMessageManager(Map<String, String> exceptions) {
		message = "";
		this.exceptions = exceptions;
	}

	/**
	 * Adds the exception message with the given content to the ouput string.
	 * 
	 * @param mess
	 *            is the message value to be added to the string.
	 */
	public void addExceptionMessage(String mess) {
		if (exceptions.containsValue(mess)) {
			if (message.length() > 0) {
				message += SEPARATOR;
			}
			message += mess;
		} else {
			throw new IllegalArgumentException("Unknown exception value.");
		}
	}

	/**
	 * Adds the exception message with the given code to the ouput string.
	 * 
	 * @param messageCode
	 *            is the exception's code from the exceptions map
	 */
	public void addExceptionMessageUsingCode(String messageCode) {
		if (exceptions.containsKey(messageCode)) {
			if (message.length() > 0) {
				message += SEPARATOR;
			}
			message += exceptions.get(messageCode);
		} else {
			throw new IllegalArgumentException("Unknown exception code.");
		}
	}

	/**
	 * A getter method for the result message.
	 * 
	 * @return the output exception message
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * Splits the messageCombination string into different messages, using the
	 * separator character, and returns them as a collection.
	 * 
	 * @param messagesCombination
	 *            is the message to split
	 * @return a collection with the splitted string of the message
	 */
	public static Collection<String> getMessages(String messagesCombination) {
		String sep = "[" + String.valueOf(SEPARATOR) + "]+";
		String[] splitted = messagesCombination.split(sep);
		Collection<String> list = new ArrayList<String>();
		for (String str : splitted) {
			list.add(str);
		}
		return list;
	}
}

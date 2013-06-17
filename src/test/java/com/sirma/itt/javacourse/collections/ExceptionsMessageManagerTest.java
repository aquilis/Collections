package com.sirma.itt.javacourse.collections;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test the functionality of the Exception message manager.
 */
public class ExceptionsMessageManagerTest {
	private static Map<String, String> messages;

	/**
	 * Inits the hash map with exception messages to be used in the tests.
	 */
	@BeforeClass
	public static void init() {
		messages = new HashMap<String, String>();
		messages.put("1", "msg 1");
		messages.put("2", "msg 2");
		messages.put("3", "msg 3");
	}

	/**
	 * Tests the adding methods - addExceptionMessage and
	 * addExceptionMessageUsingCode.
	 */
	@Test
	public void testAddingMethods() {
		ExceptionsMessageManager emm = new ExceptionsMessageManager(messages);
		emm.addExceptionMessage("msg 1");
		emm.addExceptionMessageUsingCode("2");
		emm.addExceptionMessageUsingCode("3");
		assertEquals(emm.getMessage(), "msg 1|msg 2|msg 3");
	}

	/**
	 * Tests the splitting method of the class.
	 */
	@Test
	public void testGetMessages() {
		Collection<String> list = new ArrayList<String>();
		list.add("msg 1");
		list.add("msg 2");
		list.add("msg 3");
		assertEquals(list,
				ExceptionsMessageManager.getMessages("msg 1|msg 2|msg 3"));
	}

	/**
	 * Test if the proper exception is thrown when addExceptionMessage accept
	 * string that's not present in the map of exceptions.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testErrorAddMessage() {
		ExceptionsMessageManager emm = new ExceptionsMessageManager(messages);
		emm.addExceptionMessage("random stuff");
	}

	/**
	 * Test if the proper exception is thrown when addExceptionMessageUsingCode
	 * accept code taht is not present as a key in the exceptions map.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testErrorAddMessageCode() {
		ExceptionsMessageManager emm = new ExceptionsMessageManager(messages);
		emm.addExceptionMessageUsingCode("100");
	}
}

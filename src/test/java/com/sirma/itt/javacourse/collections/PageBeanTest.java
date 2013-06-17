package com.sirma.itt.javacourse.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests the functionality of the page bean class.
 */
public class PageBeanTest {
	private static ArrayList<String> data;

	/**
	 * Constrcuts the data list and fill it with string items and instantiates
	 * the page bean.
	 */
	@BeforeClass
	public static void init() {
		data = new ArrayList<String>();
		for (int i = 0; i < 28; i++) {
			data.add("item " + (i + 1));
		}
	}

	/**
	 * Tests the firstPage() method.
	 */
	@Test
	public void testFirstPage() {
		PageBean<String> pageBrowser = new PageBean<String>(5, data);
		List<String> temp = pageBrowser.firstPage();
		assertEquals(temp.get(0), "item 1");
		assertEquals(temp.get(4), "item 5");
		assertEquals(pageBrowser.getCurrentPageNumber(), 1);
	}

	/**
	 * Tests the lastPage() method.
	 */
	@Test
	public void testLastPage() {
		PageBean<String> pageBrowser = new PageBean<String>(5, data);
		List<String> temp = pageBrowser.lastPage();
		assertEquals(temp.get(0), "item 26");
		assertEquals(temp.get(temp.size() - 1), "item 28");
		assertEquals(pageBrowser.getCurrentPageNumber(), 6);
	}

	/**
	 * Tests the next() method.
	 */
	@Test
	public void testNext() {
		PageBean<String> pageBrowser = new PageBean<String>(5, data);
		// flip to the second page
		pageBrowser.next();
		List<String> temp = pageBrowser.next();
		assertEquals(temp.get(0), "item 6");
		assertEquals(temp.get(temp.size() - 1), "item 10");
		assertEquals(pageBrowser.getCurrentPageNumber(), 2);
	}

	/**
	 * Tests the previous() method.
	 */
	@Test
	public void testPrevious() {
		PageBean<String> pageBrowser = new PageBean<String>(5, data);
		List<String> temp = null;
		// Flip all pages from the last to the first
		pageBrowser.lastPage();
		while (pageBrowser.hasPrevious()) {
			temp = pageBrowser.previous();
		}
		assertEquals(temp.get(0), "item 1");
		assertEquals(temp.get(4), "item 5");
		assertEquals(pageBrowser.getCurrentPageNumber(), 1);
	}

	/**
	 * Test the hasNext() method.
	 */
	@Test
	public void testHasNext() {
		PageBean<String> pageBrowser = new PageBean<String>(5, data);
		pageBrowser.lastPage();
		assertFalse(pageBrowser.hasNext());
	}

	/**
	 * Test the hasPrevious() method.
	 */
	@Test
	public void testHasPrevious() {
		PageBean<String> pageBrowser = new PageBean<String>(5, data);
		// flip to the first page
		pageBrowser.next();
		assertFalse(pageBrowser.hasPrevious());
	}
}

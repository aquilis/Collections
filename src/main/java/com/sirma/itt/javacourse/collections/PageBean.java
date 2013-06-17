package com.sirma.itt.javacourse.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * Gets a list of elements and seperates it on different pages, each with a
 * certain number of elements. Has public methods for browsing to the
 * next/previous page, retrieving the first/last page, goto first/next, print
 * the current page on console and open its own command line with input
 * operations: print, next, previous, exit
 * 
 * @author v.tsonev
 * @version 11/06/2013 1.0
 * @param <T>
 *            is the type of elements that the pages will contain
 */
public class PageBean<T> {

	private final int pageSize;
	private int currentPage;
	private int currentPageNumber;
	private final ArrayList<T> dataList;
	// stores the data of the current page in case it has to be used
	// or editted
	private List<T> listCurrentPage;

	/**
	 * Constructs the page bean with an initial page size and a list of data
	 * elements to work with.
	 * 
	 * @param pageSize
	 *            is the number of elements to be shown on a single page
	 * @param data
	 *            is the list of data to be used by the class
	 */
	public PageBean(int pageSize, Collection<T> data) {
		this.pageSize = pageSize;
		this.dataList = (ArrayList<T>) data;
		this.currentPage = 0;
		currentPageNumber = 0;
	}

	/**
	 * Gets the number of the current page.
	 * 
	 * @return the number of the current page
	 */
	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	/**
	 * Checks if there is a next page in the data list.
	 * 
	 * @return true if there is a next page (even if it's not full)
	 */
	public boolean hasNext() {
		return currentPage < dataList.size();
	}

	/**
	 * Checks if there is a previous page in the data list.
	 * 
	 * @return true if there is a previous page (even if it's not full)
	 */
	public boolean hasPrevious() {
		return currentPageNumber > 1;
	}

	/**
	 * Returns the next page of the data list.
	 * 
	 * @return a sublist of the main data list representing the next page
	 */
	public List<T> next() {
		if (currentPage == dataList.size()) {
			return new ArrayList<T>();
		}

		if ((currentPage + pageSize) < dataList.size() - 1) {
			List<T> tempList = dataList.subList(currentPage, currentPage
					+ pageSize);
			currentPage += pageSize;
			currentPageNumber++;
			listCurrentPage = tempList;
			return tempList;
		} else {
			List<T> tempList = new ArrayList<T>();
			for (int i = currentPage; i < dataList.size(); i++) {
				tempList.add(dataList.get(i));
			}
			currentPage = dataList.size();
			currentPageNumber++;
			listCurrentPage = tempList;
			return tempList;
		}
	}

	/**
	 * Returns the first page and makes it current page.
	 * 
	 * @return the first page of the data list
	 */
	public List<T> firstPage() {
		currentPage = 0;
		List<T> tempList = next();
		currentPageNumber = 1;
		listCurrentPage = tempList;
		return tempList;
	}

	/**
	 * Returns the last page and makes it current page.
	 * 
	 * @return the last page of the data list
	 */
	public List<T> lastPage() {
		List<T> tempList = null;
		if (dataList.size() % pageSize != 0) {
			int i = pageSize * (dataList.size() / pageSize);
			currentPageNumber = (dataList.size() / pageSize) + 1;
			tempList = dataList.subList(i, dataList.size());
		} else {
			currentPageNumber = dataList.size() / pageSize;
			tempList = dataList.subList(dataList.size() - pageSize,
					dataList.size());
		}
		currentPage = dataList.size();
		listCurrentPage = tempList;
		return tempList;
	}

	/**
	 * Returns a List representing the previous page of the data list.
	 * 
	 * @return a sublist of the main data list representing the next page
	 */
	public List<T> previous() {
		if ((currentPage == 0) || (currentPageNumber == 1)) {
			System.out.println("Error: There's no previous page.");
			return new ArrayList<T>();
		}

		if ((currentPage - (pageSize * 2)) > 0) {
			int offset = 0;
			if ((currentPage == dataList.size())
					&& (currentPage % pageSize != 0)) {
				offset = pageSize - listCurrentPage.size();
			}
			List<T> tempList = dataList.subList(currentPage - (pageSize * 2)
					+ offset, currentPage - pageSize + offset);
			currentPageNumber--;
			currentPage -= pageSize - offset;
			listCurrentPage = tempList;
			return tempList;
		} else {
			List<T> tempList = new ArrayList<T>();
			for (int i = 0; i <= (currentPage - 1) - pageSize; i++) {
				tempList.add(dataList.get(i));
			}
			currentPage = pageSize;
			currentPageNumber--;
			listCurrentPage = tempList;
			return tempList;
		}
	}

	/**
	 * Waits for the user to input some of the operations for page browsing.
	 */
	public void openCommandConsole() {
		String input = "";
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter command:");
		while (!"exit".equals(input.trim())) {
			System.out.print("> ");
			input = scn.nextLine();
			// switch statements with strings not supported by Maven
			input = input.toLowerCase();
			if ("next".equals(input)) {
				next();
			} else if ("previous".equals(input)) {
				previous();
			} else if ("exit".equals(input)) {
				System.out.println("Program command line closed.");
			} else {
				System.out.println(input + " is an invalid command.");
			}
		}
		scn.close();
	}

	/**
	 * Entry point for self-testing.
	 * 
	 * @param args
	 *            are the cmd args
	 */
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < 28; i++) {
			list.add("item " + (i + 1));
		}
		PageBean<String> test = new PageBean<String>(5, list);
		test.next();
		test.openCommandConsole();
	}
}

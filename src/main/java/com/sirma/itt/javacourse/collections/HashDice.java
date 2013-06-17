package com.sirma.itt.javacourse.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

/**
 * A hashdice program that uses two dices with m sides and throws them n times.
 * After each throw fills tha staticstics of the throws in a hash map.
 */
public class HashDice {
	// the number of throws to be performed
	private final int n;
	// the sides of the dice
	private final int m;
	// the hashtable that contains the staticstics for all throws.
	// if the same combinations is thrown several times, their numbers are
	// stored in an array list as a value for the combination string.
	private final Map<String, ArrayList<Integer>> throwStats = new HashMap<String, ArrayList<Integer>>();

	/**
	 * Constructs the dices with initial values.
	 * 
	 * @param numberOfThrows
	 *            indicates how many times the two dices will be thrown
	 * @param diceSides
	 *            indicates how many sides the two dices will have
	 */
	public HashDice(int numberOfThrows, int diceSides) {
		this.n = numberOfThrows;
		this.m = diceSides;
	}
	/**
	 * Throws the two dices n times and makes a statistics about each throw.
	 */
	public void throwDices() {
		int firstThrow;
		int secondThrow;
		String combination;
		String combinationReversed;
		Random rnd = new Random();
		Random rndSecond = new Random();
		for (int i = 1; i <= n; i++) {
			firstThrow = 1 + rnd.nextInt(m);
			secondThrow = 1 + rndSecond.nextInt(m);
			combination = firstThrow + " " + secondThrow;
			combinationReversed = secondThrow + " " + firstThrow;
			if ((!throwStats.containsKey(combination))
					&& (!throwStats.containsKey(combinationReversed))) {
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(i);
				throwStats.put(combination, temp);
			} else if (throwStats.containsKey(combination)) {
				throwStats.get(combination).add(i);
			} else if (throwStats.containsKey(combinationReversed)) {
				throwStats.get(combinationReversed).add(i);
			}
		}
	}

	/**
	 * Prints the statistics about all dice throws.
	 */
	public void printStats() {
		for (Entry<String, ArrayList<Integer>> entry : throwStats.entrySet()) {
			System.out.println("Combination: " + entry.getKey()
					+ "  Throw number: " + entry.getValue());
		}
	}

	/**
	 * Entry point for self-test.
	 * 
	 * @param args
	 *            are the cmd args
	 */
	public static void main(String[] args) {
		// construct dices with 6 sides that will be throwsn 100 times
		HashDice dice = new HashDice(100, 6);
		dice.throwDices();
		dice.printStats();
	}
}

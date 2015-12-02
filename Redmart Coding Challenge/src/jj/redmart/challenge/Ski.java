package jj.redmart.challenge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Ski {
	private static final String WHITESPACE = " ";
	private static int bestDrop, bestPathLength;
	private static String bestRoute;

	public static void main(String[] args) throws IOException {

		System.out.println("The file supplied is " + args[0]);
		int[][] mountain = readMap(args[0]);

		for (int i = 0; i < mountain.length; i++) {
			for (int j = 0; j < mountain[i].length; j++) {
				ski(i, j, mountain, new LinkedList<Integer>());
			}
		}
		
		// TEST

		System.out.println("The best route is: " + bestRoute + " with path length of " + bestPathLength
				+ " and drop of " + bestDrop);

	}

	/**
	 * Recursive method to ski through the mountain
	 * @param row
	 * @param col
	 * @param mountain
	 * @param route
	 */
	private static void ski(int row, int col, int[][] mountain, LinkedList<Integer> route) {

		Integer currentArea = mountain[row][col];
		route.add(currentArea);

		// move N : row-1 if height is lower
		if (row > 0 && validMove(mountain[row - 1][col], currentArea)) {
			ski(row - 1, col, mountain, route);
		}

		// move S : row+1 if height is lower
		if (row < mountain.length - 1 && validMove(mountain[row + 1][col], currentArea)) {
			ski(row + 1, col, mountain, route);
		}

		// move E : col+1 if height is lower
		if (col < mountain[0].length - 1 && validMove(mountain[row][col + 1], currentArea)) {
			ski(row, col + 1, mountain, route);
		}

		// move W : col-1 if height is lower
		if (col > 0 && validMove(mountain[row][col - 1], currentArea)) {
			ski(row, col - 1, mountain, route);
		}

		checkBestRoute(route);

		// done; remove last
		route.removeLast();

	}

	private static void checkBestRoute(LinkedList<Integer> route) {
		// no other move available; check if it's the longest and steepest route
		int firstEle = route.getFirst();
		int lastEle = route.getLast();

		if (route.size() == bestPathLength && firstEle - lastEle > bestDrop) {
			bestDrop = firstEle - lastEle;
			bestRoute = route.toString();

		} else if (route.size() > bestPathLength) {
			bestPathLength = route.size();
			bestDrop = firstEle - lastEle;
			bestRoute = route.toString();
		}
	}

	private static boolean validMove(int next, int curElevation) {
		boolean valid;
		valid = (next < curElevation) ? true : false;
		return valid;
	}

	/**
	 * To read the map of the mountain
	 * @param file
	 * @return
	 */
	private static int[][] readMap(String file) {
		int map[][] = null;

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			// assumes first line is the dimension
			String line = br.readLine();
			String dim[] = line.split(WHITESPACE);

			int row = Integer.parseInt(dim[0]);
			int col = Integer.parseInt(dim[1]);
			map = new int[row][col];

			for (int i = 0; i < row; i++) {
				line = br.readLine();
				String area[] = line.split(WHITESPACE);
				for (int j = 0; j < col; j++) {
					map[i][j] = Integer.parseInt(area[j]);
				}
			}
		} catch (IOException e) {
			System.out.println("IO Exception thrown  :" + e.getMessage());
		}

		return map;
	}
}

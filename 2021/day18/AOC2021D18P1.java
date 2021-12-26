import java.util.*;
import java.io.*;

public class AOC2021D18P1 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));

		String line = in.nextLine();
		ArrayList<int[]> cur = parseNumber(line);
		
		while (in.hasNextLine()) {
			line = in.nextLine();
			combine(cur, parseNumber(line));
			printNumber(cur);
			reduce(cur);
			printNumber(cur);
		}
		
		System.out.println(magnitude(cur));
	}
	
	static ArrayList<int[]> parseNumber(String s) {
		ArrayList<int[]> cur = new ArrayList<int[]>();
		int depth = 0;
		
		for (char c : s.toCharArray()) {
			if (c == '[') depth++;
			else if (c == ']') depth--;
			else if (c == ',') continue;
			else cur.add(new int[] {c - '0', depth});
		}
		
		return cur;
	}
	
	static void combine(ArrayList<int[]> a, ArrayList<int[]> b) {
		a.addAll(b);
		for (int[] x : a) {
			x[1]++;
		}
	}
	
	static void reduce(ArrayList<int[]> cur) {
		for (int a = 0; a < 2; a++) {
			for (int i = 0; i < cur.size(); i++) {
				if (a == 0 && cur.get(i)[1] >= 5) {
					if (i > 0) {
						cur.get(i - 1)[0] += cur.get(i)[0];
					}
					if (i < cur.size() - 2) {
						cur.get(i + 2)[0] += cur.get(i + 1)[0];
					}
					cur.remove(i + 1);
					cur.set(i, new int[] {0, cur.get(i)[1] - 1});
					a = -1;
					printNumber(cur);
					break;
				}
				else if (a == 1 && cur.get(i)[0] >= 10) {
					int[] val = cur.get(i);
					cur.set(i, new int[] {(val[0] + 1) / 2, val[1] + 1});
					cur.add(i, new int[] {val[0] / 2, val[1] + 1});
					a = -1;
					printNumber(cur);
					break;
				}
			}
		}
		
	}
	
	static int magnitude(ArrayList<int[]> cur) {
		for (int a = 4; a > 0; a--) {
			for (int i = 0; i < cur.size(); i++) {
				if (cur.get(i)[1] == a) {
					int val = cur.get(i)[0] * 3 + cur.get(i + 1)[0] * 2;
					cur.remove(i + 1);
					cur.set(i, new int[] {val, cur.get(i)[1] - 1});
				}
			}
		}
		
		return cur.get(0)[0];
	}
	
	static void printNumber(ArrayList<int[]> cur) {
		for (int[] x : cur) {
			System.out.print(x[0] + " ");
		}
		System.out.println();
		for (int[] x : cur) {
			System.out.print(x[1] + " ");
		}
		System.out.println("\n");
	}
}
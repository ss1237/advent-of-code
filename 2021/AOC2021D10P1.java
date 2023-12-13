import java.util.*;
import java.io.*;

public class AOC2021D10P1 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));

		HashMap<Character, Integer> map = new HashMap();
		map.put('(', -3);
		map.put('[', -57);
		map.put('{', -1197);
		map.put('<', -25137);
		map.put(')', 3);
		map.put(']', 57);
		map.put('}', 1197);
		map.put('>', 25137);
		
		int total = 0;
		
		while (in.hasNextLine()) {
			String cur = in.nextLine();
			ArrayDeque<Integer> q = new ArrayDeque();
			
			for (char c : cur.toCharArray()) {
				if (map.get(c) < 0) {
					q.addFirst(map.get(c));
				}
				else {
					if (q.peek() == -1 * map.get(c)) {
						q.remove();
					}
					else {
						total += map.get(c);
						break;
					}
				}
				
			}
		}
		
		out.println(total);
		out.close();

	}
}
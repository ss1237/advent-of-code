import java.util.*;
import java.io.*;

public class AOC2021D10P2 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));

		HashMap<Character, Integer> map = new HashMap();
		map.put('(', -1);
		map.put('[', -2);
		map.put('{', -3);
		map.put('<', -4);
		map.put(')', 1);
		map.put(']', 2);
		map.put('}', 3);
		map.put('>', 4);
		
		ArrayList<Long> scores = new ArrayList();
		
		outer: while (in.hasNextLine()) {
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
						continue outer;
					}
				}
			}
			
			if (q.size() > 0) {
				long total = 0;
				while (q.size() > 0) {
					total = total * 5 - q.poll();
				}
				scores.add(total);
			}
		}
		
		Collections.sort(scores);
		out.println(scores.get(scores.size() / 2));
		out.close();

	}
}
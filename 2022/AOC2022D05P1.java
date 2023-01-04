import java.util.*;
import java.io.*;

public class AOC2022D05P1 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		ArrayList<ArrayList> stacks = new ArrayList();
		
		String line = in.nextLine();
		for (int i = 0; i < (line.length() + 1) / 4; i++) {
			stacks.add(new ArrayList<Character>());
		}
		
		//System.out.println(stacks.size());
		
		while (line.charAt(1) != '1') {
			for (int i = 0; i < stacks.size(); i++) {
				if (line.charAt(4 * i + 1) != ' ') {
					//System.out.println(line.charAt(4 * i + 1));
					stacks.get(i).add(line.charAt(4 * i + 1));
				}
			}
			line = in.nextLine();
		}
		
		in.nextLine();
		
		while (in.hasNextLine()) {
			line = in.nextLine();
			
			String[] cum = line.split(" ");
			int amt = Integer.parseInt(cum[1]);
			int from = Integer.parseInt(cum[3]) - 1;
			int to = Integer.parseInt(cum[5]) - 1;
			
			for (int i = 0; i < amt; i++) {
				stacks.get(to).add(0, stacks.get(from).remove(0));
			}
		}
		
		String ret = "";
		
		for (int i = 0; i < stacks.size(); i++) {
			ret += stacks.get(i).get(0);
		}
		
		out.println(ret);
		
		out.close();
	}
}
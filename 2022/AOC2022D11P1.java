import java.util.*;
import java.io.*;

public class AOC2022D11P1 {
	static String filename = "aoc";
	

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		int size = 0;
		ArrayList<ArrayList<Integer>> monkeys = new ArrayList();
		ArrayList<String[]> ops = new ArrayList();
		ArrayList<Integer> div = new ArrayList();
		ArrayList<int[]> to = new ArrayList();
		
		while (in.hasNextLine()) {
			in.nextLine();
			String[] line = in.nextLine().split("[,: ] *");
			monkeys.add(new ArrayList<Integer>());
			for (int i = 3; i < line.length; i++) {
				monkeys.get(size).add(Integer.parseInt(line[i]));
			}
			
			line = in.nextLine().split("[,: ] *");
			String[] op = new String[] {line[4], line[5], line[6]};
			ops.add(op);
			
			line = in.nextLine().split("[,: ] *");
			div.add(Integer.parseInt(line[4]));
			
			to.add(new int[2]);
			line = in.nextLine().split("[,: ] *");
			to.get(size)[0] = Integer.parseInt(line[6]);
			line = in.nextLine().split("[,: ] *");
			to.get(size)[1] = Integer.parseInt(line[6]);
			
			
			if (in.hasNextLine()) in.nextLine();
			size++;
		}
		
		int[] amts = new int[size];
		
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < size; j++) {
				amts[j] -= monkeys.get(j).size();
				
				for (int old : monkeys.get(j)) {
					int oldN = newNum(ops.get(j), old) / 3;
					if (oldN % div.get(j) == 0) {
						monkeys.get(to.get(j)[0]).add(oldN);
					}
					else {
						monkeys.get(to.get(j)[1]).add(oldN);
					}
				}
				monkeys.set(j, new ArrayList<Integer>());
			}
		}
		
		Arrays.sort(amts);
		out.println(amts[0] * amts[1]);
		
		out.close();
	}
	
	static int newNum(String[] op, int old) {
		int x = op[0].equals("old") ? old : Integer.parseInt(op[0]);
		int y = op[2].equals("old") ? old : Integer.parseInt(op[2]);
		return op[1].equals("+") ? x+y : x*y;
	}
	
}